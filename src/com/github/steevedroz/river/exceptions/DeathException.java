package com.github.steevedroz.river.exceptions;

import java.util.List;

import com.github.steevedroz.river.passenger.Passenger;
import com.github.steevedroz.utils.StringUtils;

/**
 * This exception is thrown whenever a move is made that allows some predator to
 * kill its prey.
 * 
 * @author Steeve Droz
 * 
 */
public class DeathException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * The passenger that has been killed.
	 */
	private Passenger prey;
	/**
	 * The passenger that killed.
	 */
	private List<Passenger> predators;

	/**
	 * This constructor memorizes the identity of the antagonists of the
	 * killing.
	 * 
	 * @param prey
	 *            The killed passenger.
	 * @param predators
	 *            The killing passenger.
	 */
	public DeathException(Passenger prey, List<Passenger> predators) {
		this.prey = prey;
		this.predators = predators;
	}

	/**
	 * This method returns an error message.
	 * 
	 * @return "PRAY_NAME is killed by PREDATOR_NAME".
	 */
	@Override
	public String getMessage() {
		return prey.getName() + " is killed by: " + StringUtils.join(predators, ", ");
	}
}
