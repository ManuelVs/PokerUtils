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
		HoldEmAlgorithm classifier = new HoldEmAlgorithm();
		
		for (CardPair cp : range) {
			ArrayList<ArrayList<Card>> validPlayerCards = getValidCards(cp, boardCards);
			
			for (ArrayList<Card> playerCards : validPlayerCards) {
				ArrayList<Card> cards = new ArrayList<>();
				cards.addAll(playerCards);
				cards.addAll(boardCards);
				
				Hand hand = classifier.calculateHand(cards);
				
				updateCombos(combos, hand, playerCards, boardCards);
				
				if (cards.size() < 7) {
					boolean[] draws = HoldEmAlgorithm.calculateDraws(cards);
					updateDraws(combos, draws);
				}
			}
		}
		
		return combos;
	}
	
	private static void updateDraws(Combos combos, boolean[] draws) {
		if (draws[0]) {
			combos.openEndedDraw++;
		}
		
		if (draws[1]) {
			combos.gutshotDraw++;
		}
		
		if (draws[2]) {
			combos.flushDraw++;
		}
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
}
