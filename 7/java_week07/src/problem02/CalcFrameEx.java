package problem02;

import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class CalcFrameEx extends CalcFrame {
	protected static String VERSION = "0.2";

	public void addListeners() {
		for (int i = 0; i < jb.length; i++) {
			final int idx = i;

			jb[idx].addActionListener((ActionEvent ae) -> {
				// TODO Auto-generated method stub
				if (0 <= idx && idx <= 9) { // number
					String num = ae.getActionCommand();
					jtf.setText(jtf.getText() + num);
					
				} else if (10 <= idx && idx <= 13) { // operator
					String op = ae.getActionCommand();
					jtf.setText(jtf.getText() + " " + op + " ");
					
				} else if (idx == 14) { // eq
					String formula = jtf.getText().trim();
					double dResult = calculation(formula);
					String sResult = "";
					if (dResult == Double.NaN)
						sResult = "잘못된 수식";
					else
						sResult = Double.toString(dResult);
					
					dlm.addElement(formula + " = " + sResult);
					fWriteRow(formula + " = " + sResult + "\r\n");
					jtf.setText("");
					
				} else if (idx == 15) { // del
					String line = jtf.getText();
					if(line.length() == 0)
						return;
					jtf.setText(line.substring(0, line.length() - 1));
				}
			});
		}
	}

	private double calculation(String formula) {
		String[] tok = formula.split(" ");
		
		if(tok.length % 2 == 0)
			return Double.NaN;
		
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
		return result;
	}
	
	private void fWriteRow(String line) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("calc_history.txt", true);
			fw.write(line);
			fw.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public CalcFrameEx(String title, int w, int h) {
		super(title, w, h);
		setTitle(title + " - v" + VERSION);
		addListeners();
	}
}
