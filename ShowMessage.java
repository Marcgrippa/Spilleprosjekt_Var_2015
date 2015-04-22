package spillprosjekt;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ShowMessage {
	private Text text;
	private VBox box;
	public final static Image message = new Image("file:images/message.png");
	
	public ShowMessage(String text){
		this.text = new Text(50,50,text);
		setTextValues(this.text);
		Group boxG = new Group(new ImageView(message),this.text);
		this.box = new VBox(boxG);
		setBoxValues(this.box);
		
		
		
	}

	private void setTextValues(Text text) {
		text.setFont(Font.font("Kai",18));
		
		
	}
	private void setBoxValues(VBox box){
		box.setTranslateY(170);
		box.setTranslateX(200);
		box.setMaxSize(300, 200);
		box.setVisible(false);
		
		
	}
	
	public VBox getBox(){
		return box;
		
	}
	
	
	public void showMessage(String text){
		this.text.setText(text);
		box.setVisible(true);
		
		
		
	}
	
}