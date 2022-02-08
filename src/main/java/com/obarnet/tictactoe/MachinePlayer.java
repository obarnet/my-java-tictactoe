package com.obarnet.tictactoe;

import java.awt.Color;
import java.util.Random;

public class MachinePlayer extends Player {
	private static final long serialVersionUID = 5847531776646182431L;
	public static int TOTAL = 0;
	
	public MachinePlayer() {
		type = Type.MACHINE;
		token = 'O';
		name = "Terminator " + ++MachinePlayer.TOTAL;
		tokenColor  = new Color(0, 0, 0);
	}
	
	public MachinePlayer(char token) {
		type = Type.MACHINE;
		this.token = token;
		tokenColor  = new Color(0, 0, 255*MachinePlayer.TOTAL);
		name = "Terminator " + ++MachinePlayer.TOTAL;
	}
	
	@Override
	public int[] play(char[][] plays) {
		int x, y;
		while(true) {
			x = new Random().nextInt(3);
			y = new Random().nextInt(3);
			if (x >= 0 && x < 3 && y >= 0 && y < 3) {
				if (plays[x][y] == ' ') {
					break;
				}
			}
		}

		return new int[] {x,y};
	}
}
