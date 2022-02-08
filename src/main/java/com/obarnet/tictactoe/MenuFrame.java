package com.obarnet.tictactoe;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class MenuFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -2698885837785634264L;
	private JPanel mainMenuPanel;
	private JButton newGameBtn, resumeGameBtn, quitBtn;
	private GameModeFrame gameMode;
	private static MenuFrame mainMenu;
	
	public MenuFrame(String name) {
		mainMenuPanel = new JPanel();
		
		mainMenuPanel.setSize(Tictactoe.CONTENT_SIZE);
		mainMenuPanel.setPreferredSize(Tictactoe.CONTENT_SIZE);
		mainMenuPanel.setMaximumSize(Tictactoe.CONTENT_SIZE);
		setupPanel();
		add(mainMenuPanel);
		setTitle(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();

		gameMode = new GameModeFrame(name);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static MenuFrame getMenuScreen() {
		if (mainMenu == null) {
			mainMenu = new MenuFrame("Tic-Tac-Toe");
		}
		return mainMenu;
	}
	
	public void showMenu() {
		mainMenu.setVisible(true);
		File gameFile = new File("savegame.ttt");
		if (gameFile.exists()) {
			mainMenu.resumeGameBtn.setVisible(true);
		} else {
			mainMenu.resumeGameBtn.setVisible(false);
		}
	}
	
	private void setupPanel() {
		BoxLayout layout = new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS);
		mainMenuPanel.setLayout(layout);

		Dimension prefSpacing = new Dimension(20, 30);
		Dimension intraBtnSpacing = new Dimension(10, 20);
		
		mainMenuPanel.add(new Box.Filler(intraBtnSpacing, intraBtnSpacing, intraBtnSpacing));
		JLabel gameName = new JLabel("Tic Tac Toe");
		gameName.setFont(new Font(Font.SANS_SERIF, Font.ITALIC | Font.BOLD, 30));
		gameName.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuPanel.add(gameName);
		mainMenuPanel.add(new Box.Filler(prefSpacing, prefSpacing, prefSpacing));		
		
		newGameBtn = new JButton("New Game");
		newGameBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		newGameBtn.setMargin(new Insets(5, 4, 5, 4));
		newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		newGameBtn.setForeground(Color.BLUE);
		newGameBtn.setFocusPainted(false);
		newGameBtn.addActionListener(this);
		mainMenuPanel.add(newGameBtn);
		
		mainMenuPanel.add(new Box.Filler(intraBtnSpacing, intraBtnSpacing, intraBtnSpacing));
		
		resumeGameBtn = new JButton("Resume");
		resumeGameBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		resumeGameBtn.setMargin(new Insets(5, 15, 5, 15));
		resumeGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		resumeGameBtn.setForeground(Color.BLUE);
		resumeGameBtn.setFocusPainted(false);
		resumeGameBtn.addActionListener(this);
		mainMenuPanel.add(resumeGameBtn);

		mainMenuPanel.add(new Box.Filler(intraBtnSpacing, intraBtnSpacing, intraBtnSpacing));
		
		File gameFile = new File("savegame.ttt");
		resumeGameBtn.setVisible(gameFile.exists());
		
		quitBtn = new JButton("Quit");
		quitBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		quitBtn.setMargin(new Insets(5, 34, 5, 34));
		quitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitBtn.setForeground(Color.BLUE);
		quitBtn.setFocusPainted(false);
		quitBtn.addActionListener(this);
		mainMenuPanel.add(quitBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New Game")) {
			setVisible(false);
			gameMode.setVisible(true);
			dispose();
		} else if (e.getActionCommand().equals("Resume")) {
			try {
				Game.LoadGame();
			} catch (ClassNotFoundException | IOException e1) {
				System.out.println("Unable to load saved game!");
				Game.getGameLogic();
			}
		} else if (e.getActionCommand().equals("Quit") ) {
			System.exit(0);
		}
		
	}

}

class GameModeFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 6433841769134307289L;
	private JPanel gameModePanel;
	private JButton singlePlayerBtn, multiPlayerBtn, trainBtn;
	
	public GameModeFrame(String name) {
		gameModePanel = new JPanel();
		gameModePanel.setSize(Tictactoe.CONTENT_SIZE);
		gameModePanel.setPreferredSize(Tictactoe.CONTENT_SIZE);
		gameModePanel.setMaximumSize(Tictactoe.CONTENT_SIZE);
		setupPanel();
		add(gameModePanel);
		setTitle(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(false);
	}
	
	private void setupPanel() {
		BoxLayout layout = new BoxLayout(gameModePanel, BoxLayout.Y_AXIS);
		gameModePanel.setLayout(layout);
		
		Dimension prefSpacing = new Dimension(20, 30);
		Dimension intraBtnSpacing = new Dimension(10, 20);
		
		gameModePanel.add(new Box.Filler(intraBtnSpacing, intraBtnSpacing, intraBtnSpacing));
		JLabel gameMode = new JLabel("Game Mode");
		gameModePanel.add(gameMode);
		gameMode.setFont(new Font(Font.SANS_SERIF, Font.ITALIC | Font.BOLD, 30));
		gameMode.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameModePanel.add(gameMode);
		gameModePanel.add(new Box.Filler(prefSpacing, prefSpacing, prefSpacing));		
		
		singlePlayerBtn = new JButton("Single player");
		singlePlayerBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		singlePlayerBtn.setMargin(new Insets(5, 4, 5, 4));
		singlePlayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		singlePlayerBtn.setForeground(Color.BLUE);
		singlePlayerBtn.setFocusPainted(false);
		singlePlayerBtn.addActionListener(this);
		gameModePanel.add(singlePlayerBtn);
		gameModePanel.add(new Box.Filler(intraBtnSpacing, intraBtnSpacing, intraBtnSpacing));

		multiPlayerBtn = new JButton("Multiplayer");
		multiPlayerBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		multiPlayerBtn.setMargin(new Insets(5, 14, 5, 14));
		multiPlayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		multiPlayerBtn.setForeground(Color.BLUE);
		multiPlayerBtn.setFocusPainted(false);
		multiPlayerBtn.addActionListener(this);
		gameModePanel.add(multiPlayerBtn);
		gameModePanel.add(new Box.Filler(intraBtnSpacing, intraBtnSpacing, intraBtnSpacing));

		trainBtn = new JButton("Train");
		trainBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		trainBtn.setMargin(new Insets(5, 42, 5, 42));
		trainBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		trainBtn.setForeground(Color.BLUE);
		trainBtn.setFocusPainted(false);
		trainBtn.addActionListener(this);
		gameModePanel.add(trainBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Single player")) {
			setVisible(false);
			Game.getGameLogic(1).showGameBoard();
			dispose();
		} else if (e.getActionCommand().equals("Multiplayer")) {
			setVisible(false);
			Game.getGameLogic(2).showGameBoard();
			dispose();
		} else if (e.getActionCommand().equals("Train")) {
			setVisible(false);
			Game.getGameLogic(3).showGameBoard();
			dispose();
		} else if (e.getActionCommand().equals("Back") ) {
			setVisible(false);
			MenuFrame.getMenuScreen().setVisible(true);
			dispose();
		}
		
	}

}

