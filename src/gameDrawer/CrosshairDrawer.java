package gameDrawer;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameUI.GameWindows;
import gameModel.*;

public class CrosshairDrawer {
	private MyShip myShip;
	private BufferedImage sprite;
	
	public void draw(Graphics g, int size, boolean isPrevKeySpace){
		if (!isPrevKeySpace) return;
		Point p1 = myShip.getTarget1(myShip.getShotAxis());
		Point p2 = myShip.getTarget2(myShip.getShotAxis());
		
		System.out.println(p1.x + ", " + p1.y);
		System.out.println(p2.x + ", " + p2.y);
		g.drawImage(sprite, p1.x * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET + 12 - 20
				, p1.y * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET + 12, null);
		g.drawImage(sprite, p2.x * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET + 12 - 20
				, p2.y * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET + 12, null);
	}
	private CrosshairDrawer() throws IOException
	{
		myShip = MyShip.getInst();
		EnemyBlackShipManager.getInst();
		
		sprite = ImageIO.read(new File("Resources/crosshair.png"));
	}
	private static CrosshairDrawer inst;
	public static CrosshairDrawer getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new CrosshairDrawer();
		}
		return inst;
	}
	
}
