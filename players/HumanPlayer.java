package players;

import java.io.IOException;

public class HumanPlayer extends Player {

	// [Optionally Customize] Obtain identifier of opponent.
	public HumanPlayer(int opponent) {
		super(opponent);
		/* your code goes here */
	}

	// [Optionally Customize] Obtain identifier of opponent.
	public HumanPlayer() {

	}

	private String[] ACTION = { "0", "1", "2", "-", "=" };
	private String[] RESULT = { "drew", "won", "lost" };

	// [Must Customize] Fight others!
	public Action fight() {
		System.out.println(" :: Turn" + getTurn());
		// Prompts for player input
		int ch;
		boolean acceptableInput = false;
		do {

			System.out.println("    You [0/12/-=] >> ");
			try {
				ch = System.in.read();
				acceptableInput = ch == '0' || ch == '1' || ch == '2' || ch == '-' || ch == '=';

			} catch (IOException e) {
				e.printStackTrace();
				acceptableInput = false;
				ch = '#';
			}
			if (!acceptableInput) {
				System.out.println("");
			}

		} while (!acceptableInput);

		System.out.println("[" + ch + "] ");

		// Displays player action
		Action action;
		switch (ch) {
		case '1':
			System.out.println("fire a bullet");
			action = bullet();
			break;
		case '2':
			System.out.println("fire a plasma beam");
			action = plasma();
			break;
		case '-':
			System.out.println("defend using metal shield");
			action = metal();
			break;
		case '=':
			System.out.println("defend using thermal deflector");
			action = thermal();
			break;
		default:
			System.out.println("load ammo");
			action = load();
		}
		System.out.println(" (" + getAmmo() + " ammo)");

		// Submit action
		return action;

		//////////////////////////
	}

	// [Optionally Customize] Notified of opponent action in after turn.
	public void perceive(Action action) {

		super.perceive(action);
		System.out.println("    Opponent selects [" + ACTION[action.getValue()] + "] ");
		switch (action) {
		case LOAD:
			System.out.println("load ammo");
			break;
		case BULLET:
			System.out.println("fire a bullet");
			break;
		case PLASMA:
			System.out.println("fire a plasma beam");
			break;
		case METAL:
			System.out.println("defend using metal shield");
			break;
		case THERMAL:
			System.out.println("defend using thermal deflector");
			break;
		}
		System.out.println(" (" + getAmmoOpponent() + " ammo)");
	}

	// [Optionally Customize] Notified of opponent action in after turn.
	public void declared(Result result) {
		System.out.println(
				" :: You " + RESULT[result.getValue()] + " after " + getTurn() + " turns!" + "\n " + " :: Replay");

		System.out.println("    YOU ");
		for (Action action : getHistory()) {
			System.out.println(ACTION[action.getValue()]);
		}

		System.out.println("    FOE ");
		for (Action action : getHistoryOpponent()) {
			System.out.println(ACTION[action.getValue()]);
		}
	}

	/* Private stuff goes here */

}
