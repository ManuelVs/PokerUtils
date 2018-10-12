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

class HoldEmAlgorithmTest {
	private static final ArrayList<Card> highCard1 = new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.KINGd, Utils.SEVENc, Utils.THREEd, Utils.TWOd));
	private static final ArrayList<Card> highCard2 = new ArrayList<>(Arrays.asList(Utils.KINGd, Utils.QUEENd, Utils.SEVENc, Utils.THREEd, Utils.TWOh));
	
	private static final ArrayList<Card> pair1 = new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.ACEd, Utils.SEVENc, Utils.THREEd, Utils.TWOs));
	private static final ArrayList<Card> pair2 = new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.KINGd, Utils.KINGh, Utils.SEVENs, Utils.THREEd));
	
	private static final ArrayList<Card> twoPair1 = new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.ACEd, Utils.SEVENc, Utils.SEVENd, Utils.TWOc));
	private static final ArrayList<Card> twoPair2 = new ArrayList<>(Arrays.asList(Utils.KINGd, Utils.KINGh, Utils.EIGHTs, Utils.THREEh, Utils.THREEc));
	
	private static final ArrayList<Card> threeOf1 = new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.ACEd, Utils.ACEh, Utils.THREEs, Utils.TWOd));
	private static final ArrayList<Card> threeOf2 = new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.KINGd, Utils.KINGs, Utils.KINGc, Utils.FOURd));
	
	private static final ArrayList<Card> straight1 = new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.KINGs, Utils.QUEENh, Utils.JACKh, Utils.TENd));
	private static final ArrayList<Card> straight2 = new ArrayList<>(Arrays.asList(Utils.QUEENs, Utils.JACKd, Utils.TENh, Utils.NINEh, Utils.EIGHTd));
	
	private static final ArrayList<Card> flush1 = new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.JACKs, Utils.TENs, Utils.NINEs, Utils.FOURs));
	private static final ArrayList<Card> flush2 = new ArrayList<>(Arrays.asList(Utils.QUEENc, Utils.JACKc, Utils.TENc, Utils.FIVEc, Utils.FOURc));
	
	private static final ArrayList<Card> full1 = new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.ACEd, Utils.ACEh, Utils.FIVEs, Utils.FIVEd));
	private static final ArrayList<Card> full2 = new ArrayList<>(Arrays.asList(Utils.QUEENs, Utils.QUEENd, Utils.TENc, Utils.TENh, Utils.TENd));
	
	private static final ArrayList<Card> fourOf1 = new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.ACEh, Utils.ACEd, Utils.ACEc, Utils.EIGHTc));
	private static final ArrayList<Card> fourOf2 = new ArrayList<>(Arrays.asList(Utils.NINEd, Utils.SIXc, Utils.SIXd, Utils.SIXh, Utils.SIXs));
	
	private static final ArrayList<Card> straightF1 = new ArrayList<>(Arrays.asList(Utils.KINGc, Utils.QUEENc, Utils.JACKc, Utils.TENc, Utils.NINEc));
	private static final ArrayList<Card> straightF2 = new ArrayList<>(Arrays.asList(Utils.EIGHTh, Utils.SEVENh, Utils.SIXh, Utils.FIVEh, Utils.FOURh));
	
	private static final ArrayList<Card> royalF = new ArrayList<>(Arrays.asList(Utils.ACEh, Utils.KINGh, Utils.QUEENh, Utils.JACKh, Utils.TENh));
	
	// Impossibles...
	private static final ArrayList<Card> flush_full = new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.ACEh, Utils.ACEd, Utils.NINEs, Utils.NINEh, Utils.EIGHTs, Utils.SEVENs, Utils.SIXs));
	private static final ArrayList<Card> straight_poker = new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.ACEh, Utils.ACEd, Utils.ACEc, Utils.KINGs, Utils.QUEENd, Utils.JACKs, Utils.TENs));
	
	private static Stream<Arguments> handProvider() {
		return Stream.of(
				Arguments.of(highCard1, HighCard.class),
				Arguments.of(highCard2, HighCard.class),
				
				Arguments.of(pair1, Pair.class),
				Arguments.of(pair2, Pair.class),
				
				Arguments.of(twoPair1, TwoPair.class),
				Arguments.of(twoPair2, TwoPair.class),
				
				Arguments.of(threeOf1, ThreeOfAKind.class),
				Arguments.of(threeOf2, ThreeOfAKind.class),
				
				Arguments.of(straight1, Straight.class),
				Arguments.of(straight2, Straight.class),
				
				Arguments.of(flush1, Flush.class),
				Arguments.of(flush2, Flush.class),
				
				Arguments.of(full1, FullHouse.class),
				Arguments.of(full2, FullHouse.class),
				
				Arguments.of(fourOf1, FourOfAKind.class),
				Arguments.of(fourOf2, FourOfAKind.class),
				
				Arguments.of(straightF1, StraightFlush.class),
				Arguments.of(straightF2, StraightFlush.class),
				
				Arguments.of(royalF, RoyalFlush.class),
				
				Arguments.of(flush_full, FullHouse.class),
				Arguments.of(straight_poker, FourOfAKind.class)
		);
	}
	
	@ParameterizedTest(name = "{index} => {1}")
	@MethodSource("handProvider")
	void compareToTest(ArrayList<Card> cards, Class<Hand> handClass) {
		Hand hand = HoldEmAlgorithm.calculateHand(cards);
		
		assertEquals(handClass, hand.getClass());
	}
}