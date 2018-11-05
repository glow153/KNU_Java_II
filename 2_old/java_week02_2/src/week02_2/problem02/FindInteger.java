package week02_2.problem02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindInteger {
	private StringBuffer sbFileContent;
	public FindInteger() {	// ������
		System.out.println("=========== ���� ã�� ===========");
	}
	void fRead(String filepath) {	// ���� �о sbFileContent�� ����
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
			System.err.println("������ ã�� �� �����ϴ�.");
			fnfe.printStackTrace();
			return;
		} catch (IOException ioe) {
			System.err.println("���� �б⿡ �����Ͽ����ϴ�.");
			ioe.printStackTrace();
			return;
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException ioe2) {
					System.err.println("���� �ݱ⿡ �����Ͽ����ϴ�.");
					ioe2.printStackTrace();
				}
			}

			System.out.println("���� ���� : ");
			System.out.println(sbFileContent.toString().substring(0, 30) + " ...");
		}
	}
	void findInteger() {	// ������ �ܾ� ã�� ���
		String[] rows = sbFileContent.toString().split("\n"); // split by row
		int count = 0;
		for (int i = 0; i < rows.length; i++) {
			String[] words = rows[i].split(" "); // split by column
			for (int j = 0; j < words.length; j++) {
				try {
					Integer.parseInt(words[j]);
					// if exception is not threw
					System.out.println("���� : " + words[j] + " (" + i + "�� " + j + "��)");
					count += 1;
				} catch (NumberFormatException nfe) { // if exception threw
					continue;
				}
			}
		}
		System.out.println("������ �� " + count + "�� �Դϴ�.");
	}
}
