package hja.logic.gui.components;

import hja.logic.gui.model.Model;
import hja.logic.gui.model.ModelImpl;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
	
	public MainWindow() {
		initGUI();
	}
	
	private void initGUI() {
		Model model = new ModelImpl();
		CardMatrix rangeMatrix = new CardMatrix(model);
		PercentageRange percentageRange = new PercentageRange(model);
		TextRange rangeText = new TextRange(model);
		BoardCards boardCards = new BoardCards(model);
		CombosPanel combosPanel = new CombosPanel(model);
		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		leftPanel.add(rangeText);
		leftPanel.add(rangeMatrix);
		leftPanel.add(percentageRange);

		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(1, 2));
		
		boardCards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		combosPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rightPanel.add(boardCards);
		rightPanel.add(combosPanel);
		
		this.setLayout(new GridLayout(1, 2));
		//this.rootPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.add(leftPanel);
		this.add(rightPanel);
	}
}
