package problem01_e2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitalClockFrame2 extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	protected JLabel jl;
	
	public static void main(String[] args) {
		new DigitalClockFrame2("디지털 시계", 250, 80);
	}

	public DigitalClockFrame2(String title, int w, int h) {
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
	
	/** Version 3. Lambda Expression을 이용한 익명 객체 생성 및 사용
	 *  장점 : 뷰를 구성하는 객체들을 직접 접근할 수 있어 구현이 간편함 (ver.1과 동일)
	 *         코드 길이가 가장 짧고 이해가 쉬워 아주 간단한 Thread 구현할 때 용이함
	 *  단점 : 뷰와 컨트롤러가 하나의 클래스로 구성됨 (ver.1과 동일)
	 *         ver.1보다 높은 결합도 (ver.2와 동일)
	 *         복잡한 Thread 구성시 코드가 길어짐 (ver.1과 동일)
	 *         ver.2와 달리 Thread의 생성 즉시 사용하고 객체 주소를 hold하지 않으므로 프로그램 끝날 때 까지 종료시킬 수 없음
	 */
	protected void initClock() {
		new Thread(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			while(true) {
				jl.setText(sdf.format(new Date()));
			}
		}).start();
	}
}
