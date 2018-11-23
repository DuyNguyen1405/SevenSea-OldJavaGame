package gameDrawer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameController.Controller;
import gameModel.*;
import gameUI.GameWindows;

public class BlueShipRangeDrawerManager {
	private EnemyBlueShipManager blueShipManager;
	private BufferedImage sprite;
	int positionAnimation;
	
	public void draw(Graphics g) throws FileNotFoundException {
		if (Controller.getInst().isFinished()) return;
		for(EnemyBlueShip obj: blueShipManager.getInst().getBlueShipVect()) {
			Direction dir = obj.getShotAxis();
			int stepX = 0, stepY = 0;
			switch (dir) {
			case UP:
				stepX = 0;
				stepY = 1;
				break;
			case UPRIGHT:
				stepX = -1;
				stepY = 1;
				break;
			case RIGHT:
				stepX = 1;
				stepY = 0;
				break;
			case DOWNRIGHT:
				stepX = -1;
				stepY = -1;
			
			default:
				break;
			}
			
			for (int i=1;i<=obj.getAtkRange();i++) {

				if(obj.getX() == obj.getTarget2(dir).x && obj.getY() == obj.getTarget2(dir).y) break;
				Point p1 = new Point(obj.getX()+stepX*i,obj.getY()+stepY*i);
				if (p1.checkOutOfBound()) break;
				g.drawImage(sprite, p1.x * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET  -20
						, p1.y * GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET , null);
				if (p1.x == obj.getTarget1(dir).x && p1.y == obj.getTarget1(dir).y) break;
				if (p1.x == obj.getTarget2(dir).x && p1.y == obj.getTarget2(dir).y) break;
				
			}
			
			for (int i=1;i<=obj.getAtkRange();i++) {

				if(obj.getX() == obj.getTarget1(dir).x && obj.getY() == obj.getTarget1(dir).y) break;
				Point p2 = new Point(obj.getX()-stepX*i,obj.getY()-stepY*i);
				if (p2.checkOutOfBound()) break;
				g.drawImage(sprite, p2.x * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET -20 
						, p2.y * GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET , null);
				if (p2.x == obj.getTarget1(dir).x && p2.y == obj.getTarget1(dir).y) break;
				if (p2.x == obj.getTarget2(dir).x && p2.y == obj.getTarget2(dir).y) break;
				
			}
		}
		
	}
	public BlueShipRangeDrawerManager() throws IOException {
		blueShipManager = EnemyBlueShipManager.getInst();
		sprite = ImageIO.read(new File("Resources/square_range_enemy.jpg"));
	}
	
	private static BlueShipRangeDrawerManager inst;
	public static BlueShipRangeDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new BlueShipRangeDrawerManager();
		}
		return inst;
	}
	
}
