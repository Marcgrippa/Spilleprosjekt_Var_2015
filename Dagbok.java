package spillprosjekt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Dagbok extends Item{
	
	final private static int antallSider = 7;
	private ArrayList<String> IkkeSider = new ArrayList<String>();
	private ArrayList<String> OriginalSider = new ArrayList<String>();
	private ArrayList<String> Sider = new ArrayList<String>();

	public Dagbok(String ting) {
		super(ting);
		IkkeSider.add(loadFile("Dagbok1.txt"));
		IkkeSider.add(loadFile("Dagbok2.txt"));
		IkkeSider.add(loadFile("Dagbok3.txt"));
		IkkeSider.add(loadFile("Dagbok4.txt"));
		IkkeSider.add(loadFile("Dagbok5.txt"));
		IkkeSider.add(loadFile("Dagbok6.txt"));
		IkkeSider.add(loadFile("Dagbok7.txt"));
		for(int i = 0; i < antallSider; i ++){
			Sider.add("tom side");
		}
		OriginalSider = (ArrayList<String>) IkkeSider.clone();
	}
	

	// Hvilken plassering har filene? "C:/Users/Håvard/workspace/tdt4100/src/sudoku/" + 
	public String loadFile(String fileName){
        Scanner in;
        String dagbok = "";
        try
        {
            in = new Scanner(new FileReader("dagbok/" + fileName));
            while(in.hasNext()){
                String line = in.nextLine();
                dagbok += line + "\n";
            }
            in.close();
            return dagbok;
        }
        catch (FileNotFoundException e)
        {
        	return(e.getMessage());
        }
    }

	
	
	public void printBok(){
		for(String side : Sider){
			if(!side.equals("tom side")){
				System.out.println(side);	
			}
		}
	}
	
	public static int getAntallSiderTotal(){
		return antallSider;
	}
	
	public void funnetSide(){
		Random tilfTall = new Random();
		int tall = tilfTall.nextInt(IkkeSider.size());
		leggTilSide(IkkeSider.get(tall), OriginalSider.indexOf(IkkeSider.get(tall)));
		IkkeSider.remove(tall);
	}
	
	public void leggTilSide(String s, int i){
		Sider.remove(i);
		Sider.add(i, s);
	}
	
	public String streng(){
		String s = "";
		for(String side : Sider){
			if(!side.equals("tom side")){
				s +=(side);
				s += "\n";
			}
		}
		return s;
	}
}