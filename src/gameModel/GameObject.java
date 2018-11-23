package gameModel;

public class GameObject
{
	static final int myShip=0;
	static final int enemyShip=1;
	static final int island=2;
	static final int skull=3;
	static final int tornado=4;
	static final int none=5;
	static final int stepRangeX=1;
	static final int stepRangeY=1;
	protected int atkRange=3;
	
	public int getAtkRange() {
		return atkRange;
	}

	public void setAtkRange(int atkRange) {
		this.atkRange = atkRange;
	}
	protected int x,y;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public Point getXY()
	{
		Point Pret = new Point(this.x,this.y);
		return Pret;
	}
	protected boolean isVisible;
	
	public GameObject()
	{
		x=0;y=0;
		isVisible=true;
	}		
	
	public GameObject(int x,int y)
	{
		this.x=x;this.y=y;
		isVisible=true;
	}	
	
	//////////////////////////////////////////
	// Other methods						//
	//////////////////////////////////////////

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public boolean isOverlap (GameObject target)
	{
	//	System.out.println(target.getX() + ", " + target.getY() + ", " + x + ", " + y);
		return (target.getX()==this.x&&target.getY()==this.y);
	}
	
	public Point calculatePosition(Direction d)
	{
		Point pRet = new Point(x, y);
		switch (d) {
		case UP:
			pRet.y-=stepRangeY;
			break;
		case DOWN:
			pRet.y+=stepRangeY;
			break;
		case LEFT:
			pRet.x-=stepRangeX;
			break;
		case RIGHT:
			pRet.x+=stepRangeX;
			break;
		case UPLEFT :
			pRet.y-=stepRangeY;
			pRet.x-=stepRangeX;
			break;
		case UPRIGHT :
			pRet.y-=stepRangeY;
			pRet.x+=stepRangeX;
			break;
		case DOWNLEFT :
			pRet.y+=stepRangeY;
			pRet.x-=stepRangeX;
			break;
		case DOWNRIGHT :
			pRet.y+=stepRangeY;
			pRet.x+=stepRangeX;
			break;
		}
		return pRet;
	}
	
	public int calculateDistance(Point p,GameObject target)
	{
		int distance =Math.abs(p.x-target.getX())+Math.abs(p.y-target.getY());
		return distance;
	}
}
