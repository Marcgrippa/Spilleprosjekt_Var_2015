package spillprosjekt;
	


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;




public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			GameMap map = new GameMap();
			Scene scene = new Scene(map.getMainGroup(),800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
				public void handle(KeyEvent e){
					map.handle(e);
				}
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
