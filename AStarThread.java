package Model;
	
	import java.util.Random;

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
			int i = 7;
			System.out.println(p.NotDrunk() + " "+ p.BitDrunk() +" "+ p.Drunk() + " "+ p.DeadDrunk());
			synchronized(p) {
			while(direction != -1) {
				
				
				if(p.NotDrunk()){
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
					e.printStackTrace();
				}
			}
				else if(p.BitDrunk()){ 
					if(i != 1) {
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
						Thread.sleep(700); //Attente entre 2 déplacements de cases
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block 
						e.printStackTrace();
					}	
					
				} else if (i == 1) {
					Random rand = new Random();
					int n = rand.nextInt(4);
					direction = n; 
					System.out.println("je vais dans le mauvaise direction "+ n);
					switch (direction) {
					//On fait bouger le personnage
					case 0 : g.movePlayer(1,0); break; //Est
					case 1 : g.movePlayer(0,-1); break; //Nord
					case 2 : g.movePlayer(-1,0); break; //Ouest
					case 3 : g.movePlayer(0,1); break; //Sud
				}
				try {
					Thread.sleep(1000); //Attente entre 2 déplacements de cases
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				}	
							
					
					
				
				}
			} else if(p.Drunk()){ 
				if(i != 1 && i !=3  ) {
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
					Thread.sleep(700); //Attente entre 2 déplacements de cases
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				}	
				
			} else if (i == 1 || i ==3) {
				Random rand = new Random();
				int n = rand.nextInt(4);
				direction = n; 
				System.out.println("je vais dans le mauvaise direction "+ n);
				switch (direction) {
				//On fait bouger le personnage
				case 0 : g.movePlayer(1,0); break; //Est
				case 1 : g.movePlayer(0,-1); break; //Nord
				case 2 : g.movePlayer(-1,0); break; //Ouest
				case 3 : g.movePlayer(0,1); break; //Sud
			}
			try {
				Thread.sleep(1000); //Attente entre 2 déplacements de cases
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}	
						
				
				
			
			}
		} else if(p.DeadDrunk()){ 
			if(i != 1 && i !=3 && i!=5 ) {
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
				Thread.sleep(700); //Attente entre 2 déplacements de cases
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}	
			
		} else if (i == 1 || i ==3 || i==5) {
			Random rand = new Random();
			int n = rand.nextInt(4);
			direction = n; 
			System.out.println("je vais dans le mauvaise direction "+ n);
			switch (direction) {
			//On fait bouger le personnage
			case 0 : g.movePlayer(1,0); break; //Est
			case 1 : g.movePlayer(0,-1); break; //Nord
			case 2 : g.movePlayer(-1,0); break; //Ouest
			case 3 : g.movePlayer(0,1); break; //Sud
		}
		try {
			Thread.sleep(1000); //Attente entre 2 déplacements de cases
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}	
					
			
			
		
		}
	}
				
			
				
			i-=1;
			if(i==0) {
				i = 7;
		    }
	}
			
}
	
}
}
	
// ce run effectue pas-à-pas le déplacement de notre joueur. Le déplacement s'effectue différemment selon que le joueur soit bourré ou non
// pour simulé un joueur saoul, on joue sur la direction qu'on lui envoie, càd que tout les X pas , on envoie comme direction, une direction aléatoire'
// pour se faire on met un conteur, qui démarre à 7 et qui a chaque tour de boucle définissant le pas suivant, diminue de 1. On dit ensuite lorsque ce compteur
// vaut "autant" on prend une direction alétoire. Plus le joueur est saoul, plus il y a de valeur du compteur pour laquelle la direction prend des valeurs 
// aléatoire. Par exemple, quand le joueur est deaddrunk si le compteur prend comme valeur 1,3 ou 5 alors on prend une direction alétoire, ca fait 3 
// direction aléatoire sur 7 !
// Lorsque le compteur tombe à 0, on le remet 7