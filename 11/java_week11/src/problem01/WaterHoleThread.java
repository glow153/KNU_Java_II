package problem01;

public class WaterHoleThread extends Thread {
	private WaterTank tank; // shared object
	private int amount;
	public WaterHoleThread(WaterTank tank, int amount) {
		// TODO Auto-generated constructor stub
		this.tank = tank;
		this.amount = amount;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			//drain
			tank.drain(amount);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}
