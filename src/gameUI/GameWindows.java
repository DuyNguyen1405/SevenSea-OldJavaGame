/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameUI;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import gameController.*;
import gameController.Score;
import gameModel.*;
import gameUI.AboutWindow;
import mapHandler.ReadMap;
import gameDrawer.MyShipDrawer;
import gameDrawer.RangeDrawerManager;
import gameDrawer.SkullDrawerManager;
import gameDrawer.BlackShipDrawerManager;
import gameDrawer.BlueShipDrawerManager;
import gameDrawer.*;
import gameDrawer.BombDrawerManager;
import gameDrawer.EnemyShipDrawerManager;
import gameDrawer.FoodDrawerManager;
import gameDrawer.HPDrawerManager;
import gameDrawer.IslandDrawerManager;
import gameDrawer.TornadoDrawerManager;
import gameDrawer.WinFlagDrawerManager;

//ImageIO.read(new File("Resouces/Hunter.png"));
/**
 *
 * @author Tdh4vn
 */
public class GameWindows extends Frame implements Runnable{
    
	private float timer1 = 0.0f;
    private Image image;
    private Graphics second;
    private int size;
    private BufferedImage square;
    private BufferedImage win;
    private BufferedImage lose;
    private BufferedImage gameover;
    private BufferedImage newgame;
    private BufferedImage title;
    private BufferedImage backdrop;
    private MyShipDrawer msd;
    private TornadoDrawerManager tdm;
    private SkullDrawerManager sdm;
    private BlackShipDrawerManager bdm;
    private BlueShipDrawerManager bldm;
    private IslandDrawerManager idm;
    private FoodDrawerManager fdm;
    private RangeDrawerManager rdm;
    private MenuBar mbar;
    private Menu menuGame, menuSound, menuHighScore, menuHelp, menuNewGame, menuME;
    private MenuItem m1,m2,m3,m4,m5,m8, m_easy, m_hard, m_editor, m_continue;
    private CheckboxMenuItem m6, m7;
    protected boolean shot;
    protected boolean isMute;
    //private boolean isPrevKeySpace;
    private boolean isInGame;
    private boolean wantToQuit;
    String audioFilePath;
    AudioPlayerExample1 player;
    public static final int OFFSET = 31;
    public static final int SIZEOFSQUARE = 50;
    private Controller controller;
    
