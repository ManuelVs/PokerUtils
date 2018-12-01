package hja.logic.gui.components;

import hja.logic.gui.model.CombosListener;
import hja.logic.gui.model.Model;
import hja.pokerutils.Algorithm.Combos;

import javax.swing.*;
import java.awt.*;

public class CombosPanel extends JPanel implements CombosListener {
	private JLabel royalFlush;
	private JLabel straightFlush;
	private JLabel fourOfAKind;
	private JLabel fullHouse;
	private JLabel flush;
	private JLabel straight;
	private JLabel threeOfAKind;
	private JLabel twoPair;
	private JLabel overPair;
	private JLabel topPair;
	private JLabel pocketPairBelowTopPair;
	private JLabel middlePair;
	private JLabel weakPair;
	private JLabel noHandMade;
	
	private JLabel gutshotDraw;
	private JLabel openEndedDraw;
	private JLabel flushDraw;
	
	public CombosPanel(Model model) {
		model.addCombosListener(this);
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new GridLayout(17, 2));
		
		
		this.royalFlush = new JLabel();
		JLabel labelRoyalFlush = new JLabel("Royal flush");
		labelRoyalFlush.setLabelFor(this.royalFlush);
		
		this.straightFlush = new JLabel();
		JLabel labelStraightFlush = new JLabel("Straight");
		labelStraightFlush.setLabelFor(this.straightFlush);
		
		this.fourOfAKind = new JLabel();
		JLabel labelFourOfAKind = new JLabel("Four of a kind");
		labelFourOfAKind.setLabelFor(this.fourOfAKind);
		
		this.fullHouse = new JLabel();
		JLabel labelFullHouse = new JLabel("Full House");
		labelFullHouse.setLabelFor(this.fullHouse);
		
		this.flush = new JLabel();
		JLabel labelFlush = new JLabel("Flush");
		labelFlush.setLabelFor(this.flush);
		
		this.straight = new JLabel();
		JLabel labelStraight = new JLabel("Straight");
		labelStraight.setLabelFor(this.straight);
		
		this.threeOfAKind = new JLabel();
		JLabel labelThreeOfAKind = new JLabel("Three of a kind");
		labelThreeOfAKind.setLabelFor(this.threeOfAKind);
		
		this.twoPair = new JLabel();
		JLabel labelTwoPair = new JLabel("Two pair");
		labelTwoPair.setLabelFor(this.twoPair);
		
		this.overPair = new JLabel();
		JLabel labelOverPair = new JLabel("Over pair");
		labelOverPair.setLabelFor(this.overPair);
		//--
		this.topPair = new JLabel();
		JLabel labelTopPair = new JLabel("Top pair");
		labelTopPair.setLabelFor(this.topPair);
		
		this.pocketPairBelowTopPair = new JLabel();
		JLabel labelPocketPairBelowTopPair = new JLabel("Pocket Pair below top pair");
		labelPocketPairBelowTopPair.setLabelFor(this.pocketPairBelowTopPair);
		
		this.middlePair = new JLabel();
		JLabel labelMiddlePair = new JLabel("Middle pair");
		labelMiddlePair.setLabelFor(this.middlePair);
		
		this.weakPair = new JLabel();
		JLabel labelWeakPair = new JLabel("Weak pair");
		labelWeakPair.setLabelFor(this.weakPair);
		
		this.noHandMade = new JLabel();
		JLabel labelNoHandMade = new JLabel("No hand made");
		labelNoHandMade.setLabelFor(this.noHandMade);
		
		this.gutshotDraw = new JLabel();
		JLabel labelGutshotDraw = new JLabel("Gutshot draw");
		labelMiddlePair.setLabelFor(this.gutshotDraw);
		
		this.openEndedDraw = new JLabel();
		JLabel labelOpenEndedDraw = new JLabel("Open-ended draw");
		labelWeakPair.setLabelFor(this.openEndedDraw);
		
		this.flushDraw = new JLabel();
		JLabel labelFlushDraw = new JLabel("Flush draw");
		labelNoHandMade.setLabelFor(this.flushDraw);
		
		
		this.add(labelRoyalFlush);
		this.add(this.royalFlush);
		
		this.add(labelStraightFlush);
		this.add(this.straightFlush);
		
		this.add(labelFourOfAKind);
		this.add(this.fourOfAKind);
		
		this.add(labelFullHouse);
		this.add(this.fullHouse);
		
		this.add(labelFlush);
		this.add(this.flush);
		
		this.add(labelStraight);
		this.add(this.straight);
		
		this.add(labelThreeOfAKind);
		this.add(this.threeOfAKind);
		
		this.add(labelTwoPair);
		this.add(this.twoPair);
		
		this.add(labelOverPair);
		this.add(this.overPair);
		
		this.add(labelTopPair);
		this.add(this.topPair);
		
		this.add(labelPocketPairBelowTopPair);
		this.add(this.pocketPairBelowTopPair);
		
		this.add(labelMiddlePair);
		this.add(this.middlePair);
		
		this.add(labelWeakPair);
		this.add(this.weakPair);
		
		this.add(labelNoHandMade);
		this.add(this.noHandMade);
		
		this.add(labelGutshotDraw);
		this.add(this.gutshotDraw);
		
		this.add(labelOpenEndedDraw);
		this.add(this.openEndedDraw);
		
		this.add(labelFlushDraw);
		this.add(this.flushDraw);
	}
	
	@Override
	public void notify(Combos combos) {
		royalFlush.setText(Integer.toString(combos.royalFlush));
		straightFlush.setText(Integer.toString(combos.straightFlush));
		fourOfAKind.setText(Integer.toString(combos.fourOfAKind));
		fullHouse.setText(Integer.toString(combos.fullHouse));
		flush.setText(Integer.toString(combos.flush));
		straight.setText(Integer.toString(combos.straight));
		threeOfAKind.setText(Integer.toString(combos.threeOfAKind));
		twoPair.setText(Integer.toString(combos.twoPair));
		overPair.setText(Integer.toString(combos.overPair));
		topPair.setText(Integer.toString(combos.topPair));
		pocketPairBelowTopPair.setText(Integer.toString(combos.pocketPairBelowTopPair));
		middlePair.setText(Integer.toString(combos.middlePair));
		weakPair.setText(Integer.toString(combos.weakPair));
		noHandMade.setText(Integer.toString(combos.noHandMade));
		
		gutshotDraw.setText(Integer.toString(combos.gutshotDraw));
		openEndedDraw.setText(Integer.toString(combos.openEndedDraw));
		flushDraw.setText(Integer.toString(combos.flushDraw));
	}
}
