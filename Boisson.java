package Model;

import java.awt.Image;

public class Boisson extends BlockBreakable {
	public double alcool ; 
	static Image image = getImage("Bed.PNG");
	public Boisson(int X, int Y, int couleur, double alcool) {
		super(X, Y, 1, 1, 1, image);
		this.alcool = alcool;
	}
	
	public void activate() {
		this.crush();
		active_player.drink(alcool);	
		System.out.println("Je bois"+ active_player.getAlcoolemie());
		
	}	
	
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}


