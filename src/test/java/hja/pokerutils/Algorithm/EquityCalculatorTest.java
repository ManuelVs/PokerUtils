package hja.pokerutils.Algorithm;

import hja.pokerutils.Board.Board;
import hja.pokerutils.Board.Player;
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
				new Board(
					new ArrayList<>(),
					new ArrayList<>(Arrays.asList(
						new Player(
							"J1",
							new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc))),
						new Player(
							"J2",
							new ArrayList<>(Arrays.asList(TestUtils.EIGHTd, TestUtils.EIGHTh))),
						new Player(
							"J3",
							new ArrayList<>(Arrays.asList(TestUtils.ACEd, TestUtils.ACEc))),
						new Player(
							"J4",
							new ArrayList<>(Arrays.asList(TestUtils.QUEENh, TestUtils.QUEENd))),
						new Player(
							"J5",
							new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGs))),
						new Player(
							"J6",
							new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENs)))
					))
				),
				new double[]{0.14085, 0.15144, 0.47012, 0.10537, 0.08738, 0.04485}),
			Arguments.of(
				new Board(
					new ArrayList<>(Arrays.asList(TestUtils.QUEENc, TestUtils.SIXs, TestUtils.EIGHTc)),
					new ArrayList<>(Arrays.asList(
						new Player(
							"J1",
							new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc))),
						new Player(
							"J2",
							new ArrayList<>(Arrays.asList(TestUtils.EIGHTd, TestUtils.EIGHTh))),
						new Player(
							"J3",
							new ArrayList<>(Arrays.asList(TestUtils.ACEd, TestUtils.ACEc))),
						new Player(
							"J4",
							new ArrayList<>(Arrays.asList(TestUtils.QUEENh, TestUtils.QUEENd))),
						new Player(
							"J5",
							new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGs))),
						new Player(
							"J6",
							new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENs)))
					))
				),
				new double[]{0.06456, 0.05405, 0.08408, 0.73273, 0.06306, 0.00150}),
			Arguments.of(
				new Board(
					new ArrayList<>(Arrays.asList(TestUtils.QUEENc, TestUtils.SIXs, TestUtils.EIGHTc, TestUtils.KINGd)),
					new ArrayList<>(Arrays.asList(
						new Player(
							"J1",
							new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc))),
						new Player(
							"J2",
							new ArrayList<>(Arrays.asList(TestUtils.EIGHTd, TestUtils.EIGHTh))),
						new Player(
							"J3",
							new ArrayList<>(Arrays.asList(TestUtils.ACEd, TestUtils.ACEc))),
						new Player(
							"J4",
							new ArrayList<>(Arrays.asList(TestUtils.QUEENh, TestUtils.QUEENd))),
						new Player(
							"J5",
							new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGs))),
						new Player(
							"J6",
							new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENs)))
					))
				),
				new double[]{0, 0.02778, 0.02778, 0.91667, 0, 0.02778}),
			Arguments.of(
				new Board(
					new ArrayList<>(Arrays.asList(TestUtils.QUEENc, TestUtils.SIXs, TestUtils.EIGHTc, TestUtils.KINGd, TestUtils.KINGh)),
					new ArrayList<>(Arrays.asList(
						new Player(
							"J1",
							new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc))),
						new Player(
							"J2",
							new ArrayList<>(Arrays.asList(TestUtils.EIGHTd, TestUtils.EIGHTh))),
						new Player(
							"J3",
							new ArrayList<>(Arrays.asList(TestUtils.ACEd, TestUtils.ACEc))),
						new Player(
							"J4",
							new ArrayList<>(Arrays.asList(TestUtils.QUEENh, TestUtils.QUEENd))),
						new Player(
							"J5",
							new ArrayList<>(Arrays.asList(TestUtils.ACEs, TestUtils.KINGs))),
						new Player(
							"J6",
							new ArrayList<>(Arrays.asList(TestUtils.KINGc, TestUtils.QUEENs)))
					))
				),
				new double[]{0, 0, 0, 0, 0, 1}),
			Arguments.of(
				new Board(
					new ArrayList<>(),
					new ArrayList<>(Arrays.asList(
						new Player(
							"J1",
							new ArrayList<>(Arrays.asList(TestUtils.SIXd, TestUtils.SEVENc)))
					))
				),
				new double[]{1})
		);
	}
	
	@ParameterizedTest(name = "{index} => {1}")
	@MethodSource("boardProvider")
	void calculateEquityTest(Board board, double[] equity) {
		double[] cEquity = EquityCalculator.calculateEquity(board);
		
		assertArrayEquals(equity, cEquity, 0.005);
	}
}