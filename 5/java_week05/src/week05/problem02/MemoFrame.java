package week05.problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoFrame extends JFrame {
	protected static final String VERSION = "0.1";
	private int wndSizeWidth;
	private int wndSizeHeight;
	private String title;
	
	private Container ct;
	protected JTextArea jta;
	private JPanel jpMenu, jpTxt;
	protected JButton[] jb;
	protected String[] btnCap;
	
	private void initComps() {
		ct = getContentPane();
		jta = new JTextArea();
		jpMenu = new JPanel();
		jpTxt = new JPanel();
		btnCap = new String[] {"New", "Open", "Save", "Exit"};
		jb = new JButton[btnCap.length];
		for (int i = 0; i < btnCap.length; i++)
			jb[i] = new JButton(btnCap[i]);
	}
	
	private void addComps() {
		// set menu panel
		jpMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		for(int i=0;i<jb.length;i++)
			jpMenu.add(jb[i]);
		
		// set text panel
		jpTxt.setLayout(new BorderLayout(5, 5));
		jta.setLineWrap(true); // line wrapping
		jpTxt.add(jta);
		
		// set scrollable
		JScrollPane jsp = new JScrollPane(jta,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jpTxt.removeAll();
		jpTxt.add(jsp);
		
		// set container
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jpMenu, BorderLayout.NORTH);
		ct.add(jpTxt, BorderLayout.CENTER);
	}

	private void initWnd() {
		setTitle(title + " - v" + VERSION);
		setSize(wndSizeWidth, wndSizeHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void fRead(String path) {
		FileReader fr = null;
		StringBuffer sb = new StringBuffer();
		try {
			int i;
			fr = new FileReader(path);
			while((i=fr.read()) != -1)
				sb.append((char)i);
			fr.close();
		} catch(FileNotFoundException e) {
			jta.setText("파일을 찾을 수 없습니다.");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		jta.setText(sb.toString());
	}
	
	public MemoFrame(String title, int w, int h) {
		this.title = title;
		wndSizeWidth = w;
		wndSizeHeight = h;
		
		initComps();
		addComps();
		initWnd();
	}
}
