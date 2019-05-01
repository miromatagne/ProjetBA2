package Controller;
	
	import Model.Game;
	
	public class Mouse {
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
				System.out.println("je retire");
				System.out.println(x);
				System.out.println(y);
			}
		
		public void setGame(Game game) {
			this.game=game;
		}

		
	}