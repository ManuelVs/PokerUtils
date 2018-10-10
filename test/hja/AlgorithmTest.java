package hja;

import hja.Card.Card;
import hja.Card.Rank;
import hja.Card.Suit;
import hja.Hand.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgorithmTest {
	private static final Card ace = new Card(Rank.ACE, Suit.HEARTS);
	private static final Card king = new Card(Rank.KING, Suit.CLUBS);
	private static final Card queen = new Card(Rank.QUEEN, Suit.SPADES);
	private static final Card jack = new Card(Rank.JACK, Suit.DIAMONDS);
	private static final Card ten = new Card(Rank.TEN, Suit.HEARTS);
	private static final Card nine = new Card(Rank.NINE, Suit.HEARTS);
	private static final Card eight = new Card(Rank.EIGHT, Suit.DIAMONDS);
	private static final Card seven = new Card(Rank.SEVEN, Suit.DIAMONDS);
	private static final Card six = new Card(Rank.SIX, Suit.CLUBS);
	private static final Card five = new Card(Rank.FIVE, Suit.DIAMONDS);
	private static final Card four = new Card(Rank.FOUR, Suit.HEARTS);
	private static final Card three = new Card(Rank.THREE, Suit.DIAMONDS);
	private static final Card two = new Card(Rank.TWO, Suit.HEARTS);
	
	private static final Card kingh = new Card(Rank.KING, Suit.HEARTS);
	private static final Card queenh = new Card(Rank.QUEEN, Suit.HEARTS);
	private static final Card jackh = new Card(Rank.JACK, Suit.HEARTS);
	private static final Card tenh = new Card(Rank.TEN, Suit.HEARTS);
	private static final Card nineh = new Card(Rank.NINE, Suit.HEARTS);
	private static final Card eighth = new Card(Rank.EIGHT, Suit.HEARTS);
	
	private static final ArrayList<Card> hihgcard1 = new ArrayList<>(Arrays.asList(ace, king, seven, three, two));
	private static final ArrayList<Card> hihgcard2 = new ArrayList<>(Arrays.asList(king, queen, seven, three, two));
	
	private static final ArrayList<Card> pair1 = new ArrayList<>(Arrays.asList(ace, ace, seven, three, two));
	private static final ArrayList<Card> pair2 = new ArrayList<>(Arrays.asList(ace, king, king, seven, three));
	
	private static final ArrayList<Card> twopair1 = new ArrayList<>(Arrays.asList(ace, ace, seven, seven, two));
	private static final ArrayList<Card> twopair2 = new ArrayList<>(Arrays.asList(ace, king, king, three, three));
	
	private static final ArrayList<Card> threeof1 = new ArrayList<>(Arrays.asList(ace, ace, ace, three, two));
	private static final ArrayList<Card> threeof2 = new ArrayList<>(Arrays.asList(ace, king, king, king, three));
	
	private static final ArrayList<Card> straight1 = new ArrayList<>(Arrays.asList(ace, king, queen, jack, ten));
	private static final ArrayList<Card> straight2 = new ArrayList<>(Arrays.asList(king, queen, jack, ten, nine));
	
	private static final ArrayList<Card> flush1 = new ArrayList<>(Arrays.asList(ace, ten, nine, four, two));
	private static final ArrayList<Card> flush2 = new ArrayList<>(Arrays.asList(jack, eight, seven, five, three));
	
	private static final ArrayList<Card> full1 = new ArrayList<>(Arrays.asList(ace, ace, ace, five, five));
	private static final ArrayList<Card> full2 = new ArrayList<>(Arrays.asList(queen, queen, ten, ten, ten));
	
	private static final ArrayList<Card> fourof1 = new ArrayList<>(Arrays.asList(ace, ace, ace, ace, five));
	private static final ArrayList<Card> fourof2 = new ArrayList<>(Arrays.asList(ten, six, six, six, six));
	
	private static final ArrayList<Card> straightf1 = new ArrayList<>(Arrays.asList(kingh, queenh, jackh, tenh, nineh));
	private static final ArrayList<Card> straightf2 = new ArrayList<>(Arrays.asList(queenh, jackh, tenh, nineh, eighth));
	
	private static final ArrayList<Card> royalf = new ArrayList<>(Arrays.asList(ace, kingh, queenh, jackh, tenh));
	
	private static Stream<Arguments> handProvider() {
		return Stream.of(
				Arguments.of(hihgcard1, HighCard.class),
				Arguments.of(hihgcard2, HighCard.class),
				
				Arguments.of(pair1, Pair.class),
				Arguments.of(pair2, Pair.class),
				
				Arguments.of(twopair1, TwoPair.class),
				Arguments.of(twopair2, TwoPair.class),
				
				Arguments.of(threeof1, ThreeOfAKind.class),
				Arguments.of(threeof2, ThreeOfAKind.class),
				
				Arguments.of(straight1, Straight.class),
				Arguments.of(straight2, Straight.class),
				
				Arguments.of(flush1, Flush.class),
				Arguments.of(flush2, Flush.class),
				
				Arguments.of(full1, FullHouse.class),
				Arguments.of(full2, FullHouse.class),
				
				Arguments.of(fourof1, FourOfAKind.class),
				Arguments.of(fourof2, FourOfAKind.class),
				
				Arguments.of(straightf1, StraightFlush.class),
				Arguments.of(straightf2, StraightFlush.class),
				
				Arguments.of(royalf, RoyalFlush.class)
		);
	}
	
	@ParameterizedTest(name = "{index} => {1}")
	@MethodSource("handProvider")
	void compareToTest(ArrayList<Card> cards, Class<Hand> handClass) {
		Hand hand = Algorithm.calculateHand(cards);
		
		assertEquals(handClass, hand.getClass());
	}
}