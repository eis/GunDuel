package main;

import players.*;

public class Main {
	public static void main(String[] args){
		// Total number of turns per duel.
		int duelLength = 100;
		// Player identifier 1: HumanPlayer.
		Player human = new OpportunistPlayer(2);
		// Player identifier 2: GunClubPlayer.
		Player gunClub = new GunClubPlayer(1);
		
		// Prepares a duel.
		GunDuel duel = new GunDuel(human,gunClub, duelLength);
		// Start a duel.
		duel.fight();
		
		
	}

}
