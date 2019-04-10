package Model;

import java.util.ArrayList;

import View.Window;

public class Stairs extends BlockUnbreakable implements Activable {
	private int etageH;
	private int etageB;
	private Player active_player;
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
		Window windowB = windows.get(etageB);
		Window windowH = windows.get(etageH);
		windowB.players.remove(active_player);
		windowH.players.add(active_player);
		windowB.objects.remove(active_player);
		windowH.objects.add(active_player);
		windowH.setPlayers(windowH.players);
		windowH.setInventory(active_player.inventory);
		windowB.makeUnactive();
		windowH.makeActive();
		Thread t2 = new Thread(new EnergyLoss(active_player,g.getActiveWindow(windows)));
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

	@Override
	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;
	}
}
