package com.github.steevedroz.river.passenger;

import java.util.ArrayList;
import java.util.List;

import com.github.steevedroz.river.passenger.relation.RelationElement;

/**
 * This abstract class defines the general settings of a passenger.
 * 
 * @author Steeve Droz
 * 
 */
public abstract class Passenger implements RelationElement,
		Comparable<Passenger> {
	/**
	 * The name of the passenger.
	 */
	protected String name;
	/**
	 * The weight of the passenger.
	 */
	protected int weight;

	/**
	 * This constructor is called by subclasses to set the name and weight of
	 * the passenger.
	 * 
	 * @param name
	 *            The name of the passenger
	 * @param weight
	 *            The weight of the passenger
	 */
	public Passenger(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	/**
	 * Returns whether this passenger can navigate the boat or not.
	 * 
	 * @return The navigation ability of this passenger.
	 */
	public abstract boolean isNavigator();

	/**
	 * Returns whether this passenger is in the list of passengers.
	 * 
	 * @return <code>this</code> if it is there or <code>null</code> if it
	 *         isn't.
	 */
	@Override
	public List<Passenger> getPresence(List<Passenger> passengers) {
		List<Passenger> passengerResult = new ArrayList<Passenger>();
		for (Passenger passenger : passengers) {
			if (passenger.getName().equals(name)) {
				passengerResult.add(passenger);
			}
		}
		return passengerResult.size() > 0 ? passengerResult : null;
	}

	/**
	 * Compares this passenger's name with the name of another.
	 * 
	 * @param passenger
	 *            The other passenger.
	 * @return The difference between the two names.
	 */
	@Override
	public int compareTo(Passenger passenger) {
		return name.compareTo(passenger.name);
	}

	/**
	 * Returns the name of the passenger. As it is used as an identifier for the
	 * relations between passengers, one must take care of only giving the same
	 * name to passengers that behave the same way.
	 * 
	 * @return The name of the passenger.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the number of places that are taken in the boat.
	 * 
	 * @return The weight of the passenger.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Sets a new name for the passenger.
	 * 
	 * @param name
	 *            The new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets a new weight fir the passenger.
	 * 
	 * @param weight
	 *            The new weight.
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
