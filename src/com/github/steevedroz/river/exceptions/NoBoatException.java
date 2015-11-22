package com.github.steevedroz.river.exceptions;

import com.github.steevedroz.river.passenger.Passenger;

/**
 * This method is thrown when a passenger tries to board on the boat but they
 * are not on the same shore.
 * 
 * @author Steeve Droz
 * 
 */
public class NoBoatException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * The passenger that tries to board.
	 */
	private Passenger passenger;

	/**
	 * This constructor saves the identity of the passenger that unsuccessfully
	 * tries to board.
	 * 
	 * @param passenger
	 *            The passenger that is trying to board.
	 */
	public NoBoatException(Passenger passenger) {
		this.passenger = passenger;
	}

	/**
	 * The method returns an error message.
	 * 
	 * @return "PASSENGER_NAME can't board, the boat is on the other shore".
	 */
	@Override
	public String getMessage() {
		return passenger.getName()
				+ " can't board, the boat is on the other shore";
	}
}
