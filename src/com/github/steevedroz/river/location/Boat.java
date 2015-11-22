package com.github.steevedroz.river.location;

import java.util.ArrayList;

import com.github.steevedroz.river.exceptions.NoNavigatorException;
import com.github.steevedroz.river.passenger.Passenger;

/**
 * This class represents the boat that crosses the river.
 * 
 * @author Steeve Droz
 * 
 */
public class Boat extends Location {
	/**
	 * The default maximum weight the boat can hold.
	 */
	public static final int MAX_WEIGHT = 2;
	/**
	 * The default behavior of the boat when it reaches the other shore.
	 */
	public static final boolean EMPTY_ON_ARRIVAL = true;

	/**
	 * The maximum weight the boat can hold.
	 */
	protected int maxWeight;
	/**
	 * The behavior of the boat when it reaches the other shore.
	 */
	protected boolean emptyOnArrival;
	/**
	 * The shore on which the boat is currently located.
	 */
	protected Shore shore;

	/**
	 * This constructor creates a boat with a name and default values.
	 * 
	 * @param name
	 *            The name of the boat.
	 */
	public Boat(String name) {
		this(name, MAX_WEIGHT, null, EMPTY_ON_ARRIVAL);
	}

	/**
	 * This constructor creates a boat with a name, a maximum weight and default
	 * values.
	 * 
	 * @param name
	 *            The name of the boat.
	 * @param maxWeight
	 *            The maximum passenger weight the boat can hold.
	 */
	public Boat(String name, int maxWeight) {
		this(name, maxWeight, null, EMPTY_ON_ARRIVAL);
	}

	/**
	 * This constructor creates a boat with a name, a maximum weight and a
	 * starting shore.
	 * 
	 * @param name
	 *            The name of the boat.
	 * @param maxWeight
	 *            The maximum passenger weight the boat can hold.
	 * @param shore
	 *            The shore on which the boat is currently located.
	 */
	public Boat(String name, int maxWeight, Shore shore) {
		this(name, maxWeight, shore, EMPTY_ON_ARRIVAL);
	}

	/**
	 * This constructor creates a boat with a name, a maximum weight, a starting
	 * shore and a behavior concerning crossing the river.
	 * 
	 * @param name
	 *            The name of the boat.
	 * @param maxWeight
	 *            The maximum passenger weight the boat can hold.
	 * @param shore
	 *            The shore on which the boat is currently located.
	 * @param emptyOnArrival
	 *            Whether the passengers leave the boat as soon as it reaches
	 *            the other shore.
	 */
	public Boat(String name, int maxWeight, Shore shore, boolean emptyOnArrival) {
		super(name);
		this.maxWeight = maxWeight;
		this.shore = shore;
		this.emptyOnArrival = emptyOnArrival;
	}

	/**
	 * Returns a deep copy of the boat.
	 * 
	 * @return A deep copy.
	 */
	@Override
	public Boat cloneOf() {
		Boat boat = new Boat(name);
		boat.passengers = new ArrayList<Passenger>();
		for (Passenger passenger : passengers) {
			boat.passengers.add((Passenger) passenger.cloneOf());
		}
		boat.maxWeight = maxWeight;
		boat.emptyOnArrival = emptyOnArrival;
		boat.shore = shore.cloneOf();
		return boat;
	}

	/**
	 * This method adds a passenger to the boat, only if it doesn't implies
	 * getting over the maximum weight limit.
	 * 
	 * @param passenger
	 *            The passenger.
	 */
	@Override
	public void add(Passenger passenger) {
		int weight = 0;
		for (Passenger present : passengers) {
			weight += present.getWeight();
		}
		if (weight + passenger.getWeight() <= maxWeight) {
			super.add(passenger);
		}
	}

	/**
	 * This method allows the boat to cross the river if a navigator is on
	 * board.
	 * 
	 * @param destination
	 *            The other shore.
	 * @throws NoNavigatorException
	 *             This exception is thrown if no passenger on board is able to
	 *             navigate the boat.
	 */
	public void cross(Location destination) throws NoNavigatorException {
		boolean canNavigate = false;
		for (Passenger passenger : passengers) {
			if (passenger.isNavigator()) {
				canNavigate = true;
				break;
			}
		}
		if (!canNavigate) {
			throw new NoNavigatorException(this);
		}

		shore = (Shore) destination;

		if (emptyOnArrival) {
			empty();
		}
	}

	/**
	 * Removes every passenger from the boat and puts them on the current shore.
	 */
	public void empty() {
		super.empty(shore);
	}

	/**
	 * Returns the maximum weight the boat can hold.
	 * 
	 * @return The maximum weight the boat can hold.
	 */
	public int getMaxWeight() {
		return maxWeight;
	}

	/**
	 * Returns whether the passengers leave the boat on arrival.
	 * 
	 * @return Whether the passengers leave the boat on arrival.
	 */
	public boolean isEmptyOnArrival() {
		return emptyOnArrival;
	}

	/**
	 * Returns the shore the boat is on.
	 * 
	 * @return The shore the boat is on.
	 */
	public Shore getShore() {
		return shore;
	}

	/**
	 * Sets the maximum weight the boat can hold.
	 * 
	 * @param maxWeight
	 *            The weight.
	 */
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	/**
	 * Sets the behavior on arrival.
	 * 
	 * @param emptyOnArrival
	 *            The behavior.
	 */
	public void setEmptyOnArrival(boolean emptyOnArrival) {
		this.emptyOnArrival = emptyOnArrival;
	}

	/**
	 * Sets the shore the boat is on.
	 * 
	 * @param shore
	 *            The shore.
	 */
	public void setShore(Shore shore) {
		this.shore = shore;
	}
}
