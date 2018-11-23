package gameDrawer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import gameModel.EnemyBlueShip;
import gameModel.EnemyBlackShip;
import gameModel.EnemyBlackShipManager;
import gameModel.EnemyBlueShipManager;
import gameUI.GameWindows;

public class BlackShipDrawerManager extends EnemyShipDrawerManager{
	
	public BlackShipDrawerManager() throws IOException{
			
			bigImg = ImageIO.read(new File("Resources/enemy.png"));
			vecImg = new Vector<BufferedImage>();
			vecImgArr = new Vector<Vector<BufferedImage>>();
			
			vecImageUp = new Vector<BufferedImage>();
			vecImageUpRight = new Vector<BufferedImage>();
			vecImageRight = new Vector<BufferedImage>();
			vecImageDownRight = new Vector<BufferedImage>();
			vecImageDown = new Vector<BufferedImage>();
			vecImageDownLeft = new Vector<BufferedImage>();
			vecImageLeft = new Vector<BufferedImage>();
			vecImageUpLeft = new Vector<BufferedImage>();
			
			for(int i = 0; i < 8; i++){
				BufferedImage img = bigImg.getSubimage(0, 50 * i, 50, 50);
				vecImageUp.add(img);
			}
			for(int i = 0; i < 8; i++){
				BufferedImage img = bigImg.getSubimage(100, 50 * i, 50, 50);
				vecImageUpRight.add(img);
			}
			for(int i = 0; i < 8; i++){
				BufferedImage img = bigImg.getSubimage(200, 50 * i, 50, 50);
				vecImageRight.add(img);
			}
			for(int i = 0; i < 8; i++){
				BufferedImage img = bigImg.getSubimage(300, 50 * i, 50, 50);
				vecImageDownRight.add(img);
			}
			for(int i = 0; i < 8; i++){
				BufferedImage img = bigImg.getSubimage(400, 50 * i, 50, 50);
				vecImageDown.add(img);
			}
			for(int i = 0; i < 8; i++){
				BufferedImage img = bigImg.getSubimage(500, 50 * i, 50, 50);
				vecImageDownLeft.add(img);
			}
			for(int i = 0; i < 8; i++){
				BufferedImage img = bigImg.getSubimage(600, 50 * i, 50, 50);
				vecImageLeft.add(img);
			}
			for(int i = 0; i < 8; i++){
				BufferedImage img = bigImg.getSubimage(700, 50 * i, 50, 50);
				vecImageUpLeft.add(img);
			}
			
		}
	
	public void draw(Graphics g) throws IOException{
		//vecImgArr = null;
		vecImgArr = new Vector<Vector<BufferedImage>>();
		for(int i = 0; i < EnemyBlackShipManager.getBlackShipVect().size(); i++){
			switch (EnemyBlackShipManager.getBlackShipVect().get(i).getAxis()) {
			case UP:
				vecImg = vecImageUp;
				break;
			case UPRIGHT:
				vecImg = vecImageUpRight;
				break;
			case RIGHT:
				vecImg = vecImageRight;
				break;
			case DOWNRIGHT:
				vecImg = vecImageDownRight;
				break;
			case DOWN:
				vecImg = vecImageDown;
				break;
			case DOWNLEFT:
				vecImg = vecImageDownLeft;
				break;
			case LEFT:
				vecImg = vecImageLeft;
				break;
			case UPLEFT:
				vecImg = vecImageUpLeft;
				break;

			default:
				break;
			}
			vecImgArr.add(vecImg);
		}

		for(int i = 0; i < vecImgArr.size(); i ++){
			EnemyBlackShip x = EnemyBlackShipManager.getBlackShipVect().get(i);
				g.drawImage(vecImgArr.get(i).get(0), x.getX() * GameWindows.SIZEOFSQUARE - 20 + GameWindows.OFFSET
						, x.getY()* GameWindows.SIZEOFSQUARE +27+ GameWindows.OFFSET, null);
		}
	}
	
	private static BlackShipDrawerManager inst;
	public static BlackShipDrawerManager getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new BlackShipDrawerManager();
		}
		return inst;
	}

}
