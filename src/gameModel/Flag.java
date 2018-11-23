package gameModel;

import gameController.Controller;
import mapHandler.ReadMap;

public class Flag extends GameObject{
	private MyShip myShip = MyShip.getInst();
	private Flag(int x,int y)
	{
		super(x,y);		
	}
	private static Flag inst;
	public static Flag getInst()
	{
		if(inst == null)
		{
			inst = new Flag(0,0);
		}
		return inst;
	}
	public void reset()
	{
//		inst = null;
		this.setXY(12, 12);
	}
	public void eatFlag()
	{
		if(this.isOverlap(myShip))
		{
			Controller.setFinished(true);
			Controller.setVictorious(true);
			this.setX(ReadMap.size+1);
			this.setY(ReadMap.size+1);
		}
		else if(EnemyBlackShipManager.checkOverlap(this))
		{
			myShip.setHP(myShip.getHP()-1);
			this.setX(ReadMap.size+1);
			this.setY(ReadMap.size+1);
		}

	}
}
