package com.obarnet.tictactoe;

import java.util.Scanner;
import java.awt.Dimension;

public class Tictactoe {

	public static Scanner scanner = new Scanner(System.in);
	public static final Dimension CONTENT_SIZE = new Dimension(300, 300);
	public static void main(String[] args) {
		MenuFrame.getMenuScreen();
		System.out.println("\nThank you for playing Tic-Tac-Toe!");
		scanner.close();
	}
}
