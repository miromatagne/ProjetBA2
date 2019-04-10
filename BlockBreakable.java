package Model;

import java.util.ArrayList;

public class BlockBreakable extends Block implements Deletable, Activable {

    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>(); //liste de blocs
    protected Player active_player;
    private int lifepoints = 0;
    public BlockBreakable(int X, int Y, int couleur, int lifepoints) { //X,Y = position du bloc, lifepoints = "vie" du bloc avant qu'il se détruise
        super(X, Y, couleur);
        this.lifepoints = lifepoints; 
    }
    
    public void activate(){ //On "active" le bloc, on interagit avec lui, mais comment on déclenche activate() ?
        if (lifepoints == 1){
            crush(); //On détruit le bloc car il n'a plus assez de vie
        }
        else {
            lifepoints--; //On diminue la vie de 1 à chaque fois qu'on active le bloc
            this.color = lifepoints + 2; // On change la couleur du bloc (on évite de retourner au gris)
        }
    }


    public void crush(){
    	System.out.println("C");
        notifyDeletableObserver(); //On appelle la méthode qui va détruire le bloc
    }
    // //////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void attachDeletable(DeletableObserver po) {
    	//Ajoute des blocs supprimables à la liste de blocs.
        observers.add(po);
    }

    @Override
    public void notifyDeletableObserver() {
    	//Supprime les blocs qui sont supprimables.
        int i = 0;
        System.out.println(observers.size());
        for (DeletableObserver o : observers) {
            i++;
            o.delete(this, null);
        }
    }

    @Override
    public boolean isObstacle() { //On va appeler cette fonction quand on veut savoir si un objet est un obstacle
        return true; //Dans cette classe c'est toujours le cas donc on renvoie true
    }

	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateActivePlayer(Player active_player) {
		this.active_player = active_player;	
	}

}
