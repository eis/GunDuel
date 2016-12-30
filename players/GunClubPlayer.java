package players;

public class GunClubPlayer extends Player {

	public Action fight() {
		if (getTurn() % 2 == 0) {
			return load();
		} else {
			return bullet();
		}
	}

}
