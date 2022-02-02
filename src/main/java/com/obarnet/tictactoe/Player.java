package com.obarnet.tictactoe;

import java.io.Serializable;

public abstract class Player implements Serializable {
	private static final long serialVersionUID = -7562119652643315267L;

	public enum Type {
		HUMAN,
		MACHINE;
	}
	
	protected char token;
	protected Player.Type type;
	protected String name;
	
	public abstract int play(char[] plays);

	public String getName() {
		return name;
	}

	public char getToken() {
		return token;
	}

	public Player.Type getType() {
		return type;
	}
}
