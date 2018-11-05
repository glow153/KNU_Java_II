package problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalcFrame extends JFrame {
	protected static String VERSION = "0.1";
	private int wndSizeWidth;
	private int wndSizeHeight;
	private String title;
	
	protected Container ct;
//	protected JTextArea jta;
	protected DefaultListModel<String> dlm;
	protected JList<String> jlist;
	protected JTextField jtf;
	protected JPanel jpIO, jpBtns;
	protected JButton[] jb;
	protected String[] btnCap;
	
	private void initComps() {
		ct = getContentPane();
//		jta = new JTextArea();
		dlm = new DefaultListModel<>();
		jlist = new JList<>(dlm);
		jtf = new JTextField();
		jpIO = new JPanel();
		jpBtns = new JPanel();
		btnCap = new String[] {"0", "1", "2", "3", "4",
							   "5", "6", "7", "8", "9",
							   "¡À", "¡¿", "-", "+", "=", "¡ç"};
		jb = new JButton[btnCap.length];
		for (int i = 0; i < btnCap.length; i++)
			jb[i] = new JButton(btnCap[i]);
	}
	
	private void addComps() {
		// set io panel
		jpIO.setLayout(new BorderLayout(5, 5));
//		jpIO.add(jta, BorderLayout.CENTER);
		jpIO.add(jlist, BorderLayout.CENTER);
		jpIO.add(jtf, BorderLayout.SOUTH);
		
		// set btn panel
		int[] idx = {14, 15, 12, 13,
					 7, 8, 9, 10,
					 4, 5, 6, 11,
					 1, 2, 3, 0};
		jpBtns.setLayout(new GridLayout(4, 4, 5, 5));
		for(int i=0;i<jb.length;i++)
			jpBtns.add(jb[idx[i]]);
		
		// set container
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jpIO, BorderLayout.CENTER);
		ct.add(jpBtns, BorderLayout.SOUTH);
	}

	private void initWnd() {
		setTitle(title + " - v" + VERSION);
		setSize(wndSizeWidth, wndSizeHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public CalcFrame(String title, int w, int h) {
		this.title = title;
		wndSizeWidth = w;
		wndSizeHeight = h;
		
		initComps();
		addComps();
		initWnd();
	}
}
