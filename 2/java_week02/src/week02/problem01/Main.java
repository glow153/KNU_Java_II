package week02.problem01;

public class Main {
	public static void main(String[] args) {
		FileCopyManager fcm = new FileCopyManager();
		fcm.fileRead("C:/example/wisdom.txt", true);
		fcm.fileCopy("C:/example/wisdom_2.txt");
//		fcm.fileRead("C:/example/����޺�_1��_�����߾�_���õ�.mp3", false);
//		fcm.fileCopy("C:/example/����޺�_1��_�����߾�_���õ�_2.mp3");
	}
}
