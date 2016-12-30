package players;

public class GunClubPlayer extends Player {

	public GunClubPlayer(int opponent) {
		super(opponent);
	}

	public GunClubPlayer() {
		this(-1);
	}

	public Action fight() {
		if (getTurn() % 2 == 0) {
			return load();
		} else {
			return bullet();
		}
	}

}
