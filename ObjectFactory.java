package Model;

public class ObjectFactory {
	GameObject object = null;
	public GameObject createObject(String o, int x, int y)  { //ajouter throw Exception
		
		//if(x < 0) {
		//	throw new Exception("x est négatif");
		//}
		switch(o) {
			case "V": object = new Vodka(x,y,20); break;
			case "B": object = new Bed(x,y); break;
			case "C": object = new Computer(x,y); break;
			case "S": object = new Shower(x,y); break;
			case "T": object = new Toilet(x,y); break;
			case "A": object = new Apple(x,y); break;
			case "P": object = new Tree(x,y); break;
			case "U": object = new Mur(x,y); break;
			case "Y": object = null; break;
			case "M": object = new Pill(x,y); break;
			case "F": object = new Panade(x,y);
			case "G": object = new Gun(x,y); break;
			case "X": object = new Fridge(x,y);break;
			case "O": object = new Target(x,y); break;
			case "Q": object = new FurnitureStore(x,y); break;
		}
		return object;
	}
}
