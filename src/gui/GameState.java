package gui;


public abstract class GameState { //Evitar uso de esta clase
	
	protected GameStateManager gsm;
	
	public abstract void init(int inhealth, int inscore);
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);

}
