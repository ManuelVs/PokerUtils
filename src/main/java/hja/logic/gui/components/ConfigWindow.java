package hja.logic.gui.components;

import hja.logic.gui.model.Config;
import hja.logic.gui.model.ConfigListener;
import hja.logic.gui.model.Model;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Parser.CardListParser;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ConfigWindow extends JFrame implements ConfigListener {
	private static final int NUM_PLAYERS = 10;
	
	private Model model;
	
	public ConfigWindow(Model model){
		super("Configuración");
		this.model = model;
		initGUI();
	}
	
	private void initGUI() {
		/* Crear playerPanel,  saber qué jugador es
		apliicar el setConfig
		*/
		
		ArrayList<Player> players = new ArrayList<>(NUM_PLAYERS);
		
		JPanel northPanel = new JPanel();
		northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		
		JLabel boardLabel = new JLabel("Board");
		JTextField boardCardsText = new JTextField("");
		JButton randomBoardCardsButton = new JButton("Random");
		JButton setBoardCardsButton = new JButton("Set");

		randomBoardCardsButton.addActionListener(e -> {
			ArrayList<Card> boardCards = this.model.getCards();
			ArrayList<Card> allPossibleCards = getAllPossibleCards(boardCards, players);

			boardCardsText.setText("");
			boardCards.clear();

			for (int i = 0; i < 5; i++) {
				Card card = allPossibleCards.get(new Random().nextInt(allPossibleCards.size()));
				allPossibleCards.remove(card);

				boardCards.add(card);
				boardCardsText.setText(boardCardsText.getText() + card.toString());
			}
		});

		setBoardCardsButton.addActionListener(e -> {
			ArrayList<Card> boardCards = new ArrayList<>();
			ArrayList<Card> allPossibleCards = getAllPossibleCards(boardCards, players);

			String cards = boardCardsText.getText();
			boardCardsText.setText("");

			if (cards.length() == 10) {
				Reader reader = new StringReader(cards);
				try {
					ArrayList<Card> playerCards = CardListParser.parseListCard(reader, 2);
					for (int i = 0; i < 5; i++) {
						Card card = CardFactory.createCard(cards.charAt(i), cards.charAt(i + 1));

						int allCardsSize = allPossibleCards.size();
						allPossibleCards.remove(card);

						if (allPossibleCards.size() == allCardsSize - 1) {
							boardCards.add(i, card);
							boardCardsText.setText(boardCardsText.getText() + card.toString());
						} else {
							JOptionPane.showMessageDialog(this, "La carta " + cards.substring(i, i + 1) + "ya se encuentra en el tablero.", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "Las cartas del tablero están mal escritas.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Sobran caracteres en las cartas del tablero.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		northPanel.add(boardLabel);
		northPanel.add(boardCardsText);
		northPanel.add(randomBoardCardsButton);
		northPanel.add(setBoardCardsButton);
		
		JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout(2, 1));
		
		JPanel playersPanel = new JPanel();
		playersPanel.setLayout(new GridLayout(1, 4));
		playersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
		
		JButton addPlayerButton = new JButton("Añadir jugador");
		addPlayerButton.addActionListener(e -> {
			if(this.playersCount < NUM_PLAYERS) {
				
				
				
				playersPanel.add(text);
				playersPanel.add(cardPairText);
				playersPanel.add(randomButton);
				playersPanel.add(setButton);
				
				playersPanel.setLayout(new GridLayout(this.playersCount, 4));
				playersPanel.revalidate();
				playersPanel.repaint();
			}
			else {
				JOptionPane.showMessageDialog(this, "No se pueden añadir más de 10 jugadores.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		centralPanel.add(playersPanel, BorderLayout.CENTER);
		centralPanel.add(addPlayerButton, BorderLayout.SOUTH);
		
		JButton acceptButton = new JButton("Aceptar");
		
		
		JPanel southPanel = new JPanel();
		southPanel.add(acceptButton);
		
		this.setLayout(new BorderLayout(3, 1));
		
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centralPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		this.setSize(525, 580);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private ArrayList<Card> getAllPossibleCards(ArrayList<Card> boardCards, ArrayList<Player> players) {
		ArrayList<Card> allPossibleCards = CardFactory.getAllCards();
		
		for (Card card : boardCards) {
			allPossibleCards.remove(card);
		}
		
		for (Player player : players) {
			for (Card card : player.cards) {
				allPossibleCards.remove(card);
			}
		}
		
		return allPossibleCards;
	}
	
	@Override
	public void notify(Config config) {
	
	}
}
