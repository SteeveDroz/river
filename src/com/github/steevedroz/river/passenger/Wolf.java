package com.github.steevedroz.river.passenger;

import java.util.List;

/**
 * This class defines a wolf as in the Wolf-Goat-Cowl problem. All the settings
 * are predefined.
 * 
 * @author Steeve Droz
 * @see Goat
 * @see Cowl
 * @see Farmer
 */
public final class Wolf extends Passenger {
	/**
	 * The name of the wolf.
	 */
	public static final String NAME = "Loup";
	/**
	 * The weight of the wolf.
	 */
	public static final int WEIGHT = 1;

	/**
	 * Creates a wolf with default settings.
	 */
	public Wolf() {
		super(NAME, WEIGHT);
	}

	/**
	 * Returns if the wolf can navigate the boat.
	 * 
	 * @return <code>false</code>
	 */
	@Override
	public boolean isNavigator() {
		return false;
	}

	/**
	 * Returns a passenger among those present that can kill the wolf.
	 * 
	 * @param passengers
	 *            The list of passengers.
	 * @return <code>null</code>, the wolf has no predator.
	 */
	@Override
	public List<Passenger> getPresence(List<Passenger> passengers) {
		return null;
	}

	/**
	 * Returns a deep copy of the wolf.
	 * 
	 * @return A new wolf.
	 */
	@Override
	public Passenger cloneOf() {
		return new Wolf();
	}
}
