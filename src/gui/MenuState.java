package gui;

//import gnu.io.SerialPortEventListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import logic.Background;
import logic.ReadProperties;
import logic.SoundGame;
import logic.SoundMenu;

public class MenuState extends GameState{
	
	public static String playername;
	private int InHealth;
	//private static final String bg_src = Game.getSetting("imgMenu");
	private Background bg;
	private int currentChoice = 0;
	private String[] options = {
			"Start",
			"Profile",
			"HighScores",
			"Help",
			"About",
			"Quit",
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	//SerialPortEventListener evento;
	
	public MenuState(GameStateManager gsm, int inhealth, int inscore){
		
		this.gsm = gsm;
		init(inhealth,inscore);
		try{
			playername="Player";
			
			//Instacia el Objeto de la Musica del Menu Principal
			SoundMenu.getInstance();
			SoundMenu.music.start();
	
			
			bg = new Background(ReadProperties.file.getSetting("imgmenu"),1);
			bg.setVector(0.2,0);//Movimiento a la derecha
			
			
			titleColor = new Color(0,0,255);
			titleFont = new Font("Century Gothic", Font.PLAIN, 18);
			font = new Font("Arial",Font.PLAIN, 12);
			
		}
		catch(NullPointerException e){
			e.printStackTrace(); //Para detectar algún posible error
		}
	}
	
	public void init(int inhealth, int inscore){
		InHealth = 5;
	}
	public void update(){
		bg.update();
	}

	public void draw(Graphics2D g){
		
		//Dibujar Fondo
		bg.draw(g);
		//Dibujar Texto del Titulo de Juego
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Space Invaders: Crazy Defender", 16, 30);
		//Dibujar Opciones de Menu
		g.setFont(font);
		for (int i=0; i < options.length; i++){
			if (i==currentChoice){
				g.setColor(Color.green);
			}
			else{
				g.setColor(Color.red);
			}
			g.drawString(options[i],25,100 + i * 15);
		}
		
	}
	private void select(){
		if (currentChoice == 0){
			//Iniciar Juego
			SoundMenu.music.stop(); //Detiene la Música
			SoundGame.getInstance();
			SoundGame.music.start();
			gsm.setState(GameStateManager.LEVEL1STATE,InHealth,0);
			
		}
		if (currentChoice == 1){
			playername = JOptionPane.showInputDialog("Enter your name");
			
		}
		if (currentChoice == 2){
			gsm.setState(GameStateManager.SCORESSTATE, 5, 0);
		}
		if (currentChoice == 3){
			//Ayuda

		}
		if (currentChoice ==4){
			//About
			gsm.setState(GameStateManager.ABOUTSTATE,0,0);
		}
		if(currentChoice ==5){
			//Salir
			System.exit(0);
		}
	}
	public void keyPressed(int k){
		if (k == KeyEvent.VK_ENTER){
			select();
		}
		if (k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length -1;}
		}
		if (k == KeyEvent.VK_DOWN){
			currentChoice++;
			if (currentChoice == options.length){
				currentChoice = 0;}
		}
	}
	public void keyReleased(int k){}
	
	public static String getname(){
		return playername;
	}
}
