package Model;

import View.Window;

public class BulletThread implements Runnable {
	int x;
	int y;
	Bullet b;
	ModelWindow w;
	Player active_player;
	public BulletThread(Bullet b, ModelWindow w, Player active_player) {
		this.b = b;
		this.w = w;
		this.active_player = active_player;
		if(b.getDirection() == 0) {
			x = 1;
			y = 0;
		}
		if(b.getDirection() == 1) {
			x = 0;
			y = -1;
		}
		else if(b.getDirection() == 2) {
			x = -1;
			y = 0;
		}
		else if(b.getDirection() == 3) {
			x = 0;
			y = 1;
		}
	}
	public void run() {
		boolean obstacle = false;
		GameObject obj = null;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(obstacle == false) {
			for (GameObject o : w.getObjects()) {
				if (o.isAtPosition(b.getPosX() + x, b.getPosY() + y)) { //Check si il y a un objet là où il veut se déplacer
		            obstacle = o.isObstacle(); //Renvoie true si il y a un objet
		            obj = o;
		        }
		        if (obstacle == true) { //On interrompt alors la boucle parce qu'un objet est présent donc on ne peut pas se déplacer là
		            break;
		        }
			}
			if(obstacle == false) {
				b.setPosition(b.getPosX() + x, b.getPosY() + y);
			}
			else {
				if(obj instanceof Player) {
					((Player) obj).getShot();
				}
				else if(obj instanceof Target) {
					int dist = Math.abs(active_player.getPosY() - b.getPosY());
					active_player.setMoney(dist*100);
				}
				b.crush();
				w.getObjects().remove(b);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
