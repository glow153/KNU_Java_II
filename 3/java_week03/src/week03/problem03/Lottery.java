package week03.problem03;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lottery {
	protected Random rand;
	protected int[] lot;
	protected int secondBonusBall;
	protected int[] entry;

	public Lottery() {
		rand = new Random();
		lot = new int[6];
		entry = new int[6];
	}
	protected void generate() { // 당첨 번호 생성
		// 조합 뽑기
		int[] src = new int[45];
		Random r = new Random();
		for (int i = 0; i < src.length; i++)
			src[i] = i + 1;

		// shuffle
		int shfCnt = r.nextInt(100);
		for (int i = 0; i < shfCnt; i++) {
			int a = r.nextInt(45);
			int b = r.nextInt(45);
			int tmp = src[a];
			src[a] = src[b];
			src[b] = tmp;
		}

		// choose
		for (int i = 0; i < lot.length; i++)
			lot[i] = src[i];

		secondBonusBall = src[6];
	}
	protected void inputEntry() { // 응모 번호 입력
		Scanner sc = new Scanner(System.in);
		String input;
		String[] inputSplit;

		System.out.println("***** 로또 추첨기 *****");
		while (true) {
			System.out.print("응모 번호를 입력하세요 (1~45, 6개) : ");
			input = sc.nextLine();
			inputSplit = input.split(" ");
			if (inputSplit.length == 6) {
				break;
			} else {
				System.out.println("응모번호는 1~45까지 6개만 입력 가능합니다.");
			}
		}

		for (int i = 0; i < entry.length; i++) {
			entry[i] = Integer.parseInt(inputSplit[i]);
		}
		sc.close();
	}
	protected void printResult(int grade) { // 당첨 결과 출력
		StringBuffer sbTries = new StringBuffer();
		StringBuffer sbWins = new StringBuffer();
		
		sbTries.append("당첨 번호 : ")
			   .append(arrToStr(lot))
			   .append(", 2등 보너스볼 : " + secondBonusBall)
			   .append("\r\n")
			   .append("응모 번호 : ")
			   .append(arrToStr(entry));

		if (grade == 6) {
			sbTries.append(" => 꽝입니다.\r\n");
		} else {
			sbTries.append(" => 축하합니다! " + grade + "등입니다!\r\n");
			sbWins.append(sbTries);
		}

		// print to console
		System.out.println(sbTries.toString());

		// print to files
		FileWriter fwt = null;
		FileWriter fww = null;
		try {
			fwt = new FileWriter("C:/example/tries.txt", true); // append
			fww = new FileWriter("C:/example/wins.txt", true);
			fwt.write(sbTries.toString());
			fww.write(sbWins.toString());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (fwt != null) {
				try {
					fwt.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fww != null) {
				try {
					fww.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	protected String arrToStr(int[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length - 1; i++)
			sb.append(arr[i] + " ");
		sb.append(arr[arr.length - 1]);
		return sb.toString();
	}
	public void playLottery() { // template method pattern
		inputEntry();
		generate();
		Arrays.sort(lot); // 오름차순 정렬
		Arrays.sort(entry);
		int grade = LottoJudge.grade(lot, secondBonusBall, entry); // judge
		printResult(grade);
	}
}
