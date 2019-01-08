package problem01;

import java.util.Scanner;

public class PourWaterMain {
	public static void main(String[] args) {
		WaterTank tank = new WaterTank(1000);
		WaterHoleThread hole = new WaterHoleThread(tank, 100);
		Scanner sc = new Scanner(System.in);
		
		hole.start();
		
		System.out.println("========== ¹ØºüÁø µ¶¿¡ ¹° º×±â ==========");
		System.out.println("1 : ¹Ø ¹Ù´Ú¿¡ ±¸¸Û ¶Õ±â");
		System.out.println("2 : ¹° º×±â (500ml)");
		
		while(true) {
			switch(sc.nextInt()) {
			case 1:
				new WaterHoleThread(tank, 100).start();
				System.out.println(">> ±¸¸Û Ãß°¡µÊ");
				break;
			case 2:
				tank.pour(1000000);
				System.out.println(">> ¹° º×±â (+500ml) : " + tank.getWater());
				break;
			}
		}
	}
}
