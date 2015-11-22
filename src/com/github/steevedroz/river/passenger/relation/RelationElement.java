package com.github.steevedroz.river.passenger.relation;

import java.util.List;

import com.github.steevedroz.river.passenger.Passenger;

/**
 * This interface describes an element that can appear in a relation.
 * 
 * @author Steeve Droz
 * 
 */
public interface RelationElement {
	/**
	 * Returns the matching passengers if this element is in the passenger list.
	 * 
	 * @param passengers
	 *            The passenger list.
	 * @return The passengers that match the element.
	 */
	public List<Passenger> getPresence(List<Passenger> passengers);

	/**
	 * Returns a deep copy of this element.
	 * 
	 * @return A deep copy.
	 */
	public RelationElement cloneOf();
}
