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

public class AboutWindow extends Frame{
	//private BufferedImage help;
	
	public AboutWindow() throws IOException{
		//help = ImageIO.read(new File("Resources/Roll8Directions.jpg"));
		this.setTitle("About");
		this.setSize(500, 500);
		this.setVisible(true);
		this.setBounds(0, 0, 500, 500);
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
		//g.drawImage(help, 0, 100, null);
    	Graphics2D g2d = (Graphics2D)g;
    	g2d.setColor(Color.BLACK);
    	g2d.setFont(new Font("Cambria", Font.BOLD, 32));
    	g2d.drawString("SevenSeas ver1.0", 120, 100);
    	g2d.setFont(new Font("Cambria", Font.BOLD, 24));
    	g2d.drawString("Team 3", 200, 150);
    	g2d.setFont(new Font("Cambria", Font.BOLD, 18));
    	g2d.drawString("Dương Nhật Thắng", 165, 180);
    	g2d.drawString("Vũ Hữu Sơn", 190, 200);
    	g2d.drawString("Vũ Duy Khánh", 180, 220);
	}
	
}
