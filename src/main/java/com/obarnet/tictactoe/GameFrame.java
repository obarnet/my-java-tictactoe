package com.obarnet.tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener {
	private JButton[][] board = new JButton[3][3];
	private JPanel gamePanel;
	static private GameFrame gameFrame;
	
	private GameFrame(String name) {
		gamePanel = new JPanel();
		gamePanel.setSize(Tictactoe.CONTENT_SIZE);
		gamePanel.setPreferredSize(Tictactoe.CONTENT_SIZE);
		gamePanel.setMaximumSize(Tictactoe.CONTENT_SIZE);
		setupPanel();
		add(gamePanel);
		setTitle(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static GameFrame getGameScreen() {
		if (gameFrame == null) {
			gameFrame = new GameFrame("Tic-Tac-Toe");
		}
		return gameFrame;
	}
	
	public void setupPanel() {
		GridLayout layout = new GridLayout(3, 3);
		gamePanel.setLayout(layout);
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				JButton btn = new JButton();
				btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
				btn.addActionListener(this);
				board[i][j] = btn;
				gamePanel.add(btn);
			}
		}
	}
	
	private static final long serialVersionUID = 734547489676820494L;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (((JButton)e.getSource()).getText().equals("")) {
			for (int i=0; i<3; i++) {
				for (int j=0; j<3; j++) {
					if (((JButton)e.getSource()).equals(board[i][j])) {
						Game.getGameLogic().play(i, j);
						return;
					}
				} 
			}
		}
	}

	public void showBoard() {
		setVisible(true);
	}
	
	public void clearBoard() {
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				board[i][j].setText("");
			}
		}
	}

	public void updateBoard(int i, int j, Player player) {
		if (player == null) {
			board[i][j].setForeground(Color.black);
			board[i][j].setText("");
			return;
		}
		
		board[i][j].setForeground(player.getTokenColor());
		board[i][j].setText(String.valueOf(player.getToken()));
	}

	public void GoToMenu() {
		setVisible(false);
		MenuFrame.getMenuScreen().showMenu();
		dispose();
	}

	public void QuitGame() {
		System.exit(0);
	}

}
