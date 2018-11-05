package hja.logic;


import hja.logic.gui.components.MainWindow;

import javax.swing.*;

public class Main {
	
	public static void main(String... args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setSize(1400, 800);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
}
