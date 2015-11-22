package com.github.steevedroz.river.passenger.relation;

import java.util.List;

import com.github.steevedroz.river.passenger.Passenger;

/**
 * This class represents a combination of passengers such as the number of
 * passengers from a given category is compared to the number of passengers from
 * another category.<br />
 * <br />
 * This is mainly used in setups such as
 * "there shouldnt be more X than Y on the shore at any given time".
 * 
 * @author Steeve Droz
 *
 */
public class RelationCount extends Relation {
	/**
	 * Constructor defining a list of killers and protectors.
	 * 
	 * @param killers
	 *            The killers
	 * @param protectors
	 *            The protectors
	 */
	public RelationCount(RelationElement killers, RelationElement protectors) {
		super(killers, protectors);
	}

	/**
	 * Default constructor.
	 */
	public RelationCount() {
		super();
	}

	/**
	 * Returns the list of passengers that match if the killer passengers (
	 * {@link #killers}) are more than the protector passengers (
	 * {@link #protectors}).
	 */
	@Override
	public List<Passenger> getPresence(List<Passenger> passengers) {
		if (killer.getPresence(passengers) == null) {
			return null;
		}
		if (protector.getPresence(passengers) == null) {
			return killer.getPresence(passengers);
		}
		return killer.getPresence(passengers).size() > protector.getPresence(
				passengers).size() ? killer.getPresence(passengers) : null;
	}

	@Override
	public Relation cloneOf() {
		RelationCount relationCount = new RelationCount();
		relationCount.killer = this.killer.cloneOf();
		relationCount.protector = this.protector.cloneOf();
		return relationCount;
	}
}
