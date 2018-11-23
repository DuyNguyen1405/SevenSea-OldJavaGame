package gameController;

import gameModel.*;

public interface AIEnemyShip {
		
	public boolean AIcheckOverlap(Ship targetShip);
	
	Direction AIcal(Ship myShip);
	
	public void shotted();
}
