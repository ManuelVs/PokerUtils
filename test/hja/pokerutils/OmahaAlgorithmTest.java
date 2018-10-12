package hja.pokerutils;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OmahaAlgorithmTest {
	private static final ArrayList<Card> pair1 = new ArrayList<>(Arrays.asList(Utils.ACEh, Utils.ACEc, Utils.EIGHTs, Utils.FIVEh));
	private static final ArrayList<Card> fourOf1 = new ArrayList<>(Arrays.asList(Utils.TWOh, Utils.TWOd, Utils.TWOc, Utils.TWOs));
	
	// Wikipedia:
	private static final ArrayList<Card> board = new ArrayList<>(Arrays.asList(Utils.TWOs, Utils.FIVEc, Utils.TENh, Utils.SEVENd, Utils.EIGHTc));
	private static final ArrayList<Card> mike = new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.FOURs, Utils.FIVEh, Utils.KINGc));
	private static final ArrayList<Card> brian = new ArrayList<>(Arrays.asList(Utils.ACEh, Utils.THREEh, Utils.TENs, Utils.TENc));
	private static final ArrayList<Card> jess = new ArrayList<>(Arrays.asList(Utils.SEVENc, Utils.NINEc, Utils.JACKs, Utils.QUEENs));
	private static final ArrayList<Card> tony = new ArrayList<>(Arrays.asList(Utils.FOURh, Utils.SIXh, Utils.KINGs, Utils.KINGd));
	private static final ArrayList<Card> emily = new ArrayList<>(Arrays.asList(Utils.ACEd, Utils.THREEd, Utils.SIXd, Utils.NINEh));
	
	
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
	
	@ParameterizedTest(name = "{index} => {1}")
	@MethodSource("handProvider")
	void compareToTest(ArrayList<Card> player, ArrayList<Card> board, Class<Hand> handClass) {
		Hand hand = OmahaAlgorithm.calculateHand(player, board);
		
		assertEquals(handClass, hand.getClass());
	}
}