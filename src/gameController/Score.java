package gameController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Score {
	static int score;
	static String name;
	int[] topTen = new int[10];
	private Score()
	{
		score=0; 
		String line = null;
		String fileName="topScore.txt";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for(int i=0;i<10;i++)
            {
            	line = bufferedReader.readLine();
            	topTen[i]=Integer.parseInt(line);
            }

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
	
	private static Score inst;
	public static Score getInst()
	{
		if(inst == null)
		{
			inst = new Score();
		}
		return inst;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Score.name = name;
	}

	public void addScore(int add)
	{
		Score.score+=add;
	}
	public void minusScore(int minus)
	{
		Score.score-=minus;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Score.score = score;
	}
	public void reset()
	{
//		inst = null;
		score=0;
	}
	
	public int getTopTen(int index)
	{
		return topTen[index];
	}
	
	public void setTopTen()
	{
		if(score<=topTen[9])
			return;
		//System.out.println(score);
		
		topTen[9]=score;
		
		//Sorting top10 : insertion sort
		for(int i=0;i<10;i++)
			for(int j=i+1;j<10;j++)
			{
				if(topTen[i]<topTen[j])
				{
					int temp = topTen[i];
					topTen[i]=topTen[j];
					topTen[j] = temp ;
				}
			}
		saveTopten();
	}
	
	private void saveTopten()
	{
		String fileName = "topScore.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.

            for(int i=0;i<10;i++)
            {
                bufferedWriter.write(Integer.toString(topTen[i]));
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
