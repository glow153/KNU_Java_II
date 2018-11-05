package week02_2.problem01;

public class Main {
	public static void main(String[] args) {
		WordCount wc = new WordCount();
		wc.fileRead("C:/example/about_jdk.txt");
		wc.countWords(true);
	}
}
