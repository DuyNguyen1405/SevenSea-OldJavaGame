package gameDrawer;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import gameUI.GameWindows;
import gameModel.GameObject;
import gameModel.TornadoManager;

public class TornadoDrawerManager {
	private BufferedImage sprite;
	private BufferedImage bigImg;
	int positionAnimation;
	private Vector<BufferedImage> vecImg;
	private Vector<Vector<BufferedImage>> vecImgArr;
	
	public TornadoDrawerManager() throws IOException{
		
		TornadoManager tm = TornadoManager.getInst();
		
		bigImg = ImageIO.read(new File("Resources/tornado.png"));
		vecImg = new Vector<BufferedImage>();
		vecImgArr = new Vector<Vector<BufferedImage>>();
		
		
		for(int i = 0; i < tm.getTornadoVect().size(); i++){
			for (int j=0; j < 16; j++) {
				BufferedImage img = bigImg.getSubimage(0, j*50, 50, 50);
				vecImg.add(img);
			}
			vecImgArr.add(vecImg);
		}
	}
	
	public void draw(Graphics g){
		for(GameObject x : TornadoManager.getInst().getTornadoVect()){
				g.drawImage(sprite, x.getX() * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET - 20
						, x.getY()* GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET, null);
			
		}
		
	}
	
	public void update(){
		if (vecImgArr.size() == 0) return;
		for (Vector<BufferedImage> vecImg: vecImgArr) {
			if(positionAnimation >= vecImg.size() - 1){
				positionAnimation = 0;
			}else{
				positionAnimation++;
			}
			sprite = vecImg.get(positionAnimation);
		}
	}
	
	private static TornadoDrawerManager inst;
	public static TornadoDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new TornadoDrawerManager();
		}
		return inst;
	}
}
