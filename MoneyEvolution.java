package Model;


import java.util.ArrayList;
import View.Window;

public class MoneyEvolution extends Thread{
	
    public Player p;
    public Window window;
	public GameObject obj;
	public String key = "clé";
	public ArrayList<GameObject> objects;
	
	public MoneyEvolution(Player p, Window wind, GameObject obj, ArrayList<GameObject> objects) {
	
		this.p=p;
		this.window=wind;
		this.obj=obj;
		this.objects=objects;
		

		
	}
	// rappel un thread n'est pas une boucle
	public void run() {
		synchronized(key) {// le syncrhonized permet d'éviter qu'en cliquant plusieurs fois sur l'ordi plusieurs threads de type MoneyEvolution se lance en même temps
		if(obj instanceof Ordi) {                             // toutefois le synchronized empêche de couper du bois et de faire de l'ordinateur en même tps    
			while(obj.isAtPosition(p.getFrontX(), p.getFrontY())) {
				((Ordi)obj).makeMoney(p);
				window.update();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
	
			
			}
		}
		
		else if(obj instanceof Three) {
			while(obj.isAtPosition(p.getFrontX(), p.getFrontY()) && (((Three)obj).getPV())>=0) {
				((Three)obj).cut(p,objects);                                // si l'objet de type gain s'avère être un arbre, c'est ceci qui s'effectuera. 
				System.out.println("Sa vie vaut"+((Three)obj).getPV());     // cut diminue la vie de l'arbe et si celui-ci est mort cad sa vie vaut 0, 
				window.update();                                            // alors l'abre est être détruit et le joueur gagne 50 de money
				try {                                                       // Pour supprimer l'arbre, on le retire de la liste des objets a reprsentés et on 
					Thread.sleep(500);                                      // et on update la window, qui redescine tout sans l'arbre.
					                                                        // cut ne détruit l'arbre que si sa vie est nulle, si sa vie est non nulle
				} catch (InterruptedException e) {                          // il ne fait qu'en diminuer la vie, tant que le joueur est devant l'arbre         
					// TODO Auto-generated catch block                      // et que la vie est non nulle, on diminue la vie de l'arbre en appliquant cut
					e.printStackTrace();                                    // en boucle grace au while. Le thread sleep, met un tps d'attente entre chaque 
				}                                                           // perte de vie
				
			}
			
		}
		}
		
		
}
		
			
		
   
	// le thread a comme argument un obj et un joueur et la window afin de la refresh. Il dit que tant que notre joueur est en face de l'obj, qui s'avère être
    // un objet de type Gain, alors on applique makeMoney a ce joueur, ce qui fait gagner de l'argent a ce joueur, on refresh ensuite la page pour afficher
	// le nouvel argent de ce joueur.
		
		
	
	
}


