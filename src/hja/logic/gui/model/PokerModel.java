package hja.logic.gui.model;

import hja.pokerutils.Board.Board;
import hja.pokerutils.Parser.BoardParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class PokerModel implements Model {
	private ArrayList<ListChangeListener> listListeners;
	private ArrayList<BoardChangeListener> boardListeners;
	private ArrayList<Board> boardList;
	
	public PokerModel() {
		this.listListeners = new ArrayList<>();
		this.boardListeners = new ArrayList<>();
		this.boardList = new ArrayList<>();
	}
	
	public void addListener(ListChangeListener listener) {
		this.listListeners.add(listener);
	}
	
	public void addListener(BoardChangeListener listener) {
		this.boardListeners.add(listener);
	}
	
	public void changeFile(String path) throws IOException {
		FileReader reader = new FileReader(path);
		this.boardList.clear();
		while (reader.ready()) {
			Board board = BoardParser.parseBoard(reader);
			consumeLine(reader);
			this.boardList.add(board);
		}
		
		for (ListChangeListener listener : listListeners) {
			listener.notify(boardList);
		}
	}
	
	public void selectBoard(int index) {
		for (BoardChangeListener listener : boardListeners) {
			listener.notify(this.boardList.get(index));
		}
	}
	
	private void consumeLine(Reader reader) throws IOException {
		int c = 0;
		while(reader.ready() && c != '\n') {
			c = reader.read();
		}
	}
}
