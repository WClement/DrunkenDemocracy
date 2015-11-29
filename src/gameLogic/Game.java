package gameLogic;

import java.util.Scanner;


public class Game {
	
	// makes it so there can be up to 4 teams playing
	// by default, there are only 2 teams.
	private static Team[] teamList = new Team[4];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// This sets up a 2 teams
		// constructor notes: 1st position, full team name
		// 2nd position, shortcut name
		teamList[0] = new Team("Team Original","team1");
		teamList[1] = new Team("Team Challenger", "team2");
		
		for ( int i = 0; i < teamList.length; i++ ){
			if ( teamList[i] != null ){
				System.out.println( "\"" + teamList[i].getLongName() + "\" has entered the game. Their shortcut name is \"" + teamList[i].getShortName() + "\"");
			}	
		}
		
		//used for input from the console
		Scanner scan = new Scanner(System.in);
		
		int teamsAlive = 0;
		// Infinite loop while teamsAlive > 1
		do{
			//used to hold information about what command to run
			String teamName = "", command = "", options = "";
			//commands are entered in here
			//format: <shortName> <command> [options]
			if (scan.hasNext()){
				teamName = scan.next();
				
				//TODO check teamName is in the array of teams
				
				if (scan.hasNext()){
					command = scan.next();
					
					//TODO check that the command is legitimate
					
					// TODO based on the command, options may not be needed.
					if (scan.hasNext()){
						options = scan.next();
					}
				}
				else{
					System.out.println("Invalid format. No command found.");
				}
			} // done bringing in the command
			
			
			teamsAlive = 0;
			// figures out how many teams are alive
			for ( int i = 0; i < teamList.length; i++ ){
				if ( teamList[i] != null && teamList[i].stillAlive()){
					teamsAlive++;
				}	
			}
			
		}while (teamsAlive > 1); // finishes the infinite do while loop
		
		scan.close();
		
		//figures out the winner
		
		for ( int i = 0; i < teamList.length; i++ ){
			// if true, found the winner
			if ( teamList[i].stillAlive()){
				System.out.println("The winning team is: " + teamList[i].getLongName());
				break;
			}
			else if (i < teamList.length){
				System.out.println("Nobody wins when war starts wins when M.A.D. is in the air...");
			}
		}

	}

}
