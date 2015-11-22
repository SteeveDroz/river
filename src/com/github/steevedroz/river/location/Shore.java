package com.github.steevedroz.river.location;

import java.util.ArrayList;

import com.github.steevedroz.river.passenger.Passenger;

/**
 * This class represents one of the two shores of the river.
 * 
 * @author Steeve Droz
 * 
 */
public class Shore extends Location {
	/**
	 * This constructor creates a new shore.
	 * 
	 * @param name
	 *            The name of the shore.
	 */
	public Shore(String name) {
		super(name);
	}

	/**
	 * Returns a deep copy of the shore.
	 * 
	 * @return A deep copy.
	 */
	public Shore cloneOf() {
		Shore shore = new Shore(name);
		shore.passengers = new ArrayList<Passenger>();
		shore.passengers.addAll(passengers);
		return shore;
	}
}
