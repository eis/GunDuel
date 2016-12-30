package main;

import players.*;
import players.Player.Action;

public class GunDuel {
	public static enum Outcome {
		DRAW, AWIN, BWIN
	};

	public static final Outcome[][] OUTCOME = {
			{ Outcome.DRAW, Outcome.BWIN, Outcome.BWIN, Outcome.DRAW, Outcome.DRAW },
			{ Outcome.AWIN, Outcome.DRAW, Outcome.BWIN, Outcome.DRAW, Outcome.AWIN },
			{ Outcome.AWIN, Outcome.AWIN, Outcome.DRAW, Outcome.AWIN, Outcome.DRAW },
			{ Outcome.DRAW, Outcome.DRAW, Outcome.BWIN, Outcome.DRAW, Outcome.DRAW },
			{ Outcome.DRAW, Outcome.BWIN, Outcome.DRAW, Outcome.DRAW, Outcome.DRAW } };

	public GunDuel(Player a, Player b, int turns) {
		mA = a;
		mB = b;
		mTurns = turns;
	}

	public GunDuel(Player a, Player b) {
		this(a, b, 2017);
	}

	public Outcome fight() {
		int turn = 0;
		boolean forceEnd = false;
		Outcome status = Outcome.DRAW;
		while (!forceEnd && turn < mTurns && status == Outcome.DRAW) {
			Action actionA = mA.fight();
			Action actionB = mB.fight();
			// Verify that actions are valid from both sides.
			boolean validA = mA.getAmmo() >= 0;
			boolean validB = mB.getAmmo() >= 0;
			mA.perceive(actionB);
			mB.perceive(actionA);

			if (validA && validB)
				status = OUTCOME[actionA.getValue()][actionB.getValue()];
			else {
				if (validA && !validB)
					status = Outcome.AWIN;
				else if (!validA && validB)
					status = Outcome.BWIN;
				else
					status = Outcome.DRAW;
				forceEnd = true;
			}
			++turn;
		}
		// Notify players of the outcome.
		switch (status) {
		case AWIN:
			mA.declared(Player.Result.WIN);
			mB.declared(Player.Result.LOSS);
			break;
		case BWIN:
			mA.declared(Player.Result.LOSS);
			mB.declared(Player.Result.WIN);
			break;
		default:
			mA.declared(Player.Result.DRAW);
			mB.declared(Player.Result.DRAW);
		}
		return status;

	}

	private Player mA;
	private Player mB;
	private int mTurns;

}
