package Model;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import org.junit.Test;

import View.Map;
import View.Window;


public class TestBuy {
	@Test
	public void testBuy() {
		ArrayList<Window> windows = new ArrayList<Window>();
		Window w = new Window("Etage0");
		windows.add(w);
		int x = 3;
		int y = 4;
		Image image = getImage("Bed.PNG");
		Player p = new Player(x,y,image);
		Shop s = new Shop(3,3,image);
		Apple a = new Apple(1,1);
		s.SetShop();
		s.SetStockandCatalogue(a, 5);
		s.updateActivePlayer(p);
		s.mag.Buy();
		ArrayList<GameObject> inv = new ArrayList<GameObject>();
		inv.add(a);
		assertEquals((inv.get(0) instanceof Apple),p.getInventory().get(0) instanceof Apple);
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
