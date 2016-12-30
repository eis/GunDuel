package players;

public class SurvivorPlayer extends Player {

	@Override
	public Action fight() {
		 int myAmmo = getAmmo();
	        int opponentAmmo = getAmmoOpponent();
	        int turn = getTurn();
	        if (turn == 0) {
	            return load();
	        }
	        switch (opponentAmmo) {
	        case 0:
	            if (myAmmo > 2) {
	                return (getRandomInteger(1) % 2) > 0 ? bullet() : plasma();
	            }
	            return load();
	        case 1:
	            if (myAmmo > 2) {
	                return plasma();
	            }
	            return metal();
	        default:
	            if (myAmmo > 2) {
	                return plasma();
	            }
	            return (getRandomInteger(1) % 2) > 0 ? metal() : thermal();
	        }
	}

}
