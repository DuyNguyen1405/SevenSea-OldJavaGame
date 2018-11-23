package gameModel;

import java.util.Vector;

public class TornadoManager implements GameObjectsManager
{	
	protected Vector<Tornado> tornadoVect;
	public Vector<Tornado> getTornadoVect()
	{
		return tornadoVect;
	}
	private TornadoManager()
	{
		tornadoVect = new Vector<Tornado>();
	}
	
	private static TornadoManager inst;
	public static TornadoManager getInst()
	{
		if(inst == null)
		{
			inst = new TornadoManager();
		}
		return inst;
	}
	@Override
	public boolean checkOverlap(GameObject target) {
		boolean isOverlap = false;
		for(GameObject obj : tornadoVect)
			if(obj.isOverlap(target))
				isOverlap = true;
		return isOverlap;
	}
	@Override
	public void add(GameObject obj) {
		tornadoVect.addElement((Tornado) obj);
	}
	@Override
	public void remove(GameObject obj) {
		tornadoVect.removeElement(obj);
	}
	@Override
	public void reset() {
//		inst = null;
		// TODO Auto-generated method stub
		tornadoVect.removeAllElements();
	}
}
