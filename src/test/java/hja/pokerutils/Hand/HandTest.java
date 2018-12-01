package hja.pokerutils.Hand;

import hja.pokerutils.TestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {
	private static final Hand highCard1 = new HighCard(new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.KINGd, TestUtils.SEVENc, TestUtils.THREEd, TestUtils.TWOd)));
	private static final Hand highCard2 = new HighCard(new ArrayList<>(Arrays.asList(TestUtils.KINGd, TestUtils.QUEENd, TestUtils.SEVENc, TestUtils.THREEd, TestUtils.TWOh)));
	
	private static final Hand pair1 = new Pair(new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.ACEd, TestUtils.SEVENc, TestUtils.THREEd, TestUtils.TWOs)));
	private static final Hand pair2 = new Pair(new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.KINGd, TestUtils.KINGh, TestUtils.SEVENs, TestUtils.THREEd)));
	
	private static final Hand twoPair1 = new TwoPair(new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.ACEd, TestUtils.SEVENc, TestUtils.SEVENd, TestUtils.TWOc)));
	private static final Hand twoPair2 = new TwoPair(new ArrayList<>(Arrays.asList(TestUtils.KINGd, TestUtils.KINGh, TestUtils.EIGHTs, TestUtils.THREEh, TestUtils.THREEc)));
	
	private static final Hand threeOf1 = new ThreeOfAKind(new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.ACEd, TestUtils.ACEh, TestUtils.THREEs, TestUtils.TWOd)));
	private static final Hand threeOf2 = new ThreeOfAKind(new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGd, TestUtils.KINGs, TestUtils.KINGc, TestUtils.FOURd)));
	
	private static final Hand straight1 = new Straight(new ArrayList<>(Arrays.asList(TestUtils.ACEc, TestUtils.KINGs, TestUtils.QUEENh, TestUtils.JACKh, TestUtils.TENd)));
	private static final Hand straight2 = new Straight(new ArrayList<>(Arrays.asList(TestUtils.QUEENs, TestUtils.JACKd, TestUtils.TENh, TestUtils.NINEh, TestUtils.EIGHTd)));
	
	private static final Hand flush1 = new Flush(new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.JACKs, TestUtils.TENs, TestUtils.NINEs, TestUtils.FOURs)));
	private static final Hand flush2 = new Flush(new ArrayList<>(Arrays.asList(TestUtils.QUEENc, TestUtils.JACKc, TestUtils.TENc, TestUtils.FIVEc, TestUtils.FOURc)));
	
	private static final Hand full1 = new FullHouse(new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.ACEd, TestUtils.ACEh, TestUtils.FIVEs, TestUtils.FIVEd)));
	private static final Hand full2 = new FullHouse(new ArrayList<>(Arrays.asList(TestUtils.QUEENs, TestUtils.QUEENd, TestUtils.TENc, TestUtils.TENh, TestUtils.TENd)));
	
	private static final Hand fourOf1 = new FourOfAKind(new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.ACEh, TestUtils.ACEd, TestUtils.ACEc, TestUtils.EIGHTc)));
	private static final Hand fourOf2 = new FourOfAKind(new ArrayList<>(Arrays.asList(TestUtils.NINEd, TestUtils.SIXc, TestUtils.SIXd, TestUtils.SIXh, TestUtils.SIXs)));
	
	private static final Hand straightF1 = new StraightFlush(new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENc, TestUtils.JACKc, TestUtils.TENc, TestUtils.NINEc)));
	private static final Hand straightF2 = new StraightFlush(new ArrayList<>(Arrays.asList(TestUtils.EIGHTh, TestUtils.SEVENh, TestUtils.SIXh, TestUtils.FIVEh, TestUtils.FOURh)));
	
	private static final Hand royalF = new RoyalFlush(new ArrayList<>(Arrays.asList(TestUtils.ACEh, TestUtils.KINGh, TestUtils.QUEENh, TestUtils.JACKh, TestUtils.TENh)));
	
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