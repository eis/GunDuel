package players;

public class OpportunistPlayer extends Player {

	@Override
	public Action fight() {
		switch (getTurn() % 3) {
		case 0:
			return load();
		case 1:
			return metal();
		case 2:
			return bullet();
		}
		return plasma();
	}

}
