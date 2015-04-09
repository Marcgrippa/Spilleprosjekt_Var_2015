package spillprosjekt;

import java.util.ArrayList;

public class Human {

	private int x;
	private int y;
	private int sult;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int HP;
	
	
	public Human(int x, int y){
		this.x = x;
		this.y = y;
		this.sult = 10;
		this.HP = 72;
	}
	
	public void opp(){
		x--;
	}
	
	public void ned(){
		x++;
	}
	
	public void hoyre(){
		y++;
	}
	
	public void venstre(){
		y--;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void addItem(Item ting){
		inventory.add(ting);
	}
	
	public void removeItem(Item ting){
		if(inventory.contains(ting)){
			inventory.remove(ting);
		}
	}
	
	public boolean checkInventory(Item ting){
		for(Item t : inventory){
			if(t.er(ting)){
				return true;
			}
		}
		return false;
	}

	public ArrayList<Item> getInventory() {
		return this.inventory;
	}
	
	public int getSult(){
		return this.sult;
	}
	
	public void endreSult(int i){
		this.sult += i;
	}
	
	public int getHP() {
		return HP;
	}
	
	public void setHP(int hP) {
		HP = hP;
	}
	
	public void printHP(){
		System.out.println("Du har nå: " + getHP());
	}
}
