package mapHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import gameController.Controller;
import gameController.Score;
import gameModel.*;

public class MapUpdate {
	
	static int size ; 
	static int[][] map;

	private static MyShip myShip;
	private static IslandManager islandManager;
	private static SkullManager skullManager;
	private static TornadoManager tornadoManager;
	private static CannonManager foodManager;
	private static Flag flag;
	private static Bomb bomb;
	
	private MapUpdate()
	{
		myShip = MyShip.getInst();
		EnemyBlackShipManager.getInst();
		EnemyBlueShipManager.getInst();
		islandManager = IslandManager.getInst();
		skullManager = SkullManager.getInst();
		tornadoManager = TornadoManager.getInst();
		foodManager = CannonManager.getInst();
		flag = Flag.getInst();
		bomb = Bomb.getInst();
		
		size = ReadMap.size;
		map = new int[size+2][size+2];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				map[i][j]=MapProperties.BLANK;
	}
	private static MapUpdate inst;
	public static MapUpdate getInst(){
		if(inst == null)
		{
			inst = new MapUpdate();
		}
		return inst;
	}
	
	public void setBlock(int x,int y, int blockProperty)
	{
		map[y][x]=blockProperty;
	}
	
	public int getBlock(int x,int y)
	{
		return map[y][x];
	}
	
	private void inputMap()
	{
		//clear screen
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				map[i][j]=MapProperties.BLANK;
		
		map[myShip.getY()][myShip.getX()]=MapProperties.MYSHIP;
		map[flag.getY()][flag.getX()]=MapProperties.FLAG;
		map[bomb.getY()][bomb.getX()]=MapProperties.BOMB;
		
		Vector<EnemyBlackShip> enemyBlackShips = EnemyBlackShipManager.getBlackShipVect();
		Vector<EnemyBlueShip> enemyBlueShips = EnemyBlueShipManager.getBlueShipVect();
		Vector<Island> island = islandManager.getIslandVect();
		Vector<Skull> skull = skullManager.getSkullVect();
		Vector<Tornado> Tornado = tornadoManager.getTornadoVect();
		Vector<Cannon> food = foodManager.getFoodVect();
		
		
		for(EnemyBlackShip obj : enemyBlackShips)
			map[obj.getY()][obj.getX()]=MapProperties.BLACKSHIP;
		for(EnemyBlueShip obj : enemyBlueShips) 
			map[obj.getY()][obj.getX()]=MapProperties.BLUESHIP;
		for(Island obj : island)
			map[obj.getY()][obj.getX()]=MapProperties.ISLAND;
		for(Skull obj : skull)
			map[obj.getY()][obj.getX()]=MapProperties.SKULL;
		for(Tornado obj : Tornado)
			map[obj.getY()][obj.getX()]=MapProperties.TORNADO;
		for(Cannon obj : food)
			map[obj.getY()][obj.getX()]=MapProperties.FOOD;
	}
	
	private int directionToInt(Direction dir) {
		if (dir == Direction.UP) return 0;
		if (dir == Direction.DOWN) return 1;
		if (dir == Direction.LEFT) return 2;
		if (dir == Direction.RIGHT) return 3;
		if (dir == Direction.UPLEFT) return 4;
		if (dir == Direction.UPRIGHT) return 5;
		if (dir == Direction.DOWNLEFT) return 6;
		if (dir == Direction.DOWNRIGHT) return 7;
		return 8;
	}
	
	private void outputMap(String Name)
	{
		//if(ReadGameWithMap.isEmpty("mapEditor.txt"));
		String fileName = Name;
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            

            for(int i=0;i<size;i++)
            {
            	for(int j=0;j<size;j++)
            	{
            		bufferedWriter.write(Integer.toString(map[i][j]));
            	}
                bufferedWriter.newLine();
            }
          //  System.out.println(directionToInt(MyShip.getInst().getAxis()));
            bufferedWriter.write(Integer.toString(directionToInt(MyShip.getInst().getAxis()))); 
            bufferedWriter.newLine();
            for (int i=0;i<size;i++) {
            	for (int j = 0;j<size;j++) {
            		if (map[i][j] == MapProperties.BLUESHIP) {
            			//System.out.println(i + ", " + j);
            			for (EnemyBlueShip obj : EnemyBlueShipManager.getBlueShipVect()) {
            				//System.out.println("obj" + obj.getX() + ", " + obj.getY());
            				if (obj.getX()==j && obj.getY()==i){
            					//System.out.println(obj.getAxis());
            					bufferedWriter.write(Integer.toString(directionToInt(obj.getAxis())));
            				}
            			}
            		}
            	}
            }
            bufferedWriter.newLine();
            for (int i=0;i<size;i++) {
            	for (int j = 0;j<size;j++) {
            		if (map[i][j] == MapProperties.BLACKSHIP) {
            			//System.out.println(i + ", " + j);
            			for (EnemyBlackShip obj : EnemyBlackShipManager.getBlackShipVect()) {
            				//System.out.println("obj" + obj.getX() + ", " + obj.getY());
            				if (obj.getX()==j && obj.getY()==i){
            					//System.out.println(obj.getAxis());
            					bufferedWriter.write(Integer.toString(directionToInt(obj.getAxis())));
            				}
            			}
            		}
            	}
            }
            bufferedWriter.newLine();
            //System.out.println(Score.getScore());
            bufferedWriter.write(Integer.toString(Score.getScore()));
            bufferedWriter.newLine();
            if (Controller.isEasy) bufferedWriter.write("EASY");
            else bufferedWriter.write("HARD");
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}
	public void updateSaveGame(String Name)
	{
		this.inputMap();
		this.outputMap(Name);
	}
}
