package com.michaelfitzmaurice.katas.doors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class DoorTest {
	
	@Test
	public void usesDoorNumberGiveAtConstruction() {
		
		int doorNumber = new Random().nextInt(1_000_000);
		Door door = new Door(doorNumber);
		assertEquals( doorNumber, door.getNumber() );
	}
	
	@Test
	public void defaultsToClosedState() {
		
		Door door = new Door(1);
		assertFalse( "Door should initially be closed, but is open;", 
						door.isOpen() );
	}
	
	@Test
	public void opensAClosedDoorWhenFlipped() {
		
		Door door = new Door(1);
		assertFalse( "Door should initially be closed, but is open;", 
						door.isOpen() );
		door.flip();
		assertTrue( "Door should now be open, but is closed;", 
					door.isOpen() );
	}

}
