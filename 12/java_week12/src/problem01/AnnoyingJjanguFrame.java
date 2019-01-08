package problem01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnnoyingJjanguFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected String title;
	protected Container ct;
	
	protected JPanel jp;
	protected JButton jbAdd;
	
	protected int count;
	
	public static void main(String[] args){
		new AnnoyingJjanguFrame("정신 사나운 짱구", 800, 600);
	}
	
	public AnnoyingJjanguFrame(String title, int w, int h){
		this.title = title;
		this.width = w;
		this.height = h;
		count = 0;
		
		initComps();
		addComps();
		addListeners();
		initWnd();
	}
	
	protected void initComps() {
		ct = getContentPane();
		jp = new JPanel();
		jbAdd = new JButton("짱구 만들기 (현재 " + count + "개)");
	}
	
	protected void addComps() {
		ct.setLayout(new BorderLayout(5, 5));
		jp.setBackground(new Color(255, 255, 255));
		jp.setLayout(null);
		ct.add(jp, BorderLayout.CENTER);
		ct.add(jbAdd, BorderLayout.SOUTH);
	}
	
	protected void addListeners() {
		jbAdd.addActionListener(ae -> {
			JjanguLabel jj = new JjanguLabel(jp.getWidth(), jp.getHeight());
			jj.setOnJjanguMovedListener(() -> jp.updateUI());
			jp.add(jj);
			new Thread((Runnable)jj).start();
			
			count += 1;
			jbAdd.setText("짱구 만들기 (현재 " + count + "개)");
		});
	}
	
	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
