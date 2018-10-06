package hja.Hand;

import hja.Card.Card;
import hja.Card.Rank;
import hja.Card.Suit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {
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
	private static final Card eighth = new Card(Rank.EIGHT, Suit.DIAMONDS);
	
	private static final Hand hihgcard1 = new HighCard(new ArrayList<>(Arrays.asList(ace, king, seven, three, two )));
	private static final Hand hihgcard2 = new HighCard(new ArrayList<>(Arrays.asList(king, queen, seven, three, two)));
	
	private static final Hand pair1 = new Pair(new ArrayList<>(Arrays.asList(ace, ace, seven, three, two )));
	private static final Hand pair2 = new Pair(new ArrayList<>(Arrays.asList(king, king, seven, three, two)));
	
	private static final Hand twopair1 = new TwoPair(new ArrayList<>(Arrays.asList(ace, ace, seven, seven, two )));
	private static final Hand twopair2 = new TwoPair(new ArrayList<>(Arrays.asList(king, king, three, three, two)));
	
	private static final Hand threeof1 = new ThreeOfAKind(new ArrayList<>(Arrays.asList(ace, ace, ace, three, two )));
	private static final Hand threeof2 = new ThreeOfAKind(new ArrayList<>(Arrays.asList(king, king, king, three, two)));
	
	private static final Hand straight1 = new Straight(new ArrayList<>(Arrays.asList(ace, king, queen, jack, ten )));
	private static final Hand straight2 = new Straight(new ArrayList<>(Arrays.asList(king, queen, jack, ten, nine)));
	
	private static final Hand flush1 = new Flush(new ArrayList<>(Arrays.asList(ace, ten, nine, four, two)));
	private static final Hand flush2 = new Flush(new ArrayList<>(Arrays.asList(jack, eight, seven, five, three)));
	
	private static final Hand full1 = new FullHouse(new ArrayList<>(Arrays.asList(ace, ace, ace, five, five)));
	private static final Hand full2 = new FullHouse(new ArrayList<>(Arrays.asList(queen, queen, queen, ten, ten)));
	
	private static final Hand fourof1 = new FourOfAKind(new ArrayList<>(Arrays.asList(ace, ace, ace, ace, five)));
	private static final Hand fourof2 = new FourOfAKind(new ArrayList<>(Arrays.asList(six, six, six, six, ten)));
	
	private static final Hand straightf1 = new StraightFlush(new ArrayList<>(Arrays.asList(kingh, queenh, jackh, tenh, nineh)));
	private static final Hand straightf2 = new StraightFlush(new ArrayList<>(Arrays.asList(queenh, jackh, tenh, nineh, eighth)));
	
	private static final Hand royalf = new RoyalFlush(new ArrayList<>(Arrays.asList(ace, kingh, queenh, jackh, tenh)));
	
	private static Stream<Arguments> compareProvider() {
		return Stream.of(
				Arguments.of(hihgcard1, hihgcard2),
				Arguments.of(pair1, pair2),
				Arguments.of(twopair1, twopair2),
				Arguments.of(threeof1, threeof2),
				Arguments.of(straight1, straight2),
				Arguments.of(flush1, flush2),
				Arguments.of(full1, full2),
				Arguments.of(fourof1, fourof2),
				Arguments.of(straightf1, straightf2),
				
				Arguments.of(royalf, straightf1),
				Arguments.of(straightf1, fourof1),
				Arguments.of(fourof1, full2),
				Arguments.of(full1, flush2),
				Arguments.of(flush2, straight2),
				Arguments.of(straight2, threeof2),
				Arguments.of(threeof2, twopair1),
				Arguments.of(twopair1, pair1),
				Arguments.of(twopair1, hihgcard2)
		);
	}
	
	@ParameterizedTest(name = "{index} => left={0}, right={1}")
	@MethodSource("compareProvider")
	void compareToTest(Hand left, Hand right){
		int compareGreater = left.compareTo(right);
		assertTrue(compareGreater > 0);
		
		int compareLess = right.compareTo(left);
		assertTrue(compareLess < 0);
	}
}