package spillprosjekt;

public class Item {
	
	private String navn;
	
	public Item(String ting){
		this.navn = ting;
	}

	public String getNavn() {
		return navn;
	}
	
	public String toString(){
		return this.getNavn();
	}
	
	public boolean er(Item ting){
		return this.navn.equals(ting.toString());
	}
}
