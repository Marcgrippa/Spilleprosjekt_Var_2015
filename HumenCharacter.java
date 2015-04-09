package spillprosjekt;

public class HumenCharacter {
	
	private char character = 'H';
	private Inventory inventory;
	private Location location;
	private int HP = 10;
	

	public HumenCharacter(Location location) {
		this.location = location;
	}
	
	/*
	 * Metoder:
	 * skyt
	 * spis
	 * bruk nøkkelkort
	 * gå
	 * lys 
	 * 
	 */
	
	public void goLeft(MoveCommand move){
		move.goLeft(location.getX(), location.getY());
		
	}
	public void goRight(MoveCommand move){
		move.goRight(location.getX(), location.getY());
		
	}
	public void goUp(MoveCommand move){
		move.goUp(location.getX(), location.getY());
		
	}
	public void goDown(MoveCommand move){
		move.goDown(location.getX(), location.getY());
		
	}

	
	public void fire(){
		if(inventory.isCanFire() && inventory.getAmmoNumber() > 0){
			inventory.setAmmoNumber(inventory.getAmmoNumber() - 1);
			if(inventory.getAmmoNumber() == 0){
				/*
				 * Skal fjerne skudd itemet
				 */
				
//				inventory.removeItem("ammo");
			}
		}
	}
	
	public void eat(){
		if(inventory.isFood()){
			this.HP += 5;
			
//			inventory.removeItem(food);
		}
		
	}
	
	public void useKeyCard(){
		
		
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}
	
	
	
	
	
	
	

}
