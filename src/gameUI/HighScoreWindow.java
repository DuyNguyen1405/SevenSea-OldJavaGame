package gameUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameController.Score;

public class HighScoreWindow extends Frame{
	private BufferedImage help;
	
	public HighScoreWindow() throws IOException{
		help = ImageIO.read(new File("Resources/highscorepanel.gif"));
		this.setTitle("HighScore");
		this.setSize(415, 210);
		this.setVisible(true);
		//setd
		this.addWindowListener(new WindowAdapter(){
	        public void windowClosing(java.awt.event.WindowEvent e) {
	            dispose();
	        }
	    });
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
    	
		g.drawImage(help, 10, 30, null);
		Graphics2D g2d = (Graphics2D)g;
    	g2d.setColor(Color.BLACK);
    	g2d.setFont(new Font("Cambria", Font.BOLD, 50));
    	StringBuilder sb = new StringBuilder();
    	sb.append("");
    	sb.append(Score.getInst().getTopTen(0));
    	//System.out.println(Score.getInst().getTopTen(0));
    	String strI = sb.toString();
    	//System.out.println(strI);
    	//g2d.drawString(strI, 0, 0);
    	g2d.drawString(strI, 180, 140);
	}
	
}