    public GameWindows() throws IOException{
    	super();
    	gameover = ImageIO.read(new File("Resources/gameover.gif"));
    	win = ImageIO.read(new File("Resources/win.png"));
    	lose = ImageIO.read(new File("Resources/lose.png"));
    	square = ImageIO.read(new File("Resources/square.jpg"));
    	newgame = ImageIO.read(new File("Resources/newgame.png"));
    	title = ImageIO.read(new File("Resources/title.jpg"));
    	backdrop = ImageIO.read(new File("Resources/backdrop.jpg"));
        size = ReadMap.size;
        controller = Controller.getInst();
        msd = MyShipDrawer.getInst();
        tdm = TornadoDrawerManager.getInst();
        sdm = SkullDrawerManager.getInst();
        bdm = BlackShipDrawerManager.getInst();
        bldm = BlueShipDrawerManager.getInst();
        idm = IslandDrawerManager.getInst();
        fdm = FoodDrawerManager.getInst();
        rdm = RangeDrawerManager.getInst();
     //   isPrevKeySpace = false;
        shot = false;
        isInGame = false;
        wantToQuit = false;
        isMute = false;
        audioFilePath = "Resources/sounds/background.wav";
    	player = null;
		try {
			player = new AudioPlayerExample1(audioFilePath);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.setTitle("Seven Seas ver 1.0");
        this.setFocusable(true);
        this.setSize(570+209, 620);
        this.setVisible(true);
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
				case KeyEvent.VK_W:
					try {
						Controller.getInst().move(Direction.UP);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Controller.explosion) playShotSound();
		        	try {
						if (!isMute) playEndGameSound();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					shot = false;
					break;
				case KeyEvent.VK_X:
					try {
						Controller.getInst().move(Direction.DOWN);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Controller.explosion) playShotSound();
		        	try {
						if (!isMute) playEndGameSound();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					shot = false;
					break;
				case KeyEvent.VK_A:
					try {
						Controller.getInst().move(Direction.LEFT);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Controller.explosion) playShotSound();
		        	try {
						if (!isMute) playEndGameSound();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					shot = false;
					break;
				case KeyEvent.VK_D:
					try {
						Controller.getInst().move(Direction.RIGHT);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Controller.explosion) playShotSound();
		        	try {
						if (!isMute) playEndGameSound();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					shot = false;
					break;
				case KeyEvent.VK_Q:
					try {
						Controller.getInst().move(Direction.UPLEFT);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Controller.explosion) playShotSound();
		        	try {
						if (!isMute) playEndGameSound();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					shot = false;
					break;
				case KeyEvent.VK_E:
					try {
						Controller.getInst().move(Direction.UPRIGHT);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Controller.explosion) playShotSound();
		        	try {
						if (!isMute) playEndGameSound();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					shot = false;
					break;
				case KeyEvent.VK_Z:
					try {
						Controller.getInst().move(Direction.DOWNLEFT);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Controller.explosion) playShotSound();
		        	try {
						if (!isMute) playEndGameSound();
					} catch (FileNotFoundException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					shot = false;
					break;
				case KeyEvent.VK_C:
					try {
						Controller.getInst().move(Direction.DOWNRIGHT);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Controller.explosion) playShotSound();
		        	try {
						if (!isMute) playEndGameSound();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					shot = false;
					break;
				case KeyEvent.VK_SPACE:
					try {
						Controller.getInst().shot();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (!isMute) playShotSound();
		        	try {
						if (!isMute) playEndGameSound();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;

				default:
					break;
				}
				// TODO Auto-generated method stub
				
			}
		});
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(java.awt.event.WindowEvent e) {
//            	SaveGame.getInst();
//				SaveGame.PrintOut();
                System.exit(0);
            }
        });
        
        mbar = new MenuBar();
        
        menuGame = new Menu("Game");
        menuSound = new Menu("Sound");
        menuNewGame = new Menu("New Game");
        menuHelp = new Menu("Help");
        menuME = new Menu("Tools");
        
     // Create MenuItems
        m_easy=new MenuItem("Easy");
        m_hard=new MenuItem("Hard");
        m2=new MenuItem("Continue");
        m3=new MenuItem("Quit");
        
        m4=new MenuItem("High Score");
        
        m5=new MenuItem("Help");
        m8=new MenuItem("About");
        
        m6=new CheckboxMenuItem("Sound On",true);
        m7=new CheckboxMenuItem("Sound Off");
        m_editor = new MenuItem("Map Editor");
        // Attach menu items to menu
        menuNewGame.add(m_easy);
        menuNewGame.add(m_hard);
        menuGame.add(menuNewGame);
        menuGame.add(m2);
        menuGame.addSeparator();
        menuGame.add(m4);
        menuGame.addSeparator();
        menuGame.add(m3);
        
        // Attach menu items to submenu
        menuSound.add(m6);
        menuSound.add(m7);
        
        menuHelp.add(m5);
        menuHelp.add(m8);
        menuME.add(m_editor);
        
        
        // Attach menu to menu bar
        mbar.add(menuGame);
        mbar.add(menuSound);
        mbar.add(menuME);
        mbar.add(menuHelp);

        
        // Set menu bar to the frame
        setMenuBar(mbar);
        m4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new HighScoreWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		});
        
        m_editor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new MapEditorWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		});
        m_easy.addActionListener(new ActionListener() {
			
        	@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					File file = new File("mapEditor.txt");
//					file.delete();
//					Controller.getInst();
					Controller.getInst().reset();
					Controller.hasSaveGame=false;
					
					Score.getInst().setTopTen();
					stopBackgroundMusic();
					isInGame=true;
					//System.out.println("Hard");
					Controller.isEasy = true;
					Controller.getInst().setFinished(false);
			        if (!isMute) playBackgroundMusic();
			        //MapEditor.getInst().updateSaveGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		});
        m_hard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					File file = new File("mapEditor.txt");
//					file.delete();
//					Controller.getInst();
					Controller.hasSaveGame=false;
					
					Score.getInst().setTopTen();
					stopBackgroundMusic();
					isInGame=true;
					Controller.getInst().reset();
					//System.out.println("Hard");
					Controller.isEasy = false;
					Controller.getInst().setFinished(false);
			        if (!isMute) playBackgroundMusic();
			        //MapEditor.getInst().updateSaveGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		});
        m3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				SaveGame.getInst();
//				SaveGame.PrintOut();
				System.exit(0);
				// TODO Auto-generated method stub
				
			}
		});
		m2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				File file = new File("mapEditor.txt");
//				file.delete();
//				Controller.getInst();
			
				try 
				{
				Controller.hasSaveGame=true;
				
				Score.getInst().setTopTen();
				stopBackgroundMusic();
				isInGame=true;
				Controller.getInst().reset();
				} 
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//System.out.println("Hard");
				Controller.isEasy = true;
		        if (!isMute) playBackgroundMusic();
			}
		});
        m8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new AboutWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		});
        m5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new HelpWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		});
        m6.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				isMute = false;
				m7.setState(false);
				playBackgroundMusic();
				// TODO Auto-generated method stub
				
			}
		});
        m7.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				isMute = true;
				m6.setState(false);
				stopBackgroundMusic();
				// TODO Auto-generated method stub
				
			}
		});
    }
    


    @Override
    public void update(Graphics g){
        if(image == null){
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }
        
        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);
        
        g.drawImage(image, 0, 0, null);  
    }
    
    @Override
    public void paint(Graphics g){
    	
    	
    	
    	
        for(int i = 0; i < size; i++){
        	for(int j = 0; j < size; j++){
           	 	g.drawImage(square, i * SIZEOFSQUARE + OFFSET - 20
           	 				, j * SIZEOFSQUARE + 27 + OFFSET, this);
            } 
        }
        try {
        	g.drawImage(backdrop, 565, 50, null);    	
        	//System.out.println(getSize().getWidth() + ", " + getSize().getHeight());
        	//MyShipDrawer.getInst().draw(g);
        	RangeDrawerManager.getInst().draw(g);
        	BlackShipDrawerManager.getInst().draw(g);
        	BlueShipRangeDrawerManager.getInst().draw(g);
        	MyShipDrawer.getInst().draw(g);
        	BlueShipDrawerManager.getInst().draw(g);
       // 	CrosshairDrawer.getInst().draw(g, size, isPrevKeySpace);
        	//ExplosionsDrawer.getInst().draw(g);
        	SkullDrawerManager.getInst().draw(g);
        	IslandDrawerManager.getInst().draw(g);
        	WinFlagDrawerManager.getInst().draw(g);
        	BombDrawerManager.getInst().draw(g);
        	FoodDrawerManager.getInst().draw(g);
        	TornadoDrawerManager.getInst().draw(g);
        	//RangeDrawerManager.getInst().draw(g);
        	HPDrawerManager.getInst().draw(g);
        	
        	Graphics2D g2d = (Graphics2D)g;
        	g2d.setColor(Color.yellow);
        	g2d.setFont(new Font("Cambria", Font.BOLD, 32));
        	StringBuilder sb = new StringBuilder();
        	sb.append("");
        	sb.append(Score.getScore());
        	String strI = sb.toString();
        	g2d.drawString(strI, 650, 130+27);
        	if (Controller.isEasy) g2d.drawString("EASY", 637, 470);
        	else g2d.drawString("HARD", 637, 470);
        	
        	//if (shot) playShotSound();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			if(Controller.getInst().isFinished()){
				g.drawImage(gameover, 198-39, 250-19, this);
				if(Controller.getInst().isVictorious()){
					g.drawImage(win, 175-39, 370-19, this);
				} else {
					g.drawImage(lose, 175-39, 370-19, this);
				}
			//	g.drawImage(newgame, 175-100-39, 420-19, this);
//				SaveGame.getInst();
//				SaveGame.PrintOut();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (!isInGame) g.drawImage(title, 0, 30, 570+209, 590, this);
    }
    @Override
    public void run() {
        while(true){
           
            repaint();
            try{
                Thread.sleep(17);
                timer1 += 17;
				if(timer1 > 100){
					timer1 = 0.0f;
					fdm.update();
					msd.update();
					tdm.update();
					//bdm.update();
					//bldm.update();
					//ed.update();
					sdm.update();
					idm.update();
				}
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
     public void start(){
        Thread mainThread = new Thread(this);
        mainThread.start();
    }     
     
     public void playBackgroundMusic() {
    	 audioFilePath = "Resources/sounds/background.wav";
     	 player = null;
 		 try {
 			player = new AudioPlayerExample1(audioFilePath);
 		 } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
 				e.printStackTrace();
 		  }
 		    player.loop();
     }
    	 
     
     public void stopBackgroundMusic() {
 		    player.stop();
     }
     
     public void playShotSound() {
    	 
    	 Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				String audioFilePath = "Resources/sounds/explode2.wav";
		    	AudioPlayerExample1 player = null;
				try {
					player = new AudioPlayerExample1(audioFilePath);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
		    	 player.play();
			}
		});
    	 
    	 thread.start();

     }
     public void playEndGameSound() throws FileNotFoundException {
    	 if (!Controller.getInst().isFinished()) return;
    	 player.stop();
    	 
    	 Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				boolean win = false;
				try {
					win = Controller.getInst().isVictorious();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String audioFilePath = null;
				if (win) audioFilePath = "Resources/sounds/win.wav";
				else audioFilePath = "Resources/sounds/lose.wav";
		    	AudioPlayerExample1 player = null;
				try {
					player = new AudioPlayerExample1(audioFilePath);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
		    	 player.play();
			}
		});
    	 
    	 thread.start();

     }
}
