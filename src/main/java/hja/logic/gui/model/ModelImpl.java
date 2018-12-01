package hja.logic.gui.model;

import hja.pokerutils.Algorithm.Combos;
import hja.pokerutils.Algorithm.CombosAlgorithm;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Range.Range;

import javax.swing.*;
import java.util.ArrayList;

public class ModelImpl implements Model {
	private final ArrayList<Card> cards;
	private final ArrayList<BoardCardsListener> boardCardsListeners;
	private final ArrayList<CombosListener> combosListeners;
	private final ArrayList<RangeListener> rangeListeners;
	private Range range;
	private Combos combos;
	
	public ModelImpl() {
		this.cards = new ArrayList<>(5);
		this.range = new Range();
		this.combos = new Combos();
		
		this.boardCardsListeners = new ArrayList<>();
		this.combosListeners = new ArrayList<>();
		this.rangeListeners = new ArrayList<>();
	}
	
	public void setRange(Range range) {
		this.range = range;
		
		updateCombos();
		notifyRangeListeners();
	}
	
	public void setCard(Card card) {
		if (!this.cards.remove(card) && this.cards.size() < 5) {
			this.cards.add(card);
		}
		
		updateCombos();
		notifyBoardCardsListeners();
	}
	
	public void addBoardCardsListener(BoardCardsListener listener) {
		this.boardCardsListeners.add(listener);
	}
	
	public void addCombosListener(CombosListener listener) {
		this.combosListeners.add(listener);
	}
	
	public void addRangeListener(RangeListener listener) {
		this.rangeListeners.add(listener);
	}
	
	private void updateCombos() {
		Thread thread = new Thread(() -> {
			if (ModelImpl.this.cards.size() >= 3) {
				ModelImpl.this.combos = CombosAlgorithm.getCombos(range, new ArrayList<>(cards));
			}
			else combos = new Combos();
			notifyCombosListener();
		});
		thread.start();
	}
	
	private void notifyBoardCardsListeners() {
		SwingUtilities.invokeLater(() -> {
			for (BoardCardsListener listener : boardCardsListeners) {
				listener.notify(ModelImpl.this.cards);
			}
		});
	}
	
	private void notifyRangeListeners() {
		SwingUtilities.invokeLater(() -> {
			for (RangeListener listener : rangeListeners) {
				listener.notify(ModelImpl.this.range);
			}
		});
	}
	
	private void notifyCombosListener() {
		SwingUtilities.invokeLater(() -> {
			for (CombosListener listener : combosListeners) {
				listener.notify(combos);
			}
		});
	}
}
