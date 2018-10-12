package hja;

import hja.pokerutils.HoldEmAlgorithm;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.OmahaAlgorithm;
import hja.pokerutils.Parser.CardListParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String... args) {
		int option = Integer.parseInt(args[0]);
		String inputFile = args[1];
		String outputFile = args[2];
		
		try {
			FileReader reader = new FileReader(inputFile);
			FileWriter writer = new FileWriter(outputFile);
			
			switch (option) {
				case 1:
					classifyHand(reader, writer);
					break;
				case 2:
					calculateBestHand(reader, writer);
					break;
				case 3:
					sortPlayerHands(reader, writer);
					break;
				case 4:
					calculateBestHandOmaha(reader, writer);
					break;
			}
			
			reader.close();
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void classifyHand(Reader input, Writer output) throws IOException {
		Scanner scanner = new Scanner(input);
		while (scanner.hasNext()) {
			String line = scanner.next();
			StringReader stringReader = new StringReader(line);
			
			ArrayList<Card> cards = CardListParser.parseListCard(stringReader, 5);
			
			Hand bestHand = HoldEmAlgorithm.calculateHand(cards);
			boolean[] draws = HoldEmAlgorithm.calculateDraws(cards);
			
			output.write(cardsToString(cards));
			output.write(System.lineSeparator());
			output.write(" - Best hand: ");
			output.write(bestHand.toString());
			output.write(System.lineSeparator());
			
			if (draws[0]) {
				output.write(" - Draw: Straight open ended");
				output.write(System.lineSeparator());
			}
			else if (draws[1]) {
				output.write(" - Draw: Straight Gutshot");
				output.write(System.lineSeparator());
			}
			if (draws[2]) {
				output.write(" - Draw: Flush");
				output.write(System.lineSeparator());
			}
			
			output.write(System.lineSeparator());
		}
	}
	
	private static void calculateBestHand(Reader input, Writer output) {
	
	}
	
	private static void sortPlayerHands(Reader input, Writer output) {
	
	}
	
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
	
	private static String cardsToString(ArrayList<Card> cards) {
		StringBuilder sb = new StringBuilder();
		
		for (Card card : cards) {
			sb.append(card);
		}
		
		return sb.toString();
	}
}
