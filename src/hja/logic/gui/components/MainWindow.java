package hja.logic.gui.components;

import hja.logic.gui.model.PokerModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainWindow extends JFrame {
	
	public MainWindow() throws IOException {
		initGUI();
	}
	
	private void initGUI() throws IOException {
		PokerModel model = new PokerModel();
		PokerBoard pokerBoard = new PokerBoard();
		UtilsPanel utilsPanel = new UtilsPanel(model);
		model.addListener(utilsPanel);
		model.addListener(pokerBoard);
		this.setLayout(new BorderLayout());
		this.add(pokerBoard, BorderLayout.CENTER);
		this.add(utilsPanel, BorderLayout.EAST);
	}
}
