package gameDrawer;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import gameUI.GameWindows;
import gameModel.GameObject;
import gameModel.IslandManager;

public class IslandDrawerManager extends StaticObjectsDrawerManager{
	
	public IslandDrawerManager() throws IOException{
		super();
		bigImg = ImageIO.read(new File("Resources/island.png"));
	}
	
	public void draw(Graphics g){
		IslandManager im = IslandManager.getInst();
		
		for(int i = 0; i < im.getIslandVect().size(); i++){
			for(int j=0;j<4;j++) {
				BufferedImage img = bigImg.getSubimage(j*42, 42, 42, 42);
				vecImg.add(img);
			}
			vecImgArr.add(vecImg);
		}
		for(GameObject x : IslandManager.getInst().getIslandVect()){
				g.drawImage(sprite, x.getX() * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET - 20
						, x.getY()* GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET, 50, 50, null);
			
		}
		
	}
	
	private static IslandDrawerManager inst;
	public static IslandDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new IslandDrawerManager();
		}
		return inst;
	}
}
