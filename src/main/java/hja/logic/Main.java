package hja.logic;


import hja.logic.gui.components.ConfigWindow;
import hja.logic.gui.components.MainWindow;
import hja.logic.gui.model.Model;
import hja.logic.gui.model.ModelImpl;

import javax.swing.*;
import java.io.IOException;

public class Main {
	
	public static void main(String... args) throws IOException {
		Model model = new ModelImpl();
		
		ConfigWindow configWindow = new ConfigWindow(model);
		configWindow.setSize(525, 580);
		configWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		MainWindow mainWindow = new MainWindow(model);
		mainWindow.setSize(1400, 800);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		mainWindow.setVisible(true);
		configWindow.setVisible(true);
	}
	
}
