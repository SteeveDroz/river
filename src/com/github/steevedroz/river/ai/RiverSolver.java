package com.github.steevedroz.river.ai;

import java.util.ArrayList;
import java.util.List;

import com.github.steevedroz.river.River;
import com.github.steevedroz.river.exceptions.DeathException;
import com.github.steevedroz.river.exceptions.NoBoatException;
import com.github.steevedroz.river.exceptions.NoNavigatorException;
import com.github.steevedroz.river.location.Shore;
import com.github.steevedroz.river.passenger.Passenger;

/**
 * This is the main class for using the artificial intelligence that solves the
 * river problem.<br />
 * <br />
 * <b>Use:</b><br />
 * After creating a river, create another one that contains the same passengers
 * but that are in the goal position. Don't forget to put the boat on the
 * correct shore. In the Wolf-Goat-Cowl problem, this would be all the
 * passengers on the right shore and the boat as well.<br />
 * Then, create a new RiverSolver with
 * <code>RiverSolver solver = new RiverSolver(river);</code> and solve the
 * problem with <code>solver.solve(goal);</code>.
 * 
 * @author Steeve Droz
 * 
 */
public class RiverSolver {
	/**
	 * A list of the states that have already been visited.
	 */
	private List<String> visited;
	/**
	 * A list of the states that are to be visited.
	 */
	private List<RiverState> activeStates;

	/**
	 * This constructor sets up a RiverSolver with the river as starting point.
	 * 
	 * @param river
	 *            The river in its original position.
	 */
	public RiverSolver(River river) {
		this.visited = new ArrayList<String>();
		this.activeStates = new ArrayList<RiverState>();
		this.activeStates.add(new RiverState(river));
	}

	/**
	 * This method concretely solves the problem and prints each state of the
	 * process, or "No solution" if the problem has no solution.
	 * 
	 * @param goal
	 *            The final state that must be reached. Be careful not to forget
	 *            the position of the boat. It may not be possible to solve the
	 *            problem with the boat on the other shore.
	 */
	public void solve(River goal) {
		String goalState = new RiverState(goal).getState();
		while (activeStates.size() > 0) {
			RiverState current = activeStates.get(0);
			activeStates.remove(0);
			if (current.getState().equals(goalState)) {
				printSolution(current);
				return;
			}
			if (visited.contains(current.getState())) {
				continue;
			}
			visited.add(current.getState());
			addChildrenToActiveStates(current);
		}
		System.out.println("No solution");
	}

	/**
	 * This method duplicates the river in its current state for each possible
	 * move from this point. The valid duplicates are added to the list of
	 * states to be visited.
	 * 
	 * @param current
	 *            The river in its current state.
	 */
	private void addChildrenToActiveStates(RiverState current) {
		Shore source = current.getRiver().getBoat().getShore();

		List<ArrayList<Passenger>> combinations = crossingCombinations(
				source.getPassengers(), current.getRiver().getBoat()
						.getMaxWeight());
		for (ArrayList<Passenger> passengers : combinations) {
			RiverState riverState = current.createChild();
			try {
				for (Passenger passenger : passengers) {
					riverState.getRiver().board(passenger);
				}
				riverState.getRiver().cross();
				activeStates.add(riverState);
			} catch (NoBoatException e) {
				e.printStackTrace();
			} catch (NoNavigatorException | DeathException e) {
			}
		}
	}

	/**
	 * This method returns a list containing every combination of maximum
	 * <code>weight</code> passengers on a same boat.
	 * 
	 * @param passengers
	 *            The passenger list to pick the passengers from.
	 * @param weight
	 *            The maximum weight of the boat.
	 * @return A list of all the combinations of passengers that match the
	 *         requirements.
	 */
	private List<ArrayList<Passenger>> crossingCombinations(
			List<Passenger> passengers, int weight) {
		List<ArrayList<Passenger>> combinations = new ArrayList<ArrayList<Passenger>>();
		if (weight <= 0) {
			return combinations;
		}

		for (int i = 0; i < passengers.size(); i++) {
			Passenger passenger = passengers.get(i);
			List<Passenger> nextPassengers = passengers.subList(i + 1,
					passengers.size());
			List<ArrayList<Passenger>> subCombinations = crossingCombinations(
					nextPassengers, weight - passenger.getWeight());
			for (ArrayList<Passenger> otherPassengers : subCombinations) {
				otherPassengers.add(passenger);
			}
			ArrayList<Passenger> solo = new ArrayList<Passenger>();
			solo.add(passenger);
			subCombinations.add(solo);
			combinations.addAll(subCombinations);
		}
		return combinations;
	}

	/**
	 * This method is called when a path has successfully been found to the
	 * goal. It prints each step of the solution.
	 * 
	 * @param current
	 *            The state that matches the goal.
	 */
	private void printSolution(RiverState current) {
		List<RiverState> states = new ArrayList<RiverState>();
		while (current != null) {
			states.add(0, current);
			current = current.getParent();
		}
		for (RiverState state : states) {
			System.out.println(state.getState());
		}
	}
}
