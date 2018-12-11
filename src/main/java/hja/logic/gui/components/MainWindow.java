package hja.logic.gui.components;

import hja.logic.gui.model.Model;
import hja.logic.gui.model.ModelImpl;

import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {
	
	public MainWindow() {
		initGUI();
	}
	
	private void initGUI() {
		Model model = new ModelImpl();
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Archivo");
		JMenuItem openFileItem = new JMenuItem("Abrir");
		JMenuItem settingsItem = new JMenuItem("Configuración");
		
		fileMenu.add(openFileItem);
		fileMenu.add(settingsItem);
		menuBar.add(fileMenu);
		
		this.setTitle("HJA Equity calculator");
		
		PokerBoard board;
		
		try {
			board = new PokerBoard();
			ConfigWindow nextPhaseWindow = new ConfigWindow(model);
			nextPhaseWindow.setVisible(true);
			
			
			JButton nextPhaseButton = new JButton("Siguiente fase");
			nextPhaseButton.setAlignmentX(RIGHT_ALIGNMENT);
			nextPhaseButton.setFocusPainted(false);
			
			nextPhaseButton.addActionListener(e -> {

			});
			
			JPanel boardPanel = new JPanel();
			boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
			boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			
			boardPanel.add(nextPhaseButton);
			boardPanel.add(board);
			
			this.setJMenuBar(menuBar);
			this.add(boardPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
