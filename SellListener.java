package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import Model.Player;
import Model.Shop;


public class SellListener implements ActionListener {
	Shop shop;
	
	public SellListener(Shop shop) {
	
		this.shop = shop;
	}


	public void actionPerformed(ActionEvent e) {
		shop.Sell();
		
		
		
		
	}

}
