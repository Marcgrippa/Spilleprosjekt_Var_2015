package spillprosjekt;

import java.util.ArrayList;
import java.util.Scanner;

public class Hoved {
	

	public static void main(String[] args) {
		spill();
	
	}

	private static void spill(){
		int[] romFordeling = {15, 15, 10, 0, 0, 0, 0, 0, 0};
		int[] tingFordeling = {15, 50, 50};
		RoomFactory liste = new RoomFactory(romFordeling);
		ItemFactory ting = new ItemFactory(tingFordeling);
		ArrayList<Item> unfoundItems = ting.getList();
		Brett brett = new Brett(liste.getAbstractTile());
		int dim = brett.getDim();
		Human mann = new Human(dim/2, dim/2);
		Scanner scanner = new Scanner(System.in);
		brett.moved(mann.getX(), mann.getY());
		brett.print();
		System.out.println("Din sult er på: " + mann.getSult());
		while(scanner.hasNext()){
			String rom = brett.getType(mann.getX(), mann.getY());
			String token = scanner.nextLine();
			switch(token){
			case "opp":
				mann.opp();
				if(!brett.moved(mann.getX(), mann.getY())){
					mann.ned();
				}
				else{
					mann.endreSult(-1);					
				}
				break;
			case "ned":
				mann.ned();
				if(!brett.moved(mann.getX(), mann.getY())){
					mann.opp();
				}
				else{
					mann.endreSult(-1);					
				}
				break;
			case "venstre":
				mann.venstre();
				if(!brett.moved(mann.getX(), mann.getY())){
					mann.hoyre();
				}
				else{
					mann.endreSult(-1);					
				}
				break;
			case "hoyre":
				mann.hoyre();
				if(!brett.moved(mann.getX(), mann.getY())){
					mann.venstre();
				}
				else{
					mann.endreSult(-1);					
				}
				break;
			case "sok":
				if(rom.equals("s")){
					mann.addItem(unfoundItems.get(0));
					unfoundItems.remove(0);
					brett.tattTing(mann.getX(), mann.getY());
				}
				else{
					System.out.println("Dette rommet er tomt.");
				}
				break;
			case "inventory":
				System.out.println(mann.getInventory());
				break;
			case "spis":
				if(mann.checkInventory(new Item("Mat"))){
					mann.endreSult(3);
				}
				else{
					System.out.println("Du har ikke noe mat du kan spise.");
				}
			}				
			rom = brett.getType(mann.getX(), mann.getY());
			if(rom.equals("d")){
				overDramatiskFightScene();
				brett.fjernetTrussel(mann.getX(), mann.getY());
			}
			brett.print();
			System.out.println("Din sult er på: " + mann.getSult());
		}
		scanner.close();
	}

	private static void overDramatiskFightScene() {
		System.out.println("Du har støtt på noe som vil slåss mot deg og ta tingene dine!");
		System.out.println("Du overlevde uten noen komplikasjoner. Gratulerer.");
	}
}
