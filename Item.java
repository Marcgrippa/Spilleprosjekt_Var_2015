
public class Item {
	
	/*
	 * name: holder styr på item navent
	 * ammoNumber: antall skudd itemet har
	 * canFire: for Human: er true hvis du har et våpen, og false hvis du ikke har det
	 * 			for Robot: er true alltid
	 * canStab: er true hvis du har plukket opp et håndholdt våpen
	 * keyCard: er true hvis du har plukket opp et nøkkelkort - gir deg tilgang til stengte dører
	 * flashLight: er true hvis du har en lommelykt med batteri i - gir deg mot nok til å gå inn i mørke rom
	 * cofe: er true hvis du har plukket opp kaffe og gir det energi
	 * food: er true hvis du har plukket opp mat og gir deg energi
	 * spaceUse: holder styr på hvor stor plass i inventoriet itemet tar
	 */
	
	private String name = "";
	private int ammoNumber = 0;
	private boolean canFire = false;
	private boolean canStab = false;
	private boolean keyCard = false;
	private boolean flashLight = false;
	private boolean cofe = false;
	private boolean food = false;
	private int spaceUse = 0;

	

	/*
	 * Tar inn navenet på itemet du plukker opp
	 * og 'lager' itemet
	 */
	public Item(String name) {
		setName(name);
		makeItem();
	}
	
	
	
	/*
	 * Lager itemet
	 * Hvis du plukke opp et item setter variablene til en ny verdi som 
	 * initialisere funkasjonen til itemet
	 */
	private void makeItem(){
		switch (name) {
		
		case "ammo":
			setAmmoNumber(ammoNumber + 4);
			setSpaceUse(1);
			break;
		case "fireArm":
			setCanFire(true);
			setSpaceUse(2);
			break;
		case "knif":
			setCanStab(true);
			setSpaceUse(1);
			break;
		case "keyCard":
			setKeyCard(true);
			setSpaceUse(1);
			break;
		case "flashLight":
			setFlashLight(true);
			setSpaceUse(1);
			break;
		case "cofe":
			setCofe(true);
			setSpaceUse(1);
			break;
		case "food":
			setFood(true);
			setSpaceUse(1);
			break;

		default:
			break;
		}
	}
	
	
	
	/*
	 * Getter og setter
	 */
	
	
	public boolean isFood() {
		return food;
	}

	public void setFood(boolean food) {
		this.food = food;
	}

	public boolean isCofe() {
		return cofe;
	}

	public void setCofe(boolean cofe) {
		this.cofe = cofe;
	}

	public int getSpaceUse() {
		return spaceUse;
	}

	public void setSpaceUse(int spaceUse) {
		this.spaceUse = spaceUse;
	}

	public void setAmmoNumber(int ammoNumber) {
		this.ammoNumber = ammoNumber;
	}

	public void setCanFire(boolean canFire) {
		this.canFire = canFire;
	}

	public void setCanStab(boolean canStab) {
		this.canStab = canStab;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public int getAmmoNumber() {
		return ammoNumber;
	}

	public boolean isCanFire() {
		return canFire;
	}

	public boolean isCanStab() {
		return canStab;
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
	
	
	
	
	

}
