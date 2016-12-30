package players;

public class CustomPlayer extends Player {

	// [Optionally Customize] Obtain identifier of opponent.
	public CustomPlayer(int opponent) {
		super(opponent);
		/* your code goes here */
	}

	// [Optionally Customize] Obtain identifier of opponent.
	public CustomPlayer() {

	}

	// [Must Customize] Fight others!
	public Action fight() {
		/* Your code goes here */

		// Returns LOAD, BULLET, PLASMA, METAL or THERMAL.
		// You can return load(), bullet(), plasma(), metal(), thermal()
		// which help you keep track of your history and ammo count.
		return null;
		/* Your code ends here */

	}

	// [Optionally Customize] Notified of opponent action in after turn.
	public void perceive(Action action) {

		super.perceive(action);
		/* Your code goes here */

		// You can choose not to inherit this method.
		// Do not delete the super.perceive(action); below if you choose to
		// inherit.

		/* Your code ends here */
	}

	// [Optionally Customize] Notified of opponent action in after turn.
	public void declared(Result result) {
		/* Your code goes here */

		// You can choose not to inherit this method

		/* Your code ends here */
	}

	/* Private stuff goes here */

}
