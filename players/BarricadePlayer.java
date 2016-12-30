package players;

public class BarricadePlayer extends Player {

	@Override
	public Action fight() {
        if (getTurn() == 0) { return load(); }
        int r = getRandomInteger(99) + 1; //Get a random
        if ((r <= 15) && (getAmmo() > 0)) { return bullet(); } //Override any action, and just shoot
        else
        {
            if (getTurn() % 5 == 0) //Every first and fifth turn
                return load();
            if (getAmmoOpponent() == 1) return metal();
            if (getAmmoOpponent() > 1) { return r <= 50 ? metal() : thermal(); }
            if (getAmmoOpponent() == 0) return load();

        }
        return bullet();
	}

}
