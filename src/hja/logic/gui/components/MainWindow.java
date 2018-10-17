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
		this.setLayout(new BorderLayout());
		
		PokerModel model = new PokerModel();
		UtilsPanel utilsPanel = new UtilsPanel(model);
		PokerBoard pokerBoard = new PokerBoard();
		model.addListener(pokerBoard);
		model.addListener(utilsPanel);
		
		utilsPanel.setBorder(BorderFactory.createTitledBorder("Use cases: "));
		
		this.add(utilsPanel, BorderLayout.EAST);
		this.add(pokerBoard, BorderLayout.CENTER);
	}
}
