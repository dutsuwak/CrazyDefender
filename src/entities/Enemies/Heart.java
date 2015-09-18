package entities.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import logic.ReadProperties;
import entities.*;
import TileMap.TileMap;

public class Heart extends Enemy{
	
	private BufferedImage[] sprites;
	
	public Heart (TileMap tm){
		super(tm);
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		took = false;
		
		try{
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(ReadProperties.file.getSetting("heart")));
			sprites = new BufferedImage[7];
			for (int i = 0; i < sprites.length;i++)
			{
				sprites[i] = spritesheet.getSubimage(i*width,0,width,height);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		right = true;
		facingRight = true;
	}
	public void update(){
		//Actualizar Posicion
		
		//getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp,ytemp);
		
		//Actualizar Animacion
		animation.update();
	}
	public void draw(Graphics2D g){
		//if (notOnScreen()) return;
		setMapPosition();
		super.draw(g); //Se llama al metodo de la clase padre para dibujo del Enemigo
	}
	
	public void takeHeart(){
		took = true;
	}

}
