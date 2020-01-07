package Entity;
import Config.Config;
import WorldMap.WorldMap;

public class Player extends Entity{
	public Player(){
		maxHP=100;
		HP=maxHP;
		power=10;
		inventory=false;
	}
	public void inputCommand(WorldMap w, String a,String b){
		wm=w;
		switch(b){
		case "y":
		case "Y":
		case "yes":
		case "Yes"://원래 캐릭터는 거기에 그대로 두고 다른 캐릭터로 player 바꿈 
			w.player=(Player) w.next;
			break;
		case "n":
		case "N":
		case "no":
		case "No":	
			break;//싫으면 그대로 
		default:
			System.out.println("Watch your key or you'll get penalty: your HP goes down by 1");
			w.player.HP-=1;
		}
	}
	public char getIcon(){
		return '@';
	}
	
	public String showMenu(){
		System.out.println("Do you want to change character?(Y/N)");
		return Config.scan.next();
	}

}