package Model;

public class Ordi extends GameObject implements Gain{


	protected int posX;
	protected int posY;
	protected int color;
	protected static int rapporte = 10;
	

	public Ordi(int X, int Y, int color) {
			super(X, Y, color);
			
		}

	
	public boolean isObstacle() {
		return true;
	}


	
	public void makeMoney(Player p) {
		p.setMoney(rapporte);
	}


	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return false;
	}

}

// ordi est un object qui va permettre de faire gagner de l'argent grace à la méthode makeMoney