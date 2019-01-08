package problem02;

public class WaterTankEx {
	private int capacity;
	private int water;
	
	private OnWaterPouredListener wpListener;
	private OnWaterDrainedListener wdListener;
	
	public WaterTankEx(int capacity) {
		this.capacity = capacity;
		water = capacity;
	}
	
	public void setOnWaterPouredListener(OnWaterPouredListener listener) {
		this.wpListener = listener;
	}
	
	public void setOnWaterDrainedListener(OnWaterDrainedListener listener) {
		wdListener = listener;
	}
	
//	public void drain(int id, int amount) {
//		if(water < amount) {
//			return;
//		}
//		water -= amount;
//		wdListener.onWaterDrained(id + ") -" + amount +"ml, " + water + "ml 남음");
//	}
	
	public synchronized void drain(int id, int amount) {
		while (water < amount) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		water -= amount;
		wdListener.onWaterDrained(id + ") -" + amount +"ml, " + water + "ml 남음");
	}
	
	public synchronized void pour(int amount) {
		wpListener.onWaterPoured("물 부음 : " + amount);
		if (water + amount > capacity) {
			water = capacity;
			wpListener.onWaterPoured("물 넘침 !!");
		} else {
			water += amount;
		}
		notifyAll();
	}
	
	public int getWater() {
		return water;
	}
}
