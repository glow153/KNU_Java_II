package problem01;

import java.awt.BorderLayout;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitalClockFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	protected JLabel jl;
	
	protected ClockThread clock;

	public static void main(String[] args) {
		new DigitalClockFrame("디지털 시계", 250, 80);
	}
	
	public DigitalClockFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
		
		initClock();
	}

	protected void initComps() {
		ct = getContentPane();
		jl = new JLabel("", JLabel.CENTER);
	}

	protected void addComps() {
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jl);
	}

	protected void addListeners() {

	}
	
	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	protected void initClock() {
		clock = new ClockThread();
		clock.start();
	}
	
	/** Version 1. Thread 클래스를 상속받은 inner class
	 *  장점 : 뷰(윈도우의 시각적 구성요소 UI)를 구성하는 객체들을 직접 접근할 수 있어 구현이 간편함
	 *  단점 : 뷰와 컨트롤러(프로그램 내부 제어 로직)가 하나의 클래스로 구성됨
	 *         위와 같은 이유로 높은 결합도를 지님
	 *         복잡한 Thread 구성시 코드가 길어짐
	 */
	private class ClockThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			while(true) {
				jl.setText(sdf.format(new Date()));
			}
		}
	}
}
