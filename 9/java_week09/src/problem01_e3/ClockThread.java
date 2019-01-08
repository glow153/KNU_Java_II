package problem01_e3;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class ClockThread extends Thread {
	private JLabel jl;

	/** Version 4. ������ Thread Ŭ���� ���� ����
	 *  ���� : ��� ��Ʈ�ѷ��� �и��� ���Ͽ� ���յ��� ver.1,2,3�� ���� ������
	 *         ������ UI �� Thread�� �����ϴ��� ������ ���������� ������
	 *  ���� : ��Ʈ�ѷ��� ���� ������Ҹ� ���ڷ� ���޹޾� ���� ���� -> ���յ��� ������
	 */
	public ClockThread (JLabel jl) {
		this.jl = jl;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		while(true) {
			jl.setText(sdf.format(new Date()));
		}
	}
}
