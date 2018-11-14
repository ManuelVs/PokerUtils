package hja.pokerutils.Algorithm;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Card.Suit;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.Range.CardPair;
import hja.pokerutils.Range.Range;

import java.util.ArrayList;
import java.util.Collections;

public final class CombosAlgorithm {
	
	public static Combos getCombos(Range range, ArrayList<Card> boardCards) {
		Combos combos = new Combos();
		
		for (CardPair cp : range) {
			ArrayList<ArrayList<Card>> validPlayerCards = getValidCards(cp, boardCards);
			
			for (ArrayList<Card> playerCards : validPlayerCards) {
				ArrayList<Card> cards = new ArrayList<>();
				cards.addAll(playerCards);
				cards.addAll(boardCards);
				
				Hand hand = HoldEmAlgorithm.calculateHand(cards);
				
				updateCombos(combos, hand, playerCards, boardCards);
			}
		}
		
		return combos;
	}
	
	private static ArrayList<ArrayList<Card>> getValidCards(CardPair cp, ArrayList<Card> boardCards) {
		if (cp.firstRank == cp.secondRank) {
			return getValidPairCards(cp.firstRank, boardCards);
		}
		else if (cp.isSuited) {
			return getValidSuitedCards(cp.firstRank, cp.secondRank, boardCards);
		}
		else {
			return getValidOffSuitedCards(cp.firstRank, cp.secondRank, boardCards);
		}
	}
	
	private static ArrayList<ArrayList<Card>> getValidPairCards(Rank firstRank, ArrayList<Card> boardCards) {
		ArrayList<ArrayList<Card>> validCards = new ArrayList<>();
		
		Suit[] suits = Suit.values();
		
		for (int i = 0; i < suits.length; ++i) {
			for (int j = i + 1; j < suits.length; ++j) {
				Card first = CardFactory.createCard(firstRank, suits[i]);
				Card second = CardFactory.createCard(firstRank, suits[j]);
				
				if (!boardCards.contains(first) && !boardCards.contains(second)) {
					ArrayList<Card> cards = new ArrayList<>();
					cards.add(first);
					cards.add(second);
					
					validCards.add(cards);
				}
			}
		}
		
		return validCards;
	}
	
	private static ArrayList<ArrayList<Card>> getValidSuitedCards(Rank firstRank, Rank secondRank, ArrayList<Card> boardCards) {
		ArrayList<ArrayList<Card>> validCards = new ArrayList<>();
		
		Suit[] suits = Suit.values();
		
		for (Suit suit : suits) {
			Card first = CardFactory.createCard(firstRank, suit);
			Card second = CardFactory.createCard(secondRank, suit);
			
			if (!boardCards.contains(first) && !boardCards.contains(second)) {
				ArrayList<Card> cards = new ArrayList<>();
				cards.add(first);
				cards.add(second);
				
				validCards.add(cards);
			}
		}
		
		return validCards;
	}
	
	private static ArrayList<ArrayList<Card>> getValidOffSuitedCards(Rank firstRank, Rank secondRank, ArrayList<Card> boardCards) {
		ArrayList<ArrayList<Card>> validCards = new ArrayList<>();
		
		Suit[] suits = Suit.values();
		
		for (int i = 0; i < suits.length; ++i) {
			for (int j = 0; j < suits.length; ++j) {
				if (i != j) {
					Card first = CardFactory.createCard(firstRank, suits[i]);
					Card second = CardFactory.createCard(secondRank, suits[j]);
					
					if (!boardCards.contains(first) && !boardCards.contains(second)) {
						ArrayList<Card> cards = new ArrayList<>();
						cards.add(first);
						cards.add(second);
						
						validCards.add(cards);
					}
				}
			}
		}
		
		return validCards;
	}
	
	private static void updateCombos(Combos combos, Hand hand, ArrayList<Card> playerCards, ArrayList<Card> boardCards) {
		// Hacemos un combo cuando por lo menos una de nuestras cartas
		// participa en el "kernel" de la mano.
		if (!isIntersectionEmpty(playerCards, hand.getKernel())) {
			switch (hand.getType()) {
				case HIGH_CARD:
					combos.noHandMade++;
					break;
				case PAIR:
					// Computar tipos de pareja (overpair, top overPair...)
					updatePairCombos(combos, playerCards, boardCards);
					break;
				case TWO_PAIR:
					// Caso especial. En una doble pareja donde una pareja pertenece integra a la mesa,
					// hay que computarlo como un combo de pareja. (overpair, top overPair...)
					if (isPair(boardCards)) {
						// Computar tipos de pareja (overpair, top overPair...)
						updatePairCombos(combos, playerCards, boardCards);
					}
					else {
						combos.twoPair++;
					}
					break;
				case THREE_OF_A_KIND:
					combos.threeOfAKind++;
					break;
				case STRAIGHT:
					combos.straight++;
					break;
				case FLUSH:
					combos.flush++;
					break;
				case FULL_HOUSE:
					combos.fullHouse++;
					break;
				case FOUR_OF_A_KIND:
					combos.fourOfAKind++;
					break;
				case STRAIGHT_FLUSH:
					combos.straightFlush++;
					break;
				case ROYAL_FLUSH:
					combos.royalFlush++;
					break;
			}
		}
		else {
			combos.noHandMade++;
		}
	}
	
