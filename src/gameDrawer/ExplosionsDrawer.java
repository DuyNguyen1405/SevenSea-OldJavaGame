//package gameDrawer;
//
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.beans.ExceptionListener;
//import java.io.File;
//import java.io.IOException;
//import java.util.Vector;
//
//import javax.imageio.ImageIO;
//
//import gameModel.Explosion;
//import gameModel.ExplosionManager;
//import gameModel.MyShip;
//import gameModel.Point;
//import gameUI.GameWindows;
//
//public class ExplosionsDrawer {
//	private ExplosionManager explosionManager;
//	private BufferedImage sprite;
//	int positionAnimation;
//	private BufferedImage bigImg;
//	private Vector<BufferedImage> vecImg;
//	private MyShip myShip;
//	
//	public ExplosionsDrawer() throws IOException {
//		explosionManager = ExplosionManager.getInst();
//		bigImg = ImageIO.read(new File("Resources/explode.png"));
//		vecImg = new Vector<>();
//		for (int j=0; j < 14; j++) {
//			BufferedImage img = bigImg.getSubimage(j*35, 0, 35, 50);
//			vecImg.add(img);
//		}
//	}
//	
//	public void update(){
//		if(positionAnimation >= vecImg.size() - 1){
//			positionAnimation = 0;
//		}else{
//			positionAnimation++;
//		}
//			sprite = vecImg.get(positionAnimation);
//		}
//
//	public void draw(Graphics g){
//		for (Explosion obj: explosionManager.getInst().getExplosionVect()){
//			g.drawImage(sprite, obj.getX() * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET - 20
//					, obj.getY() * GameWindows.SIZEOFSQUARE + GameWindows.OFFSET, 50, 50, null);
//
//		}
//			
//	}
//	
//	private static ExplosionsDrawer inst;
//	public static ExplosionsDrawer getInst() throws IOException
//	{
//		if(inst == null)
//		{
//			inst = new ExplosionsDrawer();
//		}
//		return inst;
//	}
//}
