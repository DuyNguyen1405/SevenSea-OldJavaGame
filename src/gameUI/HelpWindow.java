package gameUI;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HelpWindow extends Frame{
	private BufferedImage help;
	
	public HelpWindow() throws IOException{
		help = ImageIO.read(new File("Resources/Roll8Directions.jpg"));
		this.setTitle("How To Play");
		this.setSize(760, 425);
		this.setVisible(true);
		this.setBounds(570, 0, 760, 590);
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
		g.drawImage(help, 0, 100, null);
	}
	
}
