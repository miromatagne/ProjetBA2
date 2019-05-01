package View;

import Model.GameObject;
import Model.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Mouse;

public class Window extends JFrame {
	private JPanel groupPanel = new JPanel(new BorderLayout());
    private Map map = new Map();
    private Status status = new Status();
    public static Window active_window = null;
    public ArrayList<GameObject> objects = new ArrayList<GameObject>();
    public ArrayList<Player> players = new ArrayList<Player>();

    public Window(String title) {
    	super(title);
        // JFrame window = new JFrame("Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 1800, 1020);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(map, BorderLayout.LINE_START);
        groupPanel.add(status, BorderLayout.LINE_END);
        this.getContentPane().add(this.groupPanel);
        //this.setVisible(true);
    }

    public void setGameObjects(ArrayList<GameObject> objects) {
        this.map.setObjects(objects);
        this.map.redraw();
    }
    public void makeActive() {
    	this.setVisible(true);
    	active_window = this;
    }
    public void makeUnactive() {
    	this.setVisible(false);
    }

    public void update() {
        this.map.redraw();
        this.status.redraw();
    }

    public void setKeyListener(KeyListener keyboard) {
        this.map.addKeyListener(keyboard);
    }

    public void setMouseListener(Mouse m) {
        this.map.addMouse(m);
    }

	public int getMapSize() {
		return map.MAP_SIZE;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		status.setPlayers(players);
	}
	public void setActiveWindow() {
		active_window = this;
		this.makeActive();
	}
	public boolean isActiveWindow() {
		return this == active_window;
	}
	public void setInventory(ArrayList<GameObject> inventory) {
		this.status.setInventory(inventory);
	}
	public void setObjects() {
		this.status.setObjects(objects);
	}
}
