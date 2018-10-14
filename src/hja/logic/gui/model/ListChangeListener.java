package hja.logic.gui.model;

import hja.pokerutils.Board.Board;

import java.util.ArrayList;

public interface ListChangeListener {
	void notify(ArrayList<Board> items);
}