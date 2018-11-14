package hja.pokerutils.Algorithm;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Range.Range;
import hja.pokerutils.TestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombosAlgorithmTest {
	private static Stream<Arguments> combosProvider() {
		return Stream.of(
				// Tests del enunciado
				Arguments.of(
						new Range(
								new Range(Rank.ACE, false),
								new Range(Rank.ACE, Rank.KING, true, false),
								new Range(Rank.KING, false),
								new Range(Rank.QUEEN, Rank.NINE, true, false),
								new Range(Rank.SIX, Rank.FIVE, true, false),
								new Range(Rank.TWO, false)
						),
						new ArrayList<>(Arrays.asList(TestUtils.ACEh, TestUtils.QUEENh, TestUtils.JACKs)),
						new Combos(0, 0, 0, 0, 0, 0, 3, 0,
								0, 3, 6, 3, 6,
								4, 0, 0, 0)
				),
				Arguments.of(
						new Range(
								new Range(Rank.NINE, true),
								new Range(Rank.SEVEN, false),
								new Range(Rank.TWO, false),
								new Range(Rank.ACE, Rank.KING, true, false),
								new Range(Rank.ACE, Rank.JACK, false, true),
								new Range(Rank.KING, Rank.JACK, true, false),
								new Range(Rank.KING, Rank.EIGHT, true, false),
								new Range(Rank.KING, Rank.QUEEN, false, false),
								new Range(Rank.KING, Rank.TEN, false, false),
								new Range(Rank.QUEEN, Rank.NINE, true, false),
								new Range(Rank.QUEEN, Rank.NINE, false, false),
								new Range(Rank.JACK, Rank.NINE, true, false),
								new Range(Rank.NINE, Rank.SEVEN, true, false),
								new Range(Rank.SEVEN, Rank.SIX, true, false)
						),
						new ArrayList<>(Arrays.asList(TestUtils.KINGh, TestUtils.JACKh, TestUtils.NINEh, TestUtils.JACKd)),
						new Combos(0, 0, 1, 10, 1, 0, 6, 0,
								6, 33, 6, 0, 33,
								15, 0, 0, 0)
				),
				Arguments.of(
						new Range(
								new Range(Rank.KING, Rank.JACK, true, false),
								new Range(Rank.QUEEN, Rank.TEN, true, false),
								new Range(Rank.JACK, Rank.NINE, true, false),
								new Range(Rank.TEN, Rank.EIGHT, true, false)
						),
						new ArrayList<>(Arrays.asList(TestUtils.KINGh, TestUtils.JACKh, TestUtils.NINEh)),
						new Combos(0, 1, 0, 0, 1, 3, 0, 6,
								0, 0, 0, 0, 0,
								3, 0, 0, 0)
				),
				
				// Otros tests comparados con Flopzilla
				Arguments.of(
						new Range(
								new Range(Rank.TWO, true),
								new Range(Rank.ACE, Rank.TEN, true, true),
								new Range(Rank.KING, Rank.TEN, true, true),
								new Range(Rank.QUEEN, Rank.TEN, true, true),
								new Range(Rank.JACK, Rank.TEN, true, true),
								
								new Range(Rank.ACE, Rank.TEN, false, true),
								new Range(Rank.KING, Rank.TEN, false, true),
								new Range(Rank.QUEEN, Rank.TEN, false, true),
								new Range(Rank.JACK, Rank.TEN, false, true)
						),
						new ArrayList<>(Arrays.asList(TestUtils.NINEd, TestUtils.SEVENs, TestUtils.FIVEh)),
						new Combos(0, 0, 0, 0, 0, 0, 9, 0,
								30, 0, 6, 0, 24,
								160, 0, 0, 0)
				),
				Arguments.of(
						new Range(
								new Range(Rank.TWO, true),
								new Range(Rank.ACE, Rank.TEN, true, true),
								new Range(Rank.KING, Rank.TEN, true, true),
								new Range(Rank.QUEEN, Rank.TEN, true, true),
								new Range(Rank.JACK, Rank.TEN, true, true),
								
								new Range(Rank.ACE, Rank.TEN, false, true),
								new Range(Rank.KING, Rank.TEN, false, true),
								new Range(Rank.QUEEN, Rank.TEN, false, true),
								new Range(Rank.JACK, Rank.TEN, false, true)
						),
						new ArrayList<>(Arrays.asList(TestUtils.KINGh, TestUtils.QUEENc, TestUtils.JACKd)),
						new Combos(0, 0, 0, 0, 0, 16, 9, 27,
								6, 24, 0, 24, 78,
								0, 0, 0, 0)
				),
				Arguments.of(
						new Range(
								new Range(Rank.ACE, Rank.EIGHT, false, false),
								new Range(Rank.ACE, Rank.TWO, false, false),
								new Range(Rank.TEN, false),
								new Range(Rank.NINE, Rank.FIVE, true, false)
						),
						new ArrayList<>(Arrays.asList(TestUtils.QUEENd, TestUtils.EIGHTc, TestUtils.THREEh)),
						new Combos(0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 6, 9, 0,
								16, 0, 0, 0)
				)
		);
	}
	
	@ParameterizedTest(name = "{index} => {0}")
	@MethodSource("combosProvider")
	void getCombosTest(Range range, ArrayList<Card> boardCards, Combos combos) {
		Combos aCombos = CombosAlgorithm.getCombos(range, boardCards);
		
		assertEquals(combos, aCombos);
	}
}
