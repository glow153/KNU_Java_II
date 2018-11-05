package week02.problem01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyManager {
	private FileInputStream fis;
	private FileOutputStream fos;
	private byte[] content;
	
	public FileCopyManager() {
		System.out.println("********* 파일 복사기 *********");
	}
	
	void fileRead(String inpath, boolean printContent) {
		try {
			fis = new FileInputStream(inpath);
			int length = fis.available();
			content = new byte[length];
			fis.read(content);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("파일 읽기에 실패하였습니다.");
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
					System.out.println("파일 닫기에 실패하였습니다.");
				}
			}
		}
		
		if(printContent) {
			System.out.println("파일 내용 : ");
			try {
				System.out.write(content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		}
	}
	
	void fileCopy(String outpath) {
		try {
			fos = new FileOutputStream(outpath);
			fos.write(content);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("파일 읽기에 실패하였습니다.");
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
					System.out.println("파일 닫기에 실패하였습니다.");
				}
			}
		}
		
		System.out.println("파일 복사가 완료되었습니다. - " + outpath);
	}
}
