package week01;

public class Main {
	void batch() {
		MyQueue q = new MyQueue();
		q.offer(1);
		q.offer(2);
		q.offer(3);
		q.poll();
		q.poll();
		q.offer(4);
		q.offer(5);
		q.offer(6);
		q.offer(7);
		q.poll();
		q.poll();
		q.offer(8);
		q.offer(9);
		q.offer(1);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.batch();
	}
}
