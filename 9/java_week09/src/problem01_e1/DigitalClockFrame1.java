package problem01_e1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitalClockFrame1 extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	protected JLabel jl;
	
	public static void main(String[] args) {
		new DigitalClockFrame1("������ �ð�", 250, 80);
	}

	public DigitalClockFrame1(String title, int w, int h) {
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

	/** Version 2. Runnable �������̽��� �͸� ���� ��ü
	 *  ���� : �並 �����ϴ� ��ü���� ���� ������ �� �־� ������ ������ (ver.1�� ����)
	 *         �ڵ� ���̰� ª�� ���ذ� ���� ������ Thread ������ �� ������
	 *  ���� : ��� ��Ʈ�ѷ��� �ϳ��� Ŭ������ ������ (ver.1�� ����)
	 *         ver.1���� ���� ���յ�
	 *         ������ Thread ������ �ڵ尡 ����� (ver.1�� ����)
	 */
	protected void initClock() {
		Thread clock = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				while(true) {
					jl.setText(sdf.format(new Date()));
				}
			}
		});
		
		clock.start();
	}
}
