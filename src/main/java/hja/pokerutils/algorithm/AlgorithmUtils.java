package hja.pokerutils.algorithm;

import hja.pokerutils.card.Card;
import hja.pokerutils.card.Rank;
import hja.pokerutils.card.Suit;

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
		for (Card card : cards) {
			Rank card_rank = card.rank;
			rank_count[card_rank.ordinal()]++;
		}
		
		return rank_count;
	}
	
	public static int[] countSuit(ArrayList<Card> cards) {
		int[] suit_count = new int[Suit.values().length];
		for (Card card : cards) {
			Suit card_suit = card.suit;
			suit_count[card_suit.ordinal()]++;
		}
		
		return suit_count;
	}
}
