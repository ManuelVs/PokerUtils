package hja.logic.gui.components;

import hja.logic.gui.model.PlayerCardsListener;
import hja.pokerutils.Card.Card;

import javax.swing.*;
import java.util.ArrayList;

public class PlayerPanel extends JPanel implements PlayerCardsListener {
	private int numPlayer;
	
	public PlayerPanel(int numPlayer) {
		this.numPlayer = numPlayer;
		initGUI();
	}
	
	private void initGUI() {
	
	}
	
	@Override
	public void notify(int player, ArrayList<Card> cards) {
	
	}
}
