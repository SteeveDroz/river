package com.github.steevedroz.river.location;

import java.util.ArrayList;
import java.util.List;

import com.github.steevedroz.river.exceptions.DeathException;
import com.github.steevedroz.river.passenger.Passenger;

/**
 * This abstract class represents a place where passengers can be found.
 * 
 * @author Steeve Droz
 * 
 */
public abstract class Location {
	/**
	 * The list of passengers that are on this location.
	 */
	protected List<Passenger> passengers;
	/**
	 * The name of the location.
	 */
	protected String name;

	/**
	 * This constructor creates a new location.
	 * 
	 * @param name
	 *            The name of the location.
	 */
	public Location(String name) {
		this.name = name;
		passengers = new ArrayList<Passenger>();
	}

	/**
	 * Creates a deep copy of the location.
	 * 
	 * @return A deep copy.
	 */
	public abstract Location cloneOf();

	/**
	 * Returns a text-only representation of this location.
	 * 
	 * @return A representation of this location.
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(name).append(":");
		for (Passenger passenger : passengers) {
			str.append(" ").append(passenger.getName());
		}

		return str.toString();
	}

	/**
	 * Adds a passenger to this location.
	 * 
	 * @param passenger
	 *            The passenger to be added.
	 */
	public void add(Passenger passenger) {
		passengers.add(passenger);
	}

	/**
	 * Adds a list of passengers to this location.
	 * 
	 * @param passengers
	 *            The passengers to be added.
	 */
	public void addAll(List<Passenger> passengers) {
		this.passengers.addAll(passengers);
	}

	/**
	 * This convenient method adds a list passengers to this location.
	 * 
	 * @param passengers
	 *            The passengers to be added.
	 */
	public void add(Passenger... passengers) {
		for (Passenger passenger : passengers) {
			this.passengers.add(passenger);
		}
	}

	/**
	 * Empties this location and puts all the passengers into another location.
	 * 
	 * @param destination
	 *            The location where to place the passengers.
	 */
	public void empty(Location destination) {
		destination.addAll(passengers);
		passengers = new ArrayList<Passenger>();
	}

	/**
	 * Moves one passenger from this location to another location.
	 * 
	 * @param passenger
	 *            The passenger to move.
	 * @param destination
	 *            The location where to place the passenger.
	 */
	public void go(Passenger passenger, Location destination) {
		if (passengers.contains(passenger)) {
			destination.add(passenger);
			passengers.remove(passenger);
		}
	}

	/**
	 * Tests if the passengers survive together on this location.
	 * 
	 * @throws DeathException
	 *             If one of the passengers kills another one, this exception is
	 *             raised.
	 */
	public void survival() throws DeathException {
		for (Passenger passenger : passengers) {
			List<Passenger> killer = passenger.getPresence(passengers);
			if (killer != null) {
				throw new DeathException(passenger, killer);
			}
		}
	}

	/**
	 * Returns the list of the passengers.
	 * 
	 * @return The list of the passengers.
	 */
	public List<Passenger> getPassengers() {
		return passengers;
	}

	/**
	 * Returns if the passenger is at that location.
	 * 
	 * @param passenger
	 *            The passenger to check.
	 * @return Whether the passenger is here or not.
	 */
	public boolean contains(Passenger passenger) {
		return passengers.contains(passenger);
	}

	/**
	 * Returns the name of the location.
	 * 
	 * @return The name of the location.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the location.
	 * 
	 * @param name
	 *            The name.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
