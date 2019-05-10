package Model;

import java.awt.Image;
import java.util.ArrayList;

import View.Window;

public class Stairs extends BlockUnbreakable implements Activable {
	private int etageH;
	private int etageB;
	private int x;
	private int y;
	private Player active_player;
	private Game g;
	private ArrayList<ModelWindow> windows;
	public Stairs(int x, int y, ArrayList<ModelWindow> windows, int etageB, int etageH, Game g) {
		super(x,y,1,1);
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
		ModelWindow windowB = windows.get(etageB);
		ModelWindow windowH = windows.get(etageH);
		windowB.getPlayers().remove(active_player);
		windowH.getPlayers().add(active_player);
		windowB.getObjects().remove(active_player);
		windowH.getObjects().add(active_player);
		windowH.setPlayers(windowH.getPlayers());
		windowH.makeActive();
		windowB.makeUnactive();
		g.notifyObservers();
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
	
	public int getEtageB() {
		return etageB;
	}
	
	public int getEtageH() {
		return etageH;
	}
}