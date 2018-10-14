package hja.logic;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.HoldEmAlgorithm;
import hja.pokerutils.Parser.CardListParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HoldEmOption implements OptionMode {
	private static void classifyHand(Reader input, Writer output) throws IOException {
		Scanner scanner = new Scanner(input);
		while (scanner.hasNext()) {
			String line = scanner.next();
			String[] parts = line.split(";");
			StringReader stringReaderPlayer = new StringReader(parts[0]);
			int numBoardCards = Integer.parseInt(parts[1]);
			StringReader stringReaderBoard = new StringReader(parts[2]);
			
			ArrayList<Card> playerCards = CardListParser.parseListCard(stringReaderPlayer, 2);
			ArrayList<Card> boardCards = CardListParser.parseListCard(stringReaderBoard, numBoardCards);
			ArrayList<Card> allCards = new ArrayList<Card>();
			allCards.addAll(playerCards);
			allCards.addAll(boardCards);
			
			Hand bestHand = HoldEmAlgorithm.calculateHand(allCards);
			boolean[] draws = HoldEmAlgorithm.calculateDraws(allCards);
			
			output.write(line);
			output.write(System.lineSeparator());
			output.write(" - Best hand: ");
			output.write(bestHand.toString());
			output.write(" with ");
			output.write(Utils.cardsToString(bestHand.getHand()));
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
	
	@Override
	public void start(String... args) throws IOException {
		String inputFile = args[1];
		String outputFile = args[2];
		
		FileReader reader = new FileReader(inputFile);
		FileWriter writer = new FileWriter(outputFile);
		
		classifyHand(reader, writer);
		
		reader.close();
		writer.close();
	}
}
