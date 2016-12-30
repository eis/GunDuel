package players;

import java.util.Vector;

public class SadisticShooterPlayer extends Player {
    boolean historySame(Vector<Action> history, int elements) {
        if (history.size() - 1 < elements) return false;
    	int firstToInclude = history.size() - elements - 1;

        for (int i = firstToInclude; i < history.size(); i++)
            if (history.get(i) != history.get(firstToInclude)) return false;
        return true;
    }
	@Override
	public Action fight() {
		int my_ammo = getAmmo();
        int opponent_ammo = getAmmoOpponent();
        int turn_number = getTurn();
        //std::cout << " :: Turn " << turn_number << " ammo: " << my_ammo << " oppo: " << opponent_ammo << std::endl;

        if (turn_number == 90) {
            // Getting impatient
            return load();
        }
        if (my_ammo == 0 && opponent_ammo == 0) {
            // It would be idiotic not to load here
            return load();
        }
        if (my_ammo >= 2 && historySame(getHistoryOpponent(), 3)) {
            if (getHistoryOpponent().get(turn_number - 1) == Action.THERMAL) return bullet();
            if (getHistoryOpponent().get(turn_number - 1) == Action.METAL) return thermal();
        }
        if (my_ammo < 2 && opponent_ammo == 1) {
            // I'd rather not die thank you very much
            return metal();
        }
        if (my_ammo == 1) {
            if (opponent_ammo == 0) {
                // You think I would just shoot you?
                return load();
            }
            if (turn_number == 2) {
                return thermal();
            }
            return bullet();
        }
        if (opponent_ammo >= 2) {
            // Your plasma weapon doesn't scare me
            return thermal();
        }
        if (my_ammo >= 2) {
            // 85% more bullet per bullet
            if (turn_number == 4) return bullet();
            return plasma();
        }
        // Just load the gun already
        return load();
	}

}
