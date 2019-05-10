package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import View.Map;

public class SourisListener implements MouseListener,Serializable {
	 private int BLOC_SIZE = 38;
	 private Map map; 
	 
	 public SourisListener(Map map) {
		 this.map=map;
	 }
	
	

	public void mousePressed(MouseEvent e) {
		int x = e.getX()/BLOC_SIZE;           // on divise la position x et y par la taille du bloc ce qui renvoie un double
		int y = e.getY()/BLOC_SIZE;           // on prend ensuite l'entier le plus proche ce qui renvoie enft le numéro de notre block 
		                                      // sur la map
		map.getMouseController().mapEvent(x, y);       // on attribue a x et y l'endroit de la position de la souris
		System.out.println(x);
		System.out.println(y);
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}



