package hja.logic.gui.components;

import hja.logic.gui.model.Model;
import hja.logic.gui.model.RangeListener;
import hja.pokerutils.Parser.RangeParser;
import hja.pokerutils.Range.Range;

import javax.swing.*;
import java.awt.*;

public class TextRange extends JPanel implements RangeListener{
	private Model model;
	private JTextField rangeTextField;
	
	private Range range;
	
	public TextRange(Model model){
		this.model = model;
		this.model.addRangeListener(this);
		initGUI();
	}
	
	private void initGUI() {
		this.rangeTextField = new JTextField();
		JLabel label = new JLabel("Range: ");
		label.setLabelFor(rangeTextField);
		
		JButton button = new JButton("Set");
		button.setFocusPainted(false);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(label);
		this.add(rangeTextField);
		this.add(button);
		rangeTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		
		button.addActionListener((e) -> {
			TextRange.this.range = RangeParser.parseRange(rangeTextField.getText());
			this.model.setRange(range);
		});
	}
	
	
	@Override
	public void notify(Range range){
		if(range != this.range) {
			this.rangeTextField.setText(range.toString());
		}
	}
}
