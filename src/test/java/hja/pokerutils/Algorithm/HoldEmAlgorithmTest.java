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

class HoldEmAlgorithmTest {
	private static final ArrayList<Card> highCard1 = new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.KINGd, TestUtils.SEVENc, TestUtils.THREEd, TestUtils.TWOd));
	private static final ArrayList<Card> highCard2 = new ArrayList<>(Arrays.asList(TestUtils.KINGd, TestUtils.QUEENd, TestUtils.SEVENc, TestUtils.THREEd, TestUtils.TWOh));
	
	private static final ArrayList<Card> pair1 = new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.ACEd, TestUtils.SEVENc, TestUtils.THREEd, TestUtils.TWOs));
	private static final ArrayList<Card> pair2 = new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.KINGd, TestUtils.KINGh, TestUtils.SEVENs, TestUtils.THREEd));
	
	private static final ArrayList<Card> twoPair1 = new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.ACEd, TestUtils.SEVENc, TestUtils.SEVENd, TestUtils.TWOc));
	private static final ArrayList<Card> twoPair2 = new ArrayList<>(Arrays.asList(TestUtils.KINGd, TestUtils.KINGh, TestUtils.EIGHTs, TestUtils.THREEh, TestUtils.THREEc));
	
	private static final ArrayList<Card> threeOf1 = new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.ACEd, TestUtils.ACEh, TestUtils.THREEs, TestUtils.TWOd));
	private static final ArrayList<Card> threeOf2 = new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGd, TestUtils.KINGs, TestUtils.KINGc, TestUtils.FOURd));
	
	private static final ArrayList<Card> straight1 = new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.KINGs, TestUtils.QUEENh, TestUtils.JACKh, TestUtils.TENd));
	private static final ArrayList<Card> straight2 = new ArrayList<>(Arrays.asList(TestUtils.QUEENs, TestUtils.JACKd, TestUtils.TENh, TestUtils.NINEh, TestUtils.EIGHTd));
	
	private static final ArrayList<Card> flush1 = new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.JACKs, TestUtils.TENs, TestUtils.NINEs, TestUtils.FOURs));
	private static final ArrayList<Card> flush2 = new ArrayList<>(Arrays.asList(TestUtils.QUEENc, TestUtils.JACKc, TestUtils.TENc, TestUtils.FIVEc, TestUtils.FOURc));
	
	private static final ArrayList<Card> full1 = new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.ACEd, TestUtils.ACEh, TestUtils.FIVEs, TestUtils.FIVEd));
	private static final ArrayList<Card> full2 = new ArrayList<>(Arrays.asList(TestUtils.QUEENs, TestUtils.QUEENd, TestUtils.TENc, TestUtils.TENh, TestUtils.TENd));
	
	private static final ArrayList<Card> fourOf1 = new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.ACEh, TestUtils.ACEd, TestUtils.ACEc, TestUtils.EIGHTc));
	private static final ArrayList<Card> fourOf2 = new ArrayList<>(Arrays.asList(TestUtils.NINEd, TestUtils.SIXc, TestUtils.SIXd, TestUtils.SIXh, TestUtils.SIXs));
	
	private static final ArrayList<Card> straightF1 = new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENc, TestUtils.JACKc, TestUtils.TENc, TestUtils.NINEc));
	private static final ArrayList<Card> straightF2 = new ArrayList<>(Arrays.asList(TestUtils.EIGHTh, TestUtils.SEVENh, TestUtils.SIXh, TestUtils.FIVEh, TestUtils.FOURh));
	
	private static final ArrayList<Card> royalF = new ArrayList<>(Arrays.asList(TestUtils.ACEh, TestUtils.KINGh, TestUtils.QUEENh, TestUtils.JACKh, TestUtils.TENh));
	
	// Impossibles...
	private static final ArrayList<Card> flush_full = new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.ACEh, TestUtils.ACEd, TestUtils.NINEs, TestUtils.NINEh, TestUtils.EIGHTs, TestUtils.SEVENs, TestUtils.SIXs));
	private static final ArrayList<Card> straight_poker = new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.ACEh, TestUtils.ACEd, TestUtils.ACEc, TestUtils.KINGs, TestUtils.QUEENd, TestUtils.JACKs, TestUtils.TENs));
	
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