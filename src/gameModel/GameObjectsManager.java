package gameModel;

public interface GameObjectsManager {

//	protected Vector<GameObject> gameObjectVect;
//	public Vector<GameObject> getGameObjectVect()
//	{
//		return gameObjectVect;
//	}
	
	public boolean checkOverlap(GameObject target);
//	{
//		boolean isOverlap = false;
//		for(GameObject obj : gameObjectVect)
//			if(obj.isOverlap(target))
//				isOverlap = true;
//		return isOverlap;
//	}
	
//	GameObjectsManager()
//	{
//		gameObjectVect = new Vector<GameObject>();
//	}
	
	public void add(GameObject obj);
//	{
//		gameObjectVect.addElement(obj);
//	}
	
	public void remove(GameObject obj);
//	{
//		gameObjectVect.removeElement(obj);
//	}
	public void reset();
}
