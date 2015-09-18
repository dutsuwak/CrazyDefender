package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entities.Player;
import logic.ReadProperties;

public class HUD {
	
	private Player player;
	private BufferedImage image;
	private Font font;
	
	public HUD(Player p){
		player = p;
		try{
			image = ImageIO.read(
					getClass().getResourceAsStream(ReadProperties.file.getSetting("HUD")));
			font = new Font("Arial", Font.PLAIN, 14);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public void draw(Graphics2D g){
		g.drawImage(image,0,10,null); //Coordenadas del HUD en Pantalla
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(player.getHealth() + "/" + 5,30,25);
		g.drawString(player.getFire() / 100 + "/" + player.getMaxFire()/100, 30,45);
	}

}
