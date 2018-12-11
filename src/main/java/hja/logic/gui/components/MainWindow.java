package hja.logic.gui.components;

import hja.logic.gui.model.Model;
import hja.logic.gui.model.ModelImpl;

import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {
	
	public MainWindow() {
		super("HJA Equity calculator");
		initGUI();
	}
	
	private void initGUI() {
		Model model = new ModelImpl();
		

		try {
			PokerBoard board = new PokerBoard(model);
			ConfigWindow configWindow = new ConfigWindow(model);
			configWindow.setVisible(true);

			this.add(board);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
