package Model;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import java.net.URL;

import View.Map;

public abstract class GameObject implements Serializable{
    protected int posX; //seule cette classe et les classes fille ont accès à ces objets
    protected int posY;
    protected int color;
    protected int length;
    protected int width;

    public GameObject(int X, int Y, int length, int width){
        this.posX = X;
        this.posY = Y;
        this.length = length;
        this.width = width;
    }
    

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getColor() {
        return this.color;
    }

    public boolean isAtPosition(int x, int y) {
    	boolean isAtPos = false;
    	for(int px = posX; px < posX + width; px++) {
    		for(int py = posY; py < posY + length; py++) {
    			if(px == x && py == y) {
    				isAtPos = true;
    			}
    		}
    	}
    	return isAtPos;
    }
    
    public void setPosition(int x, int y) {
    	this.posX = x;
    	this.posY = y;
    }
    
    public int getWidth() {
    	return this.width;
    }
    
    public int getLength() {
    	return this.length;
    }

    public abstract boolean isObstacle();

	public abstract boolean isAddable();
	
	public static Image getImage(String path) {
		Image tempImage = null; 
		try {
			URL imageurl = GameObject.class.getResource(path); 
    	tempImage = Toolkit.getDefaultToolkit().getImage(imageurl); 
    	}
    	catch(Exception e){
    	}
		return tempImage;
	}
	
}
