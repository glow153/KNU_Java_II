package problem01_e3;

import java.awt.BorderLayout;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitalClockFrame3 extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	protected JLabel jl;
	
	protected ClockThread clock;

	public static void main(String[] args) {
		new DigitalClockFrame3("������ �ð�", 250, 80);
	}
	
	public DigitalClockFrame3(String title, int w, int h) {
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
	
	protected void initClock() {
		clock = new ClockThread(jl); // Thread�� ���� ������ �� ������Ҹ� ���ڷ� ���� -> ���յ� ������
		clock.start();
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
