package com.obarnet.tictactoe;

import java.util.Random;

public class MachinePlayer extends Player {
	public static int TOTAL = 0;
	
	public MachinePlayer() {
		type = Type.MACHINE;
		token = 'O';
		name = "Terminator " + ++MachinePlayer.TOTAL;
	}
	
	public MachinePlayer(char token) {
		type = Type.MACHINE;
		this.token = token;
		name = "Terminator " + ++MachinePlayer.TOTAL;
	}
	
	@Override
	public int play() {
		System.out.println("\nMachine turn!");
		int choice;
		while(true) {
			choice = new Random().nextInt(9) + 1;
			if (choice >= 1 || choice <= 9) {
				if (plays[choice - 1] == ' ')
					break;
			}
		}

		return choice;
	}
}
