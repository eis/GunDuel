package main;

import players.*;

public class Main {
	public static void main(String[] args){
		// Total number of turns per duel.
		int duelLength = 100;
		// Player identifier 1
		Player player1 = new OpportunistPlayer(2);
		// Player identifier 2
		Player player2 = new GunClubPlayer(1);
		
		// Prepares a duel.
		GunDuel duel = new GunDuel(player1, player2, duelLength);
		// Start a duel.
		duel.fight();
		
		
	}

}
