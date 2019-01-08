package problem01_e4;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class ClockThread2 extends Thread {
	private RefreshClockCallback callback;
	
	/** Version 5. 독립된 Thread 클래스 직접 정의 + 콜백 구현
	 *  장점 : 뷰와 컨트롤러의 분리로 인하여 결합도가 ver.1,2,3에 비해 낮아짐
	 *         복잡한 UI 및 Thread를 구현하더라도 각각의 유지보수가 용이함
	 *         인터페이스를 통한 뷰의 갱신을 사용하므로 결합도가 낮음
	 *  단점 : 초심자가 이해하기 어려움
	 *         동기화 필수
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
