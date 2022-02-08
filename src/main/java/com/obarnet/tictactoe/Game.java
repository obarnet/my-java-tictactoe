package com.obarnet.tictactoe;

import java.io.*;

import javax.swing.JOptionPane;

public class Game implements Serializable {

	private static final long serialVersionUID = -5892384074229223002L;
	private static final int MAX_PLAYS = 9;
	private Player[] players = new Player[2];
	private int winner = 0;
	private int play_count = 0;
	private int currentPlayerIndex = 0;
	private char plays[][] = {{' ',' ',' '}, {' ',' ',' '}, {' ',' ',' '}};
	static private Game gameLogic;
	private int currentPlayerMode;
	private static int trainCount = 0;
	public Game(int playerMode) {
		setPlayerMode(playerMode);
	}
	
	public void showGameBoard() {
		GameFrame.getGameScreen().showBoard();
		if (getCurrentPlayer().type == Player.Type.MACHINE) {
			machinePlays();
		}
	}

	public void setPlayerMode(int playerMode) {
		currentPlayerMode = playerMode;
		switch (playerMode) {
			case 1: {
				players[0] = new HumanPlayer();
				players[1] = new MachinePlayer();
				break;
			}
			case 2: {
				players[0] = new HumanPlayer('X');
				players[1] = new HumanPlayer('O');
				break;
			}
			case 3: {
				players[0] = new MachinePlayer('X');
				players[1] = new MachinePlayer('O');
				break;
			}
			default:
				break;
		}
	}
	
	public int getPlayCount() {
		return play_count;
	}
	
	public Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}
	
	public static Game getGameLogic(int mode) {
		if(gameLogic == null) {
			gameLogic = new Game(mode);
		} else if (mode != gameLogic.currentPlayerMode) {
			gameLogic.setPlayerMode(mode);
		}
		return gameLogic;
	}
	
	public static Game getGameLogic() {
		return getGameLogic(gameLogic.currentPlayerMode);
	}
	
	public void play(int x, int y) {
		plays[x][y] = getCurrentPlayer().token;

		GameFrame.getGameScreen().updateBoard(x, y, getCurrentPlayer());

		if(!playTurn()) {
			return;
		}
		
		if (getCurrentPlayer().type == Player.Type.MACHINE) {
			machinePlays();
		}
	}
	
	public void resetGame() {
		play_count = 0;
		HumanPlayer.TOTAL = 0;
		MachinePlayer.TOTAL = 0;
		GameFrame.getGameScreen().clearBoard();
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				plays[i][j] = ' ';
			}
		}
		File gameFile = new File("savegame.ttt");
		if (gameFile.exists()) {
			gameFile.delete();
		}
	}

	private void machinePlays() {
		int[] positions = getCurrentPlayer().play(plays);
		play(positions[0], positions[1]);
	}
	
	public boolean playTurn() {
		Object[] options = { "Play Again", "Go To Menu", "Quit Game" };
		boolean keepPlaying = true;
		String message;
		if (somebodyWon()) {
			message = players[winner].getName() + " won! ";
			currentPlayerIndex = winner;
			keepPlaying = false;
		} else if (++play_count >= MAX_PLAYS) {
			message = "Nobody Won! ";
			currentPlayerIndex = 1 - currentPlayerIndex;
			keepPlaying = false;
		} else {
			currentPlayerIndex = 1 - currentPlayerIndex;
			if (currentPlayerMode != 3) {
				try {
					SaveGame();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return keepPlaying;
		}
		int result;
		
		if (currentPlayerMode == 3) {
			if (++trainCount < 1000) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				result = JOptionPane.YES_OPTION;
			} else {
				trainCount = 0;
				result = JOptionPane.NO_OPTION;
			}
		} else {
			result = JOptionPane.showOptionDialog(null, null, message, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
		}
		
		if (result == JOptionPane.YES_OPTION) {
			resetGame();
			if (getCurrentPlayer().type == Player.Type.MACHINE) {
				machinePlays();
			}
		} else if (result == JOptionPane.NO_OPTION) {
			resetGame();
			GameFrame.getGameScreen().GoToMenu();
		} else {
			resetGame();
			GameFrame.getGameScreen().QuitGame();
		}
		
		return keepPlaying;
	}
	
	public boolean somebodyWon() {
		char winnerToken = ' ';
		
		if (plays[0][0] == plays[1][1] && plays[1][1] == plays[2][2] && plays[0][0] != ' ') {
			winnerToken = plays[0][0];
		} else if (plays[0][2] == plays[1][1]	&& plays[1][1] == plays[2][0]	&& plays[0][2] != ' ') {
			winnerToken = plays[0][2];
		} else {
			for(int i=0; i<3; i++) {
				if (plays[i][0] == plays[i][1] && plays[i][1] == plays[i][2] && plays[i][0] != ' ') {
					winnerToken = plays[i][0];
				} else if (plays[0][i] == plays[1][i]	&& plays[1][i] == plays[2][i] && plays[0][i] != ' ') {
					winnerToken = plays[0][i];
				}
			}
		}

		if (winnerToken == ' ') {
			return false;
		}
		if (players[0].getToken() == winnerToken) {
			winner = 0;
		} else {
			winner = 1;
		}
		
		return true;
	}
	
	
	public static void LoadGame() throws IOException,
										ClassNotFoundException {
		
		FileInputStream input = new FileInputStream("savegame.ttt");
		ObjectInputStream objReader = new ObjectInputStream(input);
		Game tttGame = (Game) objReader.readObject();
		objReader.close();
		gameLogic = tttGame;
		gameLogic.showGameBoard();
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				Player player = null;
				if (gameLogic.plays[i][j] == 'X') {
					player = gameLogic.players[0];
				} else if (gameLogic.plays[i][j] == 'O') {
					player = gameLogic.players[1];
				}
				GameFrame.getGameScreen().updateBoard(i, j, player);
			}
		}
	}
	
	public static void SaveGame() throws IOException {
		FileOutputStream input = new FileOutputStream("savegame.ttt");
		ObjectOutputStream objWriter = new ObjectOutputStream(input);
		objWriter.writeObject(getGameLogic());
		objWriter.close();
	}
}
