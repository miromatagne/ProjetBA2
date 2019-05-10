package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import View.Status;

public class MouseForStatus implements MouseListener, Serializable {
	private int BLOCK_SIZE = 25;
	private Status status;
	private int a = 0;
	private int b = 25;
	
	public MouseForStatus(Status status) {
		this.status = status;
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX()/BLOCK_SIZE;           // on divise la position x et y par la taille du bloc ce qui renvoie un double
		int y = e.getY()/BLOCK_SIZE;           // on prend ensuite l'entier le plus proche ce qui renvoie enft le numéro de notre block 
		                                      // sur la map
	                                          // on attribue a x et y l'endroit de la position de la souris
		System.out.println(x);
		System.out.println("mon y " + y);
		System.out.println("mon x " + x);
		
		
		if(a == x && b == y) {
			status.getMouseController().statusEvent(x,y);
			
		}
		System.out.println("mon a "+ a + " mon b "+ b );
		if(x>=0  && x <= 4 && y>=25 && y<=29 ){
			if(x+1+5*(y-25) <= status.getActivePlayer().getInventory().size()) { 
				status.getMouseController().setActiveObject(x,y);
		a = x ;
		b = y ;
		status.setA(a);
		status.setB(b);
		System.out.println(a+b<=status.getActivePlayer().getInventory().size());
			}
			System.out.println(a+b<=status.getActivePlayer().getInventory().size());
		}
		
	}
//on défini un attribut active_object dans l'inventaire de notre status. Pour ce faire on part en a = 0 et b=25 -> la première case de notre inventaire. Dans paint Graphics on dit 
//que si notre inventaire n'est pas vide l'object actif initiale est celui qui se trouve en (0,25) l'objet situé en (0,25) est donné par a+5*(b-25) ce qui pour a = 0 et b = 25 
//donne 0 --> autrement dit initialement l'objet actif est le premier objet de notre inventaire (le premier objet étant d'indice 0) (ligne 190) . L'écriture a+5*(b-25) est nécéssaire pour 
//des cas où par exemple on veut que l'objet actif soit celui en (3,26). On dessine un rectangle rouge entourant notre objet actif on le descine en (a,b).
//On peut modifier l'objet actif, en cliquant sur une autre case de notre inventaire (ligne 45 à 60): si cette case = (a,b) s'est qu'on était déjà dessus on applique alors statusEvent qui vend l'objet
//présent sur cette case elle vendra l'objet d'indice a+5*(b-25) , si cette case =/= (a,b) c'est qu'on était pas encore dessus, on redéfini alors (a,b) comme étant le (x,y) de cette case
//!!! on redéfini (a,b) uniquement si la position où on a cliqué est inférieur où égale au nombre d'objet dans notre inventaire, en effet en modifiant (a,b) on modifie l'objet actif
//on dit que l'objet actif est celui en a+5*(b-25) mais si on a a+5*(b-25) qui est plus grand que notre inventaire ca pose problème. De la sorte on s'assure que l'objet actif n'est modifié
//que si on clique sur un autre objet. 
//D'autre part lorsqu'on dépose notre objet, on redéfini le nouvel objet actif comme étant celui étant en a=0 b=25, car si on retire un objet de l'inventaire, il ne peut plus être 
//objet actif, pour ce faire on applique setActiveObject() (cfr game dans la fonction removefrominventory).



	

	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
	
	
	

}
