package hja.logic.gui.components;

import hja.logic.gui.model.PlayerCardsListener;

import javax.swing.*;

public class PlayerPanel extends JPanel implements PlayerCardsListener {
	private int numPlayer;
	private ConfigWindow configWindow;
	
	private JTextField cardsText;
	
	public PlayerPanel(int numPlayer, ConfigWindow configWindow) {
		this.numPlayer = numPlayer;
		this.configWindow = configWindow;
		initGUI();
	}
	
	private void initGUI() {
		String playerName = "Jugador " + this.numPlayer;
		JLabel text = new JLabel(playerName);
		
		this.cardsText = new JTextField("");
		
		JButton randomButton = new JButton("Random");
		JButton setButton = new JButton("Set");
		JButton foldButton = new JButton("Fold");
		
		randomButton.addActionListener(e ->
			configWindow.setRandomCardsForPlayer(this.numPlayer)
		);
		
		setButton.addActionListener(e ->
			configWindow.setCardsForPlayer(this.numPlayer, cardsText.getText())
		);
		
		foldButton.addActionListener(e ->
			configWindow.foldPlayer(this.numPlayer)
		);
		
		//QUEDA CREAR GUI
	}
	
	@Override
	public void notify(int player, String cards) {
		if(player == this.numPlayer)
			this.cardsText.setText(cards);
	}
}
