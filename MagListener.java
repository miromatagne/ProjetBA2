package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import Model.Shop;
import View.Mag;

public class MagListener implements MouseListener, Serializable{
	private Mag mag;
	private int BLOCLENGHT = 100 ;
	private int BLOCKHIGHT = 40 ;
	
	
	public MagListener(Mag m) {
		this.mag = m;
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX()/BLOCLENGHT ;
		int y = e.getY()/BLOCKHIGHT; 
		mag.setPositionSelec(y);
		
		mag.getShop().j.repaint();
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
