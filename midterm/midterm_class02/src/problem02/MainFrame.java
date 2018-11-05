package problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected String title;
	protected Container ct;
	
	protected JList<String> jlist;
	protected DefaultListModel<String> dlm;
	protected JLabel jl;
	protected JTextField[] jtf;
	protected JButton jb;
	protected JTextArea jta;
	
	protected void initComps(){
		ct = getContentPane();
//		dlm = new DefaultListModel<>();
//		jlist = new JList<>(dlm);
		jta = new JTextArea();
		jl = new JLabel();
		jtf = new JTextField[3];
		for (int i=0;i<jtf.length;i++) {
			jtf[i] = new JTextField();
		}
		jb = new JButton("Throw");
		
		jta.append("1 2 3         -> 1 Strike 1 Ball\r\n");
		jta.append("4 5 6         -> 0 Strike 1 Ball\r\n");
		jta.append("7 8 9         -> out\r\n");
		jta.append("1 3 5         -> 1 Strike 0 Ball\r\n");
		jta.append("1 3 5         -> 3 Strike\r\n");
		
//		dlm.addElement("1 2 3         -> 1 Strike 1 Ball");
//		dlm.addElement("4 5 6         -> 0 Strike 1 Ball");
//		dlm.addElement("7 8 9         -> out");
//		dlm.addElement("1 3 5         -> 1 Strike 0 Ball");
		jl.setText("정답입니다!");
	}
	
	protected void addComps(){
		JPanel jp1 = new JPanel();
		jp1.setLayout(new BorderLayout());
		jp1.add(jta, BorderLayout.CENTER);
		jp1.add(jl, BorderLayout.SOUTH);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(1, 4));
		for (int i=0;i<jtf.length;i++) {
			jp2.add(jtf[i]);
		}
		jp2.add(jb);
		
		ct.setLayout(new BorderLayout());
		ct.add(jp1, BorderLayout.CENTER);
		ct.add(jp2, BorderLayout.SOUTH);
	}
	
	protected void addListeners(){
		
	}
	
	protected void initWnd(){
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public MainFrame(String title, int w, int h){
		this.title = title;
		this.width = w;
		this.height = h;
		
		initComps();
		addComps();
		addListeners();
		initWnd();
	}
	
	public static void main(String[] args) {
		new MainFrame("숫자 야구 게임", 300, 300);
	}
}
