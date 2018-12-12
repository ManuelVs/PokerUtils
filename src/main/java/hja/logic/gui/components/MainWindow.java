package hja.logic.gui.components;

import hja.logic.gui.model.Model;

import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = -4528857981366651114L;
	
	public MainWindow(Model model) throws IOException {
		super("HJA Equity calculator");
		
		PokerBoard board = new PokerBoard(model);
		this.add(board);
	}
}
