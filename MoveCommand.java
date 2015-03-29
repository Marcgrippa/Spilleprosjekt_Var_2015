
public class MoveCommand {

	private Location location; 
	
	public int goLeft(int oldX, int oldY){
		location.setX(oldX + 1);
		location.setY(oldY);
		return location.getX() + location.getY();
	}
	
	public int goRight(int oldX, int oldY){
		location.setX(oldX - 1);
		location.setY(oldY);
		return location.getX() + location.getY();
	}
	
	public int goUp(int oldX, int oldY){
		location.setX(oldX);
		location.setY(oldY - 1);
		return location.getX() + location.getY();
	}
	
	public int goDown(int oldX, int oldY){
		location.setX(oldX);
		location.setY(oldY - 1);
		return location.getX() + location.getY();
	}
	


}
