package Model;

public class TreeThread extends Thread {
	Three tree;
	Player active_player;
	public TreeThread(Three tree, Player active_player) {
		this.tree = tree;
		this.active_player = active_player;
	}
	public void run() {
		while(tree.isAtPosition(active_player.getFrontX(), active_player.getFrontY()) && tree.getPV()>=0) {
			tree.cut(active_player);                                                                         
			try {                                                      
				Thread.sleep(500);                                                              
			} catch (InterruptedException e) {                                  
				// TODO Auto-generated catch block                      
				e.printStackTrace();                                    
			}                                                           
		}
	}
}
