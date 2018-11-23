package gameDrawer;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import gameUI.GameWindows;
import gameModel.GameObject;
import gameModel.SkullManager;

public class SkullDrawerManager extends StaticObjectsDrawerManager{

	public SkullDrawerManager() throws IOException{
		super();
		bigImg = ImageIO.read(new File("Resources/rubble.png"));
		
	}
	
	public void draw(Graphics g){
		SkullManager sm = SkullManager.getInst();
		
		for(int i = 0; i < sm.getSkullVect().size(); i++){
			for(int j=11;j>=0;j--) {
				BufferedImage img = bigImg.getSubimage(j*40, 0, 40, 40);
				vecImg.add(img);
			}
			vecImgArr.add(vecImg);
		}
		for(GameObject x : SkullManager.getInst().getSkullVect()){
			if (x != null) {
				g.drawImage(sprite, x.getX() * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET - 20
						, x.getY()* GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET, 50, 50, null);
			}
		}
	}
		
	private static SkullDrawerManager inst;
	public static SkullDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new SkullDrawerManager();
		}
		return inst;
	}
}
