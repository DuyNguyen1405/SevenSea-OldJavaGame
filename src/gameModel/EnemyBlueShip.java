package gameModel;

import java.util.Vector;

public class EnemyBlueShip extends AttackShip
{	
//	public static final int defaulHP = 1;
//	protected int HP;
//	
//	public int getHP() {
//		return HP;
//	}
//
//	public void setHP(int hP) {
//		HP = hP;
//	}
	MyShip myShip ;
	public EnemyBlueShip()
	{
		super();		
		myShip=MyShip.getInst();
//		HP=defaulHP;
	}
	
	public EnemyBlueShip(int x, int y) {
		super(x,y);
		myShip = MyShip.getInst();
	}
	
	private static EnemyBlueShip inst;
	public static EnemyBlueShip getInst()
	{
		if(inst == null)
		{
			inst = new EnemyBlueShip();
		}
		return inst;
	}
	
	@Override
	public Point getTarget1(Direction shotDirection) 
	{		
		IslandManager islandManager = IslandManager.getInst();
//		AttackShip this = AttackShip.getInst();
		//case shotDirection is UP
		if(shotDirection==Direction.UP)
		{
			//search each block from this up to atkRange(3) upward
			for(int i=1;i<=getAtkRange();i++)
			{
				GameObject location = new GameObject(this.getX(),this.getY()-i);
				if(myShip.isOverlap(location))
					return location.getXY();
				EnemyBlackShipManager.getInst();
				if(EnemyBlackShipManager.checkOverlap(location))
					return location.getXY();
				
				//get target of another blue ship
				Vector<EnemyBlueShip> blueShips = EnemyBlueShipManager.getBlueShipVect();
				for(EnemyBlueShip blue : blueShips)
				{
					if(blue.isOverlap(location)&&blue!=this)
						return location.getXY();
				}
				
				if(islandManager.checkOverlap(location))
				{
					GameObject location2 = new GameObject(this.getX(),this.getY()-(i-1));
					return location2.getXY();
				}
			}
			//return max atkRange if there aren't any enemy Ship in range
			Point pret = new Point(this.getX(),this.getY()-this.getAtkRange());
//			this.setTarget1(pret);
			return pret;
		}
		//case shotDirection is UPRIGHT
		else if(shotDirection==Direction.UPRIGHT)
		{
			//search each block from this up to atkRange(3) upward and Rightward :v
			for(int i=1;i<=getAtkRange();i++)
			{
				GameObject location = new GameObject(this.getX()+i,this.getY()-i);
				if(myShip.isOverlap(location))
					return location.getXY();
				EnemyBlackShipManager.getInst();
				if(EnemyBlackShipManager.checkOverlap(location))
					return location.getXY();

				//get target of another blue ship
				Vector<EnemyBlueShip> blueShips = EnemyBlueShipManager.getBlueShipVect();
				for(EnemyBlueShip blue : blueShips)
				{
					if(blue.isOverlap(location)&&blue!=this)
						return location.getXY();
				}
				
				if(islandManager.checkOverlap(location))
				{
					GameObject location2 = new GameObject(this.getX()+(i-1),this.getY()-(i-1));
					return location2.getXY();
				}
			}
			//return max atkRange if there aren't any enemy Ship in range
			Point pret = new Point(this.getX()+getAtkRange(),this.getY()-getAtkRange());
//			this.setTarget1(pret);
			return pret;
		}
		//case shotDirection is RIGHT
		else if(shotDirection==Direction.RIGHT)
		{
			//search each block from this up to atkRange(3) Rightward :v
			for(int i=1;i<=getAtkRange();i++)
			{				
				GameObject location = new GameObject(this.getX()+i,this.getY());
				if(myShip.isOverlap(location))
					return location.getXY();
				EnemyBlackShipManager.getInst();
				if(EnemyBlackShipManager.checkOverlap(location))
					return location.getXY();

				//get target of another blue ship
				Vector<EnemyBlueShip> blueShips = EnemyBlueShipManager.getBlueShipVect();
				for(EnemyBlueShip blue : blueShips)
				{
					if(blue.isOverlap(location)&&blue!=this)
						return location.getXY();
				}
				
				if(islandManager.checkOverlap(location))
				{
					GameObject location2 = new GameObject(this.getX()+(i-1),this.getY());
					return location2.getXY();
				}
			}
			//return max atkRange if there aren't any enemy Ship in range
			Point pret = new Point(this.getX()+getAtkRange(),this.getY());
//			this.setTarget1(pret);
			return pret;
		}
		//case shotDirection is DOWNRIGHT
		else 
		{
			//search each block from this up to atkRange(3) Downward and Rightward :v
			for(int i=1;i<=getAtkRange();i++)
			{				
				GameObject location = new GameObject(this.getX()+i,this.getY()+i);
				if(myShip.isOverlap(location))
					return location.getXY();
				EnemyBlackShipManager.getInst();
				if(EnemyBlackShipManager.checkOverlap(location))
					return location.getXY();

				//get target of another blue ship
				Vector<EnemyBlueShip> blueShips = EnemyBlueShipManager.getBlueShipVect();
				for(EnemyBlueShip blue : blueShips)
				{
					if(blue.isOverlap(location)&&blue!=this)
						return location.getXY();
				}
				
				if(islandManager.checkOverlap(location))
				{
					GameObject location2 = new GameObject(this.getX()+(i-1),this.getY()+(i-1));
					return location2.getXY();
				}
			}
			//return max atkRange if there aren't any enemy Ship in range
			Point pret = new Point(this.getX()+getAtkRange(),this.getY()+getAtkRange());
//			this.setTarget1(pret);
			return pret;
		}
	}
	
