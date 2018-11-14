package hja.logic.gui.components;

import hja.logic.gui.model.Model;
import hja.logic.gui.model.RangeListener;
import hja.pokerutils.Parser.RangeParser;
import hja.pokerutils.Range.Range;

import javax.swing.*;
import java.awt.*;

public class TextRange extends JPanel implements RangeListener{
	private Model model;
	
	private JTextField textField;
	
	public TextRange(Model model){
		this.model = model;
		initGUI();
		
	}
	
	private void initGUI() {
		
		this.textField = new JTextField();
		JLabel label = new JLabel("Range: ");
		label.setLabelFor(textField);
		
		JButton button = new JButton("Set");
		button.setFocusPainted(false);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(label);
		this.add(textField);
		this.add(button);
		textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		

		
		button.addActionListener((e) -> {
			Range range = RangeParser.parseRange(textField.getText());
			System.out.println(range.toString());
			this.model.setRange(range);
			});
		
		}
		
		
		@Override
		public void notify(Range range){
			this.textField.setText(range.toString());
		
		}

	
	}
