package hja.logic.gui.components;

import hja.logic.Utils;
import hja.logic.gui.model.ListChangeListener;
import hja.logic.gui.model.Model;
import hja.pokerutils.Board.Board;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UtilsPanel extends JPanel implements ListChangeListener {
	protected Model model;
	protected ArrayList<Board> boards;
	protected JList<Board> boardJList;
	
	public UtilsPanel(Model model) {
		this.model = model;
		this.boards = new ArrayList<>();
		initGUI();
	}
	
	private void initGUI() {
		this.boardJList = new JList<>();
		this.boardJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.boardJList.setLayoutOrientation(JList.VERTICAL);
		this.boardJList.setCellRenderer(new ElementRenderer());
		this.boardJList.addListSelectionListener(this::itemSelectedListener);
		this.updateList();
		
		JButton loadButton = new JButton("Load file");
		loadButton.addActionListener(this::loadButtonListener);
		
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(boardJList), BorderLayout.CENTER);
		this.add(loadButton, BorderLayout.SOUTH);
	}
	
	private void itemSelectedListener(ListSelectionEvent e) {
		model.selectBoard(this.boardJList.getSelectedIndex());
	}
	
	private void loadButtonListener(ActionEvent actionEvent) {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		File boardListFile = chooser.getSelectedFile();
		try {
			this.model.changeFile(boardListFile.getAbsolutePath());
		} catch (IOException e) {
			String message = "Error when reading the selected file." + System.lineSeparator() + "Details: " + e.getMessage();
			JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	private void updateList() {
		DefaultListModel<Board> listModel = new DefaultListModel<Board>();
		
		for (Board board : this.boards) {
			listModel.addElement(board);
		}
		this.boardJList.setModel(listModel);
	}
	
	@Override
	public void notify(ArrayList<Board> items) {
		this.boards = items;
		this.updateList();
		
		this.boardJList.setSelectedIndex(0);
		model.selectBoard(0);
	}
	
	
	class ElementRenderer extends JPanel implements ListCellRenderer<Board> {
		private JLabel boardCardsLabel;
		private JLabel numPlayersLabel;
		
		public ElementRenderer() {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.boardCardsLabel = new JLabel();
			this.numPlayersLabel = new JLabel();
			
			this.add(boardCardsLabel);
			this.add(numPlayersLabel);
			
			setOpaque(true);
		}
		
		@Override
		public Component getListCellRendererComponent(JList<? extends Board> list, Board value, int index, boolean isSelected, boolean cellHasFocus) {
			this.boardCardsLabel.setText("Cartas en la mesa: " + Utils.cardsToString(value.boardCards));
			this.numPlayersLabel.setText("Número de jugadores: " + value.players.size());
			
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			
			return this;
		}
	}
}
