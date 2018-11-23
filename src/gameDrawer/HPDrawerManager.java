package gameDrawer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameModel.MyShip;

public class HPDrawerManager {
	private MyShip myShip;
	private BufferedImage sprite,sprite2;
	
	public HPDrawerManager() throws IOException {
		myShip = MyShip.getInst();
		sprite = ImageIO.read(new File("Resources/extralife.gif"));
		sprite2 = ImageIO.read(new File("Resources/sliderthumb.png"));
	}
	
	public void draw(Graphics g) {
		for (int i=1;i<=myShip.getHP();i++) {
			//System.out.println(myShip.getHP());
			g.drawImage(sprite, 570+50+(i-1)*20, 220+27, null);
		}
		//for (int i=1;i<=myShip.defaulHP-myShip.getHP()-1;i++) {
		//	g.drawImage(sprite2, 570+50+(i-1)*20, 220+27, null);
		//}
	}
	
	private static HPDrawerManager inst;
	public static HPDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new HPDrawerManager();
		}
		return inst;
	}
	
}
