package Model;

import java.util.ArrayList;

import View.Window;

public class Stairs extends GameObject implements Activable {
	private int etageH;
	private int etageB;
	private Player player;
	private Game g;
	private ArrayList<Window> windows;
	public Stairs(int x, int y, ArrayList<Window> windows, int etageB, int etageH, Game g) {
		super(x,y,9);
		this.windows = windows;
		this.etageB = etageB;
		this.etageH = etageH;
		this.g = g;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		player = g.getActivePlayer();
		Window windowB = windows.get(etageB);
		Window windowH = windows.get(etageH);
		windowB.players.remove(player);
		windowH.players.add(player);
		windowB.objects.remove(player);
		windowH.objects.add(player);
		windowH.setPlayers(windowH.players);
		windowH.setInventory(player.inventory);
		windowB.makeUnactive();
		windowH.makeActive();
		Thread t2 = new Thread(new EnergyLoss(player,g.getActiveWindow(windows)));
		t2.start();
	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return false;
	}
}
