package hja.logic.gui.components;

import hja.logic.gui.model.Model;
import hja.logic.gui.model.ModelImpl;

import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {
	
	public MainWindow(Model model) throws IOException {
		super("HJA Equity calculator");
		
		PokerBoard board = new PokerBoard(model);
		this.add(board);
	}
}
