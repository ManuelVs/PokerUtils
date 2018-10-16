package hja.logic;

import hja.pokerutils.Board.Board;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Parser.BoardParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HoldEmMultiplayerOption implements OptionMode {
	
	@Override
	public void start(String... args) throws IOException {
		String inputFile = args[1];
		String outputFile = args[2];
		
		FileReader reader = new FileReader(inputFile);
		FileWriter writer = new FileWriter(outputFile);
		
		orderPlayers(reader, writer);
		
		reader.close();
		writer.close();
	}
	
	private static void orderPlayers(Reader input, Writer output) throws IOException {
		Scanner scanner = new Scanner(input);
		while (scanner.hasNext()) {
			String line = scanner.next();
			Board board = BoardParser.parseBoard(new StringReader(line));
			ArrayList<Player> players = board.players;
			
			output.write(line);
			output.write(System.lineSeparator());
			for (Player player : players) {
				output.write(player.toString());
				output.write(System.lineSeparator());
			}
			output.write(System.lineSeparator());
		}
	}
}
