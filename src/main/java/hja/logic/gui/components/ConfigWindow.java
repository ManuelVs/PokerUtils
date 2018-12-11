package hja.logic.gui.components;

import hja.logic.gui.model.Config;
import hja.logic.gui.model.ConfigListener;
import hja.logic.gui.model.Model;
import hja.logic.gui.model.PlayerCardsListener;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Parser.CardListParser;
import hja.pokerutils.Utils;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class ConfigWindow extends JFrame {
	private static final int NUM_PLAYERS = 10;
	private static final int HOLDEM_MODE = 2;
	private static final int OMAHA_MODE = 4;
	
	private Model model;
	
	private ArrayList<Card> allPossibleCards;
	private Player[] players;
	private boolean[] activePlayers;
	private int mode;
	
	private Random random;
	private ArrayList<PlayerCardsListener> playerCardsListeners;
	
	public ConfigWindow(Model model) {
		super("Configuración");
		this.model = model;
		this.allPossibleCards = CardFactory.getAllCards();
		
		this.players = new Player[NUM_PLAYERS];
		this.activePlayers = new boolean[NUM_PLAYERS];
		for(int i = 0; i < NUM_PLAYERS; ++i){
			players[i] = new Player(i, new ArrayList<>());
			activePlayers[i] = false;
		}
		
		this.mode = HOLDEM_MODE;
		
		this.playerCardsListeners = new ArrayList<>();
		
		this.random = new Random();
		initGUI();
	}
	
	private void initGUI() {
		JPanel boardPanel = new JPanel();
		//AÑADIR EL BOARD
		
		JPanel playersPanel = new JPanel();
		playersPanel.setLayout(new BorderLayout(2, 1));
		
		JButton addPlayerButton = new JButton("Añadir jugador");
		addPlayerButton.addActionListener(e -> {
			PlayerPanel playerPanel = new PlayerPanel(this.playerCardsListeners.size(), this);
			addPlayerCardsListener(playerPanel);
			
			playersPanel.add(playerPanel, BorderLayout.CENTER);
			playersPanel.revalidate();
			playersPanel.repaint();
		});
		 playersPanel.add(addPlayerButton, BorderLayout.SOUTH);
		
		JPanel buttonsPanel = new JPanel();
		//AÑADIR LOS BOTONES
		
		this.setLayout(new BorderLayout(3, 1));
		
		this.add(boardPanel, BorderLayout.NORTH);
		this.add(playersPanel, BorderLayout.CENTER);
		this.add(buttonsPanel, BorderLayout.SOUTH);
		this.setSize(525, 580);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
	
	public void setCardsForPlayer(int player, String cards){
		if(this.activePlayers[player]) {
			try {
				ArrayList<Card> playerCards = CardListParser.parseListCards(cards);
				if(!allPossibleCards.containsAll(playerCards)){
					this.players[player].setCards(playerCards);
					notifyAllPlayerCardsListeners(player, cards);
				}
				else {
					//OTRO POPUP
					notifyAllPlayerCardsListeners(player, "");
				}
			} catch (ParseException e) {
				//OTRO POPUP
				notifyAllPlayerCardsListeners(player, "");
			}
		}
		else {
			//SACAR POPUP CON ERROR DE LA LECHE
			notifyAllPlayerCardsListeners(player, "");
		}
	}
	
	public void setRandomCardsForPlayer(int player){
		if(this.activePlayers[player]) {
			ArrayList<Card> playerCards = getRandomCards(this.mode);
			this.players[player].setCards(playerCards);
			notifyAllPlayerCardsListeners(player, Utils.cardsToString(playerCards));
		} else {
			//OTRO POPUP
			notifyAllPlayerCardsListeners(player, "");
		}
	}
	
	public void foldPlayer(int player){
		this.activePlayers[player] = false;
		notifyAllPlayerCardsListeners(player, "fold");
	}
	
	private void addPlayerCardsListener(PlayerCardsListener listener){
		this.playerCardsListeners.add(listener);
	}
	
	private void notifyAllPlayerCardsListeners(int player, String cards){
		for(PlayerCardsListener listener : this.playerCardsListeners){
			listener.notify(player, cards);
		}
	}
}
