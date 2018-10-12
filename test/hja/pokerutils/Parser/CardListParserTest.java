package hja.pokerutils.Parser;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardListParserTest {
	private static Stream<Arguments> cardListsProvider() {
		return Stream.of(
				Arguments.of("AhAcQhJhTh", 5, new ArrayList<>(Arrays.asList(
						Utils.ACEh,
						Utils.ACEc,
						Utils.QUEENh,
						Utils.JACKh,
						Utils.TENh
				))),
				
				Arguments.of("As2s3h4dJd", 5, new ArrayList<>(Arrays.asList(
						Utils.ACEs,
						Utils.TWOs,
						Utils.THREEh,
						Utils.FOURd,
						Utils.JACKd
				))),
				
				Arguments.of("AhAcAs4dJd", 5, new ArrayList<>(Arrays.asList(
						Utils.ACEh,
						Utils.ACEc,
						Utils.ACEs,
						Utils.FOURd,
						Utils.JACKd
				))),
				
				Arguments.of("5dKs6cTh9h", 5, new ArrayList<>(Arrays.asList(
						Utils.FIVEd,
						Utils.KINGs,
						Utils.SIXc,
						Utils.TENh,
						Utils.NINEh
				))),
				
				Arguments.of("2h2d2c2s", 4, new ArrayList<>(Arrays.asList(
						Utils.TWOh,
						Utils.TWOd,
						Utils.TWOc,
						Utils.TWOs
				)))
		);
	}
	
	@ParameterizedTest(name = "{index} => {0}")
	@MethodSource("cardListsProvider")
	void parseListCardTest(String sCards, int numCards, ArrayList<Card> aCards) throws IOException {
		StringReader reader = new StringReader(sCards);
		ArrayList<Card> cards = CardListParser.parseListCard(reader, numCards);
		
		assertEquals(aCards, cards);
	}
	
}