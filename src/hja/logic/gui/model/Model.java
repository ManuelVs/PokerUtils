package hja.logic.gui.model;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Range.Range;

public interface Model {
	
	void setRange(Range range);
	
	void setCard(Card card);
	
	void addBoardCardsListener(BoardCardsListener listener);
	
	void addCombosListener(CombosListener listener);
	
	void addRangeListener(RangeListener listener);
}
