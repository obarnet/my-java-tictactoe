package com.obarnet.tictactoe;

public class HumanPlayer extends Player {
	private static final long serialVersionUID = 8136530518611238695L;
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
	public int play(char[] plays) {
		int choice;
		while(true) {
			System.out.println("Your turn (1 - 9)");
			
			String strChoice = Tictactoe.scanner.nextLine();
			System.out.println(strChoice.toLowerCase());
			if (strChoice.toLowerCase().equals("exit")) {
				System.out.println(strChoice);
				return -1;
			}
			try {
				choice = Integer.parseInt(strChoice);
			} catch (NumberFormatException e) {
				System.out.println("You didn't enter a valid Integer, please try again!");
				choice = 0;
			}
			
			if (choice >= 1 || choice <= 9) {
				if (plays[choice - 1] == ' ')
					break;
			}
		}
		return choice;
	}
}
