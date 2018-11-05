package hja.logic.gui.components;

import hja.logic.gui.model.Model;

import javax.swing.*;
import java.awt.*;

public class TextRange extends JPanel {
	
	public TextRange(Model model){
		initGUI();
	}
	
	private void initGUI() {
		JTextField textField = new JTextField();
		JLabel label = new JLabel("Range: ");
		label.setLabelFor(textField);
		
		JButton button = new JButton("Set");
		button.setFocusPainted(false);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(label);
		this.add(textField);
		this.add(button);
		textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
	}
}
