package hja;

import hja.Hand.Hand;
import hja.Parser.CardListParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String... args){
		int option = Integer.parseInt(args[0]);
		String inputFile = args[1];
		String outputFile = args[2];
		
	}
	
	private void calculateBests(String inputFile, String outputFile) {
		try{
			Scanner scanner = new Scanner(new FileReader(inputFile));
			
			ArrayList<Card> cards = CardListParser.parseListCard(scanner);
			Hand hand = Algorithm.calculateBestHand(cards);
			ArrayList<Hand> possibleHands = Algorithm.calculateBestPossibleHands(cards);
			// print hand and possibleHands
		}
		catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado: " + inputFile);
		}
	}
}
