package Model;

public abstract class GameObject {
    protected int posX; //seule cette classe et les classes fille ont accès à ces objets
    protected int posY;
    protected int color;

    public GameObject(int X, int Y, int color) {
        this.posX = X;
        this.posY = Y;
        this.color = color;
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
        return this.posX == x && this.posY == y;
    }
    
    public void setPosition(int x, int y) {
    	this.posX = x;
    	this.posY = y;
    }

    public abstract boolean isObstacle();

	public abstract boolean isAddable();
}
