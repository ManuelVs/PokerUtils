package hja.logic;

import hja.logic.gui.components.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class GUIOption implements OptionMode {
	
	public void start(String... args) throws IOException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setSize(1400, 800);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
