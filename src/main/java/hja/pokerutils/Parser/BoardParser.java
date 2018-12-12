package hja.pokerutils.Parser;

import hja.pokerutils.Board.Board;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;

public final class BoardParser {
	
	public static Board parseBoard(Reader reader) throws IOException {
		int cNumPlayers = reader.read();
		int numPlayers = charToInt((char) cNumPlayers);
		
		reader.read();
		
		char[] cPlayersAndBoard = new char[7 * numPlayers + 5 * 2];
		reader.read(cPlayersAndBoard);
		
		String playersAndBoard = new String(cPlayersAndBoard);
		String[] parts = playersAndBoard.split(";");
		
		StringReader stringReaderBoard = new StringReader(parts[parts.length - 1]);
		ArrayList<Card> boardCards = CardListParser.parseListCard(stringReaderBoard, 5);
		
		ArrayList<Player> players = new ArrayList<>();
		
		for (int i = 0; i < numPlayers; ++i) {
			String playerName = parts[i].substring(0, 2);
			String pCards = parts[i].substring(2);
			StringReader stringReaderPlayer = new StringReader(pCards);
			
			ArrayList<Card> playerCards = CardListParser.parseListCard(stringReaderPlayer, 2);
			ArrayList<Card> allCards = new ArrayList<>();
			allCards.addAll(playerCards);
			allCards.addAll(boardCards);
			
			//Hand bestHand = classifier.calculateHand(allCards);
			
			players.add(new Player(Integer.parseInt(playerName.substring(1)), playerCards));
		}
		players.sort(Collections.reverseOrder());
		return new Board(boardCards, players);
	}
	
	private static int charToInt(char c) throws IOException {
		if ('0' <= c && c <= '9') {
			return c - '0';
		}
		else throw new IOException("Bad number: " + c);
	}
	
}
