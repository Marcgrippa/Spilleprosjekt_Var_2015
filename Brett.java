/*
 * Klasse for å lage et tilfeldig lagd brett ut ifra en gitt liste med kort. Det er noen parametre vi kan 
 * endre hvis vi vil ha mer sortert eller mindre fordelt. I koden kan det se ut som om det ikke skal havne
 * ett rom med fire utganger, men det kan skje, og jeg vet hvorfor. Hvis vi trenger kan jeg lage en metode
 * som går gjennom brettet og setter de rommene med fire utganger på nytt, men jeg tror ikke det blir noe 
 * behov for det, siden vi skal jo ha låste dører og andre kule ting. 
 * Koden kan kræsje, men det skjer veldig sjeldent. Det skjer fordi funksjonen kaller seg selv hvis den skal 
 * lete videre. Noen ganger kaller den seg selv nok ganger til å kaste en Stack-overflow-exception. Jeg vet 
 * ikke om det skjer lenger en gang, siden jeg har bare sett det en gang. Jo flere kort vi har, jo oftere 
 * skjer det tror jeg.
 */

package spillprosjekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Brett {

	private static final int dim = 100; // Vi må bare finne ett passende tall ut ifra hvor mange kort vi har.
	private AbstractTile[][] board = new AbstractTile[dim][dim];
	private List<AbstractTile> kort = new ArrayList<AbstractTile>();
	
	
	public Brett(){ // Vi kan kanskje legge noen metoder inni her som vi uansett hadde kjørt, men vi får se hva vi gjør. Det burde ikke være vanskelig å bytte ut.
		
	}
	
	public Brett(ArrayList<AbstractTile> innData){
		this.kort = innData;
		ArrayList<AbstractTile> klarKort = stokk(kort);
		this.kort = klarKort;
		fordelBrett(kort);
	}
	
	public void print(int x, int y){ 
		System.out.println("");
		for(int i = dim/4; i < 3*dim/4; i ++){ // Jeg tar den midterste halvdelen, så det ikke kommer så mye.
			for(int j = dim/4; j < 3*dim/4; j ++){
				if(board[i][j] == null || !board[i][j].isVisible()){
					System.out.print(" " + "|");
				}
				else if(x == i && y == j){
					System.out.print(board[i][j].toString().toUpperCase() + "|");
				}
				else{
					System.out.print(board[i][j] + "|");					
				}
				System.out.print("|");
			}
			System.out.println("");
		}
	}

	private ArrayList<AbstractTile> stokk(List<AbstractTile> kort){ // en shuffle-funksjon. Jeg synes ArrayList burde ha det innebygd.
		ArrayList<AbstractTile> nyeKort = new ArrayList<AbstractTile>();
		int lengde = kort.size();
		for(int i = 0; i < lengde; i++){
			nyeKort.add(null);
		}
		for(int j = 0; j < lengde; j++){
			AbstractTile kortet = kort.get(j);
			int plass = finnUbruktIndex(nyeKort, lengde);
			nyeKort.remove(plass);
			nyeKort.add(plass, kortet);
		}
		return nyeKort;
	}
	
	private int finnUbruktIndex(ArrayList<AbstractTile> kort, int grense){ // Finner en tilfeldig ubrukt plass. 
		Random tilfTall = new Random();
		int tall = tilfTall.nextInt(grense);
		if(kort.get(tall) == null){
			return tall;
		}
		else{
			return finnUbruktIndex(kort, grense);
		}
	}
	
	private void fordelBrett(List<AbstractTile> kort){
		int midten = dim/2;
		board[midten][midten] = kort.get(0); // Jeg må plassere det første kortet her, hvis ikke havner det ikke i midten. 
		kort.remove(0); // Hvis du finner en "pop" lignende metode for ArrayList kan du bruke den istedet. 
		for(int i = 0; i < kort.size(); i++){
			plasserKortError(kort.get(i), midten, midten);
		}
		board[midten][midten+1] = new EksempelTile("b");
		fiksBrett(midten, midten);
	}
	
	private void plasserKortError(AbstractTile tile, int x, int y){
		try{
			plasserKort(tile, x,y);
		}
		catch(StackOverflowError e){
			plasserKortError(tile, x, y);
		}
	}
	
	private void plasserKort(AbstractTile tile, int x, int y){ //metode for å plassere kort tilfeldig.
		Random tilfTall = new Random();
		int plass = tilfTall.nextInt(4); // Tilfeldig tall for å velge hvor den skal være. 
		switch(plass){
		case 0:
			if(board[x+1][y] != null){
				plasserKort(tile, x+1, y);
			}
			else if(sjekkAndre(x+1, y)){
				plasserKort(tile, x, y);
			}
			else{
				board[x+1][y] = tile; // Først to tester for å se om den skal kalle seg selv på nytt og prøve igjen, så blir rommet plassert. 
			}
			break;
		case 1:
			if(board[x-1][y] != null){
				plasserKort(tile, x-1, y);
			}
			else if(sjekkAndre(x-1, y)){
				plasserKort(tile, x, y);
			}
			else{
				board[x-1][y] = tile;
			}
			break;
		case 2:
			if(board[x][y+1] != null){
				plasserKort(tile, x, y+1);
			}
			else if(sjekkAndre(x, y+1)){
				plasserKort(tile, x, y);
			}
			else{
				board[x][y+1] = tile;
			}
			break;
		case 3:
			if(board[x][y-1] != null){
				plasserKort(tile, x, y-1);
			}
			else if(sjekkAndre(x, y-1)){
				plasserKort(tile, x, y);
			}
			else{
				board[x][y-1] = tile;
			}
			break;
		}
	}
	
	private boolean sjekkAndre(int x, int y){ // Sjekker om tilesene ved siden av finnes.
		int j = 0;
		if(board[x+1][y] != null){
			j++;
		}
		if(board[x-1][y] != null){
			j++;
		}
		if(board[x][y+1] != null){
			j++;
		}
		if(board[x][y-1] != null){
			j++;
		}
		return(j>=2);
	}
	
	public static int getDim(){
		return Brett.dim;
	}
	
	public boolean moved(int x, int y){
		try{
			board[x][y].makeVisible();
			return true;
		}
		catch(NullPointerException e){
			System.out.println("Der er det ingenting!");
			return false;
		}
	}
	
	public String getType(int x, int y){
		return board[x][y].toString();
	}
	
	public void tattTing(int x, int y){
		String rom = board[x][y].getNavn();
		switch(rom){
		case "s":
			board[x][y].setNavn("e");
			break;
		}
	}

	public void fjernetTrussel(int x, int y) {
		String rom = board[x][y].getNavn();
		switch(rom){
		case "d":
			board[x][y].setNavn("e");
			break;
		}
	}
	
	public String streng(int x, int y){
		String s = "";
		for(int i = dim/4; i < 3*dim/4; i ++){ // Jeg tar den midterste halvdelen, så det ikke kommer så mye.
			for(int j = dim/4; j < 3*dim/4; j ++){
				s += "   ";
				if(board[i][j] == null || !board[i][j].isVisible()){
					s+=("  " + "|");
				}
				else if(x == i && y == j){
					s+=(board[i][j].toString().toUpperCase() + "*|");
				}
				else{
					s+=(board[i][j] + "|");					
				}
				s+=("|");
			}
			s+=("\n");
		}
		return s;
	}
	
	private void fiksBrett(int x, int y){
		int midten = (dim/2);
		int nedreGrense = midten+9; 
		int ovreGrense = midten-10;
		int venstreGrense = midten-11;
		int hoyreGrense = midten+10;
		for(int i = -11; i < 10; i++){
			int j = midten+i;
			board[j][venstreGrense] = null;
			board[j][hoyreGrense] = null;
			board[ovreGrense][j] = null;
			board[nedreGrense][j] = null;
			board[j][venstreGrense+1] = new EksempelTile("e");
			board[j][hoyreGrense-1] = new EksempelTile("e");
			board[ovreGrense+1][j] = new EksempelTile("e");
			board[nedreGrense-1][j] = new EksempelTile("e");
		}
		board[midten+1][midten] = null;
		board[midten-1][midten] = null;
		board[midten][midten-1] = null;
		plasserViktig();
	}
	
	private void plasserViktig(){
		int midten = (dim/2);
		Random rand = new Random();
		int x = rand.nextInt(19);
		int y = rand.nextInt(19);
		x = x>=10 ? x-20 : x;
		y = y>=10 ? y-20 : y;
		board[midten+x][midten+y] = new EksempelTile("c");
		board[midten][midten] = new EksempelTile("z");
	}

	public ArrayList<Integer> getInt() {
		ArrayList<Integer> s = new ArrayList<Integer>();
		for(int i = (dim/2)-10; i < (dim/2)+10; i ++){ // Jeg tar den midterste halvdelen, så det ikke kommer så mye.
			for(int j = (dim/2)-9; j < (dim/2)+9; j ++){
				if(board[j][i] == null){
					s.add(9);
				}
				else{
					s.add(board[j][i].getInt());
				}
			}
		}
		System.out.println(s.size());
		return s;
	}
}
