package spillprosjekt;

public class Item {
	
	/*
	 * name: holder styr på item navent
	 * spaceUse: holder styr på hvor stor plass i inventoriet itemet tar
	 */
	
	private String name = "";
	private int spaceUse = 0;

	/*
	 * Tar inn navenet på itemet du plukker opp
	 * og 'lager' itemet
	 */
	public Item(String name) {
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
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}