package Model;

import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

import Controller.Keyboard;
import Controller.Mouse;
import View.Status;
import View.Window;

public class ModelWindow implements Serializable {
    public static ModelWindow active_window = null;
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Player> players = new ArrayList<Player>();
    String title;
    Window w = new Window("Jeu");
    
    public ModelWindow() {
    }
    
    public ArrayList<GameObject> getObjects(){
    	return this.objects;
    }
    
    public ArrayList<Player> getPlayers(){
    	return this.players;
    }
    
	public void setPlayers(ArrayList<Player> players) {
		w.getStatus().setPlayers(players);
	}
	public void setActiveWindow() {
		active_window = this;
		this.makeActive();
	}
	public boolean isActiveWindow() {
		return this == active_window;
	}
	public void setObjects() {
		this.w.getStatus().setObjects(objects);
	}
	
    public void makeActive() {
    	this.w.setVisible(true);
    	this.setObjects();
    	active_window = this;
    }
    public void makeUnactive() {
    	this.w.setVisible(false);
    }
    public void setKeyListener(Keyboard k) {
    	w.setKeyListener(k);
    }
    public void setMouseListener(Mouse m) {
    	w.setMouseListener(m);
    }
    
    public int getMapSize() {
    	return w.getMapSize();
    }
    
    public void setGameObjects(ArrayList<GameObject> objects) {
        this.w.setGameObjects(objects);
    }
    
    public void update() {
    	w.update();
    }
    public void stop() {
    	w.dispatchEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSING));
    }
    
    public Status getStatus() {
    	return w.getStatus();
    }
    
    public void newWindow() {
    	Window w = new Window("Jeu");
    }
    
    public Window getWindow() {
    	return w;
    }
    
}
