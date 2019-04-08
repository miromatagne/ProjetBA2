package Model;

import java.util.ArrayList;

import View.Window;



public class Three extends GameObject implements Gain {

    private int lifepoints = 100;
    public static int gain = 50;
    
    public Three(int X, int Y) {
        super(X, Y, 3);
        
 
    }
    
    
    
    public void cut (Player p, ArrayList<GameObject> objects){
        if (lifepoints == 0){
        	lifepoints-=10;
            objects.remove(this);
            makeMoney(p);
            System.out.println("tu gagne 50€");
            
            
        }
        else {
            lifepoints-=10;
                                       
        }  
      
    }   
    
    public int getPV() {
    	return this.lifepoints;
    	
    }
    
    public void makeMoney(Player p) {
    	p.setMoney(gain);
    }


  

    @Override
    public boolean isObstacle() {
        return true;
    }



	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return false;
	}





}