package gui;

import java.awt.*;
import java.awt.event.KeyEvent;

import logic.Background;
import logic.ReadProperties;



public class AboutState extends GameState {

	private Background bg;
	private int currentChoice = 0;
	
	private String[] texto = {
			"Instituto Tecnológico de Costa Rica",
			"Computer Engineering",
			"Algoritmos y Estructuras de Datos I",
			"Profesor: Gerardo Nereo Campos Araya",
			"Estudiantes: ",
			"Fabián A. Solano Madriz",
			"Edder Delgado Calderón",
			"Version 1.0",
			"Desarrollado sobre Java 1.8"
	};
	private String[] menu = {
			"Menu",
			"Exit"
	};
	@SuppressWarnings("unused")
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	
	public AboutState(GameStateManager gsm){
		super();
		this.gsm = gsm;
		try{
			bg = new Background(ReadProperties.file.getSetting("imgmenu"),1);
			bg.setVector(0.3,0);//Movimiento a la derecha
			
			titleColor = new Color(0,0,255);
			titleFont = new Font("Century Gothic", Font.PLAIN, 20);
			font = new Font("Arial",Font.PLAIN, 11);
			
		}
		catch(Exception e){
			e.printStackTrace(); //Para detectar algún posible error
		}
	}
	
	public void init(int inhealth, int inscore){}
	public void update(){
		bg.update();
	}
	public void draw(java.awt.Graphics2D g){
		//Dibujar Fondo
		bg.draw(g);
		//Dibujar Texto del Titulo de Juego
		g.setColor(Color.green);
		g.setFont(titleFont);
		g.drawString("About",10,20);
		//Dibujar Texto a mostrar
		g.setFont(font);
		g.setColor(Color.white);
		for (int i=0; i < texto.length; i++){
			g.drawString(texto[i],50,65+i*18);
		}
		for (int i=0; i < menu.length; i++){
			if (i==currentChoice){
				g.setColor(Color.green);
			}
			else{
				g.setColor(Color.red);
			}
			g.drawString(menu[i],284,220 + i * 15);
		}
		
	}
	
	private void select(){
		if(currentChoice==0){
			//Regresar
			gsm.setState(GameStateManager.MENUSTATE,0,0);
		}
		if(currentChoice==1){
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
				currentChoice = menu.length -1;}
		}
		if (k == KeyEvent.VK_DOWN){
			currentChoice++;
			if (currentChoice == menu.length){
				currentChoice = 0;}
		}
	}
	public void keyReleased(int k){}
}
