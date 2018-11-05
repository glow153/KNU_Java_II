package week02.problem03;

import java.io.FileReader;
import java.io.IOException;

public class GPACalc {
	private StringBuffer sbFileContent; // 파일 내용 저장 객체
	public GPACalc() {	
		sbFileContent = new StringBuffer();
	}
	void readSbjGrade(String path) { // 1학기 성적 파일 읽기
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
			System.out.println("파일 읽기에 실패하였습니다.");
		} finally {
			if(fr != null) {
				try {
					fr.close();				
				} catch(IOException ioe2) {
					ioe2.printStackTrace();
					System.out.println("파일 닫기에 실패하였습니다.");
				}
			}
		}
	}
	void printContent() { // 1학기 성적 출력
		System.out.println("========= 1학기 성적 =========");
		System.out.println(sbFileContent.toString());
		System.out.println("===========================");
	}
	void printGpa() { // 평점평균 출력
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
				System.err.println("파일 읽기 오류!! - 파일 내용이 잘못되었습니다.");
				System.err.println("  ==> " + row);
				nfe.printStackTrace();
			}
		}
		
		System.out.println("1학기 평점평균은 " + String.format("%.2f", sumOfGrade / sumOfDegree) + "입니다.");
	}
}
