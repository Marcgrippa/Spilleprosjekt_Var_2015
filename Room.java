package spillprosjekt;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Room extends ImageView {
	public final static Image empty = new Image("file:images/empty.png");
	public final static Image searchable = new Image("file:images/searchable.png");
	public final static Image brigde = new Image("file:images/Datamaskin.png");
	public final static Image power = new Image("file:images/lightning.png");
	public final static Image research = new Image("file:images/forskning.png");
	public final static Image control = new Image("file:images/controlRoom.png");
	public final static Image lighted = new Image("file:images/lighted.png");
	public final static Image sleep = new Image("file:images/sleepingRoom.png");
	private int type;
	private boolean isEmpty;
	
	public Room(int type, int xPosition, int yPosition){
		this.type = type;
		this.isEmpty = false;
		switch (type){
			case (0): this.setImage(empty);break;
			case (1): this.setImage(searchable);break;
			case (2): this.setImage(research);break;
			case (3): this.setImage(brigde);break;
			case (4): this.setImage(power);break;
			case (5): this.setImage(control);break;
			case (6): this.setImage(lighted);break;
			case (7): this.setImage(sleep);break;
			case (9): this.isEmpty = true;break;
		}
	}
	
	public boolean isEmpty(){
		return this.isEmpty;
	}

	public void sok() {
		if(this.getImage() == searchable){
			this.setImage(empty);
			this.type = 0;
		}
		
	}
	
	public void skruPa(){
		if(this.getImage() == power){
			this.setImage(lighted);
			this.type = 6;
		}
	}
	
	public int getType(){
		return this.type;
	}
}
