package hja.logic.gui.components;

import hja.logic.gui.model.BoardChangeListener;
import hja.pokerutils.Board.Board;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Card.Suit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PokerBoard extends JComponent implements BoardChangeListener {
	private static final int NUM_PLAYERS = 9;
	
	protected double aspect_ratio_card = 1.452;
	
	protected double vs;
	protected double hs;
	protected double sep;
	
	protected double fs;
	protected double cw;
	protected double ch;
	
	protected double[] wPlayer;
	protected double[] hPlayer;
	
	protected Image pokerBoard;
	protected Image[][] pokerCards;
	
	protected Board board;
	
	public PokerBoard() throws IOException {
		this.wPlayer = new double[NUM_PLAYERS];
		this.hPlayer = new double[NUM_PLAYERS];
		
		this.board = null;
		this.loadImages();
	}
	
	private void loadImages() throws IOException {
		InputStream pokerBoardStream = new FileInputStream("res/images/pokerBoard.png");
		this.pokerBoard = ImageIO.read(pokerBoardStream);
		
		loadCardImages();
	}
	
	private void loadCardImages() throws IOException {
		Rank[] ranks = Rank.values();
		Suit[] suits = Suit.values();
		
		this.pokerCards = new Image[ranks.length][suits.length];
		for (int i = 0; i < ranks.length; ++i) {
			for (int j = 0; j < suits.length; ++j) {
				String fileName = ranks[i].rank + "" + suits[j].suit + ".png";
				Path path = Paths.get("res", "images", "cards", fileName);
				
				InputStream pokerCardStream = new FileInputStream(path.toString());
				this.pokerCards[i][j] = ImageIO.read(pokerCardStream);
				pokerCardStream.close();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateReferences();
		
		paintBoard(g);
		if (this.board != null) {
			paintCards(g);
			paintText(g);
		}
	}
	
	private void paintBoard(Graphics g) {
		drawImage(g, pokerBoard, 0, 0, this.getWidth(), this.getHeight());
	}
	
	private void paintCards(Graphics g) {
		double PACK_SEP = this.cw * this.fs;
		
		for (int i = 0; i < this.board.players.size(); ++i) {
			Card firstCard = this.board.players.get(i).cards.get(0);
			Card secondCard = this.board.players.get(i).cards.get(1);
			
			Image firstImage = getImageForCard(firstCard);
			Image secondImage = getImageForCard(secondCard);
			
			drawImage(g, firstImage, this.wPlayer[i], this.hPlayer[i], this.cw, this.ch);
			drawImage(g, secondImage, this.wPlayer[i] + PACK_SEP, this.hPlayer[i], this.cw, this.ch);
		}
		
		for (int i = 0; i < this.board.boardCards.size(); ++i) {
			Card card = this.board.boardCards.get(i);
			Image image = getImageForCard(card);
			
			drawImage(g, image, vs + i * (this.cw + this.sep), hs, this.cw, this.ch);
		}
	}
	
	private void paintText(Graphics g) {
		double[] hString = new double[]{-5, -5, -5, this.ch + 12, this.ch + 12, this.ch + 12, this.ch + 12, -5, -5};
		
		for (int i = 0; i < this.board.players.size(); ++i) {
			String hand = this.board.players.get(i).hand.toString();
			g.drawString(hand, (int) this.wPlayer[i], (int) (this.hPlayer[i] + hString[i]));
		}
	}
	
	@Override
	public void notify(Board board) {
		this.board = board;
		this.repaint();
	}
	
	private void updateReferences() {
		double width = this.getWidth();
		double height = this.getHeight();
		this.fs = 1 / 3d;
		
		this.cw = height * 0.125;
		this.ch = this.cw * this.aspect_ratio_card;
		
		double pack_width = this.cw * (1 + this.fs);
		
		double v1 = width * 0.03;
		double v2 = width * 0.20;
		double v3 = width * 0.50 - pack_width * 0.5;
		double v4 = width * 0.80 - pack_width;
		double v5 = width * 0.97 - pack_width;
		
		double h1 = height * 0.03;
		double h2 = height * 0.25;
		double h3 = height * 0.75 - this.ch;
		double h4 = height * 0.97 - this.ch;
		
		this.sep = 10;
		this.hs = height * 0.5 - this.ch * 0.5;
		this.vs = width * 0.5 - 5 * this.cw * 0.5 - 2 * this.sep;
		
		this.wPlayer[0] = v3;
		this.hPlayer[0] = h4;
		
		this.wPlayer[1] = v2;
		this.hPlayer[1] = h4;
		
		this.wPlayer[2] = v1;
		this.hPlayer[2] = h3;
		
		this.wPlayer[3] = v1;
		this.hPlayer[3] = h2;
		
		this.wPlayer[4] = v2;
		this.hPlayer[4] = h1;
		
		this.wPlayer[5] = v4;
		this.hPlayer[5] = h1;
		
		this.wPlayer[6] = v5;
		this.hPlayer[6] = h2;
		
		this.wPlayer[7] = v5;
		this.hPlayer[7] = h3;
		
		this.wPlayer[8] = v4;
		this.hPlayer[8] = h4;
	}
	
	private void drawImage(Graphics g, Image image, double x, double y, double width, double height) {
		g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
	}
	
	private Image getImageForCard(Card card) {
		return this.pokerCards[card.rank.ordinal()][card.suit.ordinal()];
	}
}
