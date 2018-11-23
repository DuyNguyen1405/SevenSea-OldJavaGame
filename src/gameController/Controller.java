package gameController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import gameModel.*;
import mapHandler.MapUpdate;
//import mapHandler.ReadGame;
import mapHandler.ReadMap;
public class Controller{
	//private static final int defaultNumOfIsland = 3,defaultNumOfEnemy = 3,defaultNumOfFood = 2;
	//private static int numOfIsland ,numOfEnemy,numOfFood;
	private static int level = 0;
	public static boolean explosion;
	static boolean isFinished;
	static boolean isVictorious;
	public static boolean isEasy;
	public static boolean hasSaveGame = false;
	private static MyShip myShip;
	private static IslandManager islandManager;
	private static SkullManager skullManager;
	private static TornadoManager tornadoManager;
	private static CannonManager foodManager;
	private static Flag flag;
	private static Bomb bomb;
	private static Score score;
	private static AIenemyShipManager aiManager;
	
	public boolean isNextGame() {
		return isEasy;
	}
	public void setNextGame(boolean isNextGame) {
		Controller.isEasy = isNextGame;
	}
	public boolean isFinished() {
		return isFinished;
	}
	public static void setFinished(boolean isFinished) {
		Controller.isFinished = isFinished;
	}
	public boolean isVictorious() {
		return isVictorious;
	}
	public static void setVictorious(boolean isVictorious) {
		Controller.isVictorious = isVictorious;
	}
	
	private Controller() throws FileNotFoundException {
//		numOfIsland = defaultNumOfIsland;
//		numOfEnemy = defaultNumOfEnemy;
//		numOfFood = defaultNumOfFood;
		myShip = MyShip.getInst();
		islandManager = IslandManager.getInst();
		skullManager = SkullManager.getInst();
		tornadoManager = TornadoManager.getInst();
		foodManager = CannonManager.getInst();
		flag = Flag.getInst();
		bomb = Bomb.getInst();
		score = Score.getInst();
		explosion = false;
		aiManager = AIenemyShipManager.getInst();
//		aiBlackShip = AIblackShip.getInst();
//		aiBlueShip = AIblueShip.getInst();
	}
	
	private void initFixedLocations() throws FileNotFoundException
	{
		//ReadGameWithMap.size = 11;
		if (hasSaveGame) 
		{
			System.out.println("continue game");
			ReadMap.getInst();
			ReadMap.readGame("mapEditorContinue.txt");
		}
		else if(isEasy)
		{
			System.out.println("new game easy");
			
			ReadMap.getInst();
			ReadMap.readGame("mapEditorEasy.txt");	
//			System.out.println("Read easy");
		}
		else
		{
			//With Map
			ReadMap.getInst();
			ReadMap.readGame("mapEditorHard.txt");		
			System.out.println("Read hard");
		}
		if(level!=0)
			this.nextStage();
	}
	
//	public static int getNumOfIsland() {
//		return numOfIsland;
//	}
//	public static void setNumOfIsland(int numOfIsland) {
//		Controller.numOfIsland = numOfIsland;
//	}
//	public static int getNumOfEnemy() {
//		return numOfEnemy;
//	}
//	public static void setNumOfEnemy(int numOfEnemy) {
//		Controller.numOfEnemy = numOfEnemy;
//	}
//	public static int getNumOfFood() {
//		return numOfFood;
//	}
//	public static void setNumOfFood(int numOfFood) {
//		Controller.numOfFood = numOfFood;
//	}
	
//	private void initRandomLocation()
//	{
//		Point p = new Point();
//		
//		ReadGameWithMap.size = 11;
//		
//		myShip.setXY(5, 5);
//		
//		tornadoManager.add(new Tornado(0,0));
//		tornadoManager.add(new Tornado(0,ReadGameWithMap.size-1));
//		tornadoManager.add(new Tornado(ReadGameWithMap.size-1,0));
//		tornadoManager.add(new Tornado(ReadGameWithMap.size-1,ReadGameWithMap.size-1));
//		
//		for (int i = 0; i < numOfEnemy; i++) {
//			p = ReadGameWithMap.random();
//			//enemyBlackShipManager.add(new EnemyBlackShip(p.x, p.y));
//			enemyBlackShipManager.add(new EnemyBlackShip(p.x, p.y));
//		}
//		
//		for (int i = 0; i < numOfIsland; i++) {
//			p = ReadGameWithMap.random();
//			islandManager.add(new Island(p.x, p.y));
//		}
//		for (int i = 0; i < numOfFood; i++) {
//			p = ReadGameWithMap.random();
//			foodManager.add(new Food(p.x, p.y));
//		}	
//		//p = ReadGameWithMap.random();
//		//enemyBlueShipManager.add(new BlueShip(p.x, p.y));
//		System.out.println(p.x + ", " + p.y);
//		p = ReadGameWithMap.random();
//		flag.setXY(p.x, p.y);
//		p = ReadGameWithMap.random();
//		bomb.setXY(p.x, p.y);
//	}
	
	static boolean isValidPosition(Point p)
	{
		return (p.x >= 0) && (p.y >= 0) && (p.x < ReadMap.size) && (p.y < ReadMap.size);
	}
	
