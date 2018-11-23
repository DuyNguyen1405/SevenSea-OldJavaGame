package mapHandler;
import java.io.*;

import gameController.Controller;
import gameController.Score;
import gameModel.*;

public class ReadMap {
	public static int size = 11;
	private static MyShip myShip;
	private static EnemyBlackShipManager enemyBlackShipManager;
	private static EnemyBlueShipManager enemyBlueShipManager;
	private static IslandManager islandManager;
	private static SkullManager skullManager;
	private static TornadoManager tornadoManager;
	private static CannonManager foodManager;
	private static Flag flag;
	private static Bomb bomb;
	
	
	private ReadMap() {
		myShip = MyShip.getInst();
		flag = Flag.getInst();
		bomb = Bomb.getInst();
		Score.getInst();
		enemyBlackShipManager = EnemyBlackShipManager.getInst();
		enemyBlueShipManager = EnemyBlueShipManager.getInst();
		islandManager = IslandManager.getInst();
		skullManager = SkullManager.getInst();
		tornadoManager = TornadoManager.getInst();
		foodManager = CannonManager.getInst();
//		size = 11;
	}
	private static ReadMap inst;
	public static ReadMap getInst(){
		if(inst == null)
		{
			inst = new ReadMap();
		}
		return inst;
	}
	public static void reset()
	{
		inst=null;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		ReadMap.size = size;
	}
	
	public static Direction charToDirection(char c) {
		switch (c) {
		case '0':
			return (Direction.UP);
		case '1':
			return (Direction.DOWN);
		case '2':
			return(Direction.LEFT);
		case '3':
			return (Direction.RIGHT);
		case '4':
			return (Direction.UPLEFT);
		case '5':
			return (Direction.UPRIGHT);
		case '6':
			return (Direction.DOWNLEFT);
		case '7':
			return (Direction.DOWNRIGHT);
		default: return (Direction.RIGHT);
		}
	}
    //public static void readGame(boolean hasLeftOver, boolean isEasy) {
	public static void readGame(String fileName) {
    	
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader;
//            if (hasLeftOver)
//                fileReader = new FileReader("mapEditor.txt");
//            else if (isEasy) 
//            	fileReader = new FileReader("easy.txt");
//            else fileReader = new FileReader("hard.txt");
            fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            //Read Properties
            for(int lineIndex=0;lineIndex<size;lineIndex++)
            //while((line = bufferedReader.readLine()) != null) 
            {
            	line = bufferedReader.readLine();
                char[] temp = new char[11];
                for(int i=0;i<11;i++)
                	temp[i] = ' ';
                
                temp = line.toCharArray();
//            	char[] temp = new char[11];
//            	bufferedReader.read(temp);
//                System.out.println(line);
                
                for(int i=0;i<size;i++)
                	if((temp[i]-48)!=MapProperties.BLANK)
                	{
                		switch(temp[i]-48)
                		{
                		case MapProperties.MYSHIP:
                			myShip.setX(i);
                			myShip.setY(lineIndex);
//                			System.out.println("read myship");
                			break;
                		case MapProperties.FLAG:
                			flag.setX(i);
                			flag.setY(lineIndex);
//                			System.out.println("read flag");
                			break;
                		case MapProperties.BOMB:
                			bomb.setX(i);
                			bomb.setY(lineIndex);
//                			System.out.println("read bomb");
                			break;
                		case MapProperties.BLACKSHIP:
                			//System.out.println(i + ", " + lineIndex); 
                			enemyBlackShipManager.add(new EnemyBlackShip(i, lineIndex));
//                			System.out.println("read blackship");
                			break;
                		case MapProperties.BLUESHIP:
                			enemyBlueShipManager.add(new EnemyBlueShip(i, lineIndex));
//                			System.out.println("read blue ship");
                			break;
                		case MapProperties.ISLAND:
                			islandManager.add(new Island(i, lineIndex));
//                			System.out.println("read island");
                			break;
                		case MapProperties.SKULL:
                			skullManager.add(new Skull(i, lineIndex));
//                			System.out.println("read skull");
                			break;
                		case MapProperties.TORNADO:
                			tornadoManager.add(new Tornado(i, lineIndex));
//                			System.out.println("read tornado");
                			break;
                		case MapProperties.FOOD:
                			foodManager.add(new Cannon(i, lineIndex));
//                			System.out.println("read food");
                			break;
                		}
//                    	System.out.print(temp[i]+" ");
                	}
//                System.out.println();
//                lineIndex++;
            }     
            line = bufferedReader.readLine();
            char c = line.charAt(0);
            myShip.setAxis(charToDirection(c));
            line = bufferedReader.readLine();
            int index = 0;
            for (EnemyBlueShip obj : EnemyBlueShipManager.getBlueShipVect()) {
            	c = line.charAt(index);
            	index++;
            	obj.setAxis(charToDirection(c));
            }
            line = bufferedReader.readLine();
            index = 0;
            //System.out.println(line);
           // System.out.println(EnemyBlackShipManager.getEnemyShipVect().size());
            for (EnemyBlackShip obj : EnemyBlackShipManager.getBlackShipVect()) {
            	c = line.charAt(index);
            	index++;
            	obj.setAxis(charToDirection(c));
            }
            line = bufferedReader.readLine();
            int score = Integer.parseInt(line);
            Score.setScore(score);
            line = bufferedReader.readLine();
            if (line == "EASY") Controller.isEasy = true;
            else Controller.isEasy = false;
//            //System.out.println(c);
            // Always close files.
            bufferedReader.close();           
        }     

        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}

