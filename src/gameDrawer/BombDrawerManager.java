package gameDrawer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameModel.Bomb;
import gameUI.GameWindows;


public class BombDrawerManager {
	public Bomb bomb;
	private BufferedImage sprite;
	
	public BombDrawerManager() throws IOException {
		bomb = Bomb.getInst();
		sprite = ImageIO.read(new File("Resources/bomb.png"));
	}
	
	public void draw(Graphics g) {
		g.drawImage(sprite, bomb.getX() * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET - 20, 
				bomb.getY() * GameWindows.SIZEOFSQUARE + 27+GameWindows.OFFSET, null);
	}
	
	private static BombDrawerManager inst;
	public static BombDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new BombDrawerManager();
		}
		return inst;
	}
}
