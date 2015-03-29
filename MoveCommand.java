
public class MoveCommand {

	private Location location; 
	
	public int goLeft(int x, int y){
		location.setX(x + 1);
		location.setY(y);
		return location.getX() + location.getY();
	}
	
	public int goRight(int x, int y){
		location.setX(x - 1);
		location.setY(y);
		return location.getX() + location.getY();
	}
	
	public int goUp(int x, int y){
		location.setX(x);
		location.setY(y - 1);
		return location.getX() + location.getY();
	}
	
	public int goDown(int x, int y){
		location.setX(x);
		location.setY(y - 1);
		return location.getX() + location.getY();
	}
	


}
