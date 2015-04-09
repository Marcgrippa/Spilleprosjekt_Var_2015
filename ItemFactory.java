package spillprosjekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {

	ArrayList<Item> ting = new ArrayList<Item>();
	public ItemFactory(int[] antall){
		
		for(int i = 0; i < antall[0]; i++){
			ting.add(new Item("Skudd"));
		}
		
		for(int j = 0; j < antall[1]; j++){
			ting.add(new Item("Bandasje"));
		}
		
		for(int k = 0; k < antall[1]; k++){
			ting.add(new Item("Mat"));
		}
		ting = stokk(ting);
	}
	
	public ArrayList<Item> getList(){
		return this.ting;
	}
	
	private ArrayList<Item> stokk(List<Item> kort){ // en shuffle-funksjon. Jeg synes ArrayList burde ha det innebygd.
		ArrayList<Item> nyeKort = new ArrayList<Item>();
		int lengde = kort.size();
		for(int i = 0; i < lengde; i++){
			nyeKort.add(null);
		}
		for(int j = 0; j < lengde; j++){
			Item kortet = kort.get(j);
			int plass = finnUbruktIndex(nyeKort, lengde);
			nyeKort.remove(plass);
			nyeKort.add(plass, kortet);
		}
		return nyeKort;
	}
	
	private int finnUbruktIndex(ArrayList<Item> kort, int grense){ // Finner en tilfeldig ubrukt plass. 
		Random tilfTall = new Random();
		int tall = tilfTall.nextInt(grense);
		if(kort.get(tall) == null){
			return tall;
		}
		else{
			return finnUbruktIndex(kort, grense);
		}
	}
}
