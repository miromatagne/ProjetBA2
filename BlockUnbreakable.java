package Model;

import java.awt.Image;

public class BlockUnbreakable extends Block {

    public BlockUnbreakable(int X, int Y, int length, int width) {
        super(X, Y, length, width);
    }

    @Override
    public boolean isObstacle() {
        return true; //Ce sont des blocs que l'on ne peut pas casser, mais ça reste des obstacles
    }

	@Override
	public boolean isAddable() {
		// TODO Auto-generated method stub
		return false;
	}
}
