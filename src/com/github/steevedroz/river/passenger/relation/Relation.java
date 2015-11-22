package com.github.steevedroz.river.passenger.relation;

import java.util.List;

import com.github.steevedroz.river.passenger.Passenger;

/**
 * This class represents a relation that a {@link Passenger} has with other
 * passengers. There are two parts in a relation: the killer and the protector<br />
 * <br />
 * The killer is a passenger or group of passengers that will kill the passenger
 * to which this relation is linked.<br />
 * The protector is a passenger or group of passengers that prevent the killer
 * for doing its job.<br />
 * If the protector is present, nothing happens to the passenger, even if the
 * killer is present.
 * 
 * @author Steeve Droz
 * 
 */
public class Relation implements RelationElement {
	/**
	 * A passenger or group of passengers that would kill the passenger if they
	 * are on the same location.
	 */
	protected RelationElement killer;
	/**
	 * A passenger or group of passengers that keeps the killer from doing its
	 * killing if they are on the same location.
	 */
	protected RelationElement protector;

	/**
	 * This default constructor creates a relation with no killer nor protector.
	 */
	public Relation() {
	}

	/**
	 * This constructor creates a relation with a killer and a protector
	 * 
	 * @param killer
	 *            The killer
	 * @param protector
	 *            The protector
	 */
	public Relation(RelationElement killer, RelationElement protector) {
		this.killer = killer;
		this.protector = protector;
	}

	/**
	 * Returns a deep copy of this relation.
	 * 
	 * @return A deep copy
	 */
	public Relation cloneOf() {
		Relation relation = new Relation();
		relation.killer = killer.cloneOf();
		relation.protector = protector.cloneOf();
		return relation;
	}

	/**
	 * Returns the killers.
	 * 
	 * @return The killers.
	 */
	public RelationElement getKiller() {
		return killer;
	}

	/**
	 * Returns the protectors.
	 *
	 * @return The protectors.
	 */
	public RelationElement getProtector() {
		return protector;
	}

	/**
	 * Sets the killer.
	 * 
	 * @param killer
	 *            The killer.
	 */
	public void setKiller(RelationElement killer) {
		this.killer = killer;
	}

	/**
	 * Sets the protector.
	 * 
	 * @param protector
	 *            The protector.
	 */
	public void setProtector(RelationElement protector) {
		this.protector = protector;
	}

	/**
	 * Checks against a list of passengers if the killer and/or the protector
	 * are present. If the protector is there, <code>null</code> is returned.
	 * Otherwise, the killer is returned. Note that this killer may be
	 * <code>null</code> if the killer isn't present.
	 * 
	 * @param passengers
	 *            The list of passengers to test the presence against.
	 * @return The killer if it is present and the protector is not,
	 *         <code>null</code> otherwise.
	 */
	@Override
	public List<Passenger> getPresence(List<Passenger> passengers) {
		if (protector.getPresence(passengers) != null) {
			return null;
		}
		return killer.getPresence(passengers);
	}
}
