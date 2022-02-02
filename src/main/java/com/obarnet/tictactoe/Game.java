package com.obarnet.tictactoe;

import java.io.Serializable;

public class Game implements Serializable {

	private static final long serialVersionUID = -5892384074229223002L;
	private static final int MAX_PLAYS = 9;
	private Player[] players = new Player[2];
	private int winner = 0;
	private int play_count = 0;
	private char plays[] = new char[9];

	public Game(int playerMode) {
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
			default:
				break;
		}

		for(int i=0; i<plays.length; i++) {
			plays[i] = ' ';
		}
	}
	
	public char[] getPlays() {
		return plays;
	}
	
	public int getPlayCount() {
		return play_count;
	}
	
	public boolean Start() {
		System.out.println("The first player to play is :" + players[winner].getName());
		
		int currentPlayerIndex = winner;
		
		while(true) {
			Player currentPlayer = players[currentPlayerIndex];
			int choice = currentPlayer.play(plays);
			if (choice == -1) {
				return false;
			}
			if (!playTurn(choice, currentPlayer.getToken())) {
				break;
			}
			currentPlayerIndex = 1 - currentPlayerIndex;
			
		}
		
		printTable(plays);
		System.out.println("Play Again? y/n");

		// Create Quit Game Method

		play_count = 0;
		HumanPlayer.TOTAL = 0;
		MachinePlayer.TOTAL = 0;
		
		
		String again = Tictactoe.scanner.next();
		if (!again.equals(new String("y"))) {
			return false;
		} else {
			return true;
		}
	}
	
	public void printTable(char[] placements) {
		for(int i=0; i<3; i++) {
			System.out.println(" --- --- ---");
			System.out.print("|");
			for(int j=0; j<3; j++) {
				int index = 3 * i + j;
				System.out.print(" "+placements[index]+" |");
			}
			System.out.print("\n");
		}
		System.out.println(" --- --- ---");
	}
	
	public void printTable() {
		printTable(plays);
	}
	
	public boolean playTurn(int position, char token) {
		plays[position-1] = token;
		boolean hasNextTurn = false;
		if (somebodyWon()) {
			System.out.println(players[winner].getName() + " won! ");
		} else if (++play_count >= MAX_PLAYS) {
			System.out.println("Nobody Won! ");
			winner = 1 - winner;
		} else {
			printTable(plays);
			hasNextTurn = true;
		}
		return hasNextTurn;
	}
	
	public boolean somebodyWon() {
		char winnerToken = ' ';
		
		if (plays[0] == plays[4] && plays[4] == plays[8] && plays[0] != ' ') {
			winnerToken = plays[0];
		} else if (plays[2] == plays[4]	&& plays[4] == plays[6]	&& plays[2] != ' ') {
			winnerToken = plays[2];
		} else {
			for(int i=0; i<3; i++) {
				if (plays[3 * i] == plays[3 * i + 1] && plays[3 * i + 1] == plays[3 * i + 2] && plays[3 * i] != ' ') {
					winnerToken = plays[3 * i];
				} else if (plays[i] == plays[3 + i]	&& plays[3 + i] == plays[6 + i] && plays[i] != ' ') {
					winnerToken = plays[i];
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
}
