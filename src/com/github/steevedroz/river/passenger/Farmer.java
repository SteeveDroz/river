package com.github.steevedroz.river.passenger;

import java.util.List;

/**
 * This class defines a farmer as in the Wolf-Goat-Cowl problem. All the
 * settings are predefined.
 * 
 * @author Steeve Droz
 * @see Wolf
 * @see Goat
 * @see Cowl
 */
public final class Farmer extends Passenger {
	/**
	 * The name of the farmer.
	 */
	public static final String NAME = "Fermier";
	/**
	 * The weight of the farmer.
	 */
	public static final int WEIGHT = 0;

	/**
	 * Creates a farmer with default settings.
	 */
	public Farmer() {
		super(NAME, WEIGHT);
	}

	/**
	 * Returns if the farmer can navigate the boat.
	 * 
	 * @return <code>true</code>
	 */
	@Override
	public boolean isNavigator() {
		return true;
	}

	/**
	 * Returns a passenger among those present that can kill the farmer.
	 * 
	 * @param passengers
	 *            The list of passengers.
	 * @return <code>null</code>, the farmer has no predator.
	 */
	@Override
	public List<Passenger> getPresence(List<Passenger> passengers) {
		return null;
	}

	/**
	 * Returns a deep copy of the farmer.
	 * 
	 * @return A new farmer.
	 */
	@Override
	public Farmer cloneOf() {
		return new Farmer();
	}
}
