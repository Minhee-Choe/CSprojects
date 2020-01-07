package Entity;
import Config.Config;
import WorldMap.WorldMap;

public class Monster extends Entity{
	public Monster(){
		maxHP=50+(int)(Math.random()*200);
		HP=maxHP;
		power=20+(int)(Math.random()*50);
	}
	
	public char getIcon(){
		return 'M';
	}
	private void showStatus(Entity x){
		System.out.println(x.getIcon()+" status");
		System.out.println("HP:"+x.HP+"/"+x.maxHP+"\tPower:"+x.power);
		System.out.println("\tWeapon:"+x.inventory);
	}
	public String showMenu(){
		showStatus(this);
		System.out.println("(A)ttack / (F)lee");
		return Config.scan.next();
	}

	public void inputCommand(WorldMap w, String a,String b){
		wm=w;
		boolean flee=false;
		while(this.HP>0){
			switch(b){
			case "A":
			case "a":
			case "Attack":
			case "attack":
				w.player.HP-=w.next.power;
				if(w.player.HP<0) wm.getEnd(false);//이때 finish 는 계속 false 
				w.next.HP-=w.player.power;
				if(w.next.HP<=0) wm.moveEntity(w.player,a);
				break;
			case "F":
			case "f":
			case "flee":
			case "Flee":
				flee=true;
				break;
			default:
				System.out.println("Watch your key or you'll get penalty: your HP goes down by 1");
				w.player.HP-=1;
			}
			if(flee) break;
			wm.showStatus(w.player);
			b=this.showMenu();
			
		}
	}

}


