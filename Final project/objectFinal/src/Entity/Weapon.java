package Entity;

import java.lang.Math;
import Config.Config;
import WorldMap.WorldMap;

public class Weapon extends Entity{
	public Weapon(){
		power=10+(int)(Math.random()*50);
	}
	public char getIcon(){
		return 'W';
	}
	public String showMenu(){
		System.out.println("(E)at / (L)eave");
		return Config.scan.next();
	}

	public void inputCommand(WorldMap w, String a,String b){
		wm=w;
		switch(b){
		case "e":
		case "E":
		case "eat":
		case "Eat":
			wm.moveEntity(w.player,a);
			w.player.power+=this.power;
			break;
		case "l":
		case "L":
		case "leave":
		case "Leave":
			break;
		default:
			System.out.println("Watch your key or you'll get penalty: your HP goes down by 1");
			w.player.HP-=1;
		}
	}
	
}
