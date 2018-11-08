package hja.logic.gui.components;

import hja.logic.gui.model.Model;
import hja.logic.gui.model.PercentageListener;
import hja.logic.gui.model.RankingListener;
import hja.pokerutils.Ranking.Ranking;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageRange extends JPanel implements PercentageListener, RankingListener {
	private Model model;
	public PercentageRange(Model model){
		this.model = model;
		this.model.addRankingListener(this);
		this.model.addPercentageListener(this);
		initGUI();
	}
	
	private void initGUI() {
		JSlider slider = new JSlider(0, 100 * 100, JSlider.HORIZONTAL);
		JLabel label = new JLabel("0.0");
		label.setPreferredSize(new Dimension(40, 5));
		
		slider.addChangeListener((e) -> {
			double val = slider.getValue();
			label.setText(Double.toString(val / 100));
		});
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(slider);
		this.add(label);
		
		slider.setValue(0);
	}
	
	@Override
	public void notify(double percentage) {
	
	}
	
	@Override
	public void notify(Ranking ranking) {
	
	}
}
