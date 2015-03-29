package spillprosjekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Brett {

	private AbstractTile[][] board = new AbstractTile[10][10];
	private List<AbstractTile> kort = new ArrayList<AbstractTile>();
	
	
	public Brett(){
		
	}
	
	public void start(){
		EksempelTile en = new EksempelTile("1");
		EksempelTile to = new EksempelTile("2");
		EksempelTile tre = new EksempelTile("3");
		EksempelTile fire = new EksempelTile("4");
		EksempelTile fem = new EksempelTile("5");
		EksempelTile seks = new EksempelTile("6");
		EksempelTile sju = new EksempelTile("7");
		EksempelTile åtte = new EksempelTile("8");
		EksempelTile ni = new EksempelTile("9");
		EksempelTile ti = new EksempelTile("10");
		kort.add(en);
		kort.add(to);
		kort.add(tre);
		kort.add(fire);
		kort.add(fem);
		kort.add(seks);
		kort.add(sju);
		kort.add(åtte);
		kort.add(ni);
		kort.add(ti);
		ArrayList klarKort = stokk(kort);
		this.kort = klarKort;
		fordelBrett(kort);
		
	}
	
	public void print(){
		for(int i = 0; i < 10; i ++){
			System.out.println("------------------------");
			for(int j = 0; j < 10; j ++){
				if(board[i][j] == null){
					System.out.print("0" + "|");
				}
				else{
					System.out.print(board[i][j] + "|");					
				}
			}
			System.out.println("");
		}
	}

	private ArrayList<AbstractTile> stokk(List<AbstractTile> kort){
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
	
	private int finnUbruktIndex(ArrayList kort, int grense){
		Random tilfTall = new Random();
		int tall = tilfTall.nextInt(grense);
		if(kort.get(tall) == null){
			return tall;
		}
		else{
			return finnUbruktIndex(kort, grense);
		}
	}
	
	private void fordelBrett(List<AbstractTile> kort2){
		for(int i = 0; i < kort2.size(); i++){
			plasserKort(kort2.get(i), 5,5);
		}
	}
	
	private void plasserKort(AbstractTile tile, int x, int y){
		Random tilfTall = new Random();
		int plass = tilfTall.nextInt(3);
		switch(plass){
		case 0:
			if(board[x+1][y] != null){
				plasserKort(tile, x+1, y);
			}
			else{
				board[x+1][y] = tile;
			}
			break;
		case 1:
			if(board[x-1][y] != null){
				plasserKort(tile, x-1, y);
			}
			else{
				board[x-1][y] = tile;
			}
			break;
		case 2:
			if(board[x][y+1] != null){
				plasserKort(tile, x, y+1);
			}
			else{
				board[x][y+1] = tile;
			}
			break;
		case 3:
			if(board[x][y-1] != null){
				plasserKort(tile, x, y-1);
			}
			else{
				board[x][y-1] = tile;
			}
			break;
		}
		
	}
}
