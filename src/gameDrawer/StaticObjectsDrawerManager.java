package gameDrawer;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

public class StaticObjectsDrawerManager {
	protected BufferedImage sprite;
	protected BufferedImage bigImg;
	int positionAnimation;
	protected Vector<BufferedImage> vecImg;
	protected Vector<Vector<BufferedImage>> vecImgArr;
	
	public StaticObjectsDrawerManager() {
		vecImg = new Vector<>();
		vecImgArr = new Vector<Vector<BufferedImage>>();
	}
	
	public void update(){
		if (vecImgArr.size() == 0) return;
		//Iterator<BufferedImage> buffImIterator = vecImg.iterator();
		//while(buffImIterator.hasNext())
		//{
		//	sprite = buffImIterator.next();
		//}
		//for (Vector<BufferedImage> vecImg: vecImgArr) {
			if(positionAnimation >= vecImg.size() - 1){
				positionAnimation = 0;
			}else{
				positionAnimation++;
			}
			sprite = vecImg.get(positionAnimation);
		//}
	}
}
