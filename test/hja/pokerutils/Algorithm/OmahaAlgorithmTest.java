package hja.pokerutils.Algorithm;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.*;
import hja.pokerutils.TestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OmahaAlgorithmTest {
	private static final ArrayList<Card> pair1 = new ArrayList<>(Arrays.asList(TestUtils.ACEh, TestUtils.ACEc, TestUtils.EIGHTs, TestUtils.FIVEh));
	private static final ArrayList<Card> fourOf1 = new ArrayList<>(Arrays.asList(TestUtils.TWOh, TestUtils.TWOd, TestUtils.TWOc, TestUtils.TWOs));
	
	// Wikipedia:
	private static final ArrayList<Card> board = new ArrayList<>(Arrays.asList(TestUtils.TWOs, TestUtils.FIVEc, TestUtils.TENh, TestUtils.SEVENd, TestUtils.EIGHTc));
	private static final ArrayList<Card> mike = new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.FOURs, TestUtils.FIVEh, TestUtils.KINGc));
	private static final ArrayList<Card> brian = new ArrayList<>(Arrays.asList(TestUtils.ACEh, TestUtils.THREEh, TestUtils.TENs, TestUtils.TENc));
	private static final ArrayList<Card> jess = new ArrayList<>(Arrays.asList(TestUtils.SEVENc, TestUtils.NINEc, TestUtils.JACKs, TestUtils.QUEENs));
	private static final ArrayList<Card> tony = new ArrayList<>(Arrays.asList(TestUtils.FOURh, TestUtils.SIXh, TestUtils.KINGs, TestUtils.KINGd));
	private static final ArrayList<Card> emily = new ArrayList<>(Arrays.asList(TestUtils.ACEd, TestUtils.THREEd, TestUtils.SIXd, TestUtils.NINEh));
	
	
	private static Stream<Arguments> handProvider() {
		return Stream.of(
				Arguments.of(pair1, fourOf1, FullHouse.class),
				
				Arguments.of(mike, board, Pair.class),
				Arguments.of(brian, board, ThreeOfAKind.class),
				Arguments.of(jess, board, Straight.class),
				Arguments.of(tony, board, Straight.class),
				Arguments.of(emily, board, Straight.class)
		);
	}
	
	@ParameterizedTest(name = "{index} => {0}, {1}")
	@MethodSource("handProvider")
	void compareToTest(ArrayList<Card> player, ArrayList<Card> board, Class<Hand> handClass) {
		Hand hand = OmahaAlgorithm.calculateHand(player, board);
		
		assertEquals(handClass, hand.getClass());
	}
}