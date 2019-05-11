package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import Model.Player;
import Model.Shop;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;


public class SellListener implements ActionListener {
	Shop shop;
	
	public SellListener(Shop shop) {
	
		this.shop = shop;
	}


	public void actionPerformed(ActionEvent e) {
		shop.Sell();	
		music();
	}
	
	public void music() {
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
		try {
			InputStream test = new FileInputStream("C:\\Users\\Miro-Manuel\\Dropbox\\ULB\\BA2\\Projet Info\\Model\\Money.wav");
			BGM = new AudioStream(test);
			AudioPlayer.player.start(BGM);
			//MD = BGM.getData();
			//loop = new ContinuousAudioDataStream(MD);
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas à cette adresse.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		MGP.start(loop);
		
	}

}
