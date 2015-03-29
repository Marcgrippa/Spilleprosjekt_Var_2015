package spillprosjekt;

public abstract class AbstractTile {

	private String navn;
	
	public AbstractTile(String navn){
		this.navn = navn;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
}
