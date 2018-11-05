package week02.problem03;

import java.io.FileReader;
import java.io.IOException;

public class GPACalc {
	private StringBuffer sbFileContent; // ���� ���� ���� ��ü
	public GPACalc() {	
		sbFileContent = new StringBuffer();
	}
	void readSbjGrade(String path) { // 1�б� ���� ���� �б�
		FileReader fr = null;
		try {
			fr = new FileReader(path);
			
			while(true) {
				int c = fr.read();
				if(c == -1) // break if current char is EOF
					break;
				else
					sbFileContent.append((char)c);
			}
//			int c;
//			while((c = fr.read()) != -1) { // read until EOF
//				sbFileContent.append((char)c);
//			}
		} catch (IOException ioe) {
			// TODO: handle exception
			ioe.printStackTrace();
			System.out.println("���� �б⿡ �����Ͽ����ϴ�.");
		} finally {
			if(fr != null) {
				try {
					fr.close();				
				} catch(IOException ioe2) {
					ioe2.printStackTrace();
					System.out.println("���� �ݱ⿡ �����Ͽ����ϴ�.");
				}
			}
		}
	}
	void printContent() { // 1�б� ���� ���
		System.out.println("========= 1�б� ���� =========");
		System.out.println(sbFileContent.toString());
		System.out.println("===========================");
	}
	void printGpa() { // ������� ���
		String[] rows = sbFileContent.toString().split("\n");
		double sumOfGrade = 0;
		int sumOfDegree = 0;
		
		for (String row : rows) {
			int degree = 0;
			double grade = 0.0;
			try {
				String[] saRow = row.split(" ");
				degree = Integer.parseInt(saRow[1]);
				grade = Double.parseDouble(saRow[2]);
				sumOfDegree += degree;
				sumOfGrade += grade * degree;
			} catch (NumberFormatException nfe) {
				System.err.println("���� �б� ����!! - ���� ������ �߸��Ǿ����ϴ�.");
				System.err.println("  ==> " + row);
				nfe.printStackTrace();
			}
		}
		
		System.out.println("1�б� ��������� " + String.format("%.2f", sumOfGrade / sumOfDegree) + "�Դϴ�.");
	}
}
