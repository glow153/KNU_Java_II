package week02.problem02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MemoWriter {
	private String path = null;
	public MemoWriter(String filepath) {
		this.path = filepath;
		System.out.println("********* �ʰ��� �޸��� *********");
	}

	void fin() {
		FileReader fr = null;
		String existFileContent = "";
		
		try {
			int c;
			fr = new FileReader(path);
			while( (c = fr.read()) != -1 )
				existFileContent += (char)c;
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("������ �������� �����Ƿ� ���� �����մϴ�.");
			return;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("���� �б⿡ �����Ͽ����ϴ�.");
		} finally {
			// TODO: handle finally clause
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException ioe2) {
					// TODO: handle exception
					ioe2.printStackTrace();
					System.out.println("���� �ݱ⿡ �����Ͽ����ϴ�.");
				}				
			}
		}
		
		System.out.println("���� ���� ���� : " + existFileContent);
	}
	
	String writeMemo() {
		BufferedReader br = null;
		String ret = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("�޸� �Է��ϼ���(Enter�� ġ�� ����˴ϴ�). >>> ");
		try {
			ret = br.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("����� �Է¿� �����Ͽ����ϴ�.");
		}
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�Է� ��Ʈ�� �ݱ⿡ �����Ͽ����ϴ�.");
		}
		return ret;
	}
	
	void fout(String content) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
			fw.write(content);
		} catch (IOException ioe) {
			// TODO: handle exception
			ioe.printStackTrace();
			System.out.println("�޸� ���忡 �����Ͽ����ϴ�.");
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("���� �ݱ⿡ �����Ͽ����ϴ�.");
				}
			}
		}
		System.out.println("�޸� ���忡 �����Ͽ����ϴ�. --" + path);
	}
}
