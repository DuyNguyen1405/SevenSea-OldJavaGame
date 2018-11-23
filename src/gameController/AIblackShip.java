package gameController;

import java.util.Vector;

import gameModel.*;
import mapHandler.ReadMap;

public class AIblackShip implements AIEnemyShip{
	private static MyShip myShip;
	private static CannonManager foodManager;
	private static Flag flag;
	private static Bomb bomb;

	@Override
	public boolean AIcheckOverlap(Ship otemp) 
	{
		boolean overlap=false;
		if(foodManager.checkOverlap(otemp))
			overlap=true;
		if(flag.isOverlap(otemp))
			overlap=true;
		if(bomb.isOverlap(otemp))
			overlap=true;
		
		return overlap;
	}
	
	@Override
	public Direction AIcal(Ship enemy) {
		int minValue=2*ReadMap.size;
		Direction dir=Direction.UP;
		
		int disUp = enemy.calculateDistance(enemy.calculatePosition(Direction.UP), myShip);
		int disDown = enemy.calculateDistance(enemy.calculatePosition(Direction.DOWN), myShip);
		int disLeft = enemy.calculateDistance(enemy.calculatePosition(Direction.LEFT), myShip);
		int disRight = enemy.calculateDistance(enemy.calculatePosition(Direction.RIGHT), myShip);
		int disUpLeft = enemy.calculateDistance(enemy.calculatePosition(Direction.UPLEFT), myShip);
		int disDownLeft = enemy.calculateDistance(enemy.calculatePosition(Direction.DOWNLEFT), myShip);
		int disDownRight = enemy.calculateDistance(enemy.calculatePosition(Direction.DOWNRIGHT), myShip);
		int disUpRight = enemy.calculateDistance(enemy.calculatePosition(Direction.UPRIGHT), myShip);
		
		if(disUp<minValue&&
				Controller.isValidPosition(enemy.calculatePosition(Direction.UP)))
		{
			Point ptemp = enemy.calculatePosition(Direction.UP);
			EnemyBlackShip otemp = new EnemyBlackShip(ptemp.x,ptemp.y);
			if(!AIcheckOverlap(otemp)) //if(!overlap)
			{
				minValue=disUp;
				dir=Direction.UP;
			}
		}
		if(disDown<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.DOWN)))
		{
			Point ptemp = enemy.calculatePosition(Direction.DOWN);
			EnemyBlackShip otemp = new EnemyBlackShip(ptemp.x,ptemp.y);
			if(!AIcheckOverlap(otemp)) //if(!overlap)
			{
				minValue=disDown;
				dir=Direction.DOWN;
			}
		}
		if(disLeft<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.LEFT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.LEFT);
			EnemyBlackShip otemp = new EnemyBlackShip(ptemp.x,ptemp.y);
			if(!AIcheckOverlap(otemp)) //if(!overlap)
			{
				minValue=disLeft;
				dir=Direction.LEFT;
			}
		}
		if(disRight<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.RIGHT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.RIGHT);
			EnemyBlackShip otemp = new EnemyBlackShip(ptemp.x,ptemp.y);
			if(!AIcheckOverlap(otemp)) //if(!overlap)
			{
				minValue=disRight;
				dir=Direction.RIGHT;
			}
		}
		if(disUpLeft<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.UPLEFT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.UPLEFT);
			EnemyBlackShip otemp = new EnemyBlackShip(ptemp.x,ptemp.y);
			if(!AIcheckOverlap(otemp)) //if(!overlap)
			{
				minValue=disUpLeft;
				dir=Direction.UPLEFT;
			}
		}
		if(disDownLeft<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.DOWNLEFT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.DOWNLEFT);
			EnemyBlackShip otemp = new EnemyBlackShip(ptemp.x,ptemp.y);
			if(!AIcheckOverlap(otemp)) //if(!overlap)
			{
				minValue=disDownLeft;
				dir=Direction.DOWNLEFT;
			}
		}
		if(disUpRight<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.UPRIGHT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.UPRIGHT);
			EnemyBlackShip otemp = new EnemyBlackShip(ptemp.x,ptemp.y);
			if(!AIcheckOverlap(otemp)) //if(!overlap)
			{
				minValue=disUpRight;
				dir=Direction.UPRIGHT;
			}
		}
		if(disDownRight<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.DOWNRIGHT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.DOWNRIGHT);
			EnemyBlackShip otemp = new EnemyBlackShip(ptemp.x,ptemp.y);
			if(!AIcheckOverlap(otemp)) //if(!overlap)
			{
				minValue=disDownRight;
				dir=Direction.DOWNRIGHT;
			}
		}
		return dir;
	}
	
	private AIblackShip()
	{
		myShip = MyShip.getInst();
		foodManager = CannonManager.getInst();
		flag = Flag.getInst();
		bomb = Bomb.getInst();	
	}
	
	private static AIblackShip inst;
	public static AIblackShip getInst()
	{
		if(inst == null)
		{
			inst = new AIblackShip();
		}
		return inst;
	}

	@Override
	public void shotted() {
		// TODO Auto-generated method stub
//		System.out.println("MyShip shot 1");
		Vector<EnemyBlackShip> eneShips = EnemyBlackShipManager.getBlackShipVect();
		boolean index[] = new boolean[eneShips.size()];
		
		for(int i=0;i<eneShips.size();i++)
		{
			index[i]=false;
		}
		for(EnemyBlackShip obj : eneShips)
		{
			Point temp = new Point(obj.getX(),obj.getY());
			Point temp1 = myShip.getTarget1(myShip.getShotAxis());
			Point temp2 = myShip.getTarget2(myShip.getShotAxis());

			//Mark the index of EnemyBlackShip to be destroyed
			if (temp.x == temp1.x && temp.y == temp1.y) {

					index[eneShips.indexOf(obj)]=true;
			}
			if (temp.x == temp2.x && temp.y == temp2.y) {
				index[eneShips.indexOf(obj)]=true;
			}

		}
		
		for(int i=eneShips.size()-1;i>=0;i--)
		{
			//EnemyBlackShip obj = eneShips.get(i);
			if(index[i]==true) {		
				SkullManager.getInst().add(new Skull(eneShips.get(i).getX(), eneShips.get(i).getY()));
				EnemyBlackShipManager.remove(eneShips.get(i));
				Score.getInst().addScore(30);
				//creat explosion when hit 
				System.out.println("MyShip shot blackShip");
			}
		}
	}
}
