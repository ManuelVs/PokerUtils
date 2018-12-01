package hja.pokerutils.Card;

import hja.pokerutils.TestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {
	private static Stream<Arguments> compareProvider() {
		return Stream.of(
			Arguments.of(TestUtils.ACEc, TestUtils.KINGd),
			Arguments.of(TestUtils.KINGh, TestUtils.QUEENc),
			Arguments.of(TestUtils.QUEENd, TestUtils.JACKd),
			Arguments.of(TestUtils.JACKh, TestUtils.TENh),
			Arguments.of(TestUtils.TENc, TestUtils.NINEd)
		);
	}
	
	private static Stream<Arguments> compareEqualProvider() {
		return Stream.of(
			Arguments.of(TestUtils.ACEd, TestUtils.ACEc),
			Arguments.of(TestUtils.KINGd, TestUtils.KINGh),
			Arguments.of(TestUtils.QUEENd, TestUtils.QUEENc),
			Arguments.of(TestUtils.JACKd, TestUtils.JACKh),
			Arguments.of(TestUtils.TENh, TestUtils.TENc)
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