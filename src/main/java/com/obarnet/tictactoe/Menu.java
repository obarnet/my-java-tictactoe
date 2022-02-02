package com.obarnet.tictactoe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Menu {
	private static Game tictactoeGame;
	
	public static void show() {
		char[] positions = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
		Date date = new Date();
		
		System.out.println(date.toString()+"\n");
		
		System.out.println("Welcome to Tic Tac Toe!");
		
		System.out.println("Single Player or Multiplayer?\n");
		File gameFile = new File("savegame.ttt");
		int choice = 0;
		do {
			System.out.println("1. Single player");
			System.out.println("2. Multiplayer");
			if (gameFile.exists()) {
				System.out.println("3. Resume Game");
			}
			System.out.println("4. Quit Game\n");
			
			String strChoice = Tictactoe.scanner.nextLine();
			
			try {
				choice = Integer.parseInt(strChoice);
			} catch (NumberFormatException e) {
				System.out.println("You didn't enter a valid Integer, please try again!");
				choice = 0;
			}
		} while(choice < 1 || choice > 4 || (choice == 3 && !gameFile.exists()));
		
		if (choice == 4) {
			return;
		}
		
		if (choice == 3) {
			try {
				tictactoeGame = LoadGame();
				gameFile.delete();
				tictactoeGame.printTable();
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Problems loading saved game! Creating a new Single Player game!");
				tictactoeGame = new Game(1);
				System.out.println("Please play using the following table:");
				tictactoeGame.printTable(positions);
			}
		} else {
			tictactoeGame = new Game(choice);
			System.out.println("Please play using the following table:");
			tictactoeGame.printTable(positions);
		}
		
		boolean keepPlaying = true;
		while(keepPlaying) {
			keepPlaying = tictactoeGame.Start();
		}
		if (tictactoeGame.getPlayCount() > 0) {
			try {
				Menu.SaveGame();
			} catch (IOException e) {
				System.out.println("Couldn't save current game!");
			}
		}
		Menu.show();
	}
	
	private static Game LoadGame() throws IOException,
										ClassNotFoundException {
		FileInputStream input = new FileInputStream("savegame.ttt");
		ObjectInputStream objReader = new ObjectInputStream(input);
		Game tttGame = (Game) objReader.readObject();
		objReader.close();
		
		return tttGame;
	}
	
	
	private static void SaveGame() throws IOException {
		FileOutputStream input = new FileOutputStream("savegame.ttt");
		ObjectOutputStream objWriter = new ObjectOutputStream(input);
		objWriter.writeObject(tictactoeGame);
		objWriter.close();
	}
}
