package gameDrawer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameModel.Flag;
import gameUI.GameWindows;


public class WinFlagDrawerManager {
	public Flag flag;
	private BufferedImage sprite;
	
	public WinFlagDrawerManager() throws IOException {
		flag = Flag.getInst();
		sprite = ImageIO.read(new File("Resources/flag.png"));
	}
	
	public void draw(Graphics g) {
		g.drawImage(sprite, flag.getX() * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET - 20, 
				flag.getY() * GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET, null);
	}
	
	private static WinFlagDrawerManager inst;
	public static WinFlagDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new WinFlagDrawerManager();
		}
		return inst;
	}
}
