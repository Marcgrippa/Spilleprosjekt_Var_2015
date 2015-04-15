package spillprosjekt;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class ErikFX extends Application {
	
	Label liv_text;
	Label sult_text;
	Label inventory_text;
	Label kart_text;
	Label bok_text;
	Label mat_text;
	Label bandasje_text;
	Label skudd_text;
	Text popup_text;
	Image bilde;
	ErikFXHoved hoved = new ErikFXHoved();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		hoved.start();
		liv_text = new Label("Liv: " + hoved.getLiv());
		sult_text = new Label("Sult: " + hoved.getSult());
		inventory_text = new Label("Tingene dine: " + hoved.getInventory());
		kart_text = new Label(hoved.getBrett());
		bok_text = new Label();
		mat_text = new Label(hoved.antallMat() + "X mat");
		bandasje_text = new Label(hoved.antallBandasje() + "X bandasje");
		skudd_text = new Label(hoved.antallSkudd() + "X skudd");
		popup_text = new Text("Du gikk inn i ett nytt rom");
		popup_text.setVisible(false);
		popup_text.setFont(Font.font ("Verdana", 120));
		popup_text.setTranslateX(100);
		popup_text.setTranslateY(250);
		
		VBox root = new VBox();
		HBox tekst = new HBox();
		
		root.getChildren().addAll(liv_text, sult_text, mat_text, bandasje_text, skudd_text, inventory_text, kart_text, bok_text);
		tekst.getChildren().addAll(popup_text);

		Group gruppe = new Group();
		gruppe.getChildren().addAll(tekst, root);

		Scene scene = new Scene(gruppe, 500, 500);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
			public void handle(final KeyEvent e){
				tekst.getChildren().get(0).setVisible(false);
				bok_text.setVisible(false);
				switch(e.getCode()){
				case UP:
					hoved.opp();
					break;
				case DOWN:
					hoved.ned();
					break;
				case LEFT:
					hoved.venstre();
					break;
				case RIGHT:
					hoved.hoyre();
					break;
				case I:
					hoved.inventory();
					break;
				case S:
					hoved.sok();
					break;
				case R:
					hoved.les();
					bok_text.setText(hoved.getbok());
					bok_text.setVisible(true);
					break;
				case E:
					hoved.spis();
					break;
				case B:
					hoved.bandasje();
					break;
				case P:
					hoved.sov();
					break;
				case K:
					tekst.getChildren().get(0).setVisible(true);
					break;
				default:
					break;
				}
				oppdaterText();
			}
		});
		stage.setTitle("Romskipspill.");
		stage.setScene(scene);
		stage.show();
	}
	
	public void oppdaterText(){
		liv_text.setText("Liv: " + hoved.getLiv());
		sult_text.setText("Sult: " + hoved.getSult());
		inventory_text.setText("Tingene dine: " + hoved.getInventory());
		kart_text.setText(hoved.getBrett());
		mat_text.setText(hoved.antallMat() + "X mat");
		bandasje_text.setText(hoved.antallBandasje() + "X bandasje");
		skudd_text.setText(hoved.antallSkudd() + "X skudd");
		if(hoved.getSult() < 1){
			gameOver("sult");
		}
	}
	private void gameOver(String arsak){
		String s = "";
		for(int i = 0; i < 11; i ++){
			s+="\n";
		}
		switch(arsak){
		case "sult":
			s+= "                                                                                                                                                                                                            Du sultet i hjel.\n";
		}
		s += "                                                                                                                                                                                                            Det er gameover.";
		for(int j = 0; j < 50; j ++){
			s+="\n";
		}
		liv_text.setText(s);
		kart_text.setText("");
		sult_text.setText("");
		inventory_text.setText("");
		kart_text.setText("");
		mat_text.setText("");
		bandasje_text.setText("");
		skudd_text.setText("");
	}
}