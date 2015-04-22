package spillprosjekt;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MerkelView extends ImageView {
	
	
	public final static Image merkel = new Image("file:images/merkel.png");
	
	private int xPosition;
	private int yPosition;
	private int roomXPos;
	private int roomYPos;
	private static final int pixel = 32;
	

	public MerkelView(int xPosition,int yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.setImage(merkel);
		this.setTranslateX(xPosition);
		this.setTranslateY(yPosition);
		roomXPos = (xPosition-9)/pixel;
		roomYPos = yPosition/pixel-1;
		
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
	
	public void move(Room[][] rooms, int x, int y){
		int[] retninger = finnRetning(x, y);
		for(int i = 0; i < 4; i ++){
			switch(retninger[i]){
			case 0:
				if(moveUp(rooms)){
					return;
				}
				break;
			case 1:
				if(moveDown(rooms)){
					return;
				}
				break;
			case 2:
				if(moveLeft(rooms)){
					return;
				}
				break;
			case 3:
				if(moveRight(rooms)){
					return;
				}
				break;
			}			
		}
	}
	private boolean moveUp(Room[][] rooms){
		if (yPosition-32 > 24){
			if(rooms[roomXPos][roomYPos-1].isEmpty()){
				return false;
			}
			else{
				this.setTranslateY(yPosition-32);
				yPosition -= 32;
				this.setRoomYPos(roomYPos-1);
				rooms[roomXPos][roomYPos].setVisible(true);
				return true;
			}
		}
		else{
			return false;
		}
		
	}
	
	private boolean moveDown(Room[][] rooms){
		if (yPosition+32 < 600){
			if(rooms[roomXPos][roomYPos+1].isEmpty()){
				return false;
			}
			else{
				this.setTranslateY(yPosition+32);
				yPosition += 32;
				this.setRoomYPos(roomYPos+1);
				rooms[roomXPos][roomYPos].setVisible(true);
				return true;
			}
		}
		else{
			return false;
		}
	}
	
	private boolean moveLeft(Room[][] rooms){
		if (xPosition-32 > 0){
			if(rooms[roomXPos-1][roomYPos].isEmpty()){
				return false;
			}
			else{
				this.setTranslateX(xPosition-32);
				xPosition -= 32;
				this.setRoomXPos(roomXPos-1);
				rooms[roomXPos][roomYPos].setVisible(true);
				return true;
			}
		}

		else{
			return false;
		}
	}
	
	private boolean moveRight(Room[][] rooms){
		if (xPosition+32 < 640){
			if(rooms[roomXPos+1][roomYPos].isEmpty()){
				return false;
			}
			else{
				this.setTranslateX(xPosition+32);
				xPosition += 32;
				this.setRoomXPos(roomXPos+1);
				rooms[roomXPos][roomYPos].setVisible(true);
				return true;
			}
		}

		else{
			return false;
		}
	}
	
	private int[] finnRetning(int x, int y){
		int[] retur = new int[4];
		int xDiff = (x-roomXPos);
		int yDiff = (y-roomYPos);
		if((xDiff^2) > (yDiff^2) && xDiff != 0){
			if(xDiff < 0){
				retur[0] = 1;
				retur[3] = 0;
				if(yDiff > 0){
					retur[1] = 3;
					retur[2] = 2;
				}
				else{
					retur[1] = 2;
					retur[2] = 3;
				}
			}
			else{
				retur[0] = 0;
				retur[3] = 1;
				if(yDiff > 0){
					retur[1] = 3;
					retur[2] = 2;
				}
				else{
					retur[1] = 2;
					retur[2] = 3;
				}
			}
		}
		else{
			if(yDiff < 0){
				retur[0] = 3;
				retur[3] = 2;
				if(xDiff > 0){
					retur[1] = 1;
					retur[2] = 0;
				}
				else{
					retur[1] = 0;
					retur[2] = 1;
				}
			}
			else{
				retur[0] = 2;
				retur[3] = 3;
				if(xDiff > 0){
					retur[1] = 1;
					retur[2] = 0;
				}
				else{
					retur[1] = 0;
					retur[2] = 1;
				}
			}
		}
		return retur;
	}
}