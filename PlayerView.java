package spillprosjekt;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerView extends ImageView {
	
	
	public final static Image player = new Image("file:images/player.png");
	
	private int xPosition;
	private int yPosition;
	private int roomXPos = 10;
	private int roomYPos = 9;
	
	

	public PlayerView(int xPosition,int yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.setImage(player);
		this.setTranslateX(xPosition);
		this.setTranslateY(yPosition);
		
		
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public int getRoomXPos() {
		return roomXPos;
	}

	public void setRoomXPos(int roomXPos) {
		if (roomXPos<=19 && roomXPos>=0){
			this.roomXPos = roomXPos;
		}
	}

	public int getRoomYPos() {
		return roomYPos;
	}

	public void setRoomYPos(int roomYPos) {
		if (roomYPos<=19 && roomYPos>=0){
			this.roomYPos = roomYPos;
		}
	}
	
	public void movePlayer(char e, Room[][] rooms){
		switch(e){
		case 'w':
			if (yPosition-32 > 24){
				if(rooms[roomXPos][roomYPos-1].isEmpty()){
					return;
				}
				this.setTranslateY(yPosition-32);
				yPosition -= 32;
				this.setRoomYPos(roomYPos-1);
				rooms[roomXPos][roomYPos].setVisible(true);
			}
			
			
			break;
		case 's':
			if (yPosition+32 < 600){
				if(rooms[roomXPos][roomYPos+1].isEmpty()){
					return;
				}
				this.setTranslateY(yPosition+32);
				yPosition += 32;
				this.setRoomYPos(roomYPos+1);
				rooms[roomXPos][roomYPos].setVisible(true);
			}
			
			break;
		case 'a':
			if (xPosition-32 > 0){
				if(rooms[roomXPos-1][roomYPos].isEmpty()){
					return;
				}
				this.setTranslateX(xPosition-32);
				xPosition -= 32;
				this.setRoomXPos(roomXPos-1);
				rooms[roomXPos][roomYPos].setVisible(true);
			}
			break;
		case 'd':
			if (xPosition+32 < 640){
				if(rooms[roomXPos+1][roomYPos].isEmpty()){
					return;
				}
				this.setTranslateX(xPosition+32);
				xPosition += 32;
				this.setRoomXPos(roomXPos+1);
				rooms[roomXPos][roomYPos].setVisible(true);
			}		
			break;
	}
	}	
}