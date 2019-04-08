package Model;

public class Pomme extends GameObject implements Activable {
    Game g;
    int nourriture = 15;
    int vie = 10;
	
	
	public Pomme(int X, int Y, int color, Game g) {
		super(X, Y, color);
		this.g = g;
		// TODO Auto-generated constructor stub
	}
	
	public void activate() {
		System.out.println("Je mange");
		g.getGameObjects().remove(this);
		g.getActivePlayer().regain(nourriture,vie);
		
	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return true;
	}

}
