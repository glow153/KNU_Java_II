package week02_2.problem01;

import java.io.FileReader;
import java.io.IOException;

public class WordCount {
	private StringBuffer sbContent;
	
	public WordCount() {
		sbContent = new StringBuffer();
	}
	
	void fileRead(String infile) { // 파일 읽기
		FileReader fr = null;
		int c;
		try {
			fr = new FileReader(infile);
			while((c = fr.read()) != -1)
				sbContent.append((char)c);
		} catch (IOException ioe) {
			// TODO: handle exception
			ioe.printStackTrace();
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	void countWords(boolean dbgPrint) {
		String content = sbContent.toString()
//								  .replaceAll("\n", " ");
								  .replaceAll("\r\n", " ");
		String[] words = content.split(" ");
		if(dbgPrint) {
			System.out.println(content);
			for (int i=0;i<words.length;i++) {
//				System.out.println((i+1) + ":" + words[i]);
				System.out.println((i+1) + ":" + words[i].trim());
			}
		}
		System.out.println("단어의 개수는 " + words.length + "개 입니다.");
	}
}
