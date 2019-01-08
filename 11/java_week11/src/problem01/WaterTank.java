package problem01;

public class WaterTank {
	private int water;
	public WaterTank(int water) { // 물의 양 초기화
		this.water = water;
	}
	public synchronized void drain(int amount) { // amount 만큼 물 빠짐
		while (water < amount) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		water -= amount;

		//print
		System.out.println("물이" + amount +"ml 빠짐, " + water + "ml 남음");
	}
	public synchronized void pour(int amount) { // amount 만큼 물 보충
		water += amount;
		notifyAll();
	}
	public int getWater() { // water 반환
		return water;
	}
}
