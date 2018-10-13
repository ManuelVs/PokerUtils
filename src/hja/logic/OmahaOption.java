package hja.logic;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.OmahaAlgorithm;
import hja.pokerutils.Parser.CardListParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OmahaOption implements OptionMode {
	private static void calculateBestHandOmaha(Reader input, Writer output) throws IOException {
		Scanner scanner = new Scanner(input);
		while (scanner.hasNext()) {
			String line = scanner.next();
			StringReader stringReader = new StringReader(line);
			
			ArrayList<Card> playerCards = CardListParser.parseListCard(stringReader, 4);
			
			stringReader.read(); // ';' char
			int cNumCards = stringReader.read(); // numCards
			stringReader.read(); // ';' char
			
			int numCards = Integer.parseInt((char) cNumCards + "");
			ArrayList<Card> boardCards = CardListParser.parseListCard(stringReader, numCards);
			
			Hand bestHand = OmahaAlgorithm.calculateHand(playerCards, boardCards);
			
			output.write(line);
			output.write(System.lineSeparator());
			output.write(" - Best hand: ");
			output.write(bestHand.toString());
			output.write(System.lineSeparator());
			output.write(System.lineSeparator());
		}
	}
	
	@Override
	public void start(String... args) throws IOException {
		String inputFile = args[1];
		String outputFile = args[2];
		
		FileReader reader = new FileReader(inputFile);
		FileWriter writer = new FileWriter(outputFile);
		
		calculateBestHandOmaha(reader, writer);
		
		reader.close();
		writer.close();
	}
}
