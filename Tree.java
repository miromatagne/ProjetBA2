package Model;

import java.awt.Image;

public class Tree extends BlockBreakable implements Gain {

    private int lifepoints = 100;
    public static int gain = 50;
    Player active_player;
    
    public Tree(int X, int Y) {
        super(X, Y,2,2, 3);
    }
       
    public void cut (Player p){
        if (lifepoints == 0){
        	lifepoints-=10;
            this.crush();
            makeMoney(p);        
        }
        else {
            lifepoints-=10;
                                       
        }  
      
    }   
    
    public int getPV() {
    	return this.lifepoints;
    	
    }
    
    public void makeMoney(Player p) {
    	p.addMoney(gain);
    }



	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void activate() {
		Thread t1 = new Thread(new TreeThread(this, active_player));
		t1.start();
	}


	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;
	}





}