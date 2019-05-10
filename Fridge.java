package Model;

import java.awt.Image;

public class Fridge extends Shop {

	static Image image = getImage("Bed.JPG");
	public Fridge(int X, int Y) {
		super(X, Y,image);
		SetShop();
		SetStockandCatalogue(new Avocado(1,1), 10);
		SetStockandCatalogue(new Apple(1,1), 10);
		SetStockandCatalogue(new Bread(1,1), 10);
		SetStockandCatalogue(new Cheese(1,1), 10);
		SetStockandCatalogue(new Cherry(1,1), 10);
		SetStockandCatalogue(new Chicken(1,1), 10);
		SetStockandCatalogue(new Cookie(1,1), 10);
		SetStockandCatalogue(new Panade(1,1), 10);
		SetStockandCatalogue(new Shrimp(1,1), 10);
		SetStockandCatalogue(new Strawberry(1,1), 10);
		SetStockandCatalogue(new Tomato(1,1), 10);
		SetStockandCatalogue(new Watermelon(1,1), 10);
	}

}
