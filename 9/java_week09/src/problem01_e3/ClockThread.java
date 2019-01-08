package problem01_e3;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class ClockThread extends Thread {
	private JLabel jl;

	/** Version 4. 독립된 Thread 클래스 직접 정의
	 *  장점 : 뷰와 컨트롤러의 분리로 인하여 결합도가 ver.1,2,3에 비해 낮아짐
	 *         복잡한 UI 및 Thread를 구현하더라도 각각의 유지보수가 용이함
	 *  단점 : 컨트롤러가 뷰의 구성요소를 인자로 전달받아 직접 제어 -> 결합도가 높아짐
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