	/**
	 * Precondition: Hand formed with player and board cards is a overPair or two overPair
	 *
	 * @param combos      Combos struct to update
	 * @param playerCards Player cards
	 * @param boardCards  Board cards
	 */
	private static void updatePairCombos(Combos combos, ArrayList<Card> playerCards, ArrayList<Card> boardCards) {
		boardCards.sort(Collections.reverseOrder());
		Card bestBoardCard = boardCards.get(0);
		Card secondBestBoardCard = getSecondBestCard(boardCards);
		
		// Over overPair and pocket overPair below top overPair
		boolean isPlayerCardsPair = isPair(playerCards);
		if (isPlayerCardsPair) {
			Card handCard = playerCards.get(0);
			
			if (handCard.compareTo(bestBoardCard) > 0) {
				combos.overPair++;
			}
			else if (handCard.compareTo(secondBestBoardCard) > 0) {
				combos.pocketPairBelowTopPair++;
			}
			else combos.weakPair++;
		}
		
		// Top overPair
		if (playerCards.get(0).rank == bestBoardCard.rank || playerCards.get(1).rank == bestBoardCard.rank) {
			combos.topPair++;
		}
		
		// middle overPair
		else if (playerCards.get(0).rank == secondBestBoardCard.rank || playerCards.get(1).rank == secondBestBoardCard.rank) {
			combos.middlePair++;
		}
		
		// weak overPair
		else if (!isPlayerCardsPair) {
			combos.weakPair++;
		}
	}
	
	private static boolean isIntersectionEmpty(ArrayList<Card> playerCards, ArrayList<Card> kernel) {
		for (Card p : playerCards) {
			for (Card b : kernel) {
				if (p == b) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static boolean isPair(ArrayList<Card> cards) {
		int[] rank_count = AlgorithmUtils.countRank(cards);
		return AlgorithmUtils.find(rank_count, 0, rank_count.length, 2) < rank_count.length;
	}
	
	private static Card getSecondBestCard(ArrayList<Card> cards) {
		int i = 0;
		
		while (i + 1 < cards.size() && cards.get(i).rank == cards.get(i + 1).rank) {
			++i;
		}
		
		return i + 1 < cards.size() ? cards.get(i + 1) : null;
	}
	
	public static class Combos {
		public int royalFlush;
		public int straightFlush;
		public int fourOfAKind;
		public int fullHouse;
		public int flush;
		public int straight;
		public int threeOfAKind;
		public int twoPair;
		public int overPair;
		public int topPair;
		public int pocketPairBelowTopPair;
		public int middlePair;
		public int weakPair;
		public int noHandMade;
		
		public Combos() {
			this(0, 0, 0, 0, 0, 0,
					0, 0,
					0, 0, 0, 0, 0,
					0);
		}
		
		public Combos(int royalFlush, int straightFlush, int fourOfAKind, int fullHouse, int flush,
		              int straight, int threeOfAKind, int twoPair,
		              int overPair, int topPair, int pocketPairBelowTopPair, int middlePair, int weakPair,
		              int noHandMade) {
			this.royalFlush = royalFlush;
			this.straightFlush = straightFlush;
			this.fourOfAKind = fourOfAKind;
			this.fullHouse = fullHouse;
			this.flush = flush;
			this.straight = straight;
			this.threeOfAKind = threeOfAKind;
			this.twoPair = twoPair;
			this.overPair = overPair;
			this.topPair = topPair;
			this.pocketPairBelowTopPair = pocketPairBelowTopPair;
			this.middlePair = middlePair;
			this.weakPair = weakPair;
			this.noHandMade = noHandMade;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Royal flush   : " + royalFlush);
			sb.append(System.lineSeparator());
			
			sb.append("Straight flush: " + straightFlush);
			sb.append(System.lineSeparator());
			
			sb.append("FoK           : " + fourOfAKind);
			sb.append(System.lineSeparator());
			
			sb.append("Full House    : " + fullHouse);
			sb.append(System.lineSeparator());
			
			sb.append("Flush         : " + flush);
			sb.append(System.lineSeparator());
			
			sb.append("Straight      : " + straight);
			sb.append(System.lineSeparator());
			
			sb.append("ToK           : " + threeOfAKind);
			sb.append(System.lineSeparator());
			
			sb.append("Two Pair      : " + twoPair);
			sb.append(System.lineSeparator());
			
			sb.append("Over pair     : " + overPair);
			sb.append(System.lineSeparator());
			
			sb.append("Top  pair     : " + topPair);
			sb.append(System.lineSeparator());
			
			sb.append("PP b top pair : " + pocketPairBelowTopPair);
			sb.append(System.lineSeparator());
			
			sb.append("Middle pair   : " + middlePair);
			sb.append(System.lineSeparator());
			
			sb.append("Weak pair     : " + weakPair);
			sb.append(System.lineSeparator());
			
			sb.append("No hand made  : " + noHandMade);
			sb.append(System.lineSeparator());
			
			return sb.toString();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) return false;
			if (!(obj instanceof Combos)) return false;
			if (this == obj) return true;
			
			Combos other = (Combos) obj;
			return this.royalFlush == other.royalFlush
					&& this.straightFlush == other.straightFlush
					&& this.fourOfAKind == other.fourOfAKind
					&& this.fullHouse == other.fullHouse
					&& this.flush == other.flush
					&& this.straight == other.straight
					&& this.threeOfAKind == other.threeOfAKind
					&& this.twoPair == other.twoPair
					&& this.overPair == other.overPair
					&& this.topPair == other.topPair
					&& this.pocketPairBelowTopPair == other.pocketPairBelowTopPair
					&& this.middlePair == other.middlePair
					&& this.weakPair == other.weakPair
					&& this.noHandMade == other.noHandMade;
		}
	}
}
