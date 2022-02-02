package com.obarnet.tictactoe;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Tictactoe {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Menu.show();
		System.out.println("\nThank you for playing Tic-Tac-Toe!");
		scanner.close();
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
	
}
