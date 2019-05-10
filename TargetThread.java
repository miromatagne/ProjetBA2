package Model;

public class TargetThread implements Runnable {
	Target t;
	public TargetThread(Target t) {
		this. t = t;
	}

	@Override
	public void run() {
		while(t.getLifepoints() != 0) {
			t.setPosition(t.getPosX() + 1, t.getPosY());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.setPosition(t.getPosX() - 1, t.getPosY());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.setPosition(t.getPosX() - 1, t.getPosY());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.setPosition(t.getPosX() + 1, t.getPosY());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
