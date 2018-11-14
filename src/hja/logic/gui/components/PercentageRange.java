package hja.logic.gui.components;

import hja.logic.gui.model.Model;
import hja.pokerutils.Parser.RankingParser;
import hja.pokerutils.Ranking.Ranking;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PercentageRange extends JPanel {
	private Model model;
	private Ranking ranking;
	private double percentage;
	
	public PercentageRange(Model model){
		this.model = model;
		this.ranking = new Ranking();
		this.percentage = 0.0;
		initGUI();
	}
	
	private void initGUI() {
		JButton openRankingChooser = new JButton("...");
		JFileChooser rankingChooser = new JFileChooser("./data/rankings/");
		JLabel rankingLabel = new JLabel("Sklansky-Chubukov");
		JSlider slider = new JSlider(0, 100 * 100, JSlider.HORIZONTAL);
		JLabel percentageLabel = new JLabel("0.0");
		percentageLabel.setPreferredSize(new Dimension(40, 5));
		
		slider.addChangeListener((e) -> {
			double val = slider.getValue();
			this.percentage = val / 100;
			percentageLabel.setText(Double.toString(val / 100));
			
			
			model.setRange(ranking.selectTopCardPairs(percentage));
		});
		
		openRankingChooser.addActionListener((e) -> {
			rankingChooser.showOpenDialog((Component) e.getSource());
		});
		rankingChooser.addActionListener((e) -> {
			try {
				File f = rankingChooser.getSelectedFile();
				String name = f.getName();
				updateRanking(f);
				rankingLabel.setText(name.substring(0, name.length() - 4));
				model.setRange(ranking.selectTopCardPairs(percentage));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		File f = new File("./data/rankings/Sklansky-Chubukov.txt");
		try {
			updateRanking(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.model.setRange(this.ranking.selectTopCardPairs(this.percentage));
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(openRankingChooser);
		this.add(rankingLabel);
		this.add(slider);
		this.add(percentageLabel);
		
		slider.setValue(0);
	}
	
	private void updateRanking(File file) throws IOException {
		Reader reader = new FileReader(file);
		this.ranking = RankingParser.parseRanking(reader, (int) file.length());
	}
}
