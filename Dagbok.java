package spillprosjekt;

import java.util.ArrayList;
import java.util.Random;

public class Dagbok extends Item{
	
	final private static int antallSider = 6;
	private ArrayList<String> IkkeSider = new ArrayList<String>();
	private ArrayList<String> OriginalSider = new ArrayList<String>();
	private ArrayList<String> Sider = new ArrayList<String>();

	public Dagbok(String ting) {
		super(ting);
		IkkeSider.add("Dag en");
		IkkeSider.add("Dag to");
		IkkeSider.add("Dag tre");
		IkkeSider.add("Dag fire");
		IkkeSider.add("Dag fem");
		IkkeSider.add("Dag seks");
		for(int i = 0; i < antallSider; i ++){
			Sider.add("tom side");
		}
		OriginalSider = (ArrayList<String>) IkkeSider.clone();
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