package gameModel;

import java.util.Vector;

public class IslandManager implements GameObjectsManager
{
	protected Vector<Island> islandVect;
	public Vector<Island> getIslandVect()
	{
		return islandVect;
	}
	private IslandManager()
	{
		islandVect = new Vector<Island>();
	}
	
	private static IslandManager inst;
	public static IslandManager getInst()
	{
		if(inst == null)
		{
			inst = new IslandManager();
		}
		return inst;
	}
	@Override
	public boolean checkOverlap(GameObject target) {
		boolean isOverlap = false;
		for(GameObject obj : islandVect)
			if(obj.isOverlap(target))
				isOverlap = true;
		return isOverlap;
	}
	@Override
	public void add(GameObject obj) {
		islandVect.addElement((Island) obj);
	}
	@Override
	public void remove(GameObject obj) {
		islandVect.removeElement(obj);
	}
	@Override
	public void reset() {
//		inst = null;
		// TODO Auto-generated method stub
		islandVect.removeAllElements();
		
	}
}
