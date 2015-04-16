package com.michaelfitzmaurice.katas.doors;

import java.util.Arrays;


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
public class DoorFlipper {
	
	private Door[] doors;
	private int numberOfPasses;
	
	public DoorFlipper(int numberOfDoors) {
		this.doors = new Door[numberOfDoors];
		this.numberOfPasses = 0;
		for (int i = 0; i < doors.length; i++) {
			doors[i] = new Door(i + 1);
		};
	}
	
	public void makePass() {
		numberOfPasses++;
		for (int i = 0; i < doors.length; i++) {
			Door door = doors[i];
			if (door.getNumber() % numberOfPasses == 0) {
				doors[i].flip();
			}
		};
	}
	
	public Door[] allDoors() {
		// return a defensive copy
		return Arrays.stream(doors).toArray(Door[]::new);
	}
	
	public Door[] openDoors() {
		return Arrays.stream(doors)
					.filter( door -> door.isOpen() )
					.toArray(Door[]::new);
	}

}
