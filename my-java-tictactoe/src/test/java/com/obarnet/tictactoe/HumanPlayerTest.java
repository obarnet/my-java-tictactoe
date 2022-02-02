package com.obarnet.tictactoe;

//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
import org.junit.Test;

import com.obarnet.tictactoe.Player.Type;

public class HumanPlayerTest {
	private static final HumanPlayer player = new HumanPlayer();
	
	@Test
	public void testGetName() {
		//HumanPlayer player = new HumanPlayer();
		assertEquals("Human 1", player.getName());
	}

	@Test
	public void testGetToken() {
		//HumanPlayer player = new HumanPlayer();
		assertEquals(player.getToken(), 'X');
	}

	@Test
	public void testGetType() {
		//HumanPlayer player = new HumanPlayer();
		assertEquals(player.getType(), Type.HUMAN);
	}

}
