package problem01_e4;

import java.awt.BorderLayout;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitalClockFrame4 extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	protected JLabel jl;
	
	protected ClockThread2 clock;

	public static void main(String[] args) {
		new DigitalClockFrame4("디지털 시계", 250, 80);
	}

	public DigitalClockFrame4(String title, int w, int h) {
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
		
		clock = new ClockThread2();
	}

	protected void addComps() {
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jl);
	}

	// "Callback" 구현
	// 컨트롤러가 뷰를 조작할 때 인터페이스를 통과하여야만 수행 가능
	// 뷰의 갱신 명령을 최초로 내린 주체는 컨트롤러(Thread)이지만
	// 실제 뷰의 갱신을 수행한 주체는 뷰 자신이 되므로 결합도가 낮음
	protected void addListeners() {
		clock.setCallback(new RefreshClockCallback() {
			@Override
			public void execute(String s) {
				// TODO Auto-generated method stub
				jl.setText(s);
			}
		});
//		clock.setCallback((String s) -> {
//			jl.setText(s);
//		});
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	protected void initClock() {
		clock.start();
	}
}
