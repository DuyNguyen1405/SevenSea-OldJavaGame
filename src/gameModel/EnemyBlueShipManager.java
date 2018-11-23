package gameModel;

import java.util.Vector;

public class EnemyBlueShipManager{
	
	protected static Vector<EnemyBlueShip> BlueShipVect;
	public static Vector<EnemyBlueShip> getBlueShipVect()
	{
		return BlueShipVect;
	}
	
	public static boolean checkOverlap(GameObject target)
	{
		boolean isOverlap = false;
		for(EnemyBlueShip obj : BlueShipVect){
			//Point thisPos = new Point(obj.x,obj.y);
			//not checking itself
			if(obj.isOverlap(target))
			{
//				boolean same = false;
//				if(target.x==obj.getX()&&target.y==obj.getY())
//					same=true;
//				if(!same)
//				{
					isOverlap = true;
					break;
//				}
			}
		}
		return isOverlap;
	}
	
	public static boolean isSuicide(GameObject target)
	{
		boolean isOverlap = false;
		for(EnemyBlueShip obj : BlueShipVect){
			//Point thisPos = new Point(obj.x,obj.y);
			//not checking itself
			if(obj!=target&&obj.isOverlap(target))
			{
//				boolean same = false;
//				if(target.x==obj.getX()&&target.y==obj.getY())
//					same=true;
//				if(!same)
//				{
					isOverlap = true;
					break;
//				}
			}
		}
		return isOverlap;
	}
	
	private EnemyBlueShipManager()
	{
		BlueShipVect = new Vector<EnemyBlueShip>();
	}
	
	public static void add(EnemyBlueShip obj)
	{
		BlueShipVect.addElement(obj);
	}
	
	public static void remove(EnemyBlueShip obj)
	{
		BlueShipVect.remove(obj);
	}
	
	private static EnemyBlueShipManager inst;
	public static EnemyBlueShipManager getInst()
	{
		if(inst == null)
		{
			inst = new EnemyBlueShipManager();
		}
		return inst;
	}

	public void reset() {
		//inst = null;
		//BlueShipVect = new Vector<BlueShip>();
		// TODO Auto-generated method stub
		EnemyBlueShipManager.getBlueShipVect().removeAllElements();
	}
}
