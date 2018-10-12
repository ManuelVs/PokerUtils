package hja.pokerutils.Card;

import hja.pokerutils.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {
	private static Stream<Arguments> compareProvider() {
		return Stream.of(
				Arguments.of(Utils.ACEc, Utils.KINGd),
				Arguments.of(Utils.KINGh, Utils.QUEENc),
				Arguments.of(Utils.QUEENd, Utils.JACKd),
				Arguments.of(Utils.JACKh, Utils.TENh),
				Arguments.of(Utils.TENc, Utils.NINEd)
		);
	}
	
	private static Stream<Arguments> compareEqualProvider() {
		return Stream.of(
				Arguments.of(Utils.ACEd, Utils.ACEc),
				Arguments.of(Utils.KINGd, Utils.KINGh),
				Arguments.of(Utils.QUEENd, Utils.QUEENc),
				Arguments.of(Utils.JACKd, Utils.JACKh),
				Arguments.of(Utils.TENh, Utils.TENc)
		);
	}
	
	@ParameterizedTest(name = "{index} => left={0}, right={1}")
	@MethodSource("compareProvider")
	void compareToTest(Card left, Card right) {
		int compareGreater = left.compareTo(right);
		assertTrue(compareGreater > 0);
		
		int compareLess = right.compareTo(left);
		assertTrue(compareLess < 0);
	}
	
	@ParameterizedTest(name = "{index} => left={0}, right={1}")
	@MethodSource("compareEqualProvider")
	void compareToEqualTest(Card left, Card right) {
		int compare = left.compareTo(right);
		
		assertTrue(compare == 0);
	}
}