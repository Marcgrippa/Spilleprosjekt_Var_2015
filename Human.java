package spillprosjekt;

import java.util.ArrayList;

public class Human {

	private int x;
	private int y;
	private int sult;
	private Dagbok dagbok;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int liv;
	
	public Human(int x, int y){
		this.x = x;
		this.y = y;
		this.sult = 10;
		dagbok = new Dagbok("Dagbok");
		inventory.add(dagbok);
		liv = 150;
	}
	
	public int getLiv() {
		return liv;
	}

	public void endreLiv(int endring) {
		this.liv += endring;
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
		if(ting.er(new Item("side"))){
			dagbok.funnetSide();
		}
		else{
			inventory.add(ting);			
		}
	}
	
	public void removeItem(Item ting){
		for(Item t : inventory){
			if(t.er(ting)){
				inventory.remove(t);
				return;
			}
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
	
	public void lesDagBok(){
		dagbok.printBok();
	}
	
	public ArrayList<String> getDagBok(){
		return dagbok.getList();
	}
	
	public void fullSult(){
		this.sult = 10;
	}

	public ArrayList<String> getHelBok() {
		return dagbok.getHelList();
	}
}
