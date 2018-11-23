/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameUI;

import java.io.IOException;
import java.util.Scanner;

import gameController.Controller;
import gameModel.Point;
import mapHandler.DrawMapEditorConsole;
import mapHandler.MapEditorConsole;
import mapHandler.MapProperties;

/**
 *
 * @author Tdh4vn
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GameWindows gameWindows = new GameWindows();
        gameWindows.start();
        //gameWindows.playEndGameSound();
        //gameWindows.playBackgroundMusic();
       // if (gameWindows.shot) gameWindows.playShotSound();

        Controller.getInst();
        MapEditorConsole mapConsole=MapEditorConsole.getInst();
        DrawMapEditorConsole drawMapConsole = DrawMapEditorConsole.getInst();
        
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        Point curPoint = drawMapConsole.getCurPoint();
        char[] input = new char[10];
        input[0]='z';
        while(input[0]!='e')
        {
        	drawMapConsole.clearMap();
        	drawMapConsole.drawMap();
            
	        input = scanner.next().toCharArray();
	        drawMapConsole.setCurPointChar(99);
	        if(input[0]=='w')
	        {
	        	curPoint.y--;
	        	drawMapConsole.setCurPoint(curPoint);
	        	if(!isValid(curPoint))
	        		curPoint.y++;
	        }
	        if(input[0]=='a')
	        {
	        	curPoint.x--;
	        	drawMapConsole.setCurPoint(curPoint);
	        	if(!isValid(curPoint))
	        		curPoint.x++;
	        }
	        if(input[0]=='s')
	        {
	        	curPoint.y++;
	        	drawMapConsole.setCurPoint(curPoint);
	        	if(!isValid(curPoint))
	        		curPoint.y--;
	        }
	        if(input[0]=='d')
	        {
	        	curPoint.x++;
	        	drawMapConsole.setCurPoint(curPoint);
	        	if(!isValid(curPoint))
	        		curPoint.x--;
	        }
	        if(input[0]=='m')
	        {
	        	if(!isValid(curPoint))
	        		continue;
	        	else
	        	{
	        	int property =  mapConsole.getBlock(curPoint);
	        	
	        	if(property<MapProperties.FOOD)
	        		property++;
	        	else
	        		property=MapProperties.BLANK;

	        	drawMapConsole.setCurPointChar(property);
	        	mapConsole.setBlock(curPoint,property);
	        	}
	        }
	        if(input[0]=='e')
	        {
	        	mapConsole.outputMap("MapGen.txt");
	        	System.out.println("Map generated");
	        }
//	        mapConsole.drawMap();
        }
    }
    private static boolean isValid(Point p)
    {
    	boolean valid = true;
    	if(p.x<0||p.y<0||p.x>DrawMapEditorConsole.getInst().size-1||p.y>DrawMapEditorConsole.getInst().size-1)
    		valid = false;
    	return valid;
    }
}
