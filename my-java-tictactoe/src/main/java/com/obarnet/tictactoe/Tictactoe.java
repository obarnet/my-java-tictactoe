package com.obarnet.tictactoe;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Tictactoe {
	private static Player[] players = new Player[2];
	private static int winner = 0;
	public static Scanner scanner = new Scanner(System.in);
	private static final int MAX_PLAYS = 9;
	private static int play_count = 0;
	
	public static void main(String[] args) {
		char[] positions = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
		Date date = new Date();
		
		System.out.println(date.toString()+"\n");
		
		System.out.println("Welcome to Tic Tac Toe!");
		
		System.out.println("Single Player or Multiplayer?\n");
		
		int choice = 0;
		do {
			System.out.println("1. Single player");
			System.out.println("2. Multiplayer\n");
			
			String strChoice = scanner.nextLine();
			
			try {
				choice = Integer.parseInt(strChoice);
			} catch (NumberFormatException e) {
				System.out.println("You didn't enter a valid Integer, please try again!");
				choice = 0;
			}
		} while(choice != 1 && choice != 2);
		
		switch (choice) {
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
		
		System.out.println("Please play using the following table:");
		printTicTacToeTable(positions);
		
		boolean keepPlaying = true;
		while(keepPlaying) {
			keepPlaying = PlayGame();
		}
		System.out.println("\nThank you for playing Tic-Tac-Toe!");
		scanner.close();
	}
	
	private static boolean PlayGame() {
		System.out.println("The first player to play is :" + players[winner].getName());
		
		int currentPlayerIndex = winner;
		
		while(true) {
			Player currentPlayer = players[currentPlayerIndex];
			int choice = currentPlayer.play();
			
			if (!playTurn(choice, currentPlayer.getToken())) {
				break;
			}
			currentPlayerIndex = 1 - currentPlayerIndex;
			
		}
		
		printTicTacToeTable(Player.getPlays());
		System.out.println("Play Again? y/n");
		
		String again = scanner.next();
		if (!again.equals(new String("y"))) {
			return false;
		} else {
			for(int i=0; i<Player.getPlays().length; i++) {
				Player.getPlays()[i] = ' ';
				play_count = 0;
			}
			return true;
		}
	}

	public static void writeTextToFile(String text, String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(text + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printTicTacToeTable(char[] placements) {
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
	
	public static boolean playTurn(int position, char token) {
		Player.getPlays()[position-1] = token;
		boolean hasNextTurn = false;
		if (somebodyWon()) {
			System.out.println(players[winner].getName() + " won! ");
		} else if (++play_count >= MAX_PLAYS) {
			System.out.println("Nobody Won! ");
			winner = 1 - winner;
		} else {
			printTicTacToeTable(Player.getPlays());
			hasNextTurn = true;
		}
		return hasNextTurn;
	}
	
	public static boolean somebodyWon() {
		char winnerToken = ' ';
		char[] plays = Player.getPlays();
		
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
