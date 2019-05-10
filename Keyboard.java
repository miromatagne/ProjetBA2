package Controller;
	
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import Model.Game;
	
	public class Keyboard implements KeyListener,Serializable {
	    private Game game;
	    public static int compteur = 7;
	
	    public Keyboard(Game game) {
	        this.game = game;
	    }
	
	    @Override
	    public void keyPressed(KeyEvent event) {
	        int key = event.getKeyCode();
	        switch (key) { 
	        case KeyEvent.VK_SPACE:
	            game.action();
	            break;
	        case KeyEvent.VK_Q:
	            game.stop();
	            break;
	        case KeyEvent.VK_G:
	            try {
					game.Save();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            break;
	        case KeyEvent.VK_F:
	        	game.feed();
	        	break;
	        case KeyEvent.VK_T:
	            game.tirePlayer();
	            break;
	       case KeyEvent.VK_P:
	            game.playerPos();
	            break;
	       case KeyEvent.VK_A:
	       	    game.changeActiveWindow();
	       	    break;
	       case KeyEvent.VK_S:
	       	    game.shoot();
	       	    break;
	       }
	        
	       if(game.getActivePlayer().NotDrunk()) {
		       	switch (key) {
		       	case KeyEvent.VK_RIGHT:
		       		game.movePlayer(1, 0);
		       		break;
		       	case KeyEvent.VK_LEFT:
		       		game.movePlayer(-1, 0);
		       		break;
		       	case KeyEvent.VK_DOWN:
		       		game.movePlayer(0, 1);
		       		break;
		       	case KeyEvent.VK_UP:
		       		game.movePlayer(0, -1);
		       		break;
		       	}
		       	} 
	       		else if(game.getActivePlayer().BitDrunk()) {
		    	   compteur-=1;
		    	   if(compteur !=3) {
			        switch (key) {
			        case KeyEvent.VK_RIGHT:
			            game.movePlayer(1, 0);
			            break;
			        case KeyEvent.VK_LEFT:
			            game.movePlayer(-1, 0);
			            break;
			        case KeyEvent.VK_DOWN:
			            game.movePlayer(0, 1);
			            break;
			        case KeyEvent.VK_UP:
			            game.movePlayer(0, -1);
			             break;
			          }
			        } 
		    	   	else {
			        Random rand = new Random();
			        int n = rand.nextInt(4); 
			        if(n == 0) {
			        	game.movePlayer(1,0);
			        	
			        } else if(n == 1) {
			        	game.movePlayer(-1,0);
			        	
			        } else if(n == 2) {
			        	game.movePlayer(1,0);
			        	
			        } else if(n == 3) {
			        	game.movePlayer(0,-1);
			        	
			        }
			        
			        
			       
			        }
			       } else if(game.getActivePlayer().Drunk()) {
			    	   compteur-=1;
			    	   if(compteur !=3 && compteur !=5) {
				        switch (key) {
				        case KeyEvent.VK_RIGHT:
				            game.movePlayer(1, 0);
				            break;
				        case KeyEvent.VK_LEFT:
				            game.movePlayer(-1, 0);
				            break;
				        case KeyEvent.VK_DOWN:
				            game.movePlayer(0, 1);
				            break;
				        case KeyEvent.VK_UP:
				            game.movePlayer(0, -1);
				             break;
				          }
				        } else {
				        Random rand = new Random();
				        int n = rand.nextInt(4); 
				        if(n == 0) {
				        	game.movePlayer(1,0);
				        	
				        } else if(n == 1) {
				        	game.movePlayer(-1,0);
				        	
				        } else if(n == 2) {
				        	game.movePlayer(0,1);
				        	
				        } else if(n == 3) {
				        	game.movePlayer(0,-1);
				        	
				        }
				        
				        
				       
				        }
				       } else if(game.getActivePlayer().DeadDrunk()) {
				    	   compteur-=1;
				    	   if(compteur !=3 && compteur !=5 && compteur !=1 && compteur != 2) {
					        switch (key) {
					        case KeyEvent.VK_RIGHT:
					            game.movePlayer(1, 0);
					            break;
					        case KeyEvent.VK_LEFT:
					            game.movePlayer(-1, 0);
					            break;
					        case KeyEvent.VK_DOWN:
					            game.movePlayer(0, 1);
					            break;
					        case KeyEvent.VK_UP:
					            game.movePlayer(0, -1);
					             break;
					          }
					        } else {
					        Random rand = new Random();
					        int n = rand.nextInt(4); 
					        System.out.println("JE VAIS DANS LA MAUVAISE DIRECTION" + n);
					        if(n == 0) {
					        	game.movePlayer(1,0);
					        	
					        } else if(n == 1) {
					        	game.movePlayer(-1,0);
					        	
					        } else if(n == 2) {
					        	game.movePlayer(0,1);
					        	
					        } else if(n == 3) {
					        	game.movePlayer(0,-1);
					        	
					        }
					        
					        }
					       }
		      if(compteur == 0) {
		    	  compteur = 7;
		      }
		        
		        
		    }
		
		    @Override
		    public void keyTyped(KeyEvent e) {
		    }
		
		    @Override
		    public void keyReleased(KeyEvent e) {
		    }
		}