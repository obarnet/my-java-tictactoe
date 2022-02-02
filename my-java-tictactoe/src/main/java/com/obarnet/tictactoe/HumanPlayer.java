package com.obarnet.tictactoe;

public class HumanPlayer extends Player {
	public static int TOTAL = 0;
	
	public HumanPlayer() {
		type = Type.HUMAN;
		token = 'X';
		name = "Human " + ++HumanPlayer.TOTAL;
	}
	
	public HumanPlayer(char token) {
		type = Type.HUMAN;
		this.token = token;
		name = "Human " + ++HumanPlayer.TOTAL;
	}

	@Override
	public int play() {
		System.out.println("Your turn (1 - 9)");
		int choice;
		while(true) {
			choice = Tictactoe.scanner.nextInt();
			if (choice >= 1 || choice <= 9) {
				if (plays[choice - 1] == ' ')
					break;
			}
		}
		return choice;
	}
}
