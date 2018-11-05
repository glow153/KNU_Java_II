package week02_2.problem03;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Encryptor {
	protected StringBuffer sbContent;
	protected String infile;
	protected String outfile;
	public Encryptor(String infile, String outfile) {
		sbContent = new StringBuffer();
		this.infile = infile; 
		this.outfile = outfile;
	}
	protected void fileRead() { // 파일 읽기 (infile)
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
			System.out.println("입력 파일 내용 : " + sbContent.toString().substring(0, 50) + " ...");
		}
	}
	protected void fileWrite() { // 파일 쓰기 (outfile)
		FileWriter fw = null;
		try {
			fw = new FileWriter(outfile);
			fw.write(sbContent.toString());
		} catch (IOException ioe) {
			// TODO: handle exception
			ioe.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			System.out.println("출력 파일 내용 : " + sbContent.toString().substring(0, 50) + " ...");
		}
	}
	protected void encryptContent() { // 파일 암호화
		for(int i=0;i<sbContent.length();i++) {
			char c = Character.toLowerCase(sbContent.charAt(i));
			switch(c) {
			case 'a':
				sbContent.setCharAt(i, '4');
				break;
			case 'b':
				sbContent.setCharAt(i, '8');
				break;
			case 'c':
				sbContent.setCharAt(i, '(');
				break;
			case 'e':
				sbContent.setCharAt(i, '3');
				break;
			case 'g':
				sbContent.setCharAt(i, '9');
				break;
			case 'i':
				sbContent.setCharAt(i, '1');
				break;
			case 'l':
				sbContent.setCharAt(i, '|');
				break;
			case 'o':
				sbContent.setCharAt(i, '0');
				break;
			case 's':
				sbContent.setCharAt(i, '5');
				break;
			case 't':
				sbContent.setCharAt(i, '7');
				break;
			case 'z':
				sbContent.setCharAt(i, '2');
				break;
			}
		}
	}
	public void enc() { // template method pattern
		fileRead();
		encryptContent();
		fileWrite();
	}
}

