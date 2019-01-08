package problem01_e4;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class ClockThread2 extends Thread {
	private RefreshClockCallback callback;
	
	/** Version 5. ������ Thread Ŭ���� ���� ���� + �ݹ� ����
	 *  ���� : ��� ��Ʈ�ѷ��� �и��� ���Ͽ� ���յ��� ver.1,2,3�� ���� ������
	 *         ������ UI �� Thread�� �����ϴ��� ������ ���������� ������
	 *         �������̽��� ���� ���� ������ ����ϹǷ� ���յ��� ����
	 *  ���� : �ʽ��ڰ� �����ϱ� �����
	 *         ����ȭ �ʼ�
	 */
	public ClockThread2 () {
	}
	
	public void setCallback(RefreshClockCallback callback) {
		this.callback = callback;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		while(true) {
			callback.execute(sdf.format(new Date()));
		}
	}
}
