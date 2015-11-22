package com.github.steevedroz.river.ai;

import java.util.Collections;

import com.github.steevedroz.river.River;
import com.github.steevedroz.utils.StringUtils;

/**
 * This class represents the river in a given position. It holds the river
 * itself and a parent state, from which the current state has been reached.
 * 
 * @author Steeve Droz
 */
public class RiverState {
	/**
	 * The river in a given position.
	 */
	private River river;
	/**
	 * The river in the state where it was before the move was made that brought
	 * the river to this current state.
	 */
	private RiverState parent;

	/**
	 * This constructor creates a state with a river and the previous state.
	 * 
	 * @param river
	 *            The river in the given position.
	 * @param parent
	 *            The state of the river prior the move.
	 */
	public RiverState(River river, RiverState parent) {
		this.river = river;
		this.parent = parent;
	}

	/**
	 * This constructor creates the original state, the one that has no parent
	 * state.
	 * 
	 * @param river
	 *            The river in its original position.
	 */
	public RiverState(River river) {
		this(river, null);
	}

	/**
	 * A text-only representation of the river state.
	 */
	@Override
	public String toString() {
		return getState();
	}

	/**
	 * This method duplicates the state and sets at as a child state of this
	 * one. One move is then supposed to be made to the river so that the
	 * resulting state is just one step after this current state.
	 * 
	 * @return A duplicate version of the river.
	 */
	public RiverState createChild() {
		RiverState child = new RiverState(river.cloneOf(), this);
		return child;
	}

	/**
	 * This method visits the river and returns a text-only representation of
	 * it.<br />
	 * <br />
	 * For instance, the state <code>p1,p2,p3|p4,p5|p6|Left</code> must be
	 * understood this way:<br />
	 * <dl>
	 * <dt>p1,p2,p3</dt>
	 * <dd>Passengers p1, p2 and p3 are on the left shore.</dd>
	 * <dt>p4,p5</dt>
	 * <dd>Passengers p4 and p5 are on the right shore.</dd>
	 * <dt>p6</dt>
	 * <dd>Passenger p6 is on the boat.</dd>
	 * <dt>Left</dt>
	 * <dd>The boat is on the shore called "Left" (probably the left shore).
	 * </dd>
	 * </dl>
	 * 
	 * @return The state of the river, represented by passenger names, "," and
	 *         "|".
	 */
	public String getState() {
		Collections.sort(river.getLeft().getPassengers());
		Collections.sort(river.getRight().getPassengers());
		Collections.sort(river.getBoat().getPassengers());
		return StringUtils.join(river.getLeft().getPassengers(), ",") + "|"
				+ StringUtils.join(river.getRight().getPassengers(), ",") + "|"
				+ StringUtils.join(river.getBoat().getPassengers(), ",") + "|" + river.getBoat().getShore().getName();
	}

	/**
	 * Returns the river.
	 * 
	 * @return The river
	 */
	public River getRiver() {
		return river;
	}

	/**
	 * Returns the parent state.
	 * 
	 * @return The parent state.
	 */
	public RiverState getParent() {
		return parent;
	}

	/**
	 * Sets the river.
	 * 
	 * @param river
	 *            The river.
	 */
	public void setRiver(River river) {
		this.river = river;
	}

	/**
	 * Sets the parent state.
	 * 
	 * @param parent
	 *            The parent state.
	 */
	public void setParent(RiverState parent) {
		this.parent = parent;
	}
}
