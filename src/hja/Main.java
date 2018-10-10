package hja;

import hja.Card.Card;
import hja.Hand.Hand;
import hja.Parser.CardListParser;

import java.io.*;
import java.util.ArrayList;

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
		while (input.ready()) {
			ArrayList<Card> cards = CardListParser.parseListCard(input, 5);
			consumeLine(input);
			
			Hand bestHand = Algorithm.calculateHand(cards);
			ArrayList<Hand> draws = Algorithm.calculateDraws(cards);
			
			output.write(cardsToString(cards));
			output.write(System.lineSeparator());
			output.write(" - Best hand: ");
			output.write(bestHand.toString());
			output.write(System.lineSeparator());
			
			for (Hand draw : draws) {
				output.write(" - Draw: ");
				output.write(draw.toString());
				output.write(System.lineSeparator());
			}
			output.write(System.lineSeparator());
		}
	}
	
	private static void calculateBestHand(Reader input, Writer output) {
	
	}
	
	private static void sortPlayerHands(Reader input, Writer output) {
	
	}
	
	private static void calculateBestHandOmaha(Reader input, Writer output) {
	
	}
	
	private static String cardsToString(ArrayList<Card> cards) {
		StringBuilder sb = new StringBuilder();
		
		for (Card card : cards) {
			sb.append(card);
		}
		
		return sb.toString();
	}
	
	private static void consumeLine(Reader reader) throws IOException {
		char c = (char) reader.read();
		
		while (reader.ready() && c != '\n') {
			c = (char) reader.read();
		}
	}
}
