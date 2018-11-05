package week02.problem02;

public class Main {
	public static void main(String[] args) {
		String content = "";
		MemoWriter mw = new MemoWriter("C:/example/memo.txt");
		mw.fin();
		content = mw.writeMemo();
		mw.fout(content);
	}
}
