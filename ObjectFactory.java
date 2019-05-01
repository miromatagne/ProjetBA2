package Model;

public class ObjectFactory {
	GameObject object = null;
	public GameObject createObject(String o, int x, int y) {
		switch(o) {
			case "V": object = new Vodka(x,y,20); break;
			case "B": object = new Bed(x,y); break;
			case "C": object = new Computer(x,y,20); break;
			case "S": object = new Shower(x,y); break;
			case "T": object = new Toilet(x,y); break;
			case "A": object = new Apple(x,y); break;
			case "P": object = new Tree(x,y); break;
			case "U": object = new BlockUnbreakable(x,y);
		}
		return object;
	}
}
