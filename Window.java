package View;

import Model.*;
import Model.GameObject;
import Model.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.net.URL;
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
    private static ArrayList<Image> imlist = new ArrayList<Image>();

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
    
    public void setStatusImages(ArrayList<Image> i) {
    	this.status.setImages(i);
    }
    public void setMapImages(ArrayList<Image> i) {
    	this.map.setImages(i);
    }

    public void setGameObjects(ArrayList<GameObject> objects) {
        this.map.setObjects(objects);
        this.map.redraw();
    }
    
    public int getMapSize() {
    	return this.map.MAP_SIZE;
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
    
	public Status getStatus() {
		return this.status;
	}
	
}
