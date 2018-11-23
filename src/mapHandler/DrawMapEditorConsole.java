package mapHandler;

import gameModel.Point;

public class DrawMapEditorConsole {

	public int size ;
	int[][] consoleMap;
	char[][] map;
	Point curPoint;
	char curPointChar;
	private DrawMapEditorConsole()
	{
		size = ReadMap.size;
		//size=11;
		consoleMap = MapEditorConsole.map2;
		map = new char[size][size];
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				map[i][j]=' ';
			}
		}
		curPointChar='O';
		map[0][0]=curPointChar;
		curPoint= new Point(0,0);
	}
	public Point getCurPoint() {
		return curPoint;
	}
	public void setCurPoint(Point curPoint) {
		this.curPoint = curPoint;
	}
	public void setCurPointChar(int property) {
		switch(property)
		{
		case MapProperties.BLACKSHIP:
			curPointChar='B';
			break;
		case MapProperties.BLANK:
			curPointChar=' ';
			break;
		case MapProperties.BLUESHIP:
			curPointChar='b';
			break;
		case MapProperties.BOMB:
			curPointChar='X';
			break;
		case MapProperties.FLAG:
			curPointChar='F';
			break;
		case MapProperties.FOOD:
			curPointChar='f';
			break;
		case MapProperties.ISLAND:
			curPointChar='I';
			break;
		case MapProperties.MYSHIP:
			curPointChar='M';
			break;
		case MapProperties.SKULL:
			curPointChar='S';
			break;
		case MapProperties.TORNADO:
			curPointChar='T';
			break;
		case 99:
			curPointChar='O';
			break;
		}
	}
	private static DrawMapEditorConsole inst;
	public static DrawMapEditorConsole getInst(){
		if(inst == null)
		{
			inst = new DrawMapEditorConsole();
		}
		return inst;
	}
	public void drawMap()
	{
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				switch(consoleMap[i][j])
				{
				case MapProperties.BLACKSHIP:
					map[i][j]='B';
					break;
				case MapProperties.BLANK:
					map[i][j]=' ';
					break;
				case MapProperties.BLUESHIP:
					map[i][j]='b';
					break;
				case MapProperties.BOMB:
					map[i][j]='X';
					break;
				case MapProperties.FLAG:
					map[i][j]='F';
					break;
				case MapProperties.FOOD:
					map[i][j]='f';
					break;
				case MapProperties.ISLAND:
					map[i][j]='I';
					break;
				case MapProperties.MYSHIP:
					map[i][j]='M';
					break;
				case MapProperties.SKULL:
					map[i][j]='S';
					break;
				case MapProperties.TORNADO:
					map[i][j]='T';
					break;
				}
			}
		}
		map[curPoint.y][curPoint.x]=curPointChar;
		for(int i=0;i<2*size+1;i++)
			System.out.print("-");
		System.out.println();
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				System.out.print("|"+map[i][j]);
			}
			System.out.println("|");
			for(int k=0;k<2*size+1;k++)
				System.out.print("-");
			System.out.println();
		}
				
	}

	public void clearMap()
	{
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				map[i][j]=' ';
			}
		}
		map[0][0]=curPointChar;
	}
}
