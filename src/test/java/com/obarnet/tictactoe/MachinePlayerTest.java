package com.obarnet.tictactoe;

//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
import org.junit.Test;

import com.obarnet.tictactoe.Player.Type;

public class MachinePlayerTest {
	private static final MachinePlayer player = new MachinePlayer();
	
	@Test
	public void testGetName() {
		//MachinePlayer player = new MachinePlayer();
		assertEquals(player.getName(), "Terminator 1");
	}

	@Test
	public void testGetToken() {
		//MachinePlayer player = new MachinePlayer();
		assertEquals(player.getToken(), 'O');
	}

	@Test
	public void testGetType() {
		//MachinePlayer player = new MachinePlayer();
		assertEquals(player.getType(), Type.MACHINE);
	}

}