package Model;

import View.Window;

public class AStarThread implements Runnable{
	private Game g;
	private Player p;
	private int x;
	private int y;
	private Window window;

	public AStarThread(Game g, Player p, int x, int y, Window window) {
		this.g= g;
		this.p = p;
		this.x = x;
		this.y = y;
		this.window = window;
		
	}
	
	@Override
	public void run() {
		int direction = 0;
		synchronized(p) {
		while(direction != -1) {
			direction = (new AStar(p.getPosX(), p.getPosY(), x, y, window.objects)).getNextStep(); 
			//On obtient la direction à suivre en appelant getNextStep de la classe Astar 
			switch (direction) {
				//On fait bouger le personnage
				case 0 : g.movePlayer(1,0); break; //Est
				case 1 : g.movePlayer(0,-1); break; //Nord
				case 2 : g.movePlayer(-1,0); break; //Ouest
				case 3 : g.movePlayer(0,1); break; //Sud
			}
			try {
				Thread.sleep(500); //Attente entre 2 déplacements de cases
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block 
				// ???
				e.printStackTrace();
			}
		}
		}
	}
		

}
