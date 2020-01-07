package Entity;

import WorldMap.WorldMap;

public abstract class Entity {
	public int HP, maxHP, power;
	public boolean inventory=false;
	private int row, col;
	protected WorldMap wm;
	
	public void setRow(int i){
		this.row=i;
	}
	
	public void setCol(int j){
		this.col=j;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	abstract public char getIcon();
	
	abstract public String showMenu();
	
	abstract public void inputCommand(WorldMap w, String a,String b);
		
}


