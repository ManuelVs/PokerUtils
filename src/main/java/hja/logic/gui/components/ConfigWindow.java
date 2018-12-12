package hja.logic.gui.components;

import hja.logic.gui.model.Config;
import hja.logic.gui.model.Model;
import hja.pokerutils.Algorithm.HandClassifier;
import hja.pokerutils.Algorithm.HoldEmAlgorithm;
import hja.pokerutils.Algorithm.OmahaAlgorithm;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Parser.CardListParser;
import hja.pokerutils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class ConfigWindow extends JFrame {
	private static final int NUM_PLAYERS = 10;
	
	private static final int HOLDEM_MODE = 2;
	private static final int OMAHA_MODE = 4;
	
	private static final int PREFLOP_PHASE = 0;
	private static final int FLOP_PHASE = 1;
	private static final int TURN_PHASE = 2;
	
	private Model model;
	
	private ArrayList<Card> allPossibleCards;
	private ArrayList<Card> boardCards;
	private Player[] players;
	private boolean[] activePlayers;
	private int mode;
	private int phase;
	
	private Random random;
	private PlayerPanel[] playerPanels;
	private BoardPanel boardPanel;
	
	private JButton startButton;
	private JButton nextPhaseButton;
	
	public ConfigWindow(Model model) {
		super("Settings");
		
		this.model = model;
		this.allPossibleCards = CardFactory.getAllCards();
		this.boardCards = new ArrayList<>();
		this.mode = HOLDEM_MODE;
		this.random = new Random();
		
		this.players = new Player[NUM_PLAYERS];
		this.activePlayers = new boolean[NUM_PLAYERS];
		for(int i = 0; i < NUM_PLAYERS; ++i){
			players[i] = new Player(i, new ArrayList<>());
			
			activePlayers[i] = true;
		}
		
		initGUI();
	}
	
	private void initGUI() {
		JPanel boardPanel = createBoardPanel();
		JPanel playersPanel = createPlayersPanel();
		JPanel buttonsPanel = createButtonsPanel();
		
		this.setLayout(new BorderLayout(3, 1));
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.add(boardPanel, BorderLayout.NORTH);
		this.add(playersPanel, BorderLayout.CENTER);
		this.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	private JPanel createBoardPanel(){
		boardPanel = new BoardPanel(this);
		return boardPanel;
	}
	
	private JPanel createPlayersPanel() {
		JPanel playersPanel = new JPanel();
		playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
		
		this.playerPanels = new PlayerPanel[NUM_PLAYERS];
		for(int i = 0; i < NUM_PLAYERS; ++i){
			playerPanels[i] = new PlayerPanel(i, this);
			playersPanel.add(playerPanels[i]);
		}
		
		return playersPanel;
	}
	
	private JPanel createButtonsPanel(){
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(e ->
			resetAll()
		);
		
		startButton = new JButton("Start");
		startButton.addActionListener(e -> {
			if(validateConfig()){
				updateConfigToModel();
				startButton.setEnabled(false);
				nextPhaseButton.setEnabled(true);
			}
			else {
				JOptionPane.showMessageDialog(this,
					"The actual configuration is not valid.",
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
			}
		});
		
		nextPhaseButton = new JButton("Next phase");
		nextPhaseButton.addActionListener(e -> {
			if(phase < 3){
				phase++;
				updateConfigToModel();
			}
			else {
				JOptionPane.showMessageDialog(this, "It is already the River phase.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		nextPhaseButton.setEnabled(false);
		
		JPanel buttonsPanel = new JPanel();
		
		JComboBox<String> modes = new JComboBox<>(new String[]{ "HoldEm", "Omaha" });
		modes.addActionListener(e -> {
			resetAll();
			if(modes.getSelectedIndex() == 0){
				mode = HOLDEM_MODE;
			}
			else {
				mode = OMAHA_MODE;
			}
		});
		
		buttonsPanel.add(modes);
		buttonsPanel.add(startButton);
		buttonsPanel.add(nextPhaseButton);
		buttonsPanel.add(resetButton);
		
		return buttonsPanel;
	}
	
	private ArrayList<Card> getRandomCards(int n){
		ArrayList<Card> cards = new ArrayList<>(this.allPossibleCards);
		ArrayList<Card> out = new ArrayList<>(n);
		
		for(int i = 0; i < n; i++){
			Card card = cards.get(this.random.nextInt(cards.size()));
			out.add(card);
			cards.remove(card);
		}
		
		return out;
	}
	
	private void setCardsForPlayer(int player, String cards){
		if(this.activePlayers[player]) {
			try {
				ArrayList<Card> playerCards = CardListParser.parseListCards(cards);
				ArrayList<Card> oldCards = this.players[player].getCards();
				this.allPossibleCards.addAll(oldCards);
				
				if(allPossibleCards.containsAll(playerCards)){
					this.allPossibleCards.removeAll(playerCards);
					
					this.players[player].setCards(playerCards);
					this.playerPanels[player].setCards(cards);
				}
				else {
					JOptionPane.showMessageDialog(this,
						"Some cards of the player " + (player + 1) + " are already in this game.",
						"ERROR",
						JOptionPane.ERROR_MESSAGE);
					
					playerPanels[player].setCards("");
				}
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this, "Some cards of the player " + (player + 1) + " are not typed correctly.", "ERROR", JOptionPane.ERROR_MESSAGE);
				playerPanels[player].setCards("");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "The player " + (player + 1) + " it is not playing currently.", "ERROR", JOptionPane.ERROR_MESSAGE);
			playerPanels[player].setCards("");
		}
	}
	
	private void setRandomCardsForPlayer(int player){
		if(this.activePlayers[player]) {
			ArrayList<Card> oldCards = this.players[player].getCards();
			this.allPossibleCards.addAll(oldCards);
			
			ArrayList<Card> playerCards = getRandomCards(this.mode);
			this.allPossibleCards.removeAll(playerCards);
			
			this.players[player].setCards(playerCards);
			this.playerPanels[player].setCards(Utils.cardsToString(playerCards));
		} else {
			JOptionPane.showMessageDialog(this, "The player " + (player + 1) + " it is not playing currently.", "ERROR", JOptionPane.ERROR_MESSAGE);
			playerPanels[player].setCards("");
		}
	}
	
	private void foldPlayer(int player) {
		activePlayers[player] = false;
		playerPanels[player].setEnabled(false);
	}
	
	private void setRandomCardsForBoard() {
		this.allPossibleCards.addAll(boardCards);
		
		boardCards = getRandomCards(5);
		boardPanel.setCards(Utils.cardsToString(boardCards));
		this.allPossibleCards.removeAll(boardCards);
	}
	
	private void setBoardCards(String text) {
		try {
			ArrayList<Card> cards = CardListParser.parseListCards(text);
			this.allPossibleCards.addAll(boardCards);
			if(allPossibleCards.containsAll(cards)) {
				boardCards = cards;
				boardPanel.setCards(text);
				this.allPossibleCards.removeAll(boardCards);
			}
			else {
				JOptionPane.showMessageDialog(this, "Some of the cards of the board that you are trying to set are already in this game.", "ERROR", JOptionPane.ERROR_MESSAGE);
				boardPanel.setCards("");
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Some cards of the board are not typed correctly.", "ERROR", JOptionPane.ERROR_MESSAGE);
			boardPanel.setCards("");
		}
	}
	
	private void resetAll() {
		this.allPossibleCards = CardFactory.getAllCards();
		this.boardCards = new ArrayList<>();
		this.phase = PREFLOP_PHASE;
		
		this.players = new Player[NUM_PLAYERS];

		this.activePlayers = new boolean[NUM_PLAYERS];
		for(int i = 0; i < NUM_PLAYERS; ++i){
			players[i] = new Player(i, new ArrayList<>());
			
			activePlayers[i] = true;
			
			playerPanels[i].setEnabled(true);
			playerPanels[i].setCards("");
		}
		boardPanel.setCards("");
		
		startButton.setEnabled(true);
		nextPhaseButton.setEnabled(false);
	}
	
	private void updateConfigToModel() {
		ArrayList<Player> players = new ArrayList<>();
		for(int i = 0; i < activePlayers.length; ++i){
			if(activePlayers[i]){
				players.add(this.players[i]);
			}
		}
		
		int numBoardCards;
		switch (phase) {
			case PREFLOP_PHASE: numBoardCards = 0; break;
			case FLOP_PHASE: numBoardCards = 3; break;
			case TURN_PHASE: numBoardCards = 4; break;
			default: numBoardCards = 5; break;
		}
		ArrayList<Card> board = new ArrayList<>(this.boardCards.subList(0, numBoardCards));
		
		HandClassifier classifier;
		classifier = mode == OMAHA_MODE ? new OmahaAlgorithm() : new HoldEmAlgorithm();
		
		Config config = new Config(board, players, classifier);
		model.setConfig(config);
	}
	
	private boolean validateConfig() {
		if(boardCards.size() != 5) return false;
		
		for(int i = 0; i < activePlayers.length; ++i){
			if(activePlayers[i]){
				if(players[i].getCards().size() != mode){
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static class PlayerPanel extends JPanel {
		private int numPlayer;
		private ConfigWindow configWindow;
		
		private JTextField cardsText;
		
		private JButton randomButton;
		private JButton setButton;
		private JButton foldButton;
		
		PlayerPanel(int numPlayer, ConfigWindow configWindow) {
			this.numPlayer = numPlayer;
			this.configWindow = configWindow;
			initGUI();
		}
		
		private void initGUI() {
			String playerName = "Player " + (this.numPlayer + 1);
			JLabel text = new JLabel(playerName);
			
			cardsText = new JTextField("");
			
			randomButton = new JButton("Random & Set");
			setButton = new JButton("Set");
			foldButton = new JButton("Delete / Fold");
			
			randomButton.addActionListener(e -> {
				if (configWindow.startButton.isEnabled()) {
					configWindow.setRandomCardsForPlayer(this.numPlayer);
				}
				else {
					JOptionPane.showMessageDialog(this, "It is not allowed to change cards in the middle of the game.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			});
			
			setButton.addActionListener(e -> {
				if (configWindow.startButton.isEnabled()) {
					String cards = cardsText.getText();
					if(!cards.isEmpty())
						configWindow.setCardsForPlayer(this.numPlayer, cards);
					else
						JOptionPane.showMessageDialog(this, "There are no cards to set.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(this, "It is not allowed to change cards in the middle of the game.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			});
			
			foldButton.addActionListener(e ->
				configWindow.foldPlayer(this.numPlayer)
			);
			
			this.setLayout(new GridLayout(1, 5));
			this.add(text);
			this.add(cardsText);
			this.add(randomButton);
			this.add(setButton);
			this.add(foldButton);
		}
		
		public void setCards(String cards) {
			this.cardsText.setText(cards);
		}
		
		public void setEnabled(boolean activate){
			super.setEnabled(activate);
			
			randomButton.setEnabled(activate);
			setButton.setEnabled(activate);
			cardsText.setEnabled(activate);
			foldButton.setEnabled(activate);
		}
	}
	
	private static class BoardPanel extends JPanel {
		private ConfigWindow configWindow;
		
		private JTextField cardsText;
		
		private JButton randomButton;
		private JButton setButton;
		
		BoardPanel(ConfigWindow configWindow) {
			this.configWindow = configWindow;
			initGUI();
		}
		
		private void initGUI() {
			JLabel label = new JLabel("Board: ");
			
			cardsText = new JTextField("");
			
			randomButton = new JButton("Random & Set");
			setButton = new JButton("Set");
			
			randomButton.addActionListener(e -> {
				if (configWindow.startButton.isEnabled()) {
					configWindow.setRandomCardsForBoard();
				} else {
					JOptionPane.showMessageDialog(this, "It is not allowed to change cards in the middle of the game.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			});
			
			setButton.addActionListener(e -> {
				if (configWindow.startButton.isEnabled()) {
					String cards = cardsText.getText();
					if(!cards.isEmpty())
						configWindow.setBoardCards(cards);
					else
						JOptionPane.showMessageDialog(this, "There are no cards to set.", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				} else {
					JOptionPane.showMessageDialog(this, "It is not allowed to change cards in the middle of the game.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			});
			
			
			this.setLayout(new GridLayout(1, 4));
			this.add(label);
			this.add(cardsText);
			this.add(randomButton);
			this.add(setButton);
		}
		
		public void setCards(String cards) {
			this.cardsText.setText(cards);
		}
	}
}
