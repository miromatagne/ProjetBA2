package Model;

public class HealthChrono implements Runnable {
	Eldery e;
	public HealthChrono(Eldery e) {
		this.e = e;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e.updateTime();
		}
	}

}
