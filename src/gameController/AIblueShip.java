package gameController;

import java.util.Vector;

import gameModel.*;
import mapHandler.ReadMap;

public class AIblueShip implements AIEnemyShip{
	private static MyShip myShip;
	private static IslandManager islandManager;
	private static SkullManager skullManager;
	private static CannonManager foodManager;
	private static Flag flag;
	private static Bomb bomb;
	private static Score score;
	
	private AIblueShip()
	{
		myShip = MyShip.getInst();
		islandManager = IslandManager.getInst();
		skullManager = SkullManager.getInst();
		foodManager = CannonManager.getInst();
		flag = Flag.getInst();
		bomb = Bomb.getInst();	
		score = Score.getInst();
	}
	
	private static AIblueShip inst;
	public static AIblueShip getInst()
	{
		if(inst == null)
		{
			inst = new AIblueShip();
		}
		return inst;
	}
	
	@Override
	public boolean AIcheckOverlap(Ship otemp) {
		boolean isOverlap = false;

		if(EnemyBlackShipManager.checkOverlap(otemp))
			isOverlap=true;
		if(EnemyBlueShipManager.isSuicide(otemp))
			isOverlap=true;
		if(foodManager.checkOverlap(otemp))
			isOverlap=true;
		if(islandManager.checkOverlap(otemp))
			isOverlap=true;
		if(skullManager.checkOverlap(otemp))
			isOverlap=true;
		if(flag.isOverlap(otemp))
			isOverlap=true;
		if(bomb.isOverlap(otemp))
			isOverlap=true;
		
		return isOverlap;
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
		
		
		if(disUp<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.UP)))
		{
			Point ptemp = enemy.calculatePosition(Direction.UP);
			EnemyBlueShip otemp = new EnemyBlueShip(ptemp.x,ptemp.y);
			
			//check if blue ship hit another black Ship			
			if(!AIcheckOverlap(otemp))
			{
				minValue=disUp;
				dir=Direction.UP;
			}
		}
		
		if(disDown<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.DOWN)))
		{
			Point ptemp = enemy.calculatePosition(Direction.DOWN);
			EnemyBlueShip otemp = new EnemyBlueShip(ptemp.x,ptemp.y);
			
			//check if blue ship hit another black Ship			
			if(!AIcheckOverlap(otemp))
			{
				minValue=disDown;
				dir=Direction.DOWN;
			}
		}
		
		if(disLeft<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.LEFT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.LEFT);
			EnemyBlueShip otemp = new EnemyBlueShip(ptemp.x,ptemp.y);
			
			//check if blue ship hit another black Ship			
			if(!AIcheckOverlap(otemp))
			{
				minValue=disLeft;
				dir=Direction.LEFT;
			}
		}
		
		if(disRight<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.RIGHT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.RIGHT);
			EnemyBlueShip otemp = new EnemyBlueShip(ptemp.x,ptemp.y);
			
			//check if blue ship hit another black Ship			
			if(!AIcheckOverlap(otemp))
			{
				minValue=disRight;
				dir=Direction.RIGHT;
			}
		}
		
		if(disUpLeft<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.UPLEFT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.UPLEFT);
			EnemyBlueShip otemp = new EnemyBlueShip(ptemp.x,ptemp.y);
			
			//check if blue ship hit another black Ship			
			if(!AIcheckOverlap(otemp))
			{
				minValue=disUpLeft;
				dir=Direction.UPLEFT;
			}
		}
		
		if(disDownLeft<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.DOWNLEFT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.DOWNLEFT);
			EnemyBlueShip otemp = new EnemyBlueShip(ptemp.x,ptemp.y);
			
			//check if blue ship hit another black Ship			
			if(!AIcheckOverlap(otemp))
			{
				minValue=disDownLeft;
				dir=Direction.DOWNLEFT;
			}
		}
		
		if(disUpRight<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.UPRIGHT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.UPRIGHT);
			EnemyBlueShip otemp = new EnemyBlueShip(ptemp.x,ptemp.y);
			
			//check if blue ship hit another black Ship			
			if(!AIcheckOverlap(otemp))
			{
				minValue=disUpRight;
				dir=Direction.UPRIGHT;
			}
		}
		
		if(disDownRight<minValue&&Controller.isValidPosition(enemy.calculatePosition(Direction.DOWNRIGHT)))
		{
			Point ptemp = enemy.calculatePosition(Direction.DOWNRIGHT);
			EnemyBlueShip otemp = new EnemyBlueShip(ptemp.x,ptemp.y);
			
			//check if blue ship hit another black Ship			
			if(!AIcheckOverlap(otemp))
			{
				minValue=disDownRight;
				dir=Direction.DOWNRIGHT;
			}
		}
		return dir;
	}
	
	static void blueShipShot()
	{
		Vector<EnemyBlueShip> blueShips = EnemyBlueShipManager.getBlueShipVect();
		Vector<EnemyBlackShip> eneShips = EnemyBlackShipManager.getBlackShipVect();
		boolean indexBlack[] = new boolean[eneShips.size()];
		boolean indexBlue[] = new boolean[blueShips.size()];
		
		for(int i=0;i<eneShips.size();i++)
		{
			indexBlack[i]=false;
		}
		for(int i=0;i<blueShips.size();i++)
		{
			indexBlue[i]=false;
		}
		//BlueShip shot blackShip
		for(EnemyBlueShip blue : blueShips)
		{
			for(EnemyBlackShip obj : eneShips)
			{
				Point temp = new Point(obj.getX(),obj.getY());
				Point temp1 = blue.getTarget1(blue.getShotAxis());
				Point temp2 = blue.getTarget2(blue.getShotAxis());

				//Mark the index of EnemyBlackShip to be destroyed
				if (temp.x == temp1.x && temp.y == temp1.y) {

					indexBlack[eneShips.indexOf(obj)]=true;
					Controller.explosion = true;
				}
				else Controller.explosion = false;
				if (temp.x == temp2.x && temp.y == temp2.y) {
					indexBlack[eneShips.indexOf(obj)]=true;
					Controller.explosion = true;
				}
				else Controller.explosion = false;

			}
		}
		//BlueShip shot blue ship
		for(EnemyBlueShip blue : blueShips)
		{
			for(EnemyBlueShip obj : blueShips)
			{
				if(obj!=blue)
				{
					Point temp = new Point(obj.getX(),obj.getY());
					Point otemp = new Point(blue.getX(),blue.getY());
					Point temp1 = blue.getTarget1(blue.getShotAxis());
					Point temp2 = blue.getTarget2(blue.getShotAxis());
					Point temp3 = obj.getTarget1(obj.getShotAxis());
					Point temp4 = obj.getTarget2(obj.getShotAxis());

					//Mark the index of BlueShip to be destroyed
					if (temp.x == temp1.x && temp.y == temp1.y) {

						indexBlue[blueShips.indexOf(obj)]=true;
						Controller.explosion = true;
					}
					else Controller.explosion = false;
					if (temp.x == temp2.x && temp.y == temp2.y) {
						indexBlue[blueShips.indexOf(obj)]=true;
						Controller.explosion = true;
					}
					else Controller.explosion = false;
					//Mark the index of BlueShip to be destroyed
					if (otemp.x == temp3.x && otemp.y == temp3.y) {

						indexBlue[blueShips.indexOf(blue)]=true;
						Controller.explosion = true;
					}
					else Controller.explosion = false;
					if (otemp.x == temp4.x && otemp.y == temp4.y) {
						indexBlue[blueShips.indexOf(blue)]=true;
						Controller.explosion = true;
					}
					else Controller.explosion = false;
				}
			}
		}
		
		//remove black ship
		for(int i=eneShips.size()-1;i>=0;i--)
		{
			if(indexBlack[i]==true)
			{
				//do NOT add skull if enemy ship hit island
				skullManager.add(new Skull(eneShips.get(i).getX(), eneShips.get(i).getY()));
				eneShips.remove(eneShips.get(i));
				score.addScore(20);
			}
		}
		
		//remove blue ship
		for(int i=blueShips.size()-1;i>=0;i--)
		{
			if(indexBlue[i]==true)
			{
				//do NOT add skull if enemy ship hit island
				skullManager.add(new Skull(blueShips.get(i).getX(), blueShips.get(i).getY()));
				blueShips.remove(blueShips.get(i));
				score.addScore(30);
			}
		}
		
		//If Blue Ship shot MyShip
		for(EnemyBlueShip blue : blueShips)
		{
			Point temp0 = myShip.getLocation();
			Point temp1 = blue.getTarget1(blue.getShotAxis());
			Point temp2 = blue.getTarget2(blue.getShotAxis());

			if (temp0.x == temp1.x && temp0.y == temp1.y) {
				myShip.setHP(myShip.getHP()-1);
				Controller.explosion = true;
				skullManager.add(new Skull(myShip.getX(), myShip.getY()));
				Point temp3 = Controller.random();
				myShip.setXY(temp3.x,temp3.y);
				//blueShips.remove(blue);
			}
			else Controller.explosion = false;
			if (temp0.x == temp2.x && temp0.y == temp2.y) {
				myShip.setHP(myShip.getHP()-1);
				Controller.explosion = true;
				skullManager.add(new Skull(myShip.getX(), myShip.getY()));
				Point temp3 = Controller.random();
				myShip.setXY(temp3.x,temp3.y);
				//blueShips.remove(blue);
			}
			else Controller.explosion = false;
		}		
	}

	@Override
	public void shotted() {
		// TODO Auto-generated method stub
//		System.out.println("MyShip shot 2");
		Vector<EnemyBlueShip> eneShips = EnemyBlueShipManager.getBlueShipVect();
		boolean index[] = new boolean[eneShips.size()];
		
		for(int i=0;i<eneShips.size();i++)
		{
			index[i]=false;
		}
		for(EnemyBlueShip obj : eneShips)
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
			//BlueShip obj = eneShips.get(i);
			if(index[i]==true) {	
				
				skullManager.add(new Skull(eneShips.get(i).getX(), eneShips.get(i).getY()));
				EnemyBlueShipManager.remove(eneShips.get(i));
				score.addScore(40);
				//creat explosion when hit 
				System.out.println("MyShip shot blueShip");
			}
		}
	}
	
}
