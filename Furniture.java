package Model;

import java.awt.Image;

public class Furniture extends BlockUnbreakable implements SBableObject{
	String name;
	int price;
	public Furniture(int x, int y, int length, int width, String name, int price) {
		super(x,y,length,width);
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return true;
	}
}
