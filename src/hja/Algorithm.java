package hja;

import hja.Card.Card;
import hja.Card.Rank;
import hja.Card.Suit;
import hja.Hand.*;

import java.util.ArrayList;
import java.util.Collections;

public final class Algorithm {
	
	/**
	 * Calculates the best hand given 5 cards or more.
	 *
	 * @param cards The array of cards. Can be unordered
	 * @return The best hand
	 */
	public static Hand calculateHand(ArrayList<Card> cards) {
		if (cards.size() == 5) {
			return classifyHand(cards);
		}
		else {
			ArrayList<Card> cards_copy = new ArrayList<>(cards);
			cards_copy.remove(0);
			Hand best_hand = classifyHand(cards);
			
			for (int i = 1; i < cards.size(); ++i) {
				cards_copy = new ArrayList<>(cards);
				cards_copy.remove(i);
				
				Hand actual_hand = calculateHand(cards_copy);
				
				if (actual_hand.compareTo(best_hand) > 0) {
					best_hand = actual_hand;
				}
			}
			
			return best_hand;
		}
	}
	
	/**
	 * Calculates draws given 5 or 6 cards.
	 *
	 * @param cards The array of cards. Can be unordered
	 * @return An array of three positions. The first position indicates
	 * if the card form s 'open ended straight', the second one indicates
	 * 'straight gutshot' and the third one indicates a 'flush'
	 */
	public static boolean[] calculateDraws(ArrayList<Card> cards) {
		boolean[] draws = new boolean[3];
		
		int typeStraightDraw = calculateStraightDraw(cards);
		
		draws[0] = typeStraightDraw == 1;
		draws[1] = typeStraightDraw == 2;
		draws[2] = isFlushDraw(cards);
		
		return draws;
	}
	
	/**
	 * Returns 0 if there is no straight draw, 1 if there is open ended straight and 2
	 * if there is gutshot straight
	 *
	 * @param cards The array of cards
	 * @return 0 no draw, 1 open ended, 2 gutshot
	 */
	private static int calculateStraightDraw(ArrayList<Card> cards) {
		cards.sort(Collections.reverseOrder());
		if (isStraight(cards)) return 0;
		
		// Values for readability
		final int STRAIGHT_SIZE = 5;
		final int STRAIGHT_CRITICAL_SIZE = 4;
		
		int[] rank_count = countRank(cards);
		int count = 0, pos = -1;
		
		for (int i = 0; i < STRAIGHT_SIZE; ++i) {
			if (rank_count[i] >= 1) {
				count++;
			}
		}
		if (count == STRAIGHT_CRITICAL_SIZE) pos = 0;
		
		for (int i = STRAIGHT_SIZE; i < rank_count.length; ++i) {
			if (rank_count[i] >= 1) {
				count++;
			}
			if (rank_count[i - STRAIGHT_SIZE] >= 1) {
				count--;
			}
			
			if (count == STRAIGHT_CRITICAL_SIZE) {
				pos = i - STRAIGHT_SIZE + 1;
			}
		}
		
		if (pos != -1) {
			boolean isGutshot = rank_count[pos + 1] == 0 || rank_count[pos + 2] == 0
					|| rank_count[pos + 3] == 0;
			
			boolean isOpenEnded = rank_count[pos] == 0 || rank_count[pos + 4] == 0;
			
			if (isOpenEnded) return 1;
			if (isGutshot) return 2;
		}
		return 0;
	}
	
	private static boolean isFlushDraw(ArrayList<Card> cards) {
		int[] suit_count = countSuit(cards);
		
		return find(suit_count, 0, suit_count.length, 4) < suit_count.length;
	}
	
