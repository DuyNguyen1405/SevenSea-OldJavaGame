package gameModel;

public abstract class Ship extends GameObject
{

//	protected Direction axis, lastMove;
	protected Direction axis;
	
	public Direction getAxis() {
		return axis;
	}
	
	public void setAxis(Direction axis) {
		this.axis = axis;
	}	
	
//	public Direction getLastMove() {
//		return lastMove;
//	}
//
//	public void setLastMove(Direction lastMove) {
//		this.lastMove = lastMove;
//	}
	
	public Point getLocation()
	{
		Point location = new Point(this.x,this.y);
		return location;
	}
	protected Ship()
	{
		super();		
		axis = Direction.RIGHT;
//		lastMove = Direction.RIGHT;
	}	
	protected Ship(int x,int y)
	{
		super(x,y);		
		axis = Direction.RIGHT;
//		lastMove = Direction.RIGHT;
	}	
	
}


