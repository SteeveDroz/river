package com.github.steevedroz.river.passenger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines a cowl as in the Wolf-Goat-Cowl problem. All the settings
 * are predefined.
 * 
 * @author Steeve Droz
 * @see Wolf
 * @see Goat
 * @see Farmer
 */
public final class Cowl extends Passenger {
	/**
	 * The name of the cowl.
	 */
	public static final String NAME = "Chou";
	/**
	 * The weight of the cowl.
	 */
	public static final int WEIGHT = 1;

	/**
	 * Creates a cowl with default settings.
	 */
	public Cowl() {
		super(NAME, WEIGHT);
	}

	/**
	 * Returns if the cowl can navigate the boat.
	 * 
	 * @return <code>false</code>
	 */
	@Override
	public boolean isNavigator() {
		return false;
	}

	/**
	 * Returns a passenger among those present that can kill the cowl.
	 * 
	 * @param passengers
	 *            The list of passengers.
	 * @return the {@link Goat}, if it is present and the {@link Farmer} is not.
	 */
	@Override
	public List<Passenger> getPresence(List<Passenger> passengers) {
		List<Passenger> goat = new ArrayList<Passenger>();
		for (Passenger passenger : passengers) {
			if (passenger.getClass() == Farmer.class) {
				return null;
			}
			if (passenger.getClass() == Goat.class) {
				goat.add(passenger);
			}
		}
		return goat.size() > 0 ? goat : null;
	}

	/**
	 * Returns a deep copy of the cowl.
	 * 
	 * @return A new cowl.
	 */
	@Override
	public Cowl cloneOf() {
		return new Cowl();
	}
}
