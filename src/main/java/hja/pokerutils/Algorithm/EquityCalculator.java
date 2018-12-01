package hja.pokerutils.Algorithm;

import hja.pokerutils.Algorithm.Combinations.CombinationCalculator;
import hja.pokerutils.Algorithm.Combinations.CountedCombinationIterator;
import hja.pokerutils.Board.Board;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Hand.Hand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class EquityCalculator {
	private static ExecutorService pool = Executors.newCachedThreadPool();
	
	/*
	public static double[] calculateEquity(Board board) {
		ArrayList<Card> allPossibleCards = CardFactory.getAllCards();
		double[] equity = new double[board.players.size()];
		
		for (Card card : board.boardCards) {
			allPossibleCards.remove(card);
		}
		
		for (Player player : board.players) {
			for (Card card : player.cards) {
				allPossibleCards.remove(card);
			}
		}
		
		int nTotal = 0;
		int numLeftCards = 5 - board.boardCards.size();
		CombinationCalculator<Card> combinations = new CombinationCalculator<>(allPossibleCards, numLeftCards);
		LinkedList<Future<Integer>> futures = new LinkedList<>();
		
		for (ArrayList<Card> combination : combinations) {
			Future<Integer> future = pool.submit(() -> calculateBest(combination, board));
			futures.add(future);
			++nTotal;
		}
		
		double s = 1 / (double) nTotal;
		for (Future<Integer> future : futures) {
			try {
				Integer i = future.get();
				equity[i] += s;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return equity;
	}
	*/
	
	public static double[] calculateEquity(Board board) {
		ArrayList<Card> allPossibleCards = getPossibleCards(board);
		double[] equity = new double[board.players.size()];
		
		int nTotal = 0;
		try {
			for (Future<int[]> future : sendTasks(board, allPossibleCards)) {
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
	
	private static ArrayList<Card> getPossibleCards(Board board){
		ArrayList<Card> allPossibleCards = CardFactory.getAllCards();
		
		for (Card card : board.boardCards) {
			allPossibleCards.remove(card);
		}
		
		for (Player player : board.players) {
			for (Card card : player.cards) {
				allPossibleCards.remove(card);
			}
		}
		
		return allPossibleCards;
	}
	
	private static LinkedList<Future<int[]>> sendTasks(Board board, ArrayList<Card> allPossibleCards){
		LinkedList<Future<int[]>> futures = new LinkedList<>();
		
		int numLeftCards = 5 - board.boardCards.size();
		CombinationCalculator<Card> combinations = new CombinationCalculator<>(allPossibleCards, numLeftCards);
		int numCores = Runtime.getRuntime().availableProcessors();
		Iterator<CountedCombinationIterator<Card>> iterators = combinations.iterators(numCores);
		while (iterators.hasNext()) {
			CountedCombinationIterator<Card> iterator = iterators.next();
			
			Future<int[]> future = pool.submit(() -> {
				int[] partialEquity = new int[board.players.size()];
				while (iterator.hasNext()) {
					ArrayList<Card> combination = iterator.next();
					Integer i = calculateBest(combination, board);
					partialEquity[i] += 1;
				}
				
				return partialEquity;
			});
			
			futures.add(future);
		}
		
		return futures;
	}
	
	private static int calculateBest(ArrayList<Card> combination, Board board) {
		ArrayList<Card> cards = new ArrayList<>(board.boardCards);
		cards.addAll(combination);
		
		int bestIndex = 0;
		Player player = board.players.get(0);
		Hand bestHand = HoldEmAlgorithm.calculateHand(player.cards, cards);
		
		for (int i = 1; i < board.players.size(); ++i) {
			player = board.players.get(i);
			Hand hand = HoldEmAlgorithm.calculateHand(player.cards, cards);
			
			if (hand.compareTo(bestHand) > 0) {
				bestHand = hand;
				bestIndex = i;
			}
		}
		
		return bestIndex;
	}
}
