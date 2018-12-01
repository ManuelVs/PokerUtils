package hja.pokerutils.Algorithm;

import hja.pokerutils.Board.Board;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Hand.Hand;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class EquityCalculator {
	private static ExecutorService pool = Executors.newCachedThreadPool();
	
	public static double[] calculateEquity(Board board) {
		ArrayList<Card> allPossibleCards = CardFactory.getAllCards();
		
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
		
		double[] equity = new double[board.players.size()];
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
