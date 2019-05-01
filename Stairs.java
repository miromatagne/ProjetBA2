package Model;

import java.util.ArrayList;

import View.Window;

public class Stairs extends BlockUnbreakable implements Activable {
	private int etageH;
	private int etageB;
	private int x;
	private int y;
	private Player active_player;
	private Game g;
	private ArrayList<Window> windows;
	public Stairs(int x, int y, ArrayList<Window> windows, int etageB, int etageH, Game g) {
		super(x,y,9);
		this.x = x;
		this.y = y;
		this.windows = windows;
		this.etageB = etageB;
		this.etageH = etageH;
		this.g = g;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		g.notifyObservers();
		Window windowB = windows.get(etageB);
		Window windowH = windows.get(etageH);
		windowB.players.remove(active_player);
		windowH.players.add(active_player);
		windowB.objects.remove(active_player);
		windowH.objects.add(active_player);
		windowH.setPlayers(windowH.players);
		g.notifyObservers();
		windowH.setInventory(active_player.inventory);
		windowH.makeActive();
		windowB.makeUnactive();
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