package Model;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Shower extends BlockUnbreakable implements Activable {
	Player active_player;
	boolean isInS;
	public Shower(int x, int y) {
		super(x,y,1,1);
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		this.active_player.setPosition(this.posX, this.posY);
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
		try {
			InputStream test = new FileInputStream("C:\\Users\\Miro-Manuel\\Dropbox\\ULB\\BA2\\Projet Info\\Model\\Shower.wav");
			BGM = new AudioStream(test);
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas à cette adresse.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		music(true);
		Thread t1 = new Thread(new ShowerThread(active_player, this));
		t1.start();
		if(isInS == false) {
			music(false);
		}
	}

	@Override
	public void updateActivePlayer(Player active_player) {
		// TODO Auto-generated method stub
		this.active_player = active_player;
	}
	
	public void music(boolean a) {
		if(a) {
			AudioPlayer MGP = AudioPlayer.player;
			AudioStream BGM;
			AudioData MD;
			ContinuousAudioDataStream loop = null;
			try {
				InputStream test = new FileInputStream("C:\\Users\\Miro-Manuel\\Dropbox\\ULB\\BA2\\Projet Info\\Model\\Shower.wav");
				BGM = new AudioStream(test);
				AudioPlayer.player.start(BGM);
			} catch (FileNotFoundException e) {
				System.out.println("Le fichier n'existe pas à cette adresse.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MGP.start(loop);
		}
		else {
			AudioPlayer MGP = AudioPlayer.player;
			AudioStream BGM;
			AudioData MD;
			ContinuousAudioDataStream loop = null;
			try {
				InputStream test = new FileInputStream("C:\\Users\\Miro-Manuel\\Dropbox\\ULB\\BA2\\Projet Info\\Model\\Shower.wav");
				BGM = new AudioStream(test);
				AudioPlayer.player.stop(BGM);
			} catch (FileNotFoundException e) {
				System.out.println("Le fichier n'existe pas à cette adresse.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MGP.start(loop);
		}
	}
	
	public void notInS() {
		this.isInS = false;
	}
}
