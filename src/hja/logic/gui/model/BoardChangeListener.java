package hja.logic.gui.model;

import hja.pokerutils.Board.Board;

public interface BoardChangeListener {
	void notify(Board board);
}