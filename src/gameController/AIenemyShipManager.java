package gameController;

import java.util.Vector;

import gameModel.*;

public class AIenemyShipManager {
	private static MyShip myShip;
	private static AIblackShip aiBlackShip;
	private static AIblueShip aiBlueShip;
	private static IslandManager islandManager;
	private static SkullManager skullManager;
	private static TornadoManager tornadoManager;
	private static Score score;
	
	private AIenemyShipManager()
	{
		myShip = MyShip.getInst();
		CannonManager.getInst();
		Flag.getInst();
		Bomb.getInst();	
		islandManager = IslandManager.getInst();
		skullManager = SkullManager.getInst();
		tornadoManager = TornadoManager.getInst();
		aiBlackShip = AIblackShip.getInst();
		aiBlueShip = AIblueShip.getInst();
		score = Score.getInst();
	}
	
	private static AIenemyShipManager inst;
	public static AIenemyShipManager getInst()
	{
		if(inst == null)
		{
			inst = new AIenemyShipManager();
		}
		return inst;
	}
	public void AIshotted()
	{
		aiBlackShip.shotted();
		aiBlueShip.shotted();
	}
	public void AImove()
	{
		//AI move here
		//Vector<EnemyBlackShip> eneShips = enemyBlackShipManager.getEnemyBlackShipVect();
		Vector<EnemyBlackShip> enemyBlackShips = EnemyBlackShipManager.getBlackShipVect();
		boolean indexBlack[] = new boolean[enemyBlackShips.size()];
		Vector<EnemyBlueShip> enemyBlueShips = EnemyBlueShipManager.getBlueShipVect();
		boolean indexBlue[] = new boolean[enemyBlueShips.size()];
		
		for(int i=0;i<enemyBlackShips.size();i++)
		{
			indexBlack[i]=false;
		}
		for(int i=0;i<enemyBlueShips.size();i++)
		{
			//System.out.println(i);
			indexBlue[i]=false;
		}
		for(EnemyBlackShip obj : enemyBlackShips)
		{
			Point location = obj.calculatePosition(aiBlackShip.AIcal(obj));
			obj.setAxis(aiBlackShip.AIcal(obj));
			//obj.setLastMove(AIblackShip.AIcalculate(obj));
			obj.setX(location.x);
			obj.setY(location.y);
			
			if(tornadoManager.checkOverlap(obj))
			{
				obj.setAxis(obj.getAxis());
				obj.setX(Controller.random().x);
				obj.setY(Controller.random().y);
				System.out.println("Blackship hit tornado");
			}
			//Enemy Ship hit Skull
			if(skullManager.checkOverlap(obj))
			{
				indexBlack[enemyBlackShips.indexOf(obj)]=true;
				System.out.println("Blackship hit skull");
			}
			//Enemy Ship hit island
			if(islandManager.checkOverlap(obj))
			{
				indexBlack[enemyBlackShips.indexOf(obj)]=true;
				System.out.println("Blackship hit island");
				//System.out.println(obj.getX() + ", " + obj.getY());
			}
		}
		for(EnemyBlueShip obj : enemyBlueShips)
		{
			Point location = obj.calculatePosition(aiBlueShip.AIcal(obj));
			obj.setAxis(aiBlueShip.AIcal(obj));
			//obj.setLastMove(AIblackShip.AIcalculate(obj));
			obj.setX(location.x);
			obj.setY(location.y);
			
			if(tornadoManager.checkOverlap(obj))
			{
				obj.setAxis(obj.getAxis());
				obj.setX(Controller.random().x);
				obj.setY(Controller.random().y);
				System.out.println("Blueship hit tornado");
			}
			//Enemy Ship hit Skull
			if(skullManager.checkOverlap(obj))
			{
				indexBlue[enemyBlueShips.indexOf(obj)]=true;
				System.out.println("Blueship hit skull");
			}
			//Enemy Ship hit island
			if(islandManager.checkOverlap(obj))
			{
				indexBlue[enemyBlueShips.indexOf(obj)]=true;
				System.out.println("Blueship hit island");
			}
		}
		//get this part outside so all enemy ship move at the same time
		for(EnemyBlackShip obj : enemyBlackShips)
		{
			//Enemy Ship hit another enemy ship
			for(EnemyBlackShip test : enemyBlackShips)
			{
				if(obj!=test&&obj.isOverlap(test))
				{
					indexBlack[enemyBlackShips.indexOf(obj)]=true;	
					indexBlack[enemyBlackShips.indexOf(test)]=true;	
					System.out.println("Blackship hit blackship");
				}
			}	
		}
		
		//blue ship hit each other
		for(EnemyBlueShip obj : enemyBlueShips)
		{
			//Enemy Ship hit another enemy ship
			for(EnemyBlueShip test : enemyBlueShips)
			{
				if(obj!=test&&obj.isOverlap(test))
				{
					indexBlue[enemyBlueShips.indexOf(obj)]=true;	
					indexBlue[enemyBlueShips.indexOf(test)]=true;	
					System.out.println("Blueship hit blueship");
				}
			}	
		}
		
		//blue ship hit black ship
		for(EnemyBlueShip obj : enemyBlueShips)
		{
			//Enemy Ship hit another enemy ship
			for(EnemyBlackShip test : enemyBlackShips)
			{
				if(obj.isOverlap(test))
				{
					indexBlue[enemyBlueShips.indexOf(obj)]=true;	
					indexBlack[enemyBlackShips.indexOf(test)]=true;	
					System.out.println("Blueship hit blackship");
				}
			}	
		}
		
		//Enemy BlackShip hit myShip
		for(EnemyBlackShip obj : enemyBlackShips)
		{
			if(obj.isOverlap(myShip))
			{
				indexBlack[enemyBlackShips.indexOf(obj)]=true;	
				System.out.println("Blackship hit myShip");
				if(myShip.getHP()>1)
				{
					myShip.setHP(myShip.getHP()-1);
					Controller.explosion = true;
					Point temp2 = Controller.random();
					myShip.setXY(temp2.x,temp2.y);
//							return;
				}
				else
				{
					skullManager.add(new Skull(myShip.getX(), myShip.getY()));
					Controller.isFinished = true;
					Controller.isVictorious = false;
//							return;
				}
			}
		}	
		
		//Enemy BlueShip hit myShip
		for(EnemyBlueShip obj : enemyBlueShips)
		{
			if(obj.isOverlap(myShip))
			{
				indexBlue[enemyBlueShips.indexOf(obj)]=true;	
				System.out.println("Blueship hit myShip");
				if(myShip.getHP()>1)
				{
					myShip.setHP(myShip.getHP()-1);
					Controller.explosion = true;
					Point temp2 = Controller.random();
					myShip.setXY(temp2.x,temp2.y);
//									return;
				}
				else
				{
					skullManager.add(new Skull(myShip.getX(), myShip.getY()));
					Controller.isFinished = true;
					Controller.isVictorious = false;
//									return;
				}
			}
		}	
				
		//remove black ship
		for(int i=enemyBlackShips.size()-1;i>=0;i--)
		{
			if(indexBlack[i]==true)
			{
				System.out.println("Remove black Ship " + i);
				//do NOT add skull if enemy ship hit island
				if(!islandManager.checkOverlap(enemyBlackShips.get(i)))
					skullManager.add(new Skull(enemyBlackShips.get(i).getX(), enemyBlackShips.get(i).getY()));
				
					enemyBlackShips.remove(enemyBlackShips.get(i));
					score.addScore(20);
				
			}
		}
		//remove blue ship
		for(int i=enemyBlueShips.size()-1;i>=0;i--)
		{
			if(indexBlue[i]==true)
			{
				//do NOT add skull if enemy ship hit island
				System.out.println("Remove blue Ship " + i);
				if(!islandManager.checkOverlap(enemyBlueShips.get(i)))
					skullManager.add(new Skull(enemyBlueShips.get(i).getX(), enemyBlueShips.get(i).getY()));
				
					enemyBlueShips.remove(enemyBlueShips.get(i));
					score.addScore(30);
				
			}
		}
	}
}
