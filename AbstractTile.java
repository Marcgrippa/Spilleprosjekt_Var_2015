package spillprosjekt;

public abstract class AbstractTile {

	private String navn;
	private boolean visible;
	
	public AbstractTile(String navn){
		setNavn(navn);
		visible = false;
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
}
