package Model;

import java.awt.Image;

public class FurnitureStore extends Shop {
	public FurnitureStore(int X, int Y) {
		super(X, Y);
		SetShop();
		SetStockandCatalogue(new Bed(1,1), 10);
		SetStockandCatalogue(new Computer(1,1), 10);
		SetStockandCatalogue(new Chair(1,1), 10);
		SetStockandCatalogue(new Table(1,1), 10);
		SetStockandCatalogue(new Couch(1,1), 10);
	}
}
