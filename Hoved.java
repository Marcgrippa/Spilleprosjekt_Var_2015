package spillprosjekt;

import java.util.ArrayList;
import java.util.Scanner;

public class Hoved {
	

	public static void main(String[] args) {
		spill();
	
	}

	private static void spill(){
		int[] romFordeling = {15, 15, 10, 5, 5, 2, 2, 2, 2};
		int[] tingFordeling = {15, 50};
		RoomFactory liste = new RoomFactory(romFordeling);
		ItemFactory ting = new ItemFactory(tingFordeling);
		ArrayList<Item> unfoundItems = ting.getList();
		Brett brett = new Brett(liste.getAbstractTile());
		int dim = brett.getDim();
		Human mann = new Human(dim/2, dim/2);
		Scanner scanner = new Scanner(System.in);
		brett.moved(mann.getX(), mann.getY());
		brett.print();
		while(scanner.hasNext()){
			String rom = brett.getType(mann.getX(), mann.getY());
			String token = scanner.nextLine();
			switch(token){
			case "opp":
				mann.opp();
				if(!brett.moved(mann.getX(), mann.getY())){
					mann.ned();
				}
				break;
			case "ned":
				mann.ned();
				if(!brett.moved(mann.getX(), mann.getY())){
					mann.opp();
				}
				break;
			case "venstre":
				mann.venstre();
				if(!brett.moved(mann.getX(), mann.getY())){
					mann.hoyre();
				}
				break;
			case "hoyre":
				mann.hoyre();
				if(!brett.moved(mann.getX(), mann.getY())){
					mann.venstre();
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
			}
			rom = brett.getType(mann.getX(), mann.getY());
			if(rom.equals("d")){
				overDramatiskFightScene();
				brett.fjernetTrussel(mann.getX(), mann.getY());
			}
			
			else if(rom.equals("p")){
				powerNapScene();
				//update life
			}
			
			else if(rom.equals("c")){
				computerRomScene();
				//update map
			}
			
			else if(rom.equals("l1")){
				lockDoorScene(1);
			}
			
			else if(rom.equals("l2")){
				lockDoorScene(2);
			}
			
			else if(rom.equals("l3")){
				lockDoorScene(3);
			}
			
			else if(rom.equals("l4")){
				lockDoorScene(4);
			}
			
			brett.print();
		}
		scanner.close();
	}
	
	private static void lockDoorScene(int x){
		if(x == 1){
			System.out.println("Døren oppover er låst");
		}
		else if(x == 2){
			System.out.println("Døren til høyre er låst");
		}
		else if(x == 3){
			System.out.println("Døren nedover er låst");
		}
		else if(x == 4){
			System.out.println("Døren til venstre er låst");
		}
		
	}
	
	private static void computerRomScene(){
		System.out.println("Du fant en pc, hvis du har koden kan du få sett mer av kartet");
		// Har man koden dukker det opp noen random rom på kartet ditt
	}

	private static void powerNapScene(){
		System.out.println("Du fant en seng og øynene dine skriker etter en powernap");
		// Hvis du velger å sove får du mer liv, ellers skjer det ingen ting
		System.out.println("Du har fått 1 liv mer");
	}
	
	private static void overDramatiskFightScene() {
		System.out.println("Du har støtt på noe som vil slåss mot deg og ta tingene dine!");
		System.out.println("Du overlevde uten noen komplikasjoner. Gratulerer.");
	}
}
