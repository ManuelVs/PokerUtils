package hja.logic.gui.model;

import hja.pokerutils.Algorithm.Combos;
import hja.pokerutils.Algorithm.CombosAlgorithm;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Range.Range;

import javax.swing.*;
import java.util.ArrayList;

public class ModelImpl implements Model {
	private Config config;
	private ArrayList<ConfigListener> configListeners;
	
	public ModelImpl() {
		this.config = null;
		this.configListeners = new ArrayList<>();
	}
	
	
	@Override
	public void setConfig(Config config) {
		this.config = config;
		notifyAllConfigListeners();
	}
	
	@Override
	public void nextPhase() {
		//Ver como hacerlo
	}
	
	@Override
	public void deletePlayer(Player p) {
		ArrayList<Player> players = this.config.getPlayers();
		players.remove(p);
		this.config = new Config(players, this.config.getBoardCards());
		notifyAllConfigListeners();
	}
	
	@Override
	public void addConfigListener(ConfigListener listener) {
		this.configListeners.add(listener);
	}
	
	public void notifyAllConfigListeners(){
		SwingUtilities.invokeLater(() -> {
			for (ConfigListener listener: this.configListeners) {
				listener.notify(this.config);
			}
		});
	}
	
}