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
		System.out.println("********* 초간단 메모장 *********");
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
			System.out.println("파일이 존재하지 않으므로 새로 생성합니다.");
			return;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("파일 읽기에 실패하였습니다.");
		} finally {
			// TODO: handle finally clause
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException ioe2) {
					// TODO: handle exception
					ioe2.printStackTrace();
					System.out.println("파일 닫기에 실패하였습니다.");
				}				
			}
		}
		
		System.out.println("기존 파일 내용 : " + existFileContent);
	}
	
	String writeMemo() {
		BufferedReader br = null;
		String ret = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("메모를 입력하세요(Enter를 치면 종료됩니다). >>> ");
		try {
			ret = br.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("사용자 입력에 실패하였습니다.");
		}
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("입력 스트림 닫기에 실패하였습니다.");
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
			System.out.println("메모 저장에 실패하였습니다.");
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("파일 닫기에 실패하였습니다.");
				}
			}
		}
		System.out.println("메모 저장에 성공하였습니다. --" + path);
	}
}
