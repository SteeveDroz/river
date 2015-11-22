package com.github.steevedroz.river;

import com.github.steevedroz.river.exceptions.DeathException;
import com.github.steevedroz.river.exceptions.NoBoatException;
import com.github.steevedroz.river.exceptions.NoNavigatorException;
import com.github.steevedroz.river.location.Boat;
import com.github.steevedroz.river.location.Location;
import com.github.steevedroz.river.location.Shore;
import com.github.steevedroz.river.passenger.Passenger;

/**
 * This class contains the data and the logic for using the application. It is
 * but a command line version of the famous boat problem where a certain amount
 * of passengers need to cross the river obeying certain laws.<br />
 * <br />
 * <b>Minimal use:</b>
 * 
 * <pre>
 * // Create by default two shores with distinct names
 * // and a 2 places boat on the left shore.
 * River river = new River();
 * 
 * // Create all the passengers using predefined parameters.
 * Farmer farmer = new Farmer();
 * Cowl cowl = new Cowl();
 * Goat goat = new Goat();
 * Wolf wolf = new Wolf();
 * 
 * // Add the passengers to the left shore.
 * river.getLeft().add(farmer, cowl, goat, wolf);
 * 
 * // At this point, the settings are completed.
 * 
 * // Use the boat with the board and cross commands.
 * river.board(farmer);
 * river.board(goat);
 * river.cross();
 * 
 * // Try to solve the puzzle.
 * </pre>
 * 
 * @author Steeve Droz
 * 
 */
public class River {
	/**
	 * The boat which will bring the passengers from one point to another.
	 */
	private Boat boat;
	/**
	 * The left shore that can be an origin or a destination for the passengers.
	 */
	private Shore left;
	/**
	 * The right shore that can be an origin or a destination for the
	 * passengers.
	 */
	private Shore right;

	/**
	 * This constructor creates a river with two shores with default name and a
	 * boat with {@link Boat#MAX_WEIGHT} max weight.
	 */
	public River() {
		this(Boat.MAX_WEIGHT);
	}

	/**
	 * This constructor creates a river with two shores with default names and a
	 * boat with <code>boatMaxWeight</code> max weight.
	 * 
	 * @param boatMaxWeight
	 *            The number of places in the boat
	 * @see Boat#getMaxWeight()
	 */
	public River(int boatMaxWeight) {
		boat = new Boat("Bateau", boatMaxWeight);
		left = new Shore("Gauche");
		right = new Shore("Droite");
		boat.setShore(left);
	}

	/**
	 * A text-only graphical representation of the river.
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("* River").append("\n");
		str.append("* ").append(left).append("\n");
		str.append("* ").append(right).append("\n");
		str.append("* ").append(boat).append("\n");
		str.append("* Boat is on shore: ").append(boat.getShore().getName());

		return str.toString();
	}

	/**
	 * Creates a deep copy of this river and returns it.
	 * 
	 * @return A deep copy.
	 */
	public River cloneOf() {
		River river = new River();
		river.boat = boat.cloneOf();
		river.left = left.cloneOf();
		river.right = right.cloneOf();
		river.boat.setShore(boat.getShore() == left ? river.left : river.right);
		return river;
	}

	/**
	 * This is one of the two mostly used methods of this class. Using it moves
	 * a passenger from its current shore to the boat.
	 * 
	 * @param passenger
	 *            The passenger to move.
	 * @throws NoBoatException
	 *             If the boat and the passenger one wants to move are not
	 *             currently on the same shore, this exception is raised.
	 */
	public void board(Passenger passenger) throws NoBoatException {
		Shore shore = null;
		if (left.contains(passenger)) {
			shore = left;
		} else if (right.contains(passenger)) {
			shore = right;
		} else {
			throw new NoBoatException(passenger);
		}
		if (boat.getShore() != shore) {
			throw new NoBoatException(passenger);
		}
		shore.go(passenger, boat);
	}

	/**
	 * This is one of the two mostly used methods of this class. Using it moves
	 * the boat from its current shore to the other. Depending of the value of
	 * {@link Boat#isEmptyOnArrival()}, the passengers may leave the boat
	 * directly on arrival.
	 * 
	 * @throws NoNavigatorException
	 *             If none of the passengers aboard can navigate (i.e. none has
	 *             {@link Passenger#isNavigator()} value to true), this
	 *             exception is raised.
	 * @throws DeathException
	 *             If one of the passengers aboard kills another or if the
	 *             configuration after the crossing is such that a passenger can
	 *             kill another, this exception is raised.
	 */
	public void cross() throws NoNavigatorException, DeathException {
		Shore destination = boat.getShore() == getLeft() ? getRight()
				: getLeft();
		survival();
		boat.cross(destination);
		survival();
	}

	/**
	 * Returns the left shore of the river.
	 * 
	 * @return The left shore of the river.
	 */
	public Shore getLeft() {
		return left;
	}

	/**
	 * Returns the right shore of the river.
	 * 
	 * @return The right shore of the river.
	 */
	public Shore getRight() {
		return right;
	}

	/**
	 * Returns the boat that crosses the river.
	 * 
	 * @return The boat that crosses the river.
	 */
	public Boat getBoat() {
		return boat;
	}

	/**
	 * Sets a new left shore on the river.
	 * 
	 * @param left
	 *            The new shore.
	 */
	public void setLeft(Shore left) {
		this.left = left;
	}

	/**
	 * Sets a new right shore on the river.
	 * 
	 * @param right
	 *            The new shore.
	 */
	public void setRight(Shore right) {
		this.right = right;
	}

	/**
	 * Sets a new boat on the river.
	 * 
	 * @param boat
	 *            The new shore.
	 */
	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	/**
	 * Tests for survival on each shore and on the boat.
	 * 
	 * @throws DeathException
	 *             If one or more of the three locations hold a murder, this
	 *             exception is raised.
	 * @see Location#survival()
	 */
	private void survival() throws DeathException {
		left.survival();
		right.survival();
		boat.survival();
	}
}
