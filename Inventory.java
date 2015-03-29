import java.util.ArrayList;


public class Inventory {
	
	/*
	 * inventory: holder styr på hvilke items du har
	 * inventorySlotsMax: sier hvor mange plasser du har i inventoriet ditt
	 * inventorySlotUsed: sier hvor mange plasser i inventoriet du har brukt opp
	 * character: holder styr på om inventoriet hører til Human eller Robot
	 * 
	 * ammoNumber: antall skudd itemet har
	 * canFire: for Human: er true hvis du har et våpen, og false hvis du ikke har det
	 * 			for Robot: er true alltid
	 * canStab: er true hvis du har plukket opp et håndholdt våpen
	 * keyCard: er true hvis du har plukket opp et nøkkelkort - gir deg tilgang til stengte dører
	 * flashLight: er true hvis du har en lommelykt med batteri i - gir deg mot nok til å gå inn i mørke rom
	 * cofe: er true hvis du har plukket opp kaffe og gir det energi
	 * food: er true hvis du har plukket opp mat og gir deg energi
	 */
	
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int inventorySlotsMax = 10;
	private int inventorySlotsUsed = 0;
	private char character;
	
	private int ammoNumber = 0;
	private boolean canFire = false;
	private boolean canStab = false;
	private boolean keyCard = false;
	private boolean flashLight = false;
	private boolean cofe = false;
	private boolean food = false;

	
	/*
	 * Konstruktøren tar inn en char som velger om inventoriet hører til Human karakteren eller
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
	 * Ser om det er mulig å plukke opp flere items
	 * Kan ikke plukke opp flere items hvis inventorien er full
	 */
	public boolean canPickUpItem(Item item){
		if(inventorySlotsUsed + item.getSpaceUse() < getInventorySlotsMax()){
			return true;
		}
		return false;
	}


	/*
	 * Legger til et item i inventoriet 
	 * Og kaller metoden som oppdatere tilstanden til karakteren
	 */
	public void addItemToInventory(Item item){
		if(canPickUpItem(item)){
			inventory.add(new Item(item.getName()));
		}
		updateAblities();

	}
	
	
	/*
	 * Oppdatere tilstanden til karakteren
	 * Type, hvis han har plukket opp et våpen så kan han nå skyte
	 */
	private void updateAblities(){
		for(Item item: inventory){
			switch (item.getName()) {
			
			case "ammo":
				setAmmoNumber(ammoNumber + 4);
				item.setSpaceUse(1);
				break;
			case "fireArm":
				setCanFire(true);
				item.setSpaceUse(2);
				break;
			case "knife":
				setCanStab(true);
				item.setSpaceUse(1);
				break;
			case "keyCard":
				setKeyCard(true);
				item.setSpaceUse(1);
				break;
			case "flashLight":
				setFlashLight(true);
				item.setSpaceUse(1);
				break;
			case "coffee":
				setCofe(true);
				item.setSpaceUse(1);
				break;
			case "food":
				setFood(true);
				item.setSpaceUse(1);
				break;
			default:
				break;
			}
		}
		
	}
	
	/*
	 * Metodent tar inn to inventories og et item som er ønske å bytte
	 * Hvis mottakeren har plass i inventoriet sitt, så fjernes itemet 
	 * fra den tidligere eieren og legges til i den nye eieren
	 */
	public void changeItem(Inventory oldItemholder, Inventory newItemHolder, Item item){
		if(canChange(newItemHolder, item)){
			oldItemholder.removeItem(item);
			newItemHolder.addItemToInventory(item);
		}
		
	}

	/*
	 * Returnere true hvis det er plass i inventoriet til den nye eieren av itemet
	 */
	private boolean canChange(Inventory newItemHolder, Item item) {
		return newItemHolder.getInventorySlotsUsed() + item.getSpaceUse() <= newItemHolder.getInventorySlotsMax();
	}
	
	/*
	 * Fjerner et item fra inventori listen
	 */
	private void removeItem(Item item){
		if(inventory.contains(item)){
			inventory.remove(item);
		}
		
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

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public int getInventorySlotsMax() {
		return inventorySlotsMax;
	}

	public void setInventorySlotsMax(int inventorySlotsMax) {
		this.inventorySlotsMax = inventorySlotsMax;
	}

	public int getAmmoNumber() {
		return ammoNumber;
	}

	public void setAmmoNumber(int ammoNumber) {
		this.ammoNumber = ammoNumber;
	}

	public boolean isCanFire() {
		return canFire;
	}

	public void setCanFire(boolean canFire) {
		this.canFire = canFire;
	}

	public boolean isCanStab() {
		return canStab;
	}

	public void setCanStab(boolean canStab) {
		this.canStab = canStab;
	}

	public boolean isKeyCard() {
		return keyCard;
	}

	public void setKeyCard(boolean keyCard) {
		this.keyCard = keyCard;
	}

	public boolean isFlashLight() {
		return flashLight;
	}

	public void setFlashLight(boolean flashLight) {
		this.flashLight = flashLight;
	}

	public boolean isCofe() {
		return cofe;
	}

	public void setCofe(boolean cofe) {
		this.cofe = cofe;
	}

	public boolean isFood() {
		return food;
	}

	public void setFood(boolean food) {
		this.food = food;
	}

	public void setInventorySlotsUsed(int inventorySlotsUsed) {
		this.inventorySlotsUsed = inventorySlotsUsed;
	}
	

}
