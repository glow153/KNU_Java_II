package week02.problem01;

public class Main {
	public static void main(String[] args) {
		FileCopyManager fcm = new FileCopyManager();
		fcm.fileRead("C:/example/wisdom.txt", true);
		fcm.fileCopy("C:/example/wisdom_2.txt");
//		fcm.fileRead("C:/example/¿Á»ó´Þºû_1Áý_¼ö°íÇß¾î_¿À´Ãµµ.mp3", false);
//		fcm.fileCopy("C:/example/¿Á»ó´Þºû_1Áý_¼ö°íÇß¾î_¿À´Ãµµ_2.mp3");
	}
}
