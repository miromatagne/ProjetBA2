package Controller;
	import java.io.Serializable;

import Model.Game;
	
	public class Mouse implements Serializable {
	    private static Game game;  // on range game dans une variable static qu'on défini dans main
	
	    public Mouse() {
	    }
	
	    public void mapEvent(int x, int y) {
			synchronized(game) {
				game.sendPlayer(x, y);
				//game.EarnMoney(x,y);
				game.addtoInventory(x,y);
			}
		}
		
		public void statusEvent(int x, int y) {
				game.removeFromInventory(x,y);
			}
		
		public static void setGame(Game game) {
			Mouse.game=game;
		}
		
	public void setActiveObject(int x, int y) {
		game.setActiveObject(x, y);
	}
		
	}