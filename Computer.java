package Model;

public class Computer extends BlockUnbreakable implements Gain,Activable {


	protected int posX;
	protected int posY;
	protected int color;
	Player active_player;
	protected static int rapporte = 10;
	

	public Computer(int X, int Y, int color) {
			super(X, Y, color);
			
		}
	
	public void makeMoney(Player p) {
		p.addMoney(rapporte);
	}


	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void activate() {
		Thread t3 = new Thread(new MoneyEvolution(active_player, this));
		t3.start();
	}


	@Override
	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;
	}



}

// ordi est un object qui va permettre de faire gagner de l'argent grace à la méthode makeMoney