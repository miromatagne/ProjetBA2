import java.util.ArrayList;

import Controller.Keyboard;
import Controller.Mouse;
import Model.Game;
import View.Window;

public class Main {
    public static void main(String[] args) {
        int numberOfWindows = 3;
        ArrayList<Window> windows = new ArrayList<Window>();
        for (int i = 0; i < numberOfWindows; i++) {
        	Window w = new Window("Etage" + i);
        	windows.add(w);
        }
        Game game = new Game(windows);
        Keyboard keyboard = new Keyboard(game);
        Mouse mouse = new Mouse(game);
        
        for (Window window : windows) {
        	 window.setKeyListener(keyboard);
             window.setMouseListener(mouse);
        }
    }
}
