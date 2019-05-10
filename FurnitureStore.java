package Model;

import java.awt.Image;

public class FurnitureStore extends Shop {
	static Image image = getImage("Bed.JPG");
	public FurnitureStore(int X, int Y) {
		super(X, Y,image);
		SetShop();
		SetStockandCatalogue(new Bed(1,1), 10);
		SetStockandCatalogue(new Computer(1,1), 10);
		SetStockandCatalogue(new Chair(1,1), 10);
		SetStockandCatalogue(new Table(1,1), 10);
		SetStockandCatalogue(new Couch(1,1), 10);
	}
}
