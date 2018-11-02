package hja.pokerutils.Algorithm;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Card.Suit;

import java.util.ArrayList;

public final class AlgorithmUtils {
	public static int find(int[] arr, int start, int end, int elem) {
		int i = start;
		
		while (i < end && arr[i] != elem) {
			++i;
		}
		
		return i;
	}
	
	public static int[] countRank(ArrayList<Card> cards) {
		int[] rank_count = new int[Rank.values().length];
		for (int i = 0; i < cards.size(); ++i) {
			Rank card_rank = cards.get(i).rank;
			rank_count[card_rank.ordinal()]++;
		}
		
		return rank_count;
	}
	
	public static int[] countSuit(ArrayList<Card> cards) {
		int[] suit_count = new int[Suit.values().length];
		for (int i = 0; i < cards.size(); ++i) {
			Suit card_suit = cards.get(i).suit;
			suit_count[card_suit.ordinal()]++;
		}
		
		return suit_count;
	}
}
