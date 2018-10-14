package hja.logic;

import hja.pokerutils.Board.Board;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.HoldEmAlgorithm;
import hja.pokerutils.Parser.CardListParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HoldEmMultiplayerOption implements OptionMode {
	private static void classifyPlayers(Reader input, Writer output) throws IOException {
		Scanner scanner = new Scanner(input);
		while (scanner.hasNext()) {
			String line = scanner.next();
			String[] parts = line.split(";");
			
			int numPlayers = Integer.parseInt(parts[0]);
			
			StringReader stringReaderBoard = new StringReader(parts[parts.length - 1]);
			ArrayList<Card> boardCards = CardListParser.parseListCard(stringReaderBoard, 5);
			
			ArrayList<Player> players = new ArrayList<>();
			
			for (int i = 1; i <= numPlayers; ++i) {
				String playerName = parts[i].substring(0, 2);
				String pCards = parts[i].substring(2);
				StringReader stringReaderPlayer = new StringReader(pCards);
				
				ArrayList<Card> playerCards = CardListParser.parseListCard(stringReaderPlayer, 2);
				ArrayList<Card> allCards = new ArrayList<>();
				allCards.addAll(playerCards);
				allCards.addAll(boardCards);
				
				Hand bestHand = HoldEmAlgorithm.calculateHand(allCards);
				
				players.add(new Player(playerName, bestHand, playerCards));
			}
			players.sort(Collections.reverseOrder());
			Board board = new Board(boardCards, players);
			
			
			output.write(line);
			output.write(System.lineSeparator());
			for (Player p : players) {
				output.write(p.toString());
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
		
		classifyPlayers(reader, writer);
		
		reader.close();
		writer.close();
	}
	
	
}
