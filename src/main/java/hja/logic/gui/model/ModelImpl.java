package hja.logic.gui.model;

import hja.pokerutils.Algorithm.Combos;
import hja.pokerutils.Algorithm.CombosAlgorithm;
import hja.pokerutils.Algorithm.EquityCalculatorHoldEm;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Range.Range;

import javax.swing.*;
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
	
	@Override
	public void nextPhase() {
		int phase = this.config.getPhase();
		if(phase >= 3){
			phase = 0;
		}
		else {
			phase += 1;
		}
		
		this.config.setPhase(phase);
		onConfigChanged();
	}
	
	@Override
	public void deletePlayer(Player p) {
		ArrayList<Player> players = this.config.getPlayers();
		players.remove(p);
		
		this.config = new Config(this.config.getBoardCards(), players);
		onConfigChanged();
	}
	
	@Override
	public void addConfigListener(ConfigListener listener) {
		this.configListeners.add(listener);
	}
	
	public void notifyAllConfigListeners(){
		for (ConfigListener listener: this.configListeners) {
			listener.notify(this.config);
		}
	}
	
	private void onConfigChanged() {
		if(thread == null || !thread.isAlive()){
			thread = new Thread(() -> {
				updateEquityOnConfig();
				notifyAllConfigListeners();
			});
			thread.start();
		}
	}
	
	private void updateEquityOnConfig() {
		EquityCalculatorHoldEm.calculateEquity(config);
	}
}