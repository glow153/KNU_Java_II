package week02_2.problem02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindInteger {
	private StringBuffer sbFileContent;
	public FindInteger() {	// 생성자
		System.out.println("=========== 정수 찾기 ===========");
	}
	void fRead(String filepath) {	// 파일 읽어서 sbFileContent에 저장
		BufferedReader br = null;
		sbFileContent = new StringBuffer();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(
									new FileInputStream(filepath), "UTF-8"));
			while ((line = br.readLine()) != null) { // EOF
				sbFileContent.append(line + "\n");
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println("파일을 찾을 수 없습니다.");
			fnfe.printStackTrace();
			return;
		} catch (IOException ioe) {
			System.err.println("파일 읽기에 실패하였습니다.");
			ioe.printStackTrace();
			return;
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException ioe2) {
					System.err.println("파일 닫기에 실패하였습니다.");
					ioe2.printStackTrace();
				}
			}

			System.out.println("파일 내용 : ");
			System.out.println(sbFileContent.toString().substring(0, 30) + " ...");
		}
	}
	void findInteger() {	// 숫자인 단어 찾고 출력
		String[] rows = sbFileContent.toString().split("\n"); // split by row
		int count = 0;
		for (int i = 0; i < rows.length; i++) {
			String[] words = rows[i].split(" "); // split by column
			for (int j = 0; j < words.length; j++) {
				try {
					Integer.parseInt(words[j]);
					// if exception is not threw
					System.out.println("정수 : " + words[j] + " (" + i + "행 " + j + "열)");
					count += 1;
				} catch (NumberFormatException nfe) { // if exception threw
					continue;
				}
			}
		}
		System.out.println("정수는 총 " + count + "개 입니다.");
	}
}
