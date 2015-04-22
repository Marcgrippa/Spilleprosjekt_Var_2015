package spillprosjekt;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
	private Group mainGroup1;
	private Group instructionGroup;
	private Group dagBokGroup;
	private Group merkelGroup;
	public final static int pixels = 32;
	private PlayerView playerV;
	private ErikFXHoved hoved;
	private Text lifeV;
	private Text hungerV;
	private Text foodV;
	private Text bandagesV;
	private Text shotsV;
	private Text inventoryV;
	private Button howToKnapp;
	private Button alt1;
	private Button alt2;
	private ShowMessage message;
	private Boolean stromPa = false;
	
	
	public GameMap(){
		hoved = new ErikFXHoved();
		hoved.start();
		initMerkelGroup();
		initInstructionScreen();
		initDagBokScreen();
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
		
		howToKnapp = new Button("Hva gjør knappene??");
		howToKnapp.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				oppdaterDagBokScreen();
				settSynlig("instructions");
			}
		});
		initBackground();
		initAltKnapper();
		message = new ShowMessage("Er Erik dust? ");
		Group textG = new Group(message.getBox());
		playerV = new PlayerView((10*pixels)+9,10*pixels);
		Group player = new Group(playerV);
		mainGroup1 = new Group(initBackground(), roomImages,player, merkelGroup, initStatView(), howToKnapp, textG, alt1, alt2);
		settSynlig("instructions");
		mainGroup = new Group(mainGroup1, instructionGroup, dagBokGroup);
	}

	private void initAltKnapper() {
		alt1 = new Button("Alternativ en");
		alt2 = new Button("Alternativ to");
		alt1.setTranslateX(200);
		alt1.setTranslateY(370);
		alt2.setTranslateX(400);
		alt2.setTranslateY(370);
		alt1.setVisible(false);
		alt2.setVisible(false);
	}
	
	private void initInstructionScreen(){
		Text instructionText = new Text("Her er det forklart hva knappene gjør:");
		Text pilTastOpp = new Text("piltast opp: beveger deg opp.");
		Text pilTastNed = new Text("piltast ned: beveger deg ned.");
		Text pilTastVenstre = new Text("piltast venstre: beveger deg til venstre.");
		Text pilTastHoyre = new Text("piltast opp: beveger deg til høyre.");
		Text I = new Text("i: åpner inventory.");
		Text S = new Text("s: søker i det rommet du er i, hvis du kan gjøre det.");
		Text R = new Text("r: leser det du har funnet så langt av dagboken.");
		Text E = new Text("e: spiser mat, hvis du har det.");
		Text B = new Text("b: bandasjerer deg selv.");
		Text P = new Text("p: forsøker å skru på strømmen i det rommet du er i.");
		Button tilbakeKnapp = new Button("Start spillet!");
		tilbakeKnapp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				settSynlig("main");
				tilbakeKnapp.setText("Tilbake til spillet.");
			}
			
		});
		VBox boks = new VBox(tilbakeKnapp, instructionText, pilTastOpp, pilTastNed, pilTastVenstre, pilTastHoyre, I, S, R, E, B, P);
		BoksIterator boksIterator = new BoksIterator(boks);
		while(boksIterator.hasNext()){ 
			Object node = boksIterator.next();
			if(node instanceof Text){
				((Text) node).setFont(Font.font("Kai", 24));
			}
		}
		instructionGroup = new Group(boks);
	}
	
	private void initDagBokScreen(){
		ArrayList<String> sider = hoved.getHelBok();
		VBox boks = new VBox();
		for(String side : sider){
			boks.getChildren().add(new Text(side));
		}
		dagBokGroup = new Group(boks);
	}
	
	private void oppdaterDagBokScreen(){
		for(int i = 0; i < Dagbok.getAntallSiderTotal(); i++){
			ArrayList<String> liste = hoved.getbok();
			Node s = ((VBox) dagBokGroup.getChildren().get(0)).getChildren().get(i);
			if(liste.get(i).equals("tom side")){
				System.out.println(((Text) s).getText());
				s.setVisible(false);
				System.out.println("falsk");
			}
			else{
				s.setVisible(true);
				System.out.println("sann");
			}
		}
	}
	
	private Group initBackground(){
		Image background = new Image("file:images/background.png");
		ImageView view = new ImageView(background);
		view.setFitWidth(640);
		this.background = new Group(view);
		return this.background;
		
	}
	
	private void initMerkelGroup(){
		merkelGroup = new Group();
		merkelGroup.getChildren().add(new MerkelView(13*pixels+9, 13*pixels));
		merkelGroup.getChildren().add(new MerkelView(3*pixels+9, 4*pixels));
		merkelGroup.getChildren().add(new MerkelView(7*pixels+9, 7*pixels));
		merkelGroup.getChildren().add(new MerkelView(7*pixels+9, 10*pixels));
		merkelGroup.getChildren().add(new MerkelView(1*pixels+9, 4*pixels));
	}
	
	public Group getMainGroup(){
		return mainGroup;
	}
	
	
	public void handle(KeyEvent e) {
		message.getBox().setVisible(false);
		switch(e.getCode()){
		case UP:
			merkelMove();
			playerV.movePlayer('w', rooms);
			hoved.opp();
			break;
		case DOWN:
			merkelMove();
			playerV.movePlayer('s', rooms);
			hoved.ned();
			break;
		case LEFT:
			merkelMove();
			playerV.movePlayer('a', rooms);
			hoved.venstre();
			break;
		case RIGHT:
			merkelMove();
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
			hoved.les();
			if(dagBokGroup.isVisible()){
				settSynlig("main");
			}
			else{
				oppdaterDagBokScreen();
				settSynlig("dagBok");
			}
			break;
		case E:
			hoved.spis();
			break;
		case B:
			hoved.bandasje();
			break;
		case P:
			FXSkruPa();
			break;
		case V:
			FXVinn();
		default:
			break;
		}
		oppdaterTilstand();
	}
	
	private void FXVinn() {
		int x = playerV.getRoomXPos();
		int y = playerV.getRoomYPos();
		if(rooms[x][y].getType() == 3){
			if(stromPa){
				//Vinner.
			}			
		}
	}

	private void oppdaterTilstand(){
		if(mottMerkel()){
			merkelMote();
		}
		lifeV.setText(Integer.toString(hoved.getLiv()));
		hungerV.setText(Integer.toString(hoved.getSult()));
		foodV.setText(Integer.toString(hoved.antallMat()));
		bandagesV.setText(Integer.toString(hoved.antallBandasje()));
		shotsV.setText(Integer.toString(hoved.antallSkudd()));
		if(sjekkStrom()){
			lifeV.setText("Eqrwerwer");
			this.stromPa = true;
		}
	}
	
	private void merkelMote(){
		if(hoved.antallSkudd() == 0){
			message.showMessage(hoved.merkelMote("boksekamp"));
		}
		else{
			String s = "Du har møtt en merkel,\nmen du har skudd så du kan\nskyte og skremme hun bort.\nAlternativ 1: Du skyter merkel.\nAlternativ to: Du skyter ikke.";
			altMerkelSkudd(s);
		}
	}
	
	private void altMerkelSkudd(String tekst){
		message.showMessage(tekst);
		alt1.setVisible(true);
		alt2.setVisible(true);
		alt1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				message.showMessage(hoved.merkelMote("skyting"));
				alt1.setVisible(false);
				alt2.setVisible(false);
				lifeV.setText(Integer.toString(hoved.getLiv()));
				hungerV.setText(Integer.toString(hoved.getSult()));
				foodV.setText(Integer.toString(hoved.antallMat()));
				bandagesV.setText(Integer.toString(hoved.antallBandasje()));
				shotsV.setText(Integer.toString(hoved.antallSkudd()));
			}
		});
		alt2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				message.showMessage(hoved.merkelMote("boksekamp"));
				alt1.setVisible(false);
				alt2.setVisible(false);
				lifeV.setText(Integer.toString(hoved.getLiv()));
				hungerV.setText(Integer.toString(hoved.getSult()));
				foodV.setText(Integer.toString(hoved.antallMat()));
				bandagesV.setText(Integer.toString(hoved.antallBandasje()));
				shotsV.setText(Integer.toString(hoved.antallSkudd()));
			}
		});
	}
	
	private boolean mottMerkel(){
		int x = playerV.getRoomXPos();
		int y = playerV.getRoomYPos();
		for(Node n : merkelGroup.getChildren()){
			int mx = ((MerkelView) n).getRoomXPos();
			int my = ((MerkelView) n).getRoomYPos();
			if(x == mx && y == my){
				return true;
			}
		}
		return false;
	}
	
	private boolean sjekkStrom() {
		for(Room[] rad : rooms){
			for(Room rom : rad){
				if(rom.getType() == 4){					
					return false;
				}
			}			
		} 
		return true;
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
		message.showMessage("Du fant: " + hoved.getSistFunnet().getNavn().toLowerCase());
	}
	
	private void FXSkruPa(){
		int x = playerV.getRoomXPos();
		int y = playerV.getRoomYPos();
		rooms[x][y].skruPa();
	}
	
	private void settSynlig(String rom){
		mainGroup1.setVisible(rom.equals("main"));
		instructionGroup.setVisible(rom.equals("instructions"));
		dagBokGroup.setVisible(rom.equals("dagBok"));
	}
	
	private void merkelMove(){
		for(Node n : merkelGroup.getChildren()){
			((MerkelView) n).move(rooms, playerV.getRoomXPos(), playerV.getRoomYPos());
		}
	}
}
