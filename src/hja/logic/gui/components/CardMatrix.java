package hja.logic.gui.components;

import hja.logic.gui.model.Model;
import hja.logic.gui.model.RangeListener;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Range.CardPair;
import hja.pokerutils.Range.Range;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardMatrix extends JPanel implements RangeListener {
	private Model model;
	private Range range;
	
	private CardButton[][] cardPairButtons;
	
	public CardMatrix(Model model){
		this.model = model;
		this.model.addRangeListener(this);
		
		this.range = new Range();
		
		initGUI();
	}
	
	private void initGUI() {
		Rank[] ranks = Rank.values();
		this.setLayout(new GridLayout(ranks.length, ranks.length));
		
		ArrayList<CardPair> allCardPairs = new ArrayList<>(ranks.length * ranks.length);
		
		for(int i = 0; i < ranks.length; ++i) {
			allCardPairs.add(new CardPair(ranks[i], ranks[i], false));
			for(int j = i + 1; j < ranks.length; ++j) {
				allCardPairs.add(new CardPair(ranks[j], ranks[i], true));
				allCardPairs.add(new CardPair(ranks[j], ranks[i], false));
			}
		}
		
		cardPairButtons = new CardButton[ranks.length][ranks.length];
		for(CardPair cp : allCardPairs){
			Point p = getPosition(cp);
			
			CardButton cb = new CardButton(cp.toString());
			cardPairButtons[p.x][p.y] = cb;
			
			Color normal;
			Color selected;
			if(cp.isSuited){
				normal = new Color(255, 238, 150);
				selected = new Color(239, 191, 3);
			}
			else if(cp.firstRank == cp.secondRank){
				normal = new Color(181, 210, 255);
				selected = new Color(4, 85, 227);
			}
			else{
				normal = new Color(255, 182, 181);
				selected = new Color(240, 39, 6);
			}
			
			cb.setBackground(normal);
			cb.setSelectedBackground(selected);
			
			cb.addActionListener((e) -> {
				if(cb.isSelected()){
					this.range.delete(cp);
				}
				else{
					this.range.add(cp);
				}
				
				cb.setSelected(!cb.isSelected());
				this.model.setRange(this.range);
			});
		}
		
		for(int i = 0; i < cardPairButtons.length; ++i){
			for(int j = 0; j < cardPairButtons[i].length; ++j){
				JButton button = cardPairButtons[i][j];
				this.add(button);
			}
		}
	}
	
	private static Point getPosition(CardPair cp) {
		int row = Rank.ACE.ordinal() - cp.firstRank.ordinal();
		int column = Rank.ACE.ordinal() - cp.secondRank.ordinal();
		
		if(!cp.isSuited){
			int tmp = column;
			column = row;
			row = tmp;
		}
		
		return new Point(row, column);
	}
	
	@Override
	public void notify(Range range) {
		this.range = new Range(range);
		
		clearButtons();
		for(CardPair cp : range){
			Point p = getPosition(cp);
			CardButton cb = cardPairButtons[p.x][p.y];
			
			cb.setSelected(true);
		}
	}
	
	private void clearButtons(){
		for(int i = 0; i < cardPairButtons.length; ++i){
			for(int j = 0; j < cardPairButtons[i].length; ++j){
				cardPairButtons[i][j].setSelected(false);
			}
		}
	}
}
