package Entity;

import WorldMap.WorldMap;

public class Wall extends Entity{
	public char getIcon(){
		return '#';
	}
	public String showMenu(){
		System.out.println("You have to kill all of the monsters to finish this game");
		return null;
	}
	public void inputCommand(WorldMap w, String a,String b){
	
	}
}
