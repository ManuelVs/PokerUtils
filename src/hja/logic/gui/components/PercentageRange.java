package hja.logic.gui.components;

import hja.logic.gui.model.Model;

import javax.swing.*;

public class PercentageRange extends JPanel {
	
	public PercentageRange(Model model){
		initGUI();
	}
	
	private void initGUI() {
		JSlider slider = new JSlider(0, 100 * 100, JSlider.HORIZONTAL);
		JLabel label = new JLabel("0.0");
		
		slider.addChangeListener((e) -> {
			double val = slider.getValue();
			label.setText(Double.toString(val / 100));
		});
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(slider);
		this.add(label);
		
		slider.setValue(0);
	}
}
