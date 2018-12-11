package hja.pokerutils.Algorithm;

import hja.pokerutils.Algorithm.Combinations.CombinationCalculator;
import hja.pokerutils.Algorithm.Combinations.CountedCombinationIterator;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Hand.Hand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class EquityCalculator {
	private static ExecutorService pool = Executors.newCachedThreadPool();
	
	public static double[] calculateEquity(ArrayList<Player> players, ArrayList<Card> boardCards, HandClassifier classifier) {
		ArrayList<Card> allPossibleCards = getPossibleCards(players, boardCards);
		double[] equity = new double[players.size()];
		
		int nTotal = 0;
		try {
			for (Future<int[]> future : sendTasks(players, boardCards, classifier, allPossibleCards)) {
				int[] partialEquity = future.get();
				
				for (int i = 0; i < partialEquity.length; ++i) {
					equity[i] += partialEquity[i];
					nTotal += partialEquity[i];
				}
			}
			
			for (int i = 0; i < equity.length; ++i) {
				equity[i] /= nTotal;
				players.get(i).setEquity(equity[i]);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return equity;
	}
	
	private static ArrayList<Card> getPossibleCards(ArrayList<Player> players, ArrayList<Card> boardCards) {
		ArrayList<Card> allPossibleCards = CardFactory.getAllCards();
		
		for (Card card : boardCards) {
			allPossibleCards.remove(card);
		}
		
		for (Player player : players) {
			for (Card card : player.getCards()) {
				allPossibleCards.remove(card);
			}
		}
		
		return allPossibleCards;
	}
	
	private static ArrayList<Future<int[]>> sendTasks(ArrayList<Player> players, ArrayList<Card> boardCards, HandClassifier classifier, ArrayList<Card> allPossibleCards) {
		ArrayList<Future<int[]>> futures = new ArrayList<>();
		
		int numLeftCards = 5 - boardCards.size();
		CombinationCalculator<Card> combinations = new CombinationCalculator<>(allPossibleCards, numLeftCards);
		int numCores = Runtime.getRuntime().availableProcessors();
		Iterator<CountedCombinationIterator<Card>> iterators = combinations.iterators(numCores);
		while (iterators.hasNext()) {
			CountedCombinationIterator<Card> iterator = iterators.next();
			
			Future<int[]> future = pool.submit(() -> {
				int[] partialEquity = new int[players.size()];
				while (iterator.hasNext()) {
					ArrayList<Card> combination = iterator.next();
					Integer i = calculateBest(combination, players, boardCards, classifier);
					partialEquity[i] += 1;
				}
				
				return partialEquity;
			});
			
			futures.add(future);
		}
		
		return futures;
	}
	
	private static int calculateBest(ArrayList<Card> combination, ArrayList<Player> players, ArrayList<Card> boardCards, HandClassifier classifier) {
		ArrayList<Card> cards = new ArrayList<>(boardCards);
		cards.addAll(combination);
		
		int bestIndex = 0;
		Player player = players.get(0);
		Hand bestHand = classifier.calculateHand(player.getCards(), cards);
		
		for (int i = 1; i < players.size(); ++i) {
			player = players.get(i);
			Hand hand = classifier.calculateHand(player.getCards(), cards);
			
			if (hand.compareTo(bestHand) > 0) {
				bestHand = hand;
				bestIndex = i;
			}
		}
		
		return bestIndex;
	}
}
