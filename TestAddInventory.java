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

public class TestAddInventory {

	@Test
	void testAddInventory() {
		ArrayList<Window> windows = new ArrayList<Window>();
		Window w = new Window("Etage0");
		windows.add(w);
		int x = 3;
		int y = 4;
		Player p = new Player(x,y);
		Apple a = new Apple(1,2);
		p.addtoinventory(a);
		assertEquals(p.getInventory().get(0),a);
	}
	
}
