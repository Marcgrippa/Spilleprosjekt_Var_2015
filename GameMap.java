package spillprosjekt;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameMap {
	private Room[][] rooms;
	private int numberOfX = 20;
	private int numberOfY = 18;
	private Group roomImages = new Group();
	private Group background;
	private Group mainGroup;
	public final static int pixels = 32;
	private PlayerView playerV;
	private ErikFXHoved hoved;
	private Text lifeV;
	private Text hungerV;
	private Text foodV;
	private Text bandagesV;
	private Text shotsV;
	private Text inventoryV;
	
	
	public GameMap(){
		hoved = new ErikFXHoved();
		hoved.start();
		initGameMap(hoved.getBrettInt());
	}
	
	private void initGameMap(ArrayList<Integer> romListe){
		System.out.println(romListe);
		rooms = new Room[numberOfX][numberOfY];
		for (int x = 0; x < numberOfX; x++){
			for (int y = 0; y < numberOfY; y++){
				rooms[x][y] = new Room(romListe.get(0),x,y);
				romListe.remove(0);
			}
		}
		for (int x = 0; x < numberOfX; x++){
			for (int y = 0; y < numberOfY; y++){
				rooms[x][y].setTranslateX((x*pixels));
				rooms[x][y].setTranslateY((y*pixels)+24);
				rooms[x][y].setVisible(true);
				roomImages.getChildren().add(rooms[x][y]);	
			}
		}
		initBackground();
		playerV = new PlayerView((10*pixels)+9,10*pixels);
		Group player = new Group(playerV);
		mainGroup = new Group(initBackground(), roomImages,player, initStatView());
	}

//	private void initGameMap1() {
//		rooms = new Room[numberOfX][numberOfY];
//		for (int x = 0; x < numberOfX; x++){
//			for (int y = 0; y < numberOfY; y++){
//				
//				rooms[x][y] = new Room(new Random().nextInt(2),x,y);
//				
//				
//			}
//		}
//		for (int x = 0; x < numberOfX; x++){
//			for (int y = 0; y < numberOfY; y++){
//				rooms[x][y].setTranslateX((x*pixels));
//				rooms[x][y].setTranslateY((y*pixels)+24);
//				rooms[x][y].setVisible(false);
//				roomImages.getChildren().add(rooms[x][y]);	
//			}
//		}
//		initBackground();
//		playerV = new PlayerView((9*pixels)+8,9*pixels);
//		Group player = new Group(playerV);
//		mainGroup = new Group(roomImages,player);
//		
//		
//		
//	}
	
	private Group initBackground(){
		Image background = new Image("file:images/bakgrunn.png");
		ImageView view = new ImageView(background);
		this.background = new Group(view);
		return this.background;
		
	}
	
	public Group getMainGroup(){
		return mainGroup;
	}
	
	
	public void handle(KeyEvent e) {
		switch(e.getCode()){
		case UP:
			playerV.movePlayer('w', rooms);
			hoved.opp();
			break;
		case DOWN:
			playerV.movePlayer('s', rooms);
			hoved.ned();
			break;
		case LEFT:
			playerV.movePlayer('a', rooms);
			hoved.venstre();
			break;
		case RIGHT:
			playerV.movePlayer('d', rooms);
			hoved.hoyre();
			break;
		case I:
			hoved.inventory();
			break;
		case S:
			hoved.sok();
			FXSok();
			break;
		case R:
			
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
		default:
			break;
		}
		oppdaterTekst();
	}
	
	private void oppdaterTekst(){
		
	}
	
	private Group initStatView(){
		Text life = new Text(50,50,"Life:");
		Text hunger = new Text(50,50,"Hunger:");
		Text food = new Text(50,50,"Food:");
		Text bandages = new Text(50,50,"Bandages:");
		Text shots = new Text(50,50,"Shots:");
		Text inventory = new Text(50,50,"Inventory");
		lifeV = new Text(50,50,"100");
		hungerV = new Text(50,50,"100");
		foodV = new Text(50,50,"0");
		bandagesV = new Text(50,50,"0");
		shotsV = new Text(50,50,"0");
		VBox topLeft = new VBox(life,hunger,food,bandages,shots);
		VBox topRight = new VBox(this.lifeV, this.hungerV,this.foodV, this.bandagesV, this.shotsV);
		for (int x = 0; x < 5; x++){
			Text text = (Text) topLeft.getChildren().get(x);
			Text text2 = (Text) topRight.getChildren().get(x);
			text.setFont(Font.font("Kai",18));
			text2.setFont(Font.font("Kai",18));
		
			
		}
		topLeft.setTranslateX(660);
		topLeft.setTranslateY(24);
		topLeft.setSpacing(20);
		topRight.setTranslateX(765);
		topRight.setTranslateY(24);
		topRight.setSpacing(20);
		Group statView = new Group(topLeft,topRight);
		return statView;
		
		
	}

	private void FXSok() {
		int x = playerV.getRoomXPos();
		int y = playerV.getRoomYPos();
		rooms[x][y].sok();
	}
}
