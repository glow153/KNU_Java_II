package problem02;

public class WaterHoleThreadEx extends Thread {
	private WaterTankEx tank;
	private int id;
	private int amount;
	
	public WaterHoleThreadEx(int id, WaterTankEx tank, int drainAmount) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.tank = tank;
		this.amount = drainAmount;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			//drain
			tank.drain(id, amount);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}
