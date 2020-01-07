package WorldMap;
import Config.Config;
import Entity.*;

public class WorldMap {
	protected Entity[][] map;
	private char[][] charmap;
	public Player player;
	public Entity next;
	
	private void insertEntity(int x, int y, Entity en){
		map[x][y]= en;
	}
	
	private void deleteEntity(int x, int y){
		map[x][y]=null;
	}
	
	public void initialize(char[][] map){
		this.map= new Entity[map.length][map[0].length];
		charmap=map;
		Entity a =null;
		//this.map=new Entity.Entity[map.length][];
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){
				switch(map[i][j]){
				case 'M':
					a=new Monster();
					break;
				case '#':
					a=new Wall();
					break;
				case '@':
					a=new Player();
					break;
				case 'F':
					a=new Food();
					break;
				case 'R':
					a=new Rock();
					break;
				case 'W':
					a=new Weapon();
					break;
				default:
					a=null;
				}
				insertEntity(i,j,a);
				if(a!=null){
				a.setRow(i);
				a.setCol(j);
				}
				if(map[i][j]=='@') {
					player=(Player)this.map[i][j];
				}
			}
		}
	}
	
	private void showMap(){
		for(int i=0; i<charmap.length; i++){
			for(int j=0; j<charmap[i].length; j++) {System.out.print(charmap[i][j]);}
			System.out.println();
		}
	}
	
	public void getNext(Entity x, String command){//move 하기전에 그 앞에 있는 객체를 next에 저장
		int i;
		switch(command){
		case "a":
		case "A":
			if((i=x.getCol())!=0) next=map[x.getRow()][x.getCol()-1];
			break;
		case "d":
		case "D":
			if((i=x.getCol())!=map[0].length) next=map[x.getRow()][x.getCol()+1];
			break;
		case "w":
		case "W":
			if((i=x.getRow())!=0) next=map[x.getRow()-1][x.getCol()];
			break;
		case "s":
		case "S":
			if((i=x.getRow())!=map.length) next=map[x.getRow()+1][x.getCol()]; 
			break;
		default:
			System.out.println("Wrong command!");
		}
	}
	
	public void moveEntity(Entity x, String command){//move 하기전에 그 앞에 있는 객체를 next에 저
		int i;
		if(next==null || next.getIcon()!='#'){
		switch(command){
		case "a":
		case "A":
			if((i=x.getCol())!=0){ map[x.getRow()][x.getCol()]=null; x.setCol(--i); map[x.getRow()][x.getCol()]=x;}
			break;
		case "d":
		case "D":
			if((i=x.getCol())!=map[0].length) { map[x.getRow()][x.getCol()]=null; x.setCol(++i); map[x.getRow()][x.getCol()]=x;}
			break;
		case "w":
		case "W":
			if((i=x.getRow())!=0) { map[x.getRow()][x.getCol()]=null; x.setRow(--i); map[x.getRow()][x.getCol()]=x;}
			break;
		case "s":
		case "S":
			if((i=x.getRow())!=map.length) { map[x.getRow()][x.getCol()]=null; x.setRow(++i); map[x.getRow()][x.getCol()]=x;}
			break;
		default:
			System.out.println("Watch your key or you'll get penalty: your HP goes down by 1");
			player.HP-=1;
		}
		}
	}
	public String showMenu(){
		System.out.println(Config.mainMenu);
		return Config.scan.next();
	}
	
	public void showStatus(Entity x){
		System.out.println(x.getIcon()+" status");
		System.out.println("HP:"+x.HP+"/"+x.maxHP+"\tPower:"+x.power);
		System.out.println("\tWeapon:"+x.inventory);
	}
	public void run(){
		//아이콘을 받고
		//q 할 때까지 혹은 finish가 트루 될때까지 계속 command 받기 
		boolean finish=false;
		String a=null;
		showMap();//맵보여주
		showStatus(player);
		a=showMenu();//명령받
		while(!(a.equals("q")||a.equals("Q")||a.equals("quit")||a.equals("Quit"))){//명령q나올때까지 
			getNext(player,a);//Player가 가는 지역에 있는 Entity next를 조사.  
			if(next==null) moveEntity(player,a);// 가는 지역에 아무것도 없으면 move.
			else{//뭔가있음 showMenu해서 뭐 할수잇는지 보여주고 
				String b=next.showMenu();
				next.inputCommand(this,a,b);
			}
			finish=true;
			for(int i=0; i<charmap.length; i++){//entitiy map을 charmap에 복사 
				for(int j=0; j<charmap[i].length; j++){
					if(map[i][j]==null) charmap[i][j]='.';
					else {
						if(map[i][j].getIcon()=='M') finish=false;  //check if there's any monster if yes, continue, no-> getend() with boolean finish true
						charmap[i][j]=map[i][j].getIcon();
					}
				}
			}
			if(player.HP<=0) getEnd(finish);
			if(finish) getEnd(finish);
			showMap();//맵보여주
			showStatus(player);
			a=showMenu();//명령받기 
		}
		//q 받으면 monster 있는지 다시 체크
		finish=true;
		for(int i=0; i<charmap.length; i++){
			for(int j=0; j<charmap[i].length; j++) {if(map[i][j]!=null){if(map[i][j].getIcon()=='M') finish=false;}}
		}
		getEnd(finish);
	}
	
	public void getEnd(boolean x){//finish boolean 을 받아서 true면 clear! or game over! and stop all program.
		if(x) System.out.println("Clear!");
		else System.out.println("Game over..Try again...");
		System.exit(0);
	}
}
