package Model;

public class BlockUnbreakable extends Block {

    public BlockUnbreakable(int X, int Y) {
        super(X, Y, 0);
    }
    public BlockUnbreakable(int X, int Y, int color) {
        super(X, Y, color);
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
