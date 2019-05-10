package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import Model.Player;
import Model.Shop;

public class BuyListener implements ActionListener,Serializable{
    Shop shop;
	
	public BuyListener(Shop shop) {
		this.shop = shop;
	}


    public void actionPerformed(ActionEvent e) {
    	shop.mag.Buy();
		
		
	}  



}
