package spillprosjekt;

public class Location {
	
	private int xPos;
	private int yPos;

	public Location(int x,int y) {
		setY(y);
		setX(x);
	}
	
	public void setY(int y) {
		this.yPos = y;
	}
	
	public void setX(int x) {
		this.xPos = x;
	}
	
	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
}
