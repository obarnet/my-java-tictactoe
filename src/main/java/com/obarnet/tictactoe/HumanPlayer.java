package com.obarnet.tictactoe;

import java.awt.Color;

public class HumanPlayer extends Player {
	private static final long serialVersionUID = 8136530518611238695L;
	public static int TOTAL = 0;
	
	public HumanPlayer() {
		type = Type.HUMAN;
		token = 'X';
		name = "Human " + ++HumanPlayer.TOTAL;
		tokenColor  = new Color(0, 0, 255);
	}
	
	public HumanPlayer(char token) {
		type = Type.HUMAN;
		this.token = token;
		tokenColor  = new Color(0, 0, 255*HumanPlayer.TOTAL++);
		name = "Human " + HumanPlayer.TOTAL;
	}

	@Override
	public int[] play(char[][] plays) {
		return null;
	}
}
