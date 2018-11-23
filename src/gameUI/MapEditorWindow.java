package gameUI;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapEditorWindow extends Frame{
	private int x,y;
	private BufferedImage sprite, square;
    public static final int OFFSET = 31;
    public static final int SIZEOFSQUARE = 50;
	
	public MapEditorWindow() throws IOException{
		x = 0;
		y = 0;
		square = ImageIO.read(new File("Resources/square.jpg"));
		sprite = ImageIO.read(new File("Resources/square_range_enemy.jpg"));
		this.setTitle("Map Editor");
		this.setSize(570, 590);
		this.setVisible(true);
		//setd
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					
					break;

				default:
					break;
				}
				// TODO Auto-generated method stub
				
			}
		});
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
		for(int i = 0; i < 11; i++){
        	for(int j = 0; j < 11; j++){
           	 	g.drawImage(square, i * SIZEOFSQUARE + OFFSET - 20
           	 				, j * SIZEOFSQUARE + OFFSET, this);
            } 
        }
	}
	
}
