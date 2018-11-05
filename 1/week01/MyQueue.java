package week01;

public class MyQueue {
	private int[] queue;
	private int left;
	private int right;

	public MyQueue() {
		queue = new int[10];
		left = 0;
		right = 0;
	}

	void offer(int n) {
		queue[right] = n;
		right += 1;
		printQueue();
	}

	int poll() {
		int ret = queue[left];
		left += 1;
		printQueue();
		return ret;
	}

	private void printQueue() {
		System.out.print("idx:");
		for (int i = 0; i < 10; i++) {
			System.out.print(" "+i);
		}
		System.out.println();
		System.out.print("que:");
		for (int i = 0; i < 10; i++) {
			if(i < left || i >= right)
				System.out.print(" X");
			else
				System.out.print(String.format("%2d", queue[i]));
		}
		System.out.println();
		System.out.println();
	}
	
	
}
