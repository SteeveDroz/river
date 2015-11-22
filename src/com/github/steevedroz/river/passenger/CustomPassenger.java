package com.github.steevedroz.river.passenger;

import java.util.ArrayList;
import java.util.List;

import com.github.steevedroz.river.passenger.relation.Relation;
import com.github.steevedroz.river.passenger.relation.RelationElement;

/**
 * This class allows the user to customly define a passenger. Each of its
 * connections with the other passengers may be set.<br />
 * <br />
 * One must be very careful not to forget anything when creating a custom
 * passenger, because a small mistake can change the game rules, making the game
 * too easy or impossible.<br />
 * <br />
 * <b>Standard use:</b>
 * 
 * <pre>
 * // Three passengers are created for the sake of the example.
 * // One must pay attention that the name will be uses as an identifier. Two
 * // passengers with the same name will be considered as the same kind of
 * // passengers.
 * 
 * CustomPassenger teacher = new CustomPassenger(&quot;Teacher&quot;);
 * CustomPassenger kid = new CustomPassenger(&quot;Kid&quot;);
 * CustomPassenger bully = new CustomPassenger(&quot;Bully&quot;);
 * 
 * // A new relation is added, defining that the teacher can't leave the kid and
 * // the bully alone together.
 * 
 * kid.addRelation(bully, teacher);
 * 
 * // Let's say now that only the teacher is allowed to navigate the boat.
 * 
 * teacher.setNavigator(true);
 * 
 * // This could have also been achieved with the next line.
 * 
 * teacher = new CustomPassenger(&quot;Teacher&quot;, true);
 * </pre>
 * 
 * @author Steeve Droz
 * 
 */
public class CustomPassenger extends Passenger {
	/**
	 * The default weight of a passenger.
	 */
	public static final int WEIGHT = 1;

	/**
	 * Holds whether this passenger can navigate the boat or not.
	 */
	private boolean navigator;
	/**
	 * The list of all the relations i.e. killers and protectors of this
	 * passenger.
	 */
	private List<Relation> relations;

	/**
	 * A complete constructor that allows to define the name, weight and
	 * navigation ability of the passenger.
	 * 
	 * @param name
	 *            The name of the passenger. As it will be used as an identifier
	 *            for the relations, two passengers with different type of
	 *            relations must have different names.
	 * @param weight
	 *            The number of places the passenger takes in the boat.
	 * @param navigator
	 *            Whether the passenger can navigate the boat or not.
	 */
	public CustomPassenger(String name, int weight, boolean navigator) {
		super(name, weight);
		this.navigator = navigator;
		this.relations = new ArrayList<Relation>();
	}

	/**
	 * A convenient constructor that allows to define the name and weight of the
	 * passenger.<br />
	 * <br />
	 * The navigation ability is set to <code>false</code>
	 * 
	 * @param name
	 *            The name of the passenger. As it will be used as an identifier
	 *            for the relations, two passengers with different type of
	 *            relations must have different names.
	 * @param weight
	 *            The number of places the passenger takes in the boat.
	 */
	public CustomPassenger(String name, int weight) {
		this(name, weight, false);
	}

	/**
	 * A convenient constructor that allows to define the name and navigation
	 * ability of the passenger.<br />
	 * <br />
	 * The weight is set to {@link #WEIGHT}.
	 * 
	 * @param name
	 *            The name of the passenger. As it will be used as an identifier
	 *            for the relations, two passengers with different type of
	 *            relations must have different names.
	 * @param navigator
	 *            Whether the passenger can navigate the boat or not.
	 */
	public CustomPassenger(String name, boolean navigator) {
		this(name, WEIGHT, navigator);
	}

	/**
	 * A convenient constructor that allows to define the name of the passenger.<br />
	 * <br />
	 * The weight is set to {@link #WEIGHT}.<br />
	 * The navigation ability is set to <code>false</code>
	 * 
	 * @param name
	 *            The name of the passenger. As it will be used as an identifier
	 *            for the relations, two passengers with different type of
	 *            relations must have different names.
	 */
	public CustomPassenger(String name) {
		this(name, WEIGHT, false);
	}

	/**
	 * Displays the name of this passenger.
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Returns a deep copy of this passenger.
	 * 
	 * @return A deep copy of this passenger.
	 */
	@Override
	public CustomPassenger cloneOf() {
		CustomPassenger customPassenger = new CustomPassenger(name, weight);
		customPassenger.navigator = navigator;
		customPassenger.relations = new ArrayList<Relation>();
		for (Relation relation : relations) {
			customPassenger.relations.add(relation.cloneOf());
		}
		return customPassenger;
	}

	/**
	 * Adds a new relation to this passenger.<br />
	 * <br />
	 * This method is to be used with complex relations including a combination
	 * of killers and/or protectors. For a basic relation: one killer and one
	 * protector, use {@link #addRelation(RelationElement, RelationElement)}.
	 * 
	 * @param relation
	 *            The relation to be added to this passenger.
	 */
	public void addRelation(Relation relation) {
		relations.add(relation);
	}

	/**
	 * Adds a new relation to this passenger, defining a killer and a protector.
	 * 
	 * @param killer
	 *            The passenger that threats this passenger.
	 * @param protector
	 *            The passenger that protects this passenger against its killer.
	 */
	public void addRelation(RelationElement killer, RelationElement protector) {
		relations.add(new Relation(killer, protector));
	}

	/**
	 * Returns the navigation ability of this passenger, whether it is capable
	 * of maneuvering the boat when it is aboard.
	 * 
	 * @return The navigation ability.
	 */
	@Override
	public boolean isNavigator() {
		return navigator;
	}

	/**
	 * Sets the navigation ability of this passenger, whether it is capable of
	 * maneuvering the boat when it is aboard.
	 * 
	 * @param navigator
	 *            The navigation ability.
	 */
	public void setNavigator(boolean navigator) {
		this.navigator = navigator;
	}

	/**
	 * This method scans the list of passengers that are on the same location as
	 * this passenger and checks if the configuration allows another passenger
	 * to kill it.
	 * 
	 * @param passengers
	 *            The list of passengers.
	 * @return The first passenger that can kill this passenger or
	 *         <code>null</code> if there aren't any.
	 */
	@Override
	public List<Passenger> getPresence(List<Passenger> passengers) {
		List<Passenger> passengerResult = new ArrayList<Passenger>();
		for (Relation relation : relations) {
			List<Passenger> killers = relation.getPresence(passengers);
			if (killers != null) {
				passengerResult.addAll(killers);
			}
		}
		return passengerResult.size() > 0 ? passengerResult : null;
	}
}
