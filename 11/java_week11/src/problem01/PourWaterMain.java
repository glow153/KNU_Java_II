package problem01;

import java.util.Scanner;

public class PourWaterMain {
	public static void main(String[] args) {
		WaterTank tank = new WaterTank(1000);
		WaterHoleThread hole = new WaterHoleThread(tank, 100);
		Scanner sc = new Scanner(System.in);
		
		hole.start();
		
		System.out.println("========== �غ��� ���� �� �ױ� ==========");
		System.out.println("1 : �� �ٴڿ� ���� �ձ�");
		System.out.println("2 : �� �ױ� (500ml)");
		
		while(true) {
			switch(sc.nextInt()) {
			case 1:
				new WaterHoleThread(tank, 100).start();
				System.out.println(">> ���� �߰���");
				break;
			case 2:
				tank.pour(1000000);
				System.out.println(">> �� �ױ� (+500ml) : " + tank.getWater());
				break;
			}
		}
	}
}
