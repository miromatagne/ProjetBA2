package Model;

import java.awt.Image;

import View.Window;

public class Gun extends BlockUnbreakable implements Activable{
	int bullets = 10;
	Window window;
	Player active_player;
	static Image image = getImage("Gun.PNG");
	public Gun(int X, int Y) {
		super(X, Y, 1, 1, image);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		boolean obstacle = false;
		for (GameObject o : window.objects) {
			if (o.isAtPosition(active_player.getFrontX(), active_player.getFrontY())) { //Check si il y a un objet là où il veut se déplacer
	            obstacle = o.isObstacle(); //Renvoie true si il y a un objet
	        }
	        if (obstacle == true) { //On interrompt alors la boucle parce qu'un objet est présent donc on ne peut pas se déplacer là
	            break;
	        }
		}
		if(bullets > 0 && obstacle == false) {
			bullets -= 1;
			Bullet b = new Bullet(active_player.getFrontX(), active_player.getFrontY());
			window.objects.add(b);
			b.setDirection(active_player.getDirection());
			Thread t1 = new Thread(new BulletThread(b,window,active_player));
			t1.start();
		}
	}
	@Override
	public void updateActivePlayer(Player active_player) {
		// TODO Auto-generated method stub
		this.active_player = active_player;
	}
	
	public int getBullets() {
		return this.bullets;
	}
	
	public void addBullets(int nbBullets) {
		this.bullets += nbBullets;
	}
	
	public void updateActiveWindow(Window window) {
		this.window = window;
	}
	
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return true;
	}
}
