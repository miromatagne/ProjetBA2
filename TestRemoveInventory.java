package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import View.Map;
import View.Window;

class TestRemoveInventory {

	@Test
	void testAddInventory() {
		ArrayList<ModelWindow> windows = new ArrayList<ModelWindow>();
		ModelWindow w = new ModelWindow();
		w.getObjects().add(new Computer(1,6));
		windows.add(w);
		Game g = new Game(windows,1,0,0);
		Player p = new Player(3,4);
		g.setActivePlayer(p);
		ArrayList<GameObject> ar = new ArrayList<GameObject>();
		ar.add(new Apple(1,1));
		p.setInventory(ar);
		g.removeFromInventory(0, 25);
		GameObject obj = null;
		for(GameObject o : w.getObjects()) {
			if(o instanceof Apple) {
				obj = o;
			}
		}
		assertEquals(obj.getPosX(),p.getFrontX());
	}
	
	public Image getImage(String path) {
		Image tempImage = null; 
		try {
			URL imageurl = Map.class.getResource(path); 
    	tempImage = Toolkit.getDefaultToolkit().getImage(imageurl); 
    	}
    	catch(Exception e){
    	}
		return tempImage;
	}

}
