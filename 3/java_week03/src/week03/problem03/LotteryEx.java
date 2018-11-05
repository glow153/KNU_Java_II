package week03.problem03;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class LotteryEx extends Lottery {
	protected void inputEntry(boolean auto) { // ���� ��ȣ �Է�, �ڵ����
		Scanner sc = new Scanner(System.in);
		String input;
		String[] inputSplit;
		
		System.out.println("***** �ζ� ��÷�� *****");
		while (true) {
			System.out.print("���� ��ȣ�� �Է��ϼ��� (1~45, 6��) : ");
			if (auto) {
				// auto generating mode
				generate(entry);
				System.out.println(arrToStr(entry));
				return;
			} else {
				input = sc.nextLine();
				inputSplit = input.split(" ");
				if (inputSplit.length == 6) {
					break;
				} else {
					System.out.println("�����ȣ�� 1~45���� 6���� �Է� �����մϴ�.");
				}
			}
		}
		
		for (int i = 0; i < entry.length; i++) {
			entry[i] = Integer.parseInt(inputSplit[i]);
		}
		sc.close();
	}
	
	protected void generate(int[] target) { // ���� �̱�, �ٸ� ���
		LinkedList<Integer> src = new LinkedList<>();
		Random r = new Random();
		for (int i = 0; i < 45; i++)
			src.addLast(i + 1);
		
		// choose
		for (int i = 0; i < target.length; i++)
			target[i] = src.remove(r.nextInt(44 - i));
		
		secondBonusBall = src.remove(r.nextInt(39));
	}
	
	
	public void playLottery(boolean auto) { // template method pattern
		inputEntry(auto);
		generate(lot);
		Arrays.sort(lot);
		Arrays.sort(entry);
		int grade = LottoJudge.grade(lot, secondBonusBall, entry); // judge
		printResult(grade);
	}
}
