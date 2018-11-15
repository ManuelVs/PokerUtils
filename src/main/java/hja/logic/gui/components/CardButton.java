package hja.logic.gui.components;

import javax.swing.*;
import java.awt.*;

public class CardButton extends JButton {
	private Color selectedBackgroundColor;
	
	private boolean selected = false;
	
	public CardButton(String text) {
		super(text);
		super.setContentAreaFilled(false);
		
		this.setMargin(new Insets(0, 0, 0, 0));
		this.setFocusPainted(false);
		selectedBackgroundColor = getBackground();
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		repaint();
	}
	
	public void setSelectedBackground(Color color) {
		this.selectedBackgroundColor = color;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
			g.setColor(getBackground());
		}
		else {
			if (!selected) g.setColor(getBackground());
			else g.setColor(selectedBackgroundColor);
		}
		
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
}