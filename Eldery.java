package Model;

import java.awt.Image;

public class Eldery extends Player implements Worker{
	public Eldery(int x, int y) {
		super(x,y);
		Thread t1 = new Thread(new HealthChrono(this));
		t1.start();
	}
	int health = 100;
	int min = 1;
	int sec = 0;
	
	public void gainHealth(int i) {
		this.min = 1;
		this.sec = 0;
		if (this.health >= 100-i) {
			this.health = 100;
		}
		else {
			this.health += i;
		}
	}
	public void loseHealth() {
		this.min = 1;
		this.sec = 0;
		if(this.health < 50) {
			this.health = 0;
		}
		else {
			this.health -= 50;
		}
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getMin() {
		return this.min;
	}
	
	public int getSec() {
		return this.sec;
	}
	
	public void updateTime() {
		if(this.min == 0 && this.sec == 0) {
			loseHealth();
		}
		if(this.sec == 0) {
			this.min -= 1;
			this.sec = 59;
		}
		else {
			this.sec -= 1;
		}
	}
}
