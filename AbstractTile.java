package spillprosjekt;

public abstract class AbstractTile {

	private String navn;
	private boolean visible;
	
	public AbstractTile(String navn){
		setNavn(navn);
		visible = true;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public String toString(){
		return this.getNavn();
	}
	
	public boolean isVisible(){
		return this.visible;
	}
	
	public void makeVisible(){
		this.visible = true;
	}
	
	public void makeInvisible(){
		this.visible = false;
	}
	
	public int getInt(){
		switch(navn){
		case "e":
			return 0;
		case "s":
			return 1;
		case "p":
			return 4;
		case "b":
			return 3;
		case "r":
			return 2;
		case "c":
			return 5;
		case "z":
			return 7;
		default:
			return 0;
		}
	}
}
