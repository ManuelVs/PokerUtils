package hja.logic.gui.model;

//import hja.pokerutils.Board.Player;

public interface Model {
	
	void setConfig(Config config);
	
	void nextPhase();
	
//	void deletePlayer(Player p);
	
	void addConfigListener(ConfigListener listener);
}
