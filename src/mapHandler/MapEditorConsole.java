package mapHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import gameModel.*;

public class MapEditorConsole {
	
	static int size ; 
	static int[][] map2;
	
	private MapEditorConsole()
	{
		EnemyBlackShipManager.getInst();
		EnemyBlueShipManager.getInst();
		
		size = ReadMap.size;
		//map = new int[size][size];
		map2 = new int[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				{
				//map[i][j]=MapProperties.BLANK;
				map2[i][j]=MapProperties.BLANK;
				}
	}
	private static MapEditorConsole inst;
	public static MapEditorConsole getInst(){
		if(inst == null)
		{
			inst = new MapEditorConsole();
		}
		return inst;
	}
	
	public void setBlock(Point curPoint, int blockProperty)
	{
		map2[curPoint.y][curPoint.x]=blockProperty;
	}
	
	public int getBlock(Point curPoint)
	{
		return map2[curPoint.y][curPoint.x];
	}
	
	public void outputMap(String mapEdited)
	{
		//if(ReadGameWithMap.isEmpty("mapEditor.txt"));
		String fileName = mapEdited;
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
            		bufferedWriter.write(Integer.toString(map2[i][j]));
            	}
                bufferedWriter.newLine();
            }
 
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
}
