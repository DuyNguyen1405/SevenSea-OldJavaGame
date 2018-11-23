package gameModel;

import gameController.Controller;
import mapHandler.ReadMap;

public class Bomb extends GameObject{
	private MyShip myShip = MyShip.getInst();
	private Bomb(int x,int y)
	{
		super(x,y);		
	}
	private static Bomb inst;
	public static Bomb getInst()
	{
		if(inst == null)
		{
			inst = new Bomb(0, 0);
		}
		return inst;
	}
	public void reset()
	{
//		inst = null;
		this.setXY(11, 11);
	}
	public void eatBomb()
	{
		if(this.isOverlap(myShip))
		{	
			System.out.println("Eat bomb");
			if (EnemyBlackShipManager.getBlackShipVect().size() != 0)
			{
				SkullManager.getInst().add
				(new Skull(EnemyBlackShipManager.getBlackShipVect().firstElement().getX()
					, EnemyBlackShipManager.getBlackShipVect().firstElement().getY()));
				EnemyBlackShipManager.remove(EnemyBlackShipManager.getBlackShipVect().firstElement());				
			}
			else {
				SkullManager.getInst().add(new Skull(EnemyBlueShipManager.getBlueShipVect().firstElement().getX()
						, EnemyBlueShipManager.getBlueShipVect().firstElement().getY()));
				EnemyBlueShipManager.remove(EnemyBlueShipManager.getBlueShipVect().firstElement());
			}
			Controller.explosion = true;
			this.setX(ReadMap.size+1);
			this.setY(ReadMap.size+1);
			System.out.println("Eat bomb");
		}
		else Controller.explosion = false;
	}
}