	private static Hand classifyHand(ArrayList<Card> cards) {
		cards.sort(Collections.reverseOrder());
		
		if (isRoyalFlush(cards)) {
			return new RoyalFlush(cards);
		}
		else if (isStraightFlush(cards)) {
			return new StraightFlush(cards);
		}
		else {
			int[] rank_count = countRank(cards);
			
			if (isFourOfAKind(rank_count)) {
				return new FourOfAKind(cards);
			}
			else if (isFullHouse(rank_count)) {
				return new FullHouse(cards);
			}
			else if (isFlush(cards)) {
				return new Flush(cards);
			}
			else if (isStraight(cards)) {
				return new Straight(cards);
			}
			else if (isThreeOfAKind(rank_count)) {
				return new ThreeOfAKind(cards);
			}
			else if (isTwoPair(rank_count)) {
				return new TwoPair(cards);
			}
			else if (isPair(rank_count)) {
				return new Pair(cards);
			}
			else {
				return new HighCard(cards);
			}
		}
	}
	
	private static boolean isRoyalFlush(ArrayList<Card> cards) {
		return isFlush(cards)
				&& cards.get(0).rank == Rank.ACE
				&& cards.get(1).rank == Rank.KING
				&& cards.get(2).rank == Rank.QUEEN
				&& cards.get(3).rank == Rank.JACK
				&& cards.get(4).rank == Rank.TEN;
	}
	
	private static boolean isStraightFlush(ArrayList<Card> cards) {
		return isStraight(cards) && isFlush(cards);
	}
	
	private static boolean isFourOfAKind(int[] rank_count) {
		return find(rank_count, 0, rank_count.length, 4) < rank_count.length;
	}
	
	private static boolean isFullHouse(int[] rank_count) {
		return isThreeOfAKind(rank_count) && isPair(rank_count);
	}
	
	private static boolean isFlush(ArrayList<Card> cards) {
		return cards.get(0).suit == cards.get(1).suit
				&& cards.get(1).suit == cards.get(2).suit
				&& cards.get(2).suit == cards.get(3).suit
				&& cards.get(3).suit == cards.get(4).suit;
	}
	
	private static boolean isStraight(ArrayList<Card> cards) {
		return cards.get(0).rank.compareTo(cards.get(1).rank) == 1
				&& cards.get(1).rank.compareTo(cards.get(2).rank) == 1
				&& cards.get(2).rank.compareTo(cards.get(3).rank) == 1
				&& cards.get(3).rank.compareTo(cards.get(4).rank) == 1
				|| cards.get(0).rank == Rank.ACE
				&& cards.get(1).rank == Rank.FIVE
				&& cards.get(2).rank == Rank.FOUR
				&& cards.get(3).rank == Rank.THREE
				&& cards.get(4).rank == Rank.TWO;
	}
	
	private static boolean isThreeOfAKind(int[] rank_count) {
		return find(rank_count, 0, rank_count.length, 3) < rank_count.length;
	}
	
	private static boolean isTwoPair(int[] rank_count) {
		int pos_first_pair = find(rank_count, 0, rank_count.length, 2);
		int pos_second_pair = find(rank_count, pos_first_pair + 1, rank_count.length, 2);
		return pos_second_pair < rank_count.length;
	}
	
	private static boolean isPair(int[] rank_count) {
		return find(rank_count, 0, rank_count.length, 2) < rank_count.length;
	}
	
	private static int find(int[] arr, int start, int end, int elem) {
		int i = start;
		
		while (i < end && arr[i] != elem) {
			++i;
		}
		
		return i;
	}
	
	private static int[] countRank(ArrayList<Card> cards) {
		int[] rank_count = new int[Rank.values().length];
		for (int i = 0; i < 5; ++i) {
			Rank card_rank = cards.get(i).rank;
			rank_count[card_rank.ordinal()]++;
		}
		
		return rank_count;
	}
	
	private static int[] countSuit(ArrayList<Card> cards) {
		int[] suit_count = new int[Suit.values().length];
		for (int i = 0; i < 5; ++i) {
			Suit card_suit = cards.get(i).suit;
			suit_count[card_suit.ordinal()]++;
		}
		
		return suit_count;
	}
}
