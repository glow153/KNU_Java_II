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
		new DigitalClockFrame("������ �ð�", 250, 80);
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
	
	/** Version 1. Thread Ŭ������ ��ӹ��� inner class
	 *  ���� : ��(�������� �ð��� ������� UI)�� �����ϴ� ��ü���� ���� ������ �� �־� ������ ������
	 *  ���� : ��� ��Ʈ�ѷ�(���α׷� ���� ���� ����)�� �ϳ��� Ŭ������ ������
	 *         ���� ���� ������ ���� ���յ��� ����
	 *         ������ Thread ������ �ڵ尡 �����
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
