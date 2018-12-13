package hja.pokerutils.algorithm;

import hja.pokerutils.board.Player;
import hja.pokerutils.card.Card;
import hja.pokerutils.TestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class EquityCalculatorTest {
	
	private static Stream<Arguments> boardProvider() {
		return Stream.of(
			Arguments.of(
				new ArrayList<>(),
				new ArrayList<>(Arrays.asList(
					new Player(
						1,
						new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc))),
					new Player(
						2,
						new ArrayList<>(Arrays.asList(TestUtils.EIGHTd, TestUtils.EIGHTh))),
					new Player(
						3,
						new ArrayList<>(Arrays.asList(TestUtils.ACEd, TestUtils.ACEc))),
					new Player(
						4,
						new ArrayList<>(Arrays.asList(TestUtils.QUEENh, TestUtils.QUEENd))),
					new Player(
						5,
						new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGs))),
					new Player(
						6,
						new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENs)))
				)),
				new double[]{0.14085, 0.15144, 0.47012, 0.10537, 0.08738, 0.04485}),
			Arguments.of(
				new ArrayList<>(Arrays.asList(TestUtils.QUEENc, TestUtils.SIXs, TestUtils.EIGHTc)),
				new ArrayList<>(Arrays.asList(
					new Player(
						1,
						new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc))),
					new Player(
						2,
						new ArrayList<>(Arrays.asList(TestUtils.EIGHTd, TestUtils.EIGHTh))),
					new Player(
						3,
						new ArrayList<>(Arrays.asList(TestUtils.ACEd, TestUtils.ACEc))),
					new Player(
						4,
						new ArrayList<>(Arrays.asList(TestUtils.QUEENh, TestUtils.QUEENd))),
					new Player(
						5,
						new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGs))),
					new Player(
						6,
						new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENs)))
				)),
				new double[]{0.06456, 0.05405, 0.08408, 0.73273, 0.06306, 0.00150}),
			Arguments.of(
				new ArrayList<>(Arrays.asList(TestUtils.QUEENc, TestUtils.SIXs, TestUtils.EIGHTc, TestUtils.KINGd)),
				new ArrayList<>(Arrays.asList(
					new Player(
						1,
						new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc))),
					new Player(
						2,
						new ArrayList<>(Arrays.asList(TestUtils.EIGHTd, TestUtils.EIGHTh))),
					new Player(
						3,
						new ArrayList<>(Arrays.asList(TestUtils.ACEd, TestUtils.ACEc))),
					new Player(
						4,
						new ArrayList<>(Arrays.asList(TestUtils.QUEENh, TestUtils.QUEENd))),
					new Player(
						5,
						new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGs))),
					new Player(
						6,
						new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENs)))
				)),
				new double[]{0, 0.02778, 0.02778, 0.91667, 0, 0.02778}),
			Arguments.of(
				new ArrayList<>(Arrays.asList(TestUtils.QUEENc, TestUtils.SIXs, TestUtils.EIGHTc, TestUtils.KINGd, TestUtils.KINGh)),
				new ArrayList<>(Arrays.asList(
					new Player(
						1,
						new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc))),
					new Player(
						2,
						new ArrayList<>(Arrays.asList(TestUtils.EIGHTd, TestUtils.EIGHTh))),
					new Player(
						3,
						new ArrayList<>(Arrays.asList(TestUtils.ACEd, TestUtils.ACEc))),
					new Player(
						4,
						new ArrayList<>(Arrays.asList(TestUtils.QUEENh, TestUtils.QUEENd))),
					new Player(
						5,
						new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGs))),
					new Player(
						6,
						new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENs)))
				)),
				new double[]{0, 0, 0, 0, 0, 1}),
			Arguments.of(
				new ArrayList<>(),
				new ArrayList<>(Arrays.asList(
					new Player(
						1,
						new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc)))
				)),
				new double[]{1})
		);
	}
	
	@ParameterizedTest(name = "{index} => {1}")
	@MethodSource("boardProvider")
	void calculateEquityTest(ArrayList<Card> boardCards, ArrayList<Player> players, double[] equity) {
		double[] cEquity = EquityCalculator.calculateEquity(players, boardCards, new HoldEmAlgorithm());
		
		assertArrayEquals(equity, cEquity, 0.005);
	}
}