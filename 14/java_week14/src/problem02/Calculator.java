package problem02;

public class Calculator {
	public String calculation(String formula) {
		String[] tok = formula.split(" ");
		
		if(tok.length % 2 == 0)
			return "잘못된 수식입니다.";
		try {
			double result = Double.parseDouble(tok[0]);
			for (int i = 1; i < tok.length; i += 2) {
				if (tok[i].equals("+")) {
					result += Double.parseDouble(tok[i + 1]);
				} else if (tok[i].equals("-")) {
					result -= Double.parseDouble(tok[i + 1]);
				} else if (tok[i].equals("×") || tok[i].equals("*")) {
					result *= Double.parseDouble(tok[i + 1]);
				} else if (tok[i].equals("÷") || tok[i].equals("/")) {
					double operand = Double.parseDouble(tok[i + 1]);
					if (operand != 0.0)
						result /= operand;
					else
						result = Double.NaN;
				}
			}
			return formula + " = " + String.format("%.2f", result);
		} catch (Exception e) {
			return "잘못된 수식입니다.";
		}
	}
}
