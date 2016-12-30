package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.GunDuel.Outcome;
import players.*;

public class Main {
	static class MyOutcome implements Comparable<MyOutcome> {
		int wins = 0; int draws = 0; int losses = 0;

		final Player p;
		public MyOutcome(Player p) {
			this.p = p;
		}
		@Override
		public String toString() {
			return "[wins=" + wins + ", draws=" + draws + ", losses=" + losses + "]";
		}

		@Override
		public int compareTo(MyOutcome other) {
			if (wins != other.wins)
				return Integer.compare(other.wins, this.wins);
			if (draws != other.draws)
				return Integer.compare(other.draws, this.draws);
			return 0;
		}
		
	};
	public static void main(String[] args){
		// Total number of turns per duel.
		int duelLength = 100;
		
		List<Player> players = new ArrayList<>();
		players.add(new SadisticShooterPlayer());
		players.add(new TurtlePlayer());
		players.add(new OpportunistPlayer());
		players.add(new GunClubPlayer());
		players.add(new BarricadePlayer());
		players.add(new SurvivorPlayer());
		players.add(new DeceptivePlayer());
		players.add(new PlasmaPlayer());
		
		Map<Player, MyOutcome> outcomes = new HashMap<Player, MyOutcome>(players.size());
		List<MyOutcome> outcomesList = new ArrayList<MyOutcome>(players.size());
		for (int round = 1; !players.isEmpty(); round++) {
			
			outcomes.clear();
			outcomesList.clear();
			
			for (Player p : players) {
				MyOutcome o = new MyOutcome(p);
				outcomes.put(p, o);
				outcomesList.add(o);
			}
			for (int i = 0; i < 100; i++) {
				for (int a = 0; a < players.size(); a++) {
				    for (int b = 0; b < players.size(); b++) {
				    	if (a == b) {
				    		continue;
				    	}
						// Prepares a duel.
				    	Player p1 = players.get(a);
				    	Player p2 = players.get(b);
						GunDuel duel = new GunDuel(p1, p2, duelLength);
						// Start a duel.
						Outcome o = duel.fight();
						// Report & record
						//System.out.print(p1 + " vs " + p2 + ": ");
						if (o == Outcome.AWIN) {
							//System.out.println(p1 + " won");
							outcomes.get(p1).wins++;
							outcomes.get(p2).losses++;
						} else if (o == Outcome.DRAW) {
							//System.out.println("draw");
							outcomes.get(p1).draws++;
							outcomes.get(p2).draws++;
						} else if (o == Outcome.BWIN) {
							//System.out.println(p2 + " won");
							outcomes.get(p1).losses++;
							outcomes.get(p2).wins++;
						}
				    }
				}
			}
			Collections.sort(outcomesList);
			MyOutcome lastOutcome = outcomesList.get(outcomesList.size() -1);
			System.out.printf("Four round %d, player %s leaves the table with score %s%n",
					round, lastOutcome.p.getClass().getName(),
					lastOutcome);
			players.remove(lastOutcome.p);
			if (players.size() < 2) {
				System.out.printf("Winner is %s with final score %s%n",
						players.get(0).getClass().getName(),
						outcomesList.get(0));
				break;
			}
		}
		
	}

}