	public void move(Direction direction)
	{
		if(isFinished) return;
		
		Point newMyShipPoint = myShip.calculatePosition(direction);
		GameObject temp = new GameObject(newMyShipPoint.x,newMyShipPoint.y);

		// Does NOT move to hit an island
		if(islandManager.checkOverlap(temp)) return;
		if(skullManager.checkOverlap(temp)) return;
		if(EnemyBlackShipManager.checkOverlap(temp)) return;
		if(EnemyBlueShipManager.checkOverlap(temp)) return;
		
		if(isValidPosition(newMyShipPoint))
		{
			myShip.setAxis(direction);
			myShip.setX(newMyShipPoint.x);
			myShip.setY(newMyShipPoint.y);
		}
		else
			return;
//		this.eatFlag();	
		flag.eatFlag();	
		//this.eatFood();
		foodManager.eatFood();
//		this.eatBomb();
		bomb.eatBomb();
		
		//AI move here
		aiManager.AImove();	
		AIblueShip.blueShipShot();
//		this.eatFlag();
		flag.eatFlag();	

		//check if game over
		if(EnemyBlackShipManager.getBlackShipVect().size()==0
				&&EnemyBlueShipManager.getBlueShipVect().size()==0)
		{
			isFinished = true;
			isVictorious = true;
			level++;
			return;
		}
		//2 ship hit myShip at the same time
		if(skullManager.checkOverlap(myShip))
		{
			if(myShip.getHP()>1)
			{
				myShip.setHP(myShip.getHP()-1);
				explosion = true;
				Point temp2 = random();
				myShip.setXY(temp2.x,temp2.y);
				return;
			}
			else
			{
				//skullManager.add(new Skull(myShip.getX(), myShip.getY()));
				isFinished = true;
				isVictorious = false;
				return;
			}
		}
		else explosion = false;
		
		//Teleport after enemy move
		if(tornadoManager.checkOverlap(myShip))
		{
			myShip.setAxis(direction);
			Point p = random();
			myShip.setX(p.x);
			myShip.setY(p.y);
		}
		//Update save game
		MapUpdate.getInst().updateSaveGame("mapEditorContinue.txt");
	}
	public void shot()
	{
		if(isFinished) return;
		
		aiManager.AIshotted();
		
		//AI move here
		aiManager.AImove();		
		AIblueShip.blueShipShot();
//		this.eatFlag();
		flag.eatFlag();	
		
		if(EnemyBlackShipManager.getBlackShipVect().size()==0
				&&EnemyBlueShipManager.getBlueShipVect().size()==0)
		{
			isFinished = true;
			isVictorious = true;
			level++;
			return;
		}
		
		//If 2 ship hit myShip at the same time
		if(skullManager.checkOverlap(myShip))
		{
			if(myShip.getHP()>1)
			{
				myShip.setHP(myShip.getHP()-1);
				Point temp2 = random();
				myShip.setXY(temp2.x,temp2.y);
				return;
			}
			else
			{
				//skullManager.add(new Skull(myShip.getX(), myShip.getY()));
				isFinished = true;
				isVictorious = false;
				return;
			}
		}
		//Update save game
		MapUpdate.getInst().updateSaveGame("mapEditorContinue.txt");
	}
	
	public static Point random()
	{
		int x=0,y=0;
		boolean test = false;
		Random rand = new Random();		
		while(!test)
		{
			x = rand.nextInt(ReadMap.size);
			y = rand.nextInt(ReadMap.size);
			GameObject temp = new GameObject(x,y);
			test=true;
			
			if(skullManager.checkOverlap(temp))
				test=false;
			if(islandManager.checkOverlap(temp))
				test=false;
			if(EnemyBlackShipManager.checkOverlap(temp))
				test=false;
			if(EnemyBlueShipManager.checkOverlap(temp))
				test=false;
			if(tornadoManager.checkOverlap(temp))
				test=false;
			if(myShip.isOverlap(temp))
				test=false;
			if(foodManager.checkOverlap(temp))
				test=false;
			if(bomb.isOverlap(temp))
				test=false;
			if(flag.isOverlap(temp))
				test=false;
		}
		Point pret= new Point(x,y);
		return pret;
	}
	
	public void reset() throws IOException {
//		inst = null;
//		EnemyBlackShipManager.getInst().reset();
//		EnemyBlueShipManager.getInst().reset();
//		SkullManager.getInst().reset();
//		IslandManager.getInst().reset();
//		FoodManager.getInst().reset();
//		myShip.setAtkRange(3);
//		myShip.setHP(MyShip.defaulHP);
//		//enemyBlackShipManager.getInst().reset();
//	//	enemyBlueShipManager.getInst().reset();
//		Point p = ReadGameWithMap.random();
//		Flag.getInst().setXY(p.x, p.y);
//		p = ReadGameWithMap.random();
//		Bomb.getInst().setXY(p.x, p.y);
//		//Score.reset();
//		isEasy=true;
//		//ReadGame.reset();
//		score.reset();
//		//myShip.getInst().reset();
//		//MyShipDrawer.getInst().reset();
				
		EnemyBlackShipManager.getInst().reset();
		EnemyBlueShipManager.getInst().reset();
		myShip.reset();
		islandManager.reset();;
		skullManager.reset();
		tornadoManager.reset();
		foodManager.reset();
		flag.reset();
		bomb.reset();
		score.reset();
		explosion = false;
		inst = null;
		//initFixedLocations();
	}
	public void nextStage()
	{
		//this.reset();
		for(int i=0;i<=level;i++)
		{
			Point ran = random();
			EnemyBlackShipManager.add(new EnemyBlackShip(ran.x, ran.y));
//			Point ran2 = random();
//			EnemyBlueShipManager.add(new EnemyBlueShip(ran2.x, ran2.y));
		}
	}
	private static Controller inst;
	public static Controller getInst() throws FileNotFoundException{
		if(inst == null)
		{
			inst = new Controller();
			inst.initFixedLocations();
//			inst.initRandomLocation();
		}
		return inst;
	}
}
