package com.github.steevedroz.river.passenger.relation;

import java.util.ArrayList;
import java.util.List;

import com.github.steevedroz.river.passenger.Passenger;

/**
 * This enum contains basic boolean operatiors <code>AND</code> and
 * <code>OR</code> that are used to define complex relations between passengers,
 * such as:<br />
 * "<code>passenger</code> is killed by <code>killer1</code> {@link #AND}
 * <code>killer2</code> if they are together, but is protected by
 * <code>protector1</code> {@link #OR} <code>protector2</code> if either of them
 * is present".
 * 
 * @author Steeve Droz
 * 
 */
public enum RelationOperator {
	/**
	 * The <code>AND</code> operator, describes a relation where both elements
	 * must be present.
	 */
	AND {
		/**
		 * Returns a passenger if both are present.
		 * 
		 * @param passenger1
		 *            The first passenger.
		 * @param passenger2
		 *            The second passenger.
		 * @return <code>passenger1</code> if both <code>passenger1</code> and
		 *         <code>passenger2</code> are different from <code>null</code>,
		 *         <code>null</code> otherwise.
		 */
		@Override
		public List<Passenger> getPresence(List<Passenger> passenger1,
				List<Passenger> passenger2) {
			ArrayList<Passenger> passengers = new ArrayList<Passenger>();
			if (passenger1 != null && passenger2 != null) {
				passengers.addAll(passenger1);
				passengers.addAll(passenger2);
			}
			return passengers.size() > 0 ? passengers : null;
		}
	},
	/**
	 * The <code>OR</code> operator, describes a relation where either of the
	 * elements must be present.
	 */
	OR {
		/**
		 * Returns a passenger if any is present.
		 * 
		 * @param passenger1
		 *            The first passenger.
		 * @param passenger2
		 *            The second passenger.
		 * @return <code>passenger1</code> if it is present,
		 *         <code>passenger2</code> if it is present and the other is
		 *         not, and <code>null</code> if none are present.
		 */
		@Override
		public List<Passenger> getPresence(List<Passenger> passenger1,
				List<Passenger> passenger2) {
			List<Passenger> passengers = new ArrayList<Passenger>();
			if (passenger1 != null) {
				passengers.addAll(passenger1);
			}
			if (passenger2 != null) {
				passengers.addAll(passenger2);
			}
			return passengers.size() > 0 ? passengers : null;
		}
	};

	/**
	 * Returns a passenger if the boolean operation is true.
	 * 
	 * @param passenger1
	 *            The first passenger.
	 * @param passenger2
	 *            The second passenger.
	 * @return A passenger if the boolean operation is true.
	 */
	public abstract List<Passenger> getPresence(List<Passenger> passenger1,
			List<Passenger> passenger2);
}
