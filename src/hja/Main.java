package hja;

import hja.Card.Card;
import hja.Hand.Hand;
import hja.Parser.CardListParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String... args) {
		int option = Integer.parseInt(args[0]);
		String inputFile = args[1];
		String outputFile = args[2];
	}
	
	private void calculateBests(String inputFile, String outputFile) {
		try {
			FileReader fileReader = new FileReader(inputFile);
			
			ArrayList<Card> cards = CardListParser.parseListCard(fileReader, 5);
			Hand hand = Algorithm.calculateBestHand(cards);
			ArrayList<Hand> possibleHands = Algorithm.calculateBestPossibleHands(cards);
			// print hand and possibleHands
		}
		catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado: " + inputFile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
