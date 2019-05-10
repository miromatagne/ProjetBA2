package Model;

public class MovePlayer implements Runnable {
	Player active_player;
	public int posx;
	public int posy;
	public Game game;
	public String key = "cle";
	
	public MovePlayer(Player p, int x, int y, Game G) {
		this.active_player = p;
		this.posx = x;
		this.posy = y;
		this.game = G;
		
	}
	
	public void run() {
		synchronized(key){
			/*try {
				//Thread.sleep(active_player.speed);
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
    int nextX = active_player.getPosX() + posx; //Coordonnées du prochain déplacement (1 case)
    int nextY = active_player.getPosY() + posy;
    boolean obstacle = false;
    for (GameObject object : game.getActiveWindow().getObjects()) {
        if (object.isAtPosition(nextX, nextY)) { //Check si il y a un objet là où il veut se déplacer
            obstacle = object.isObstacle(); //Renvoie true si il y a un objet
        }
        if (obstacle == true) { //On interrompt alors la boucle parce qu'un objet est présent donc on ne peut pas se déplacer là
            break;
        }
    }
    active_player.rotate(posx, posy); //Fait tourner le perso sur lui meme, on voit la petite barre noire qui tourne
    if (obstacle == false) {
        active_player.move(posx,posy);
    }
    game.notifyView();
}
		
}
	
	

	
	

}
