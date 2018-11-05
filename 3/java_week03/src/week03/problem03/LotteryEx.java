package week03.problem03;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class LotteryEx extends Lottery {
	protected void inputEntry(boolean auto) { // 응모 번호 입력, 자동기능
		Scanner sc = new Scanner(System.in);
		String input;
		String[] inputSplit;
		
		System.out.println("***** 로또 추첨기 *****");
		while (true) {
			System.out.print("응모 번호를 입력하세요 (1~45, 6개) : ");
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
					System.out.println("응모번호는 1~45까지 6개만 입력 가능합니다.");
				}
			}
		}
		
		for (int i = 0; i < entry.length; i++) {
			entry[i] = Integer.parseInt(inputSplit[i]);
		}
		sc.close();
	}
	
	protected void generate(int[] target) { // 조합 뽑기, 다른 방법
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
