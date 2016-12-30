package players;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Player {
	
	// Load ammo, shoot bullet / plasma, metal shield / thermal deflector
	public static enum Action {
		LOAD(0), BULLET(1), PLASMA(2), METAL(3), THERMAL(4);

		private final int value;

		private Action(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	};

	// Did the player win?
	public static enum Result {
		DRAW(0), WIN(1), LOSS(2);

		private final int value;

		private Result(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	};

	// [Must Customize] Fight others!
	public abstract Action fight();

	// [Optionally Customize] Notified of opponent action in after turn.
	public void perceive(Action action) {
		mHistoryOpponent.add(action);
		switch (action) {
		case LOAD:
			mAmmoOpponent += 1;
			break;
		case BULLET:
			mAmmoOpponent -= 1;
			break;
		case PLASMA:
			mAmmoOpponent -= 2;
			break;
		default:
		}
	}

	// [Optionally Customize] Notified of win / loss / draw status.
	public void declared(Result result) {

	};

	public static int getRandomInteger(int max) {
		return ThreadLocalRandom.current().nextInt(0, max + 1);
	}

	// Constructor which accepts identifier of opponent.
	public Player(int opponent) {
		mOpponent = opponent;
		mAmmo = 0;
	}

	public Player() {
		this(-1);
	}

	// Get ammo count.
	public int getAmmo() {
		return mAmmo;
	}

	// Get current turn count (0-based).
	public int getTurn() {
		return mTurn;
	}

	// Get History of player.
	public Vector<Action> getHistory() {
		return mHistory;
	}

	// Get History of opponent.
	public Vector<Action> getHistoryOpponent() {
		return mHistoryOpponent;
	}

	// Action '0': load ammo
	protected Action load() {
		mAmmo += 1;
		return action(Action.LOAD);
	}

	// Action '1': fire bullet
	protected Action bullet() {
		mAmmo -= 1;
		return action(Action.BULLET);
	}

	// Action '2': fire plasma
	protected Action plasma() {
		mAmmo -= 2;
		return action(Action.PLASMA);
	}

	// Action '-': shoot bullet
	protected Action metal() {
		return action(Action.METAL);
	}

	// Action '=': shoot bullet
	protected Action thermal() {
		return action(Action.THERMAL);
	}

	// Get the unique identifier of opponent.
	protected int getOpponent() {
		return mOpponent;
	}

	// Get ammo count of opponent.
	protected int getAmmoOpponent() {
		return mAmmoOpponent;
	}

	// Amount of loaded ammo.
	private int mAmmo;

	// Number of completed turns.
	private int mTurn;

	// Unique identifier per opponent player class.
	private int mOpponent;

	// Records history of player.
	private Vector<Action> mHistory = new Vector<Action>();

	// Records history of opponent.
	private Vector<Action> mHistoryOpponent = new Vector<Action>();

	// Records ammo count of opponent;
	int mAmmoOpponent;

	// Records player action and increments turn counter.
	private Action action(Action my_action) {
		++mTurn;
		mHistory.add(my_action);
		return my_action;
	}

}
