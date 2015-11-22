package com.github.steevedroz.river.passenger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines a goat as in the Wolf-Goat-Cowl problem. All the settings
 * are predefined.
 * 
 * @author Steeve Droz
 * @see Wolf
 * @see Cowl
 * @see Farmer
 */
public final class Goat extends Passenger {
	/**
	 * The name of the goat.
	 */
	public static final String NAME = "Chèvre";
	/**
	 * The weight of the goat.
	 */
	public static final int WEIGHT = 1;

	/**
	 * Creates a goat with default settings.
	 */
	public Goat() {
		super(NAME, WEIGHT);
	}

	/**
	 * Returns if the goat can navigate the boat.
	 * 
	 * @return <code>false</code>
	 */
	@Override
	public boolean isNavigator() {
		return false;
	}

	/**
	 * Returns a passenger among those present that can kill the goat.
	 * 
	 * @param passengers
	 *            The list of passengers.
	 * @return the {@link Wolf}, if it is present and the {@link Farmer} is not.
	 */
	@Override
	public List<Passenger> getPresence(List<Passenger> passengers) {
		List<Passenger> wolf = new ArrayList<Passenger>();
		for (Passenger passenger : passengers) {
			if (passenger.getClass() == Farmer.class) {
				return null;
			}
			if (passenger.getClass() == Wolf.class) {
				wolf.add(passenger);
			}
		}
		return wolf.size() > 0 ? wolf : null;
	}

	/**
	 * Returns a deep copy of the goat.
	 * 
	 * @return A new goat.
	 */
	@Override
	public Passenger cloneOf() {
		return new Goat();
	}
}
