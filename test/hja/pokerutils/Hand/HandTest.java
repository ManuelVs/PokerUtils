package hja.pokerutils.Hand;

import hja.pokerutils.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {
	private static final Hand highCard1 = new HighCard(new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.KINGd, Utils.SEVENc, Utils.THREEd, Utils.TWOd)));
	private static final Hand highCard2 = new HighCard(new ArrayList<>(Arrays.asList(Utils.KINGd, Utils.QUEENd, Utils.SEVENc, Utils.THREEd, Utils.TWOh)));
	
	private static final Hand pair1 = new Pair(new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.ACEd, Utils.SEVENc, Utils.THREEd, Utils.TWOs)));
	private static final Hand pair2 = new Pair(new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.KINGd, Utils.KINGh, Utils.SEVENs, Utils.THREEd)));
	
	private static final Hand twoPair1 = new TwoPair(new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.ACEd, Utils.SEVENc, Utils.SEVENd, Utils.TWOc)));
	private static final Hand twoPair2 = new TwoPair(new ArrayList<>(Arrays.asList(Utils.KINGd, Utils.KINGh, Utils.EIGHTs, Utils.THREEh, Utils.THREEc)));
	
	private static final Hand threeOf1 = new ThreeOfAKind(new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.ACEd, Utils.ACEh, Utils.THREEs, Utils.TWOd)));
	private static final Hand threeOf2 = new ThreeOfAKind(new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.KINGd, Utils.KINGs, Utils.KINGc, Utils.FOURd)));
	
	private static final Hand straight1 = new Straight(new ArrayList<>(Arrays.asList(Utils.ACEc, Utils.KINGs, Utils.QUEENh, Utils.JACKh, Utils.TENd)));
	private static final Hand straight2 = new Straight(new ArrayList<>(Arrays.asList(Utils.QUEENs, Utils.JACKd, Utils.TENh, Utils.NINEh, Utils.EIGHTd)));
	
	private static final Hand flush1 = new Flush(new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.JACKs, Utils.TENs, Utils.NINEs, Utils.FOURs)));
	private static final Hand flush2 = new Flush(new ArrayList<>(Arrays.asList(Utils.QUEENc, Utils.JACKc, Utils.TENc, Utils.FIVEc, Utils.FOURc)));
	
	private static final Hand full1 = new FullHouse(new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.ACEd, Utils.ACEh, Utils.FIVEs, Utils.FIVEd)));
	private static final Hand full2 = new FullHouse(new ArrayList<>(Arrays.asList(Utils.QUEENs, Utils.QUEENd, Utils.TENc, Utils.TENh, Utils.TENd)));
	
	private static final Hand fourOf1 = new FourOfAKind(new ArrayList<>(Arrays.asList(Utils.ACEs, Utils.ACEh, Utils.ACEd, Utils.ACEc, Utils.EIGHTc)));
	private static final Hand fourOf2 = new FourOfAKind(new ArrayList<>(Arrays.asList(Utils.NINEd, Utils.SIXc, Utils.SIXd, Utils.SIXh, Utils.SIXs)));
	
	private static final Hand straightF1 = new StraightFlush(new ArrayList<>(Arrays.asList(Utils.KINGc, Utils.QUEENc, Utils.JACKc, Utils.TENc, Utils.NINEc)));
	private static final Hand straightF2 = new StraightFlush(new ArrayList<>(Arrays.asList(Utils.EIGHTh, Utils.SEVENh, Utils.SIXh, Utils.FIVEh, Utils.FOURh)));
	
	private static final Hand royalF = new RoyalFlush(new ArrayList<>(Arrays.asList(Utils.ACEh, Utils.KINGh, Utils.QUEENh, Utils.JACKh, Utils.TENh)));
	
	private static Stream<Arguments> compareProvider() {
		return Stream.of(
				Arguments.of(highCard1, highCard2),
				Arguments.of(pair1, pair2),
				Arguments.of(twoPair1, twoPair2),
				Arguments.of(threeOf1, threeOf2),
				Arguments.of(straight1, straight2),
				Arguments.of(flush1, flush2),
				Arguments.of(full1, full2),
				Arguments.of(fourOf1, fourOf2),
				Arguments.of(straightF1, straightF2),
				
				Arguments.of(royalF, straightF1),
				Arguments.of(straightF1, fourOf1),
				Arguments.of(fourOf1, full2),
				Arguments.of(full1, flush2),
				Arguments.of(flush2, straight2),
				Arguments.of(straight2, threeOf2),
				Arguments.of(threeOf2, twoPair1),
				Arguments.of(twoPair1, pair1),
				Arguments.of(twoPair1, highCard2)
		);
	}
	
	@ParameterizedTest(name = "{index} => left={0}, right={1}")
	@MethodSource("compareProvider")
	void compareToTest(Hand left, Hand right) {
		int compareGreater = left.compareTo(right);
		assertTrue(compareGreater > 0);
		
		int compareLess = right.compareTo(left);
		assertTrue(compareLess < 0);
	}
}