package hja.pokerutils.Parser;

import hja.pokerutils.Card.Rank;
import hja.pokerutils.Range.CardPair;
import hja.pokerutils.Range.Range;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RangeParserTest {
	private static Stream<Arguments> rangeProvider() {
		return Stream.of(
				Arguments.of("AA", new ArrayList<>(Arrays.asList(
						new CardPair(Rank.ACE, Rank.ACE, false))
				)),
				Arguments.of("AQs", new ArrayList<>(Arrays.asList(
						new CardPair(Rank.ACE, Rank.QUEEN, true))
				)),
				Arguments.of("AQo", new ArrayList<>(Arrays.asList(
						new CardPair(Rank.ACE, Rank.QUEEN, false))
				)),
				Arguments.of("94s", new ArrayList<>(Arrays.asList(
						new CardPair(Rank.NINE, Rank.FOUR, true))
				)),
				Arguments.of("94s+", new ArrayList<>(Arrays.asList(
						new CardPair(Rank.NINE, Rank.FOUR, true),
						new CardPair(Rank.NINE, Rank.FIVE, true),
						new CardPair(Rank.NINE, Rank.SIX, true),
						new CardPair(Rank.NINE, Rank.SEVEN, true),
						new CardPair(Rank.NINE, Rank.EIGHT, true))
				)),
				Arguments.of("55+", new ArrayList<>(Arrays.asList(
						new CardPair(Rank.FIVE, Rank.FIVE, false),
						new CardPair(Rank.SIX, Rank.SIX, false),
						new CardPair(Rank.SEVEN, Rank.SEVEN, false),
						new CardPair(Rank.EIGHT, Rank.EIGHT, false),
						new CardPair(Rank.NINE, Rank.NINE, false),
						new CardPair(Rank.TEN, Rank.TEN, false),
						new CardPair(Rank.JACK, Rank.JACK, false),
						new CardPair(Rank.QUEEN, Rank.QUEEN, false),
						new CardPair(Rank.KING, Rank.KING, false),
						new CardPair(Rank.ACE, Rank.ACE, false))
				)),
				Arguments.of("92s-95s", new ArrayList<>(Arrays.asList(
						new CardPair(Rank.NINE, Rank.TWO, true),
						new CardPair(Rank.NINE, Rank.THREE, true),
						new CardPair(Rank.NINE, Rank.FOUR, true),
						new CardPair(Rank.NINE, Rank.FIVE, true))
				)),
				Arguments.of("ATo-AKo", new ArrayList<>(Arrays.asList(
						new CardPair(Rank.ACE, Rank.TEN, false),
						new CardPair(Rank.ACE, Rank.JACK, false),
						new CardPair(Rank.ACE, Rank.QUEEN, false),
						new CardPair(Rank.ACE, Rank.KING, false))
				))
		);
	}
	
	private static Stream<Arguments> outOfRangeProvider() {
		return Stream.of(
				Arguments.of("ATo-AAo"),
				Arguments.of("54s, ATo-AAo"),
				Arguments.of("32s-3As")
		);
	}
	
	private static Stream<Arguments> malformedProvider() {
		return Stream.of(
				Arguments.of("AAs"),
				Arguments.of("AAo"),
				Arguments.of("QQs"),
				Arguments.of("93s,65o,JJo")
		);
	}
	
	@ParameterizedTest(name = "{index} => {0}")
	@MethodSource("rangeProvider")
	void parseListCardTest(String sRange, ArrayList<CardPair> aRange) {
		Range range = RangeParser.parseRange(sRange);
		
		int i = 0;
		for(CardPair cp : range){
			assertTrue(aRange.contains(cp));
			++i;
		}
		
		assertEquals(aRange.size(), i);
	}
	
	@ParameterizedTest(name = "{index} => {0}")
	@MethodSource("outOfRangeProvider")
	void testOutOfBoundsRange(String range) {
		assertThrows(IllegalArgumentException.class, () -> RangeParser.parseRange(range));
	}
	
	@ParameterizedTest(name = "{index} => {0}")
	@MethodSource("malformedProvider")
	void testMalformedRange(String range) {
		assertNull(RangeParser.parseRange(range));
	}
	
	private <T> ArrayList<T> createArrayFromIterable(Iterable<T> iterable) {
		ArrayList<T> ts = new ArrayList<>();
		
		for (T t : iterable) {
			ts.add(t);
		}
		
		return ts;
	}
}
