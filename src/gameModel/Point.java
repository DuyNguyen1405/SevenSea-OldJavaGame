package gameModel;

import java.io.FileNotFoundException;

import mapHandler.ReadMap;

public class Point {
	public int x;
	public int y;
	
	public boolean checkOutOfBound() throws FileNotFoundException {
		int size = ReadMap.size;
		if (x<0 || x >= size) return true;
		if (y<0 || y >= size) return true;
		return false;
		
	}
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean hasSomething() {
		GameObject target = new GameObject(x, y);
		EnemyBlackShipManager.getInst();
		//System.out.println(x + ", " + y);
		for (GameObject obj: EnemyBlackShipManager.getBlackShipVect()) {
			if (obj.isOverlap(target)) return true;
		}
		EnemyBlueShipManager.getInst();
		for (GameObject obj: EnemyBlueShipManager.getBlueShipVect()) {
			if (obj.isOverlap(target)) return true;
		}
		for (GameObject obj: IslandManager.getInst().getIslandVect()) {
			if (obj.isOverlap(target)) return true;
		}
		return false;
	}
	
	
}
