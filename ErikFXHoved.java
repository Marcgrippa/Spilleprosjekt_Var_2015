package spillprosjekt;

import java.util.ArrayList;

public class ErikFXHoved {

	int[] romFordeling = {200, 200, 0, 0, 20, 0, 0, 0, 0};
	int[] tingFordeling = {30, 30, 30, Dagbok.getAntallSiderTotal()};
	RoomFactory liste = new RoomFactory(romFordeling);
	ItemFactory ting = new ItemFactory(tingFordeling);
	ArrayList<Item> unfoundItems = ting.getList();
	Brett brett = new Brett(liste.getAbstractTile());
	int dim = Brett.getDim();
	Human mann = new Human(dim/2, dim/2);
	String rom = brett.getType(mann.getX(), mann.getY());
	boolean stromPa = false;
	boolean mottMerkel = false;
	
	public ErikFXHoved(){
		
	}
	
	public void start(){
		brett.moved(mann.getX(), mann.getY());
		brett.print(mann.getX(), mann.getY());
	}
	
	public void opp(){
		mann.opp();
			if(!brett.moved(mann.getX(), mann.getY())){
				mann.ned();
			}
		brett.print(mann.getX(), mann.getY());
		gjortFlytt();
	}
	
	public void ned(){
		mann.ned();
			if(!brett.moved(mann.getX(), mann.getY())){
				mann.opp();
			}
		brett.print(mann.getX(), mann.getY());
		gjortFlytt();
	}
	
	public void venstre(){
		mann.venstre();
			if(!brett.moved(mann.getX(), mann.getY())){
				mann.hoyre();
			}
		brett.print(mann.getX(), mann.getY());
		gjortFlytt();
	}
	
	public void hoyre(){
		mann.hoyre();
			if(!brett.moved(mann.getX(), mann.getY())){
				mann.venstre();
			}
		brett.print(mann.getX(), mann.getY());
		gjortFlytt();
	}
	
	private void gjortFlytt(){
		rom = brett.getType(mann.getX(), mann.getY());
		if(rom.equals("d")){
			kamp();
		}
		else if(rom.equals("b") && !mottMerkel){
			forsteMote();
		}
		mann.endreSult(-1);
		System.out.println("Din sult er på: " + mann.getSult());
	}
	
	private void kamp(){
		System.out.println("Du har støtt på noe som vil slåss mot deg og ta tingene dine!");
		if(mann.checkInventory(new Item("Skudd"))){
			mann.removeItem(new Item("Skudd"));
			System.out.println("Du brukte ett av skuddene dine for å drepe det som slåss mot deg. Du mister ingen liv.");
		}
		else{
			mann.endreLiv(-15);			
			if(mann.getLiv() < 1){
				System.out.println("Du er egentlig død, men siden dette ikke er ferdig versjon får du spille videre.");
			}
			else{
				System.out.println("Du overlevde uten noen komplikasjoner. Gratulerer.");				
			}
		}
		System.out.println("Liv igjen: " + mann.getLiv());
		brett.fjernetTrussel(mann.getX(), mann.getY());
	}
	
	public void inventory(){
		System.out.println(mann.getInventory());
	}
	
	public void sok(){
		if(rom.equals("s")){
			mann.addItem(unfoundItems.get(0));
			unfoundItems.remove(0);
			brett.tattTing(mann.getX(), mann.getY());
			System.out.println(mann.getInventory());
		}
		else{
			System.out.println("Dette rommet er tomt.");
		}
	}
	
	public void les(){
		mann.lesDagBok();
	}
	
	public void spis(){
		if(mann.checkInventory(new Item("Mat"))){
			mann.endreSult(3);
			mann.removeItem(new Item("Mat"));
		}
		else{
			System.out.println("Du har ikke noe mat du kan spise.");
		}
		System.out.println("Din sult er på: " + mann.getSult());
	}
	
	public void bandasje(){
		if(mann.checkInventory(new Item("Bandasje"))){
			mann.endreLiv(20);
			mann.removeItem(new Item("Bandasje"));
		}
		else{
			System.out.println("Du har ingen bandasjer du kan bruke, men kanskje det ligger noen i ett av rommene?");
		}
		System.out.println("Du har " + mann.getLiv() + " liv igjen.");
	}
	
	public int getLiv(){
		return mann.getLiv();
	}
	
	public int getSult(){
		return mann.getSult();
	}
	
	public ArrayList<Item> getInventory(){
		return mann.getInventory();
	}
	
	public String getBrett(){
		return brett.streng(mann.getX(), mann.getY());
	}
	
	public ArrayList<String> getbok(){
		return mann.getDagBok();
	}
	
	public int antallMat(){
		int i = 0;
		for(Item t : mann.getInventory()){
			if(t.er(new Item("Mat"))){
				i++;
			}
		}
		return i;
	}
	
	public int antallBandasje(){
		int i = 0;
		for(Item t : mann.getInventory()){
			if(t.er(new Item("Bandasje"))){
				i++;
			}
		}
		return i;
	}
	
	public int antallSkudd(){
		int i = 0;
		for(Item t : mann.getInventory()){
			if(t.er(new Item("Skudd"))){
				i++;
			}
		}
		return i;
	}
	
	public void sov(){
		if(brett.getType(mann.getX(), mann.getY()).equals("p")){
			mann.fullSult();
		}
	}
	
	private void forsteMote(){
		mann.endreLiv(-50);
		mottMerkel = true;
	}

	public ArrayList<Integer> getBrettInt() {
		return brett.getInt();
	}
	
	public void setStromPa(){
		this.stromPa = true;
	}

	public ArrayList<String> getHelBok() {
		return mann.getHelBok();
	}
}