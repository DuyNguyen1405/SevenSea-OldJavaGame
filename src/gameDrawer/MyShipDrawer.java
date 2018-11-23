package gameDrawer;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import gameUI.GameWindows;
import gameModel.*;

public class MyShipDrawer {
	private MyShip myShip;
	private BufferedImage sprite;
	int positionAnimation;
	private BufferedImage bigImg;
	private Vector<BufferedImage> vecImage;
	private Vector<BufferedImage> vecImageUp;
	private Vector<BufferedImage> vecImageUpRight;
	private Vector<BufferedImage> vecImageRight;
	private Vector<BufferedImage> vecImageDownRight;
	private Vector<BufferedImage> vecImageDown;
	private Vector<BufferedImage> vecImageDownLeft;
	private Vector<BufferedImage> vecImageUpLeft;
	private Vector<BufferedImage> vecImageLeft;
	
	
	public void draw(Graphics g) throws IOException{
		Direction dir = myShip.getAxis();
		vecImage = new Vector<>();
		switch (dir) {
		case UP:
			vecImage = vecImageUp;
			break;
		case UPRIGHT:
			vecImage = vecImageUpRight;
			break;
		case RIGHT:
			vecImage = vecImageRight;
			break;
		case DOWNRIGHT:
			vecImage = vecImageDownRight;
			break;
		case DOWN:
			vecImage = vecImageDown;
			break;
		case DOWNLEFT:
			vecImage = vecImageDownLeft;
			break;
		case LEFT:
			vecImage = vecImageLeft;
			break;
		case UPLEFT:
			vecImage = vecImageUpLeft;
			break;

		default:
			break;
		}
		//System.out.println(myShip.getX() + ", " + myShip.getY());
		g.drawImage(sprite, myShip.getX() * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET - 20
				, myShip.getY()* GameWindows.SIZEOFSQUARE + 27 + GameWindows.OFFSET, null);
	}
	
	private MyShipDrawer() throws IOException
	{
		myShip = MyShip.getInst();
	//	positionAnimation = 0;
		System.out.println("ok!");
		//System.out.println(myShip.getLastMove());
		bigImg = ImageIO.read(new File("Resources/tau.png"));
		
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
	
	public void update(){
		//System.out.println(vecImage.size());
		if (vecImage == null) return;
		if(positionAnimation >= vecImage.size() - 1){
			positionAnimation = 0;
		}else{
			positionAnimation++;
		}
		sprite = vecImage.get(positionAnimation);
	}
	
	private static MyShipDrawer inst;
	public static MyShipDrawer getInst() throws IOException
	{
		if(inst == null)
		{
			inst = new MyShipDrawer();
		}
		return inst;
	}

	public static void reset() throws IOException {
		inst = new MyShipDrawer();
		// TODO Auto-generated method stub
		
	}
	
}
