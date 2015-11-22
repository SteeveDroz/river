package com.github.steevedroz.river.passenger.relation;

import java.util.ArrayList;
import java.util.List;

import com.github.steevedroz.river.passenger.Passenger;

/**
 * This class represents a combination of passengers. <code>element1</code> and
 * <code>element2</code> both contain a {@link Passenger} or a combination of
 * passengers. Using {@link RelationOperator}, one can produce any kind of
 * <code>AND</code>/<code>OR</code> combination of boolean expression.
 * 
 * @author Steeve Droz
 * 
 */
public class RelationBoolean implements RelationElement {
	/**
	 * Represents the first element of the operation.
	 */
	private RelationElement element1;
	/**
	 * Represents the second element of the operation.
	 */
	private RelationElement element2;
	/**
	 * Represents the operator of the expression: <code>AND</code> or
	 * <code>OR</code>.
	 */
	private RelationOperator operator;

	/**
	 * This constructor creates a blank operation ready to be hydrated.
	 */
	public RelationBoolean() {
	}

	/**
	 * This method returns a deep copy of this relation boolean.
	 * 
	 * @return A deep copy.
	 */
	@Override
	public RelationElement cloneOf() {
		RelationBoolean relationBoolean = new RelationBoolean();
		relationBoolean.element1 = element1.cloneOf();
		relationBoolean.element2 = element2.cloneOf();
		relationBoolean.operator = operator;
		return relationBoolean;
	}

	/**
	 * Checks for presence of the relation in the list of passengers.<br />
	 * <br />
	 * If <code>operator</code> is set to {@link RelationOperator#AND}, a value
	 * is returned if both <code>element1</code> and <code>element2</code> both
	 * return a value, otherwise <code>null</code> is returned.<br />
	 * <br />
	 * If <code>operator</code> is set to {@link RelationOperator#OR}, a value
	 * is returned if either element returns a value, otherwise
	 * <code>null</code> is returned.
	 * 
	 * @param passengers
	 *            The list of passengers to check the presence against.
	 * @return The passenger that was found or <code>null</code> if nothing
	 *         matches.
	 */
	@Override
	public List<Passenger> getPresence(List<Passenger> passengers) {
		ArrayList<Passenger> passenger1 = new ArrayList<Passenger>();
		ArrayList<Passenger> passenger2 = new ArrayList<Passenger>();
		for (Passenger passenger : passengers) {
			if (element1.getPresence(passengers) != null
					&& element1.getPresence(passengers).contains(passenger)) {
				passenger1.add(passenger);
			} else if (element2.getPresence(passengers) != null
					&& element2.getPresence(passengers).contains(passenger)) {
				passenger2.add(passenger);
			}
		}
		return operator.getPresence(passenger1, passenger2);
	}

	/**
	 * Returns the first element.
	 * 
	 * @return The first element.
	 */
	public RelationElement getElement1() {
		return element1;
	}

	/**
	 * Returns the second element.
	 * 
	 * @return The second element.
	 */
	public RelationElement getElement2() {
		return element2;
	}

	/**
	 * Returns the operator.
	 * 
	 * @return The operator.
	 */
	public RelationOperator getOperator() {
		return operator;
	}

	/**
	 * Sets the first element.
	 * 
	 * @param element1
	 *            The element to set.
	 */
	public void setElement1(RelationElement element1) {
		this.element1 = element1;
	}

	/**
	 * Sets the second element.
	 * 
	 * @param element1
	 *            The element to set.
	 */
	public void setElement2(RelationElement element2) {
		this.element2 = element2;
	}

	/**
	 * Sets the operator.
	 * 
	 * @param element1
	 *            The operator.
	 */
	public void setOperator(RelationOperator operator) {
		this.operator = operator;
	}
}
