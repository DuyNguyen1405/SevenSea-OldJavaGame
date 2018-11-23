package gameDrawer;

import java.awt.Graphics;
import java.awt.font.NumericShaper.Range;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameController.Controller;
import gameModel.Direction;
import gameModel.MyShip;
import gameModel.Point;
import gameUI.GameWindows;

public class RangeDrawerManager {
	private MyShip myShip;
	private BufferedImage sprite;
	int positionAnimation;
	
	public void draw(Graphics g) throws FileNotFoundException {
		if (Controller.getInst().isFinished()) return;
		Direction dir = myShip.getShotAxis();
		int stepX = 0, stepY = 0;
		switch (dir) {
		case UP:
			stepX = 0;
			stepY = 1;
			break;
		case UPRIGHT:
			stepX = 1;
			stepY = -1;
			break;
		case RIGHT:
			stepX = -1;
			stepY = 0;
			break;
		case DOWNRIGHT:
			stepX = 1;
			stepY = 1;
		
		default:
			break;
		}
		
		for (int i=1;i<=myShip.getAtkRange();i++) {
			if(myShip.getX() == myShip.getTarget2(dir).x && myShip.getY() == myShip.getTarget2(dir).y) break;
			Point p1 = new Point(myShip.getX()+stepX*i,myShip.getY()+stepY*i);
			if (p1.checkOutOfBound()) break;
			g.drawImage(sprite, p1.x * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET  -20
					, p1.y * GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET , null);
			if (p1.x == myShip.getTarget1(dir).x && p1.y == myShip.getTarget1(dir).y) break;
			if (p1.x == myShip.getTarget2(dir).x && p1.y == myShip.getTarget2(dir).y) break;
		}
		
		for (int i=1;i<=myShip.getAtkRange();i++) {
			if(myShip.getX() == myShip.getTarget1(dir).x && myShip.getY() == myShip.getTarget1(dir).y) break;
			Point p2 = new Point(myShip.getX()-stepX*i,myShip.getY()-stepY*i);
			if (p2.checkOutOfBound()) break;
			g.drawImage(sprite, p2.x * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET -20 
					, p2.y * GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET , null);
			if (p2.x == myShip.getTarget1(dir).x && p2.y == myShip.getTarget1(dir).y) break;
			if (p2.x == myShip.getTarget2(dir).x && p2.y == myShip.getTarget2(dir).y) break;
		}
	}
	
	public RangeDrawerManager() throws IOException {
		myShip = MyShip.getInst();
		sprite = ImageIO.read(new File("Resources/square_range.jpg"));
	}
	
	private static RangeDrawerManager inst;
	public static RangeDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new RangeDrawerManager();
		}
		return inst;
	}
}
