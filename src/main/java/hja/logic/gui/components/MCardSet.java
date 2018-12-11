package hja.logic.gui.components;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Card.Suit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class MCardSet {
	protected static Image[][] pokerCards;
	
	static {
		Rank[] ranks = Rank.values();
		Suit[] suits = Suit.values();
		
		pokerCards = new Image[ranks.length][suits.length];
		for (int i = 0; i < ranks.length; ++i) {
			for (int j = 0; j < suits.length; ++j) {
				String fileName = ranks[i].rank + "" + suits[j].suit + ".png";
				URL cardURL = PokerBoard.class.getResource("cards/" + fileName);
				
				try {
					pokerCards[i][j] = ImageIO.read(cardURL);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected double posX, posY, width, height, fs;
	protected ArrayList<Card> images;
	
	public MCardSet() {
	
	}
	
	private static Image getImageForCard(Card card) {
		return pokerCards[card.rank.ordinal()][card.suit.ordinal()];
	}
	
	public void draw(Graphics g) {
		double PACK_SEP = this.width * this.fs;
		double start = 0;
		
		for (Card c : images) {
			Image cardImage = getImageForCard(c);
			
			g.drawImage(cardImage, (int) (posX + start), (int) posY, (int) width, (int) height, null);
			start += PACK_SEP;
		}
	}
	
	public double getPosX() {
		return posX;
	}
	
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getFs() {
		return fs;
	}
	
	public void setFs(double fs) {
		this.fs = fs;
	}
	
	public ArrayList<Card> getImages() {
		return images;
	}
	
	public void setImages(ArrayList<Card> images) {
		this.images = images;
	}
}
