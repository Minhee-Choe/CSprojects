package Entity;
import Config.Config;
import WorldMap.WorldMap;

public class Food extends Entity{
	public char getIcon(){
		return 'F';
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
			w.player.HP+=30;
			if(w.player.HP>w.player.maxHP) w.player.HP=w.player.maxHP;
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
