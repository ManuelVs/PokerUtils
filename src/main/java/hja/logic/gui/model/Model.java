package hja.logic.gui.model;

import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Range.Range;

import java.util.ArrayList;

public interface Model {
	
	void setConfig(Config config);

	void nextPhase();
	
	void deletePlayer(Player p);
	
	void addConfigListener(ConfigListener listener);
}
