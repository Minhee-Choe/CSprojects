package Entity;
import Config.Config;
import WorldMap.WorldMap;

public class Rock extends Entity{
	public Rock(){
		power=(int)(Math.random()*50);
	}
	public char getIcon(){
		return 'R';
	}
	public String showMenu(){
		System.out.println("(M)ove / (L)eave");
		return Config.scan.next();
	}
	
	public void inputCommand(WorldMap w, String a,String b){
		wm=w;
		wm.showStatus(wm.player);
		wm.showStatus(wm.next);
		switch(b){
		case "m":
		case "M":
		case "Move":
		case "move":
			if(wm.player.power>wm.next.power){
				Entity tmp;
				tmp=wm.next;
				wm.getNext(wm.next,a);
				if(wm.next==null) wm.next=tmp;
				if(wm.next==tmp) {wm.moveEntity(wm.next,a); wm.moveEntity(wm.player,a);}
			}
			else System.out.println("You need more power");
			break;
		case "l":
		case "L":
		case "leave":
		case "Leave":
			break;
		default:
			System.out.println("Watch your key or you'll get penalty: your HP goes down by 1");
			wm.player.HP-=1;
			}
		}
}

