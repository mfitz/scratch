package com.michaelfitzmaurice.katas.doors;

import static java.lang.String.format;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Problem: You have 100 doors in a row that are all initially closed. 
 * You make 100 passes by the doors. The first time through, you visit 
 * every door and toggle the door (if the door is closed, you open it; 
 * if it is open, you close it). The second time you only visit every 
 * 2nd door (door #2, #4, #6, ...). The third time, every 3rd door 
 * (door #3, #6, #9, ...), etc, until you only visit the 100th door.
 * 
 * Question: What state are the doors in after the last pass? 
 *           Which are open, which are closed?
 *
 */
public class DoorFlipperTest {
	
	private DoorFlipper doorFlipper;
	
	@Before
	public void setup() {
		doorFlipper = new DoorFlipper(100);
	}

	@Test
	public void exposesADoorListOfTheSpecifiedSize() {
		
		int numberOfDoors = new Random().nextInt(1000);
		doorFlipper = new DoorFlipper(numberOfDoors);
		assertEquals(numberOfDoors, doorFlipper.allDoors().length );
	}
	
	@Test
	public void indexesDoorNumbersAtOne() {
		
		Door[] doors = doorFlipper.allDoors();
		assertEquals( 1, doors[0].getNumber() );
		assertEquals( doors.length, doors[doors.length -1].getNumber() );
	}
	
	@Test
	public void defaultsAllDoorsToClosed() {
		
		Door[] doors = doorFlipper.allDoors();
		for (int i = 0; i < doors.length; i++) {
			String failMsg = 
				format("Door number %s is open, but should be closed", 
						doors[i].getNumber() );
			assertFalse( failMsg, doors[i].isOpen() );
		}
	}
	
	@Test
	public void flipsAllDoorsOnFirstPass() {
		
		Door[] doors = doorFlipper.allDoors();
		
		doorFlipper.makePass();
		
		for (int i = 0; i < doors.length; i++) {
			String failMsg = 
				format("Door number %s is closed, but should be open", 
						doors[i].getNumber() );
			assertTrue( failMsg, doors[i].isOpen() );
		}
	}
	
	@Test
	public void flipsOnlyEveryOtherDoorOnSecondPass() {
		
		Door[] doors = doorFlipper.allDoors();
		doPasses(2);
		
		for (int i = 0; i < doors.length; i++) {
			assertDoorInExpectedState(2, doors[i]);
		}
	}
	
	@Test
	public void flipsEveryNthDoorOnNthPass() {
		
		int numberOfPasses = randomNumberOfPasses();
		doPasses(numberOfPasses);
		
		Door[] doors = doorFlipper.allDoors();
		for (int i = 0; i < doors.length; i++) {
			assertDoorInExpectedState(numberOfPasses, doors[i]);
		}
	}

	@Test
	public void defaultsOpenDoorListToEmpty() {
		assertEquals( 0, doorFlipper.openDoors().length);
	}
	
	@Test
	public void filtersClosedDoorsOutOfOpenDoorList() {
		
		int numberOfPasses = randomNumberOfPasses();
		Door[] expectedOpenDoors = expectedOpenDoors(numberOfPasses);
		
		doPasses(numberOfPasses);
		
		Door[] openDoors = doorFlipper.openDoors();
		assertArrayEquals("Unexpected open door list", 
							expectedOpenDoors, 
							openDoors);
	}
	
	@Test
	public void printOpenDoorsAfter100Passes() {
		
		doPasses(100);
		for( Door openDoor : doorFlipper.openDoors() ) {
			System.out.println(openDoor);
		}
	}

	
	///////////////////////////////////////////////////////
	// helper methods
	///////////////////////////////////////////////////////

	private int randomNumberOfPasses() {
		return new Random().nextInt(doorFlipper.allDoors().length);
	}
	
	private void doPasses(int numberOfPasses) {
		for (int i = 0; i < numberOfPasses; i++) {
			doorFlipper.makePass();
		}
	}
	
	private void assertDoorInExpectedState(int numberOfPasses, Door door) {
		
		boolean doorExpectedOpen = 
			doorExpectedToBeOpen(numberOfPasses, door);
		String expectedDoorState = doorExpectedOpen ? "open" : "closed";
		String failMsg = 
			format("Door %s should be in %s state, but is not;", 
						door.getNumber(), 
						expectedDoorState);
		assertEquals( failMsg, doorExpectedOpen, door.isOpen() );
	}

	private boolean doorExpectedToBeOpen(int numberOfPasses, Door door) {
		
		int doorNumber = door.getNumber();
		boolean doorExpectedOpen = false;
		// brute force to derive state after n passes
		for (int passNumber = 1; passNumber <= numberOfPasses; passNumber++) {
			if (doorNumber % passNumber == 0) {
				doorExpectedOpen = (! doorExpectedOpen);
			}
		}
		
		return doorExpectedOpen;
	}
	
	private Door[] expectedOpenDoors(int numberOfPasses) {
		
		List<Door> expectedOpenDoors = new ArrayList<Door>();
		
		Door[] doors = doorFlipper.allDoors();
		for (int i = 0; i < doors.length; i++) {
			Door door = doors[i];
			if ( doorExpectedToBeOpen(numberOfPasses, door) ) {
				expectedOpenDoors.add(door);
			}
		}
		
		return expectedOpenDoors.toArray(new Door[expectedOpenDoors.size()]);
	}
	
}
