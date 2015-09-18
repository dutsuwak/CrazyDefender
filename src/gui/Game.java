package gui;

import javax.swing.JFrame;
import logic.ReadProperties;

public class Game {
	public static void main(String[] args){ //Clase Main Principal
		//Instancia ReadProperties para la lectura del archivo config.properties
		ReadProperties.getInstance();
		JFrame window = new JFrame("Crazy Defender");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	  }
}

