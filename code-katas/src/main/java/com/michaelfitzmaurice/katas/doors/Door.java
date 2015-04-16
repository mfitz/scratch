package com.michaelfitzmaurice.katas.doors;

public class Door {
	
	private int number;
	private boolean open;
	
	public Door(int number) {
		super();
		this.number = number;
		this.open = false;
	}

	public void flip() {
		open = ! open;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public int getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + (open ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Door other = (Door) obj;
		if (number != other.number)
			return false;
		if (open != other.open)
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Door [number=" + number + ", open=" + open + "]";
	}
	
}