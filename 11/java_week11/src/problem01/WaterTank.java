package problem01;

public class WaterTank {
	private int water;
	public WaterTank(int water) { // ���� �� �ʱ�ȭ
		this.water = water;
	}
	public synchronized void drain(int amount) { // amount ��ŭ �� ����
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
		System.out.println("����" + amount +"ml ����, " + water + "ml ����");
	}
	public synchronized void pour(int amount) { // amount ��ŭ �� ����
		water += amount;
		notifyAll();
	}
	public int getWater() { // water ��ȯ
		return water;
	}
}
