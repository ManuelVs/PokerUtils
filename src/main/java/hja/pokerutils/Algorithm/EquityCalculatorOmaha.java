package hja.pokerutils.Algorithm;

import hja.logic.gui.model.Config;
import hja.pokerutils.Algorithm.Combinations.CombinationCalculator;
import hja.pokerutils.Algorithm.Combinations.CountedCombinationIterator;
import hja.pokerutils.Board.Board;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Hand.Hand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class EquityCalculatorOmaha {
	private static ExecutorService pool = Executors.newCachedThreadPool();
	
	public static double[] calculateEquity(Config config) {
		ArrayList<Card> allPossibleCards = getPossibleCards(config);
		double[] equity = new double[config.getPlayers().size()];
		
		int nTotal = 0;
		try {
			for (Future<int[]> future : sendTasks(config, allPossibleCards)) {
				int[] partialEquity = future.get();
				
				for (int i = 0; i < partialEquity.length; ++i) {
					equity[i] += partialEquity[i];
					nTotal += partialEquity[i];
				}
			}
			
			for (int i = 0; i < equity.length; ++i) {
				equity[i] /= nTotal;
			}
		}
		catch (Exception e) { e.printStackTrace(); }
		
		return equity;
	}
	
	private static ArrayList<Card> getPossibleCards(Config config){
		ArrayList<Card> allPossibleCards = CardFactory.getAllCards();
		
		for (Card card : config.getBoardCards()) {
			allPossibleCards.remove(card);
		}
		
		for (Player player : config.getPlayers()) {
			for (Card card : player.getCards()) {
				allPossibleCards.remove(card);
			}
		}
		
		return allPossibleCards;
	}
	
	private static ArrayList<Future<int[]>> sendTasks(Config config, ArrayList<Card> allPossibleCards){
		ArrayList<Future<int[]>> futures = new ArrayList<>();
		
		int numLeftCards = 5 - config.getBoardCards().size();
		CombinationCalculator<Card> combinations = new CombinationCalculator<>(allPossibleCards, numLeftCards);
		int numCores = Runtime.getRuntime().availableProcessors();
		Iterator<CountedCombinationIterator<Card>> iterators = combinations.iterators(numCores);
		while (iterators.hasNext()) {
			CountedCombinationIterator<Card> iterator = iterators.next();
			
			Future<int[]> future = pool.submit(() -> {
				int[] partialEquity = new int[config.getPlayers().size()];
				while (iterator.hasNext()) {
					ArrayList<Card> combination = iterator.next();
					Integer i = calculateBest(combination, config);
					partialEquity[i] += 1;
				}
				
				return partialEquity;
			});
			
			futures.add(future);
		}
		
		return futures;
	}
	
	private static int calculateBest(ArrayList<Card> combination, Config config) {
		ArrayList<Card> cards = new ArrayList<>(config.getBoardCards());
		cards.addAll(combination);
		
		int bestIndex = 0;
		Player player = config.getPlayers().get(0);
		Hand bestHand = OmahaAlgorithm.calculateHand(player.getCards(), cards);
		
		for (int i = 1; i < config.getPlayers().size(); ++i) {
			player = config.getPlayers().get(i);
			Hand hand = OmahaAlgorithm.calculateHand(player.getCards(), cards);
			
			if (hand.compareTo(bestHand) > 0) {
				bestHand = hand;
				bestIndex = i;
			}
		}
		
		return bestIndex;
	}
}
