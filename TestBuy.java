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
		Player p = new Player(x,y);
		Shop s = new Shop(3,3);
		Apple a = new Apple(1,1);
		s.SetShop();
		s.SetStockandCatalogue(a, 5);
		s.updateActivePlayer(p);
		s.mag.Buy();
		ArrayList<GameObject> inv = new ArrayList<GameObject>();
		inv.add(a);
		assertEquals((inv.get(0) instanceof Apple),p.getInventory().get(0) instanceof Apple);
		}
	
}
