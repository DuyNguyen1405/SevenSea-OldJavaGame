package gameModel;

import java.util.Vector;

public class EnemyBlackShipManager{
	
	protected static Vector<EnemyBlackShip> EnemyShipVect;
	public static Vector<EnemyBlackShip> getBlackShipVect()
	{
		return EnemyShipVect;
	}
	
	public static boolean checkOverlap(GameObject target)
	{
		boolean isOverlap = false;
		for(EnemyBlackShip obj : EnemyShipVect){
			if(obj.isOverlap(target))
			{
				isOverlap = true;
				break;
			}
		}
		return isOverlap;
	}
	
	private EnemyBlackShipManager()
	{
		EnemyShipVect = new Vector<EnemyBlackShip>();
	}
	
	public static void add(EnemyBlackShip obj)
	{
		EnemyShipVect.addElement(obj);
	}
	
	public static void remove(EnemyBlackShip obj)
	{
		EnemyShipVect.remove(obj);
	}
	
	private static EnemyBlackShipManager inst;
	public static EnemyBlackShipManager getInst()
	{
		if(inst == null)
		{
			inst = new EnemyBlackShipManager();
		}
		return inst;
	}
	public void reset() {
//		inst = null;
//		EnemyShipVect = new Vector<EnemyBlackShip>();
		// TODO Auto-generated method stub
		EnemyBlackShipManager.getBlackShipVect().removeAllElements();
	}
}
