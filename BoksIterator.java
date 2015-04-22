package spillprosjekt;

import java.util.Iterator;

import javafx.scene.layout.VBox;

public class BoksIterator implements Iterator<Object> {

	private int i;
	VBox boks;
	public BoksIterator(VBox boks){
		this.i = 0;
		this.boks = boks;
	}
	@Override 
	public boolean hasNext() {
		return boks.getChildren().size() > i;
	}

	@Override
	public Object next() {
		return boks.getChildren().get(i++);
	}

}
