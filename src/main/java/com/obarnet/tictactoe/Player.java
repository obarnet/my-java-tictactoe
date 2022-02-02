package com.obarnet.tictactoe;

public abstract class Player {
	public enum Type {
		HUMAN,
		MACHINE;
	}
	
	protected char token;
	protected Player.Type type;
	protected static final char[] plays = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
	protected String name;
	
	public abstract int play();

	public String getName() {
		return name;
	}

	public char getToken() {
		return token;
	}

	public Player.Type getType() {
		return type;
	}

	public static char[] getPlays() {
		return plays;
	}
	
}
