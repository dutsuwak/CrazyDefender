package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import logic.ReadProperties;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	//dimensiones que son leidas desde el archivo config.properties
	public static final int WIDTH = Integer.parseInt(ReadProperties.file.getSetting("width"));
	public static final int HEIGHT = Integer.parseInt(ReadProperties.file.getSetting("height"));
	public static final int SCALE =Integer.parseInt(ReadProperties.file.getSetting("scale"));
	
	//Thread para el Juego
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	//Imagen
	private BufferedImage image;
	private Graphics2D g;
	private GameStateManager gsm;
	
	
	//Game State Manager (Main)
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
		
	}
	public void addNotify(){
		super.addNotify();
		if (thread == null){
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	private void init(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager(0, 0);
	}
	public void run(){
		init();
		//
		long elapsed;
		long wait;
		long start;
		
		//loop del Juego
		while(running){
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime()-start;
			
			wait = targetTime - elapsed / 1000000; //Porque esta en ms y ns.
			if (wait < 0) wait = 5;
			try{
				Thread.sleep(wait);
			}
			
			catch(Exception e){
				e.printStackTrace(); // Para detectar Posibles Errores
				
			}
		}
	}
	
	private void update() {
		gsm.update();
	}
	private void draw(){
		gsm.draw(g);
	}
	private void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,WIDTH * SCALE, HEIGHT * SCALE,null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key){}
	public void keyPressed(KeyEvent key){
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key){
		gsm.keyReleased(key.getKeyCode());
	}
	

}