	@Override
	public Point getTarget2(Direction shotDirection)
	{
//		myShip myShip = myShip.getInst();
		IslandManager islandManager = IslandManager.getInst();
//		AttackShip this = AttackShip.getInst();
		//case shotDirection is UP
		if(shotDirection==Direction.UP)
		{
			//search each block from this up to atkRange(3) downward
			for(int i=1;i<=getAtkRange();i++)
			{
				GameObject location = new GameObject(this.getX(),this.getY()+i);
				if(myShip.isOverlap(location))
					return location.getXY();
				EnemyBlackShipManager.getInst();
				if(EnemyBlackShipManager.checkOverlap(location))
					return location.getXY();

				//get target of another blue ship
				Vector<EnemyBlueShip> blueShips = EnemyBlueShipManager.getBlueShipVect();
				for(EnemyBlueShip blue : blueShips)
				{
					if(blue.isOverlap(location)&&blue!=this)
						return location.getXY();
				}
				
				if(islandManager.checkOverlap(location))
				{
					GameObject location2 = new GameObject(this.getX(),this.getY()+(i-1));
					return location2.getXY();
				}
			}
			//return max atkRange if there aren't any enemy Ship in range
			Point pret = new Point(this.getX(),this.getY()+getAtkRange());
//			this.setTarget2(pret);
			return pret;
		}
		//case shotDirection is UPRIGHT
		else if(shotDirection==Direction.UPRIGHT)
		{
			//search each block from this up to atkRange(3) downward and leftward :v
			for(int i=1;i<=getAtkRange();i++)
			{
				GameObject location = new GameObject(this.getX()-i,this.getY()+i);
				if(myShip.isOverlap(location))
					return location.getXY();
				EnemyBlackShipManager.getInst();
				if(EnemyBlackShipManager.checkOverlap(location))
					return location.getXY();

				//get target of another blue ship
				Vector<EnemyBlueShip> blueShips = EnemyBlueShipManager.getBlueShipVect();
				for(EnemyBlueShip blue : blueShips)
				{
					if(blue.isOverlap(location)&&blue!=this)
						return location.getXY();
				}
				
				if(islandManager.checkOverlap(location))
				{
					GameObject location2 = new GameObject(this.getX()-(i-1),this.getY()+(i-1));
					return location2.getXY();
				}
			}
			//return max atkRange if there aren't any enemy Ship in range
			Point pret = new Point(this.getX()-getAtkRange(),this.getY()+getAtkRange());
//			this.setTarget2(pret);
			return pret;
		}
		//case shotDirection is RIGHT
		else if(shotDirection==Direction.RIGHT)
		{
			//search each block from this up to atkRange(3) leftward :v
			for(int i=1;i<=getAtkRange();i++)
			{
				GameObject location = new GameObject(this.getX()-i,this.getY());
				if(myShip.isOverlap(location))
					return location.getXY();
				EnemyBlackShipManager.getInst();
				if(EnemyBlackShipManager.checkOverlap(location))
					return location.getXY();

				//get target of another blue ship
				Vector<EnemyBlueShip> blueShips = EnemyBlueShipManager.getBlueShipVect();
				for(EnemyBlueShip blue : blueShips)
				{
					if(blue.isOverlap(location)&&blue!=this)
						return location.getXY();
				}
				
				if(islandManager.checkOverlap(location))
				{
					GameObject location2 = new GameObject(this.getX()-(i-1),this.getY());
					return location2.getXY();
				}
			}
			//return max atkRange if there aren't any enemy Ship in range
			Point pret = new Point(this.getX()-getAtkRange(),this.getY());
//			this.setTarget2(pret);
			return pret;
		}
		//case shotDirection is DOWNRIGHT
		else 
		{
			//search each block from this up to atkRange(3) upward and leftward :v
			for(int i=1;i<=getAtkRange();i++)
			{
				GameObject location = new GameObject(this.getX()-i,this.getY()-i);
				if(myShip.isOverlap(location))
					return location.getXY();
				EnemyBlackShipManager.getInst();
				if(EnemyBlackShipManager.checkOverlap(location))
					return location.getXY();

				//get target of another blue ship
				Vector<EnemyBlueShip> blueShips = EnemyBlueShipManager.getBlueShipVect();
				for(EnemyBlueShip blue : blueShips)
				{
					if(blue.isOverlap(location)&&blue!=this)
						return location.getXY();
				}
				
				if(islandManager.checkOverlap(location))
				{
					GameObject location2 = new GameObject(this.getX()-(i-1),this.getY()-(i-1));
					return location2.getXY();
				}
			}
			//return max atkRange if there aren't any enemy Ship in range
			Point pret = new Point(this.getX()-getAtkRange(),this.getY()-getAtkRange());
//			this.setTarget2(pret);
			return pret;
		}
	}

	public void reset() {
		//inst = null;
		// TODO Auto-generated method stub
		
		
	}
}


