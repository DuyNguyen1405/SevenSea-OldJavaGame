package gameModel;

public class AllEnemyShipManager{
	
	protected EnemyBlackShipManager EnemyBlackShip;
	protected EnemyBlueShipManager EnemyBlueShip;
		
	public boolean checkOverlap(GameObject target)
	{
		boolean isOverlap = false;
		if(EnemyBlackShipManager.checkOverlap(target))
			isOverlap=true;
		if(EnemyBlueShipManager.checkOverlap(target))
			isOverlap=true;
//		if(EnemyRedShip.checkOverlap(target))
//			isOverlap=true;
//		if(EnemyGhostShip.checkOverlap(target))
//			isOverlap=true;
		
		return isOverlap;
	}
	
	private AllEnemyShipManager()
	{
		EnemyBlackShip = EnemyBlackShipManager.getInst();
		EnemyBlueShip = EnemyBlueShipManager.getInst();
//		EnemyRedShip = EnemyRedShipManager.getInst();
//		EnemyGhostShip = EnemyGhostShipManager.getInst();
	}
	
	private static AllEnemyShipManager inst;
	public static AllEnemyShipManager getInst()
	{
		if(inst == null)
		{
			inst = new AllEnemyShipManager();
		}
		return inst;
	}
}
