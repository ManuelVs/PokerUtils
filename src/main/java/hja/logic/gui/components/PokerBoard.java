package hja.logic.gui.components;

import hja.logic.gui.model.Config;
import hja.logic.gui.model.ConfigListener;
import hja.pokerutils.Board.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class PokerBoard extends JPanel implements ConfigListener {
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
	
	protected Config config;
	protected MCardSet cardSetDrawer;
	
	public PokerBoard() throws IOException {
		this.wPlayer = new double[NUM_PLAYERS];
		this.hPlayer = new double[NUM_PLAYERS];
		this.cardSetDrawer = new MCardSet();
		
		this.config = null;
		
		URL pokerBoardURL = PokerBoard.class.getResource("pokerBoard.png");
		this.pokerBoard = ImageIO.read(pokerBoardURL);
		
		initGUI();
	}
	
	private void initGUI(){
	
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
		for (int i = 0; i < this.board.players.size(); ++i) {
			this.cardSetDrawer.setImages(this.board.players.get(i).cards);
			
			this.cardSetDrawer.setPosX(this.wPlayer[i]);
			this.cardSetDrawer.setPosY(this.hPlayer[i]);
			this.cardSetDrawer.setWidth(this.cw);
			this.cardSetDrawer.setHeight(this.ch);
			this.cardSetDrawer.setFs(this.fs);
			
			this.cardSetDrawer.draw(g);
		}
		
		this.cardSetDrawer.setImages(this.board.boardCards);
		this.cardSetDrawer.setPosX(vs);
		this.cardSetDrawer.setPosY(hs);
		this.cardSetDrawer.setWidth(this.cw);
		this.cardSetDrawer.setHeight(this.ch);
		this.cardSetDrawer.setFs(1.05);
		this.cardSetDrawer.draw(g);
	}
	
	private void paintText(Graphics g) {
		double[] hString = new double[]{-5, -5, -5, this.ch + 12, this.ch + 12, this.ch + 12, this.ch + 12, -5, -5};
		
		for (int i = 0; i < this.board.players.size(); ++i) {
			String hand = this.board.players.get(i).hand.toString();
			g.drawString(hand, (int) this.wPlayer[i], (int) (this.hPlayer[i] + hString[i]));
		}
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
		
		this.wPlayer[8] = v4;
		this.hPlayer[8] = h4;
	}
	
	private void drawImage(Graphics g, Image image, double x, double y, double width, double height) {
		g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
	}
	
	@Override
	public void notify(Config config) {
		this.config = config;
	}
}
