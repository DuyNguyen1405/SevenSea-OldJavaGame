package gameModel;
public abstract class AttackShip extends Ship
{	
	//private Point target1, target2;
	protected AttackShip()
	{
		super();		
//		target1 = new Point(-1, -1);
//		target2 = new Point(-1, -1);
	}	
	
	protected AttackShip(int x, int y) {
		super(x,y);
	}
	
//	private void setTarget1(Point target1) {
//		this.target1 = target1;
//	}
//
//	private void setTarget2(Point target2) {
//		this.target2 = target2;
//	}

	public Direction getShotAxis()
	{
		Direction axis = this.getAxis();
		Direction dir = Direction.UP;
		if(axis == Direction.UP||axis == Direction.DOWN)
			dir = Direction.RIGHT;
		else if(axis == Direction.LEFT||axis == Direction.RIGHT)
			dir = Direction.UP;
		else if(axis == Direction.UPRIGHT||axis == Direction.DOWNLEFT)
			dir = Direction.DOWNRIGHT;
		else
			dir = Direction.UPRIGHT;
		return dir;
	}
	
	public abstract Point getTarget1(Direction shotDirection);
	
	public abstract Point getTarget2(Direction shotDirection);

}


