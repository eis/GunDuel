package players;

public class TurtlePlayer extends Player {

	@Override
	public Action fight() {
		if (getAmmoOpponent() > 0) {
            // Beware! Opponent has ammo!

            if (getRandomInteger(4) == 0 && getAmmo() > 0) 
                // YOLO it:
                return getAmmo() > 1 ? plasma() : bullet();

            // Play it safe:
            if (getAmmoOpponent() == 1) return metal();
            return (getRandomInteger(1) > 0) ? metal() : thermal();
        }

        if (getAmmo() == 0) 
            // Nobody has ammo: Time to load up.
            return load();

        else if (getAmmo() > 1) 
            // We have enough ammo for a plasma: fire it!
            return plasma();

        else 
            // Either load, or take a shot.
            return (getRandomInteger(1) > 0) ? load() : bullet();
	}

}
