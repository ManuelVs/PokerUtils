package hja.logic.gui.components;

import hja.logic.gui.model.BoardCardsListener;
import hja.logic.gui.model.Model;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Card.Suit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardCards extends JPanel implements BoardCardsListener {
	private static Color[] suit_color = new Color[]{
			new Color(255, 182, 181),
			new Color(183, 240, 128),
			new Color(181, 210, 255),
			new Color(214, 214, 214),
	};
	private static Color[] suit_selected_color = new Color[]{
			new Color(240, 39, 6),
			new Color(56, 164, 17),
			new Color(4, 85, 227),
			new Color(100, 100, 100),
	};
	
	private Model model;
	
	private Color defaultColor;
	private CardButton[] cardButtons;
	private CardButton[][] matrixButtons;
	
	public BoardCards(Model model) {
		this.model = model;
		this.model.addBoardCardsListener(this);
		
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel boardPanel = new JPanel();
		cardButtons = new CardButton[5];
		cardButtons[0] = new CardButton("  ");
		cardButtons[1] = new CardButton("  ");
		cardButtons[2] = new CardButton("  ");
		cardButtons[3] = new CardButton("  ");
		cardButtons[4] = new CardButton("  ");
		this.defaultColor = cardButtons[0].getBackground();
		
		boardPanel.setLayout(new GridLayout(1, 4));
		boardPanel.add(cardButtons[0]);
		boardPanel.add(cardButtons[1]);
		boardPanel.add(cardButtons[2]);
		boardPanel.add(cardButtons[3]);
		boardPanel.add(cardButtons[4]);
		
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		this.add(boardPanel, c);
		
		c.gridwidth = 1;
		
		Suit[] suits = Suit.values();
		Rank[] ranks = Rank.values();
		this.matrixButtons = new CardButton[suits.length][ranks.length];
		for (int s = 0; s < suits.length; ++s) {
			for (int r = 0; r < ranks.length; ++r) {
				Rank rank = ranks[ranks.length - r - 1];
				Suit suit = suits[s];
				
				c.gridx = s;
				c.gridy = r + 1;
				
				CardButton cb = new CardButton(rank.rank + "" + suit.suit);
				cb.setBackground(suit_color[s]);
				cb.setSelectedBackground(suit_selected_color[s]);
				cb.addActionListener(new CardClicked(rank, suit, model));
				
				this.matrixButtons[suit.ordinal()][rank.ordinal()] = cb;
				this.add(cb, c);
			}
		}
	}
	
	@Override
	public void notify(ArrayList<Card> cards) {
		clearMatrix();
		
		for (int i = 0; i < cards.size(); ++i) {
			Rank rank = cards.get(i).rank;
			Suit suit = cards.get(i).suit;
			
			CardButton cb = cardButtons[i];
			cb.setBackground(suit_selected_color[suit.ordinal()]);
			cb.setSelectedBackground(suit_selected_color[suit.ordinal()]);
			cb.setText(rank.rank + "" + suit.suit);
			
			// Set button matrix appropriately
			matrixButtons[suit.ordinal()][rank.ordinal()].setSelected(true);
		}
	}
	
	private void clearMatrix() {
		for (int i = 0; i < matrixButtons.length; ++i) {
			for (int j = 0; j < matrixButtons[i].length; ++j) {
				matrixButtons[i][j].setSelected(false);
			}
		}
		
		for (CardButton cb : cardButtons) {
			cb.setBackground(defaultColor);
			cb.setSelectedBackground(defaultColor);
			cb.setText("  ");
		}
	}
	
	private static class CardClicked implements ActionListener {
		private Rank rank;
		private Suit suit;
		private Model model;
		
		public CardClicked(Rank r, Suit s, Model model) {
			this.rank = r;
			this.suit = s;
			this.model = model;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setCard(CardFactory.createCard(rank, suit));
		}
	}
	
}
