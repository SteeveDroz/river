package com.github.steevedroz.river;

import com.github.steevedroz.river.ai.RiverSolver;
import com.github.steevedroz.river.passenger.CustomPassenger;
import com.github.steevedroz.river.passenger.relation.RelationBoolean;
import com.github.steevedroz.river.passenger.relation.RelationCount;
import com.github.steevedroz.river.passenger.relation.RelationOperator;

public class UseRiver {
	public static void main(String[] args) {
		Type type = Type.CANIBALS;
		type.create();

		RiverSolver solver = new RiverSolver(type.river);
		solver.solve(type.goal);
	}

	enum Type {
		FARMER {
			@Override
			public void create() {
				river = new River(2);
				goal = new River();

				CustomPassenger farmer = new CustomPassenger("Fermier", true);
				CustomPassenger cowl = new CustomPassenger("Chou");
				CustomPassenger goat = new CustomPassenger("Chèvre");
				CustomPassenger wolf = new CustomPassenger("Loup");

				cowl.addRelation(goat, farmer);
				goat.addRelation(wolf, farmer);

				river.getLeft().add(farmer, cowl, goat, wolf);

				goal.getRight().add(farmer, cowl, goat, wolf);
				goal.getBoat().setShore(goal.getRight());
			}
		},
		FARMER_2 {
			@Override
			public void create() {
				river = new River(3);
				goal = new River();

				CustomPassenger farmer = new CustomPassenger("Fermier", true);
				CustomPassenger cowl = new CustomPassenger("Chou");
				CustomPassenger goat = new CustomPassenger("Chèvre");
				CustomPassenger wolf = new CustomPassenger("Loup");
				CustomPassenger stick = new CustomPassenger("Bâton");
				CustomPassenger fire = new CustomPassenger("Feu");

				cowl.addRelation(goat, farmer);
				goat.addRelation(wolf, farmer);
				wolf.addRelation(stick, farmer);
				stick.addRelation(fire, farmer);

				river.getLeft().add(farmer, cowl, goat, wolf, stick, fire);

				goal.getRight().add(farmer, cowl, goat, wolf, stick, fire);
				goal.getBoat().setShore(goal.getRight());
			}
		},
		FAMILY {
			@Override
			public void create() {
				river = new River(2);
				goal = new River();

				CustomPassenger cop = new CustomPassenger("Policier", true);
				CustomPassenger thief = new CustomPassenger("Voleur");

				CustomPassenger mom = new CustomPassenger("Mère", true);
				CustomPassenger girl1 = new CustomPassenger("Fille");
				CustomPassenger girl2 = new CustomPassenger("Fille");

				CustomPassenger dad = new CustomPassenger("Père", true);
				CustomPassenger boy1 = new CustomPassenger("Fils");
				CustomPassenger boy2 = new CustomPassenger("Fils");

				mom.addRelation(thief, cop);

				girl1.addRelation(thief, cop);
				girl1.addRelation(dad, mom);

				girl2.addRelation(thief, cop);
				girl2.addRelation(dad, mom);

				dad.addRelation(thief, cop);

				boy1.addRelation(thief, cop);
				boy1.addRelation(mom, dad);

				boy2.addRelation(thief, cop);
				boy2.addRelation(mom, dad);

				river.getLeft().add(cop, thief, mom, girl1, girl2, dad, boy1,
						boy2);

				goal.getRight().add(cop, thief, mom, girl1, girl2, dad, boy1,
						boy2);
				goal.getBoat().setShore(goal.getRight());
			}
		},
		COUPLES {
			@Override
			public void create() {
				river = new River(2);
				goal = new River();

				CustomPassenger husband1 = new CustomPassenger("Mari 1", true);
				CustomPassenger wife1 = new CustomPassenger("Femme 1", true);

				CustomPassenger husband2 = new CustomPassenger("Mari 2", true);
				CustomPassenger wife2 = new CustomPassenger("Femme 2", true);

				CustomPassenger husband3 = new CustomPassenger("Mari 3", true);
				CustomPassenger wife3 = new CustomPassenger("Femme 3", true);

				wife1.addRelation(husband2, husband1);
				wife1.addRelation(husband3, husband1);

				wife2.addRelation(husband1, husband2);
				wife2.addRelation(husband3, husband2);

				wife3.addRelation(husband1, husband3);
				wife3.addRelation(husband2, husband3);

				river.getLeft().add(husband1, wife1, husband2, wife2, husband3,
						wife3);
				goal.getRight().add(husband1, wife1, husband2, wife2, husband3,
						wife3);
				goal.getBoat().setShore(goal.getRight());
			}
		},
		CANIBALS {

			@Override
			public void create() {
				river = new River(2);
				goal = new River();

				CustomPassenger missionary1 = new CustomPassenger(
						"Missionnaire", true);
				CustomPassenger missionary2 = new CustomPassenger(
						"Missionnaire", true);
				CustomPassenger missionary3 = new CustomPassenger(
						"Missionnaire", true);

				CustomPassenger canibal1 = new CustomPassenger("Canibal", true);
				CustomPassenger canibal2 = new CustomPassenger("Canibal", true);
				CustomPassenger canibal3 = new CustomPassenger("Canibal", true);

				// FIXME Not working properly

				RelationBoolean killers1 = new RelationBoolean();
				RelationBoolean killers2 = new RelationBoolean();

				killers1.setElement1(canibal1);
				killers1.setElement2(killers2);
				killers1.setOperator(RelationOperator.OR);

				killers2.setElement1(canibal2);
				killers2.setElement2(canibal3);
				killers2.setOperator(RelationOperator.OR);

				RelationBoolean protectors1 = new RelationBoolean();
				RelationBoolean protectors2 = new RelationBoolean();

				protectors1.setElement1(missionary1);
				protectors1.setElement2(protectors2);
				protectors1.setOperator(RelationOperator.OR);

				protectors2.setElement1(missionary2);
				protectors2.setElement2(missionary3);
				protectors2.setOperator(RelationOperator.OR);

				RelationCount relationCount = new RelationCount(killers1,
						protectors1);

				missionary1.addRelation(relationCount);
				missionary2.addRelation(relationCount);
				missionary3.addRelation(relationCount);

				river.getLeft().add(missionary1, missionary2, missionary3,
						canibal1, canibal2, canibal3);
				goal.getRight().add(missionary1, missionary2, missionary3,
						canibal1, canibal2, canibal3);
				goal.getBoat().setShore(goal.getRight());
			}
		};

		public River river;
		public River goal;

		public abstract void create();
	}
}
