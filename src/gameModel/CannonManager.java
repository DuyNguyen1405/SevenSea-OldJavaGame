package gameModel;

import java.util.Vector;

public class CannonManager implements GameObjectsManager
{
	protected Vector<Cannon> FoodVect;
	private MyShip myShip = MyShip.getInst();
	
	public Vector<Cannon> getFoodVect()
	{
		return FoodVect;
	}
	private CannonManager()
	{
		FoodVect = new Vector<Cannon>();
	}
	
	private static CannonManager inst;
	public static CannonManager getInst()
	{
		if(inst == null)
		{
			inst = new CannonManager();
		}
		return inst;
	}
	@Override
	public boolean checkOverlap(GameObject target) {
		boolean isOverlap = false;
		for(GameObject obj : FoodVect)
			if(obj.isOverlap(target))
				isOverlap = true;
		return isOverlap;
	}
	@Override
	public void add(GameObject obj) {
		FoodVect.addElement((Cannon) obj);
	}
	@Override
	public void remove(GameObject obj) {
		FoodVect.removeElement(obj);
	}
	@Override
	public void reset() {
//		inst = null;
		// TODO Auto-generated method stub
		this.getFoodVect().removeAllElements();
	}
	
	public void eatFood()
	{
		if(checkOverlap(myShip))
		{
			Vector<Cannon> foodVect = getFoodVect();
			boolean index[] = new boolean[foodVect.size()];
			
			for(int i=0;i<foodVect.size();i++)
			{
				index[i]=false;
			}
			for(Cannon obj : foodVect)
			{
				//Enemy Ship hit another enemy ship
				if(obj.isOverlap(myShip))
					index[foodVect.indexOf(obj)]=true;	
			}
			
			for(int i=foodVect.size()-1;i>=0;i--)
			{
				if(index[i]==true)
				{
					this.remove(foodVect.get(i));
					if (myShip.getAtkRange() <= 5) 
						myShip.setAtkRange(myShip.getAtkRange()+1);
				}
			}
		}
	}
}
