package week03.problem03;

public class LottoJudge {
	public static int grade(int[] lot, int bonus, int[] entry) {
		int count = 0;
		boolean secondBonus = false;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (lot[i] == entry[j]) {
					count++;
					break;
				}
			}
			if (bonus == entry[i]) {
				secondBonus = true;
			}
		}

		if (count == 3) {
			return 5;
		} else if (count == 4) {
			return 4;
		} else if (count == 5) {
			return 3;
		} else if (count == 5 && secondBonus) {
			return 2;
		} else if (count == 6) {
			return 1;
		} else {
			return 6;
		}
	}
}
