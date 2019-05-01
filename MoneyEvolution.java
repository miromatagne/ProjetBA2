package Model;

public class MoneyEvolution implements Runnable{
	
    private Player p;
	private String key = "cle";
	private Computer c;
	
	public MoneyEvolution(Player p, Computer c) {
		this.p=p;
		this.c = c;
	}
	public void run() {
		//synchronized(key) {                               
		while(c.isAtPosition(p.getFrontX(), p.getFrontY()) && p instanceof Worker) {
			((Computer)c).makeMoney(p);			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			//}
				
		
	
			
			}
		}
		
		/*else if(obj instanceof Three) {
			while(obj.isAtPosition(p.getFrontX(), p.getFrontY()) && (((Three)obj).getPV())>=0) {
				((Three)obj).cut(p,objects);                               
										
				//window.update();                                            
				try {                                                      
					Thread.sleep(500);                                      
					                                                        
				} catch (InterruptedException e) {                                  
					// TODO Auto-generated catch block                      
					e.printStackTrace();                                    
				}                                                           
				
			}
			
		} */
		}
		
		
}
		
			
		
   
	// le thread a comme argument un obj et un joueur et la window afin de la refresh. Il dit que tant que notre joueur est en face de l'obj, qui s'avère être
    // un objet de type Gain, alors on applique makeMoney a ce joueur, ce qui fait gagner de l'argent a ce joueur, on refresh ensuite la page pour afficher
	// le nouvel argent de ce joueur.
		
		
	
	


