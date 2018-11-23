package gameDrawer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameModel.CannonManager;
import gameModel.GameObject;
import gameUI.GameWindows;

public class FoodDrawerManager extends StaticObjectsDrawerManager{
	
	public FoodDrawerManager() throws IOException {
		super();
		bigImg = ImageIO.read(new File("Resources/Military-Cannon-icon.png"));
	}
	
	public void draw(Graphics g){
		CannonManager fm = CannonManager.getInst();
		
		for(int i = 0; i < fm.getFoodVect().size(); i++) {
			BufferedImage img = bigImg.getSubimage(0, 0, 50, 50);
			vecImg.add(img);
			vecImgArr.add(vecImg);	
		}
		for(GameObject x : CannonManager.getInst().getFoodVect()){
				g.drawImage(sprite, x.getX() * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET - 20
						, x.getY()* GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET, 50, 50, null);
			
		}
	}
	
	private static FoodDrawerManager inst;
	public static FoodDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new FoodDrawerManager();
		}
		return inst;
	}
}
