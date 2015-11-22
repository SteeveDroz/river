package com.github.steevedroz.river.exceptions;

import com.github.steevedroz.river.location.Boat;
import com.github.steevedroz.river.passenger.Passenger;

/**
 * This exception is thrown when the boat tries to cross the river but none of
 * its passenger is capable of navigation.
 * 
 * @author Steeve Droz
 * @see Passenger#isNavigator()
 * 
 */
public class NoNavigatorException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * The boat that tries to cross the river.
	 */
	private Boat boat;

	/**
	 * This constructor saves the boat that tries to cross the river.
	 * 
	 * @param boat
	 *            The boat.
	 */
	public NoNavigatorException(Boat boat) {
		this.boat = boat;
	}

	/**
	 * This method returns an error message.
	 * 
	 * @return "No navigator present on BOAT_NAME".
	 */
	@Override
	public String getMessage() {
		return "No navigator present on " + boat;
	}
}
