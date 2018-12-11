package hja.logic.gui.model;

import hja.pokerutils.Algorithm.EquityCalculator;
import hja.pokerutils.Board.Player;

import java.util.ArrayList;

public class ModelImpl implements Model {
	private Config config;
	private ArrayList<ConfigListener> configListeners;
	private Thread thread;
	
	public ModelImpl() {
		this.config = null;
		this.configListeners = new ArrayList<>();
	}
	
	
	@Override
	public void setConfig(Config config) {
		this.config = config;
		onConfigChanged();
	}
	
/*
	@Override
	public void deletePlayer(Player p) {
		ArrayList<Player> players = this.config.getPlayers();
		players.remove(p);
		
		this.config = new Config(this.config.getBoardCards(), players);
		onConfigChanged();
	}*/
	
	@Override
	public void addConfigListener(ConfigListener listener) {
		this.configListeners.add(listener);
	}
	
	public void notifyAllConfigListeners() {
		for (ConfigListener listener : this.configListeners) {
			listener.notify(this.config);
		}
	}
	
	private void onConfigChanged() {
		if (thread == null || !thread.isAlive()) {
			thread = new Thread(() -> {
				updateEquityOnConfig();
				notifyAllConfigListeners();
			});
			thread.start();
		}
	}
	
	private void updateEquityOnConfig() {
		EquityCalculator.calculateEquity(config.getPlayers(), config.getBoardCards(), config.getClassifier());
	}
}