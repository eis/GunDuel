package players;

public class PlasmaPlayer extends Player {

	@Override
	public Action fight() {
        // Imma Firin Mah Lazer!
        if (getAmmo() > 1) return plasma();

        // Imma Block Yur Lazer!
        if (getAmmoOpponent() > 1) return thermal();

        // Imma need more Lazer ammo
        return load();
	}

}
