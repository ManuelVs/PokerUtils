package hja.logic.gui.components;

import hja.logic.gui.model.CombosListener;
import hja.logic.gui.model.Model;
import hja.pokerutils.Algorithm.CombosAlgorithm;

import javax.swing.*;

public class CombosPanel extends JPanel implements CombosListener {
	private JSlider royalFlush;
	private JSlider straightFlush;
	private JSlider fourOfAKind;
	private JSlider fullHouse;
	private JSlider flush;
	private JSlider straight;
	private JSlider threeOfAKind;
	private JSlider twoPair;
	private JSlider overPair;
	private JSlider topPair;
	private JSlider pocketPairBelowTopPair;
	private JSlider middlePair;
	private JSlider weakPair;
	private JSlider noHandMade;
	
	private JTextArea combosArea;
	
	public CombosPanel(Model model){
		model.addCombosListener(this);
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		combosArea = new JTextArea();
		
		this.add(combosArea);
		
		/*
		this.royalFlush = new JSlider();
		JLabel labelRoyalFlush = new JLabel("Royal flush");
		labelRoyalFlush.setLabelFor(this.royalFlush);
		
		this.straightFlush = new JSlider();
		JLabel labelStraightFlush = new JLabel("Straight");
		labelStraightFlush.setLabelFor(this.straightFlush);
		
		this.fourOfAKind = new JSlider();
		JLabel labelFourOfAKind = new JLabel("Four of a kind");
		labelFourOfAKind.setLabelFor(this.fourOfAKind);
		
		this.fullHouse = new JSlider();
		JLabel labelFullHouse = new JLabel("Full House");
		labelFullHouse.setLabelFor(this.fullHouse);
		
		this.flush = new JSlider();
		JLabel labelFlush = new JLabel("Flush");
		labelFlush.setLabelFor(this.flush);
		
		this.straight = new JSlider();
		JLabel labelStraight = new JLabel("Straight");
		labelStraight.setLabelFor(this.straight);
		
		this.threeOfAKind = new JSlider();
		JLabel labelThreeOfAKind = new JLabel("Three of a kind");
		labelThreeOfAKind.setLabelFor(this.threeOfAKind);
		
		this.twoPair = new JSlider();
		JLabel labelTwoPair = new JLabel("Two pair");
		labelTwoPair.setLabelFor(this.twoPair);
		
		this.overPair = new JSlider();
		JLabel labelOverPair = new JLabel("Over pair");
		labelOverPair.setLabelFor(this.overPair);
		//--
		this.topPair = new JSlider();
		JLabel labelTopPair = new JLabel("Top pair");
		labelTopPair.setLabelFor(this.topPair);
		
		this.pocketPairBelowTopPair = new JSlider();
		JLabel labelPocketPairBelowTopPair = new JLabel("PP below tp");
		labelPocketPairBelowTopPair.setLabelFor(this.pocketPairBelowTopPair);
		
		this.middlePair = new JSlider();
		JLabel labelMiddlePair = new JLabel("Middle pair");
		labelMiddlePair.setLabelFor(this.middlePair);
		
		this.weakPair = new JSlider();
		JLabel labelWeakPair = new JLabel("Weak pair");
		labelWeakPair.setLabelFor(this.weakPair);
		
		this.noHandMade = new JSlider();
		JLabel noHandMade = new JLabel("No hand made");
		noHandMade.setLabelFor(this.noHandMade);
		
		
		this.add(this.royalFlush);
		this.add(this.straightFlush);
		this.add(this.fourOfAKind);
		this.add(this.fullHouse);
		this.add(this.flush);
		this.add(this.straight);
		this.add(this.threeOfAKind);
		this.add(this.twoPair);
		this.add(this.overPair);
		this.add(this.topPair);
		this.add(this.pocketPairBelowTopPair);
		this.add(this.middlePair);
		this.add(this.weakPair);
		this.add(this.noHandMade);
		*/
	}
	
	@Override
	public void notify(CombosAlgorithm.Combos combos) {
		this.combosArea.setText(combos.toString());
		/*
		royalFlush.setValue(combos.royalFlush);
		straightFlush.setValue(combos.straightFlush);
		fourOfAKind.setValue(combos.fourOfAKind);
		fullHouse.setValue(combos.fullHouse);
		flush.setValue(combos.flush);
		straight.setValue(combos.straight);
		threeOfAKind.setValue(combos.threeOfAKind);
		twoPair.setValue(combos.twoPair);
		overPair.setValue(combos.overPair);
		topPair.setValue(combos.topPair);
		pocketPairBelowTopPair.setValue(combos.pocketPairBelowTopPair);
		middlePair.setValue(combos.middlePair);
		weakPair.setValue(combos.weakPair);
		noHandMade.setValue(combos.noHandMade);
		*/
	}
}
