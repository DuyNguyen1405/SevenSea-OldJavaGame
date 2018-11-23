package gameModel;

import java.util.Vector;

public class SkullManager implements GameObjectsManager
{	
	protected Vector<Skull> skullVect;
	public Vector<Skull> getSkullVect()
	{
		return skullVect;
	}
	private SkullManager()
	{
		skullVect = new Vector<Skull>();
	}
	
	private static SkullManager inst;
	public static SkullManager getInst()
	{
		if(inst == null)
		{
			inst = new SkullManager();
		}
		return inst;
	}
	@Override
	public boolean checkOverlap(GameObject target) {
		boolean isOverlap = false;
		for(GameObject obj : skullVect)
			if(obj.isOverlap(target))
				isOverlap = true;
		return isOverlap;
	}
	@Override
	public void add(GameObject obj) {
		skullVect.addElement((Skull) obj);
	}
	@Override
	public void remove(GameObject obj) {
		skullVect.removeElement(obj);
	}
	@Override
	public void reset() {
//		inst = null;
		// TODO Auto-generated method stub
		skullVect.removeAllElements();
	}
}
