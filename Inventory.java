import java.util.ArrayList;


public class Inventory {
	
	/*
	 * inventory: holder styr p� hvilke items du har
	 * inventorySlotsMax: sier hvor mange plasser du har i inventoriet ditt
	 * inventorySlotUsed: sier hvor mange plasser i inventoriet du har brukt opp
	 * character: holder styr p� om inventoriet h�rer til Human eller Robot
	 */
	
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int inventorySlotsMax = 10;
	private int inventorySlotsUsed = 0;
	private char character;

	
	/*
	 * Konstrukt�ren tar inn en char som velger om inventoriet h�rer til Human karakteren eller
	 * Robot karakteren. 
	 */
	public Inventory(char c) {
		if(c == 'H'){
			setCharacter('H');
		}
		else{
			setCharacter('R');
		}
		
		
	}
	
	/*
	 * Ser om det er mulig � plukke opp flere items
	 * Kan ikke plukke opp flere items hvis inventorien er full
	 */
	public boolean canPickUpItem(){
		if(inventorySlotsMax < inventorySlotsUsed){
			return true;
		}
		return false;
	}


	/*
	 * Legger til et item i inventoriet 
	 */
	public void addItemToInventory(String itemName){
		inventory.add(new Item(itemName));
	}

	/*
	 * Getters og Setters
	 */
	public int getInventorySlots() {
		return inventorySlotsMax;
	}
	public char getCharacter() {
		return character;
	}
	
	public void setCharacter(char character) {
		this.character = character;
	}
	
	public void setInventorySlots(int inventorySlots) {
		this.inventorySlotsMax = inventorySlots;
	}
	
	public int getInventorySlotsUsed() {
		for(Item itemslots: inventory){
			inventorySlotsUsed += itemslots.getSpaceUse();
		}
		return inventorySlotsUsed;
	}

}
