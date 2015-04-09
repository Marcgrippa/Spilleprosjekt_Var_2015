package spillprosjekt;


public class Item_MIN {
	
	/*
	 * name: holder styr p� item navent
	 * spaceUse: holder styr p� hvor stor plass i inventoriet itemet tar
	 */
	private String navn;
	
	private int spaceUse = 0;

	/*
	 * Tar inn navenet p� itemet du plukker opp
	 * og 'lager' itemet
	 */
	public Item_MIN(String name) {
		setName(name);
	}
	
	/*
	 * Getter og setter
	 */
	public int getSpaceUse() {
		return spaceUse;
	}
		

	public void setSpaceUse(int spaceUse) {
		this.spaceUse = spaceUse;
	}
		
		
	public String getNavn() {
		return navn;
	}
	public void setName(String name) {
		this.navn = name;
	}

	public String toString(){
		return this.getNavn();
	}
}