package problem01;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
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
	
	protected JTextArea jta;
	protected JList<String> jlist;
	protected DefaultListModel<String> dlm;
	protected JButton jb[]; 
	protected JTextField jtfFilepath;
	protected String[] caption = {"파일 불러오기", "단어별 개수 세기", "결과 파일로 내보내기"};
	
	protected String[] words;
	protected int[] counts;
	protected int wordsPtr = 0;
	
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
		new MainFrame("단어의 개수 세기", 400, 280);
	}
	
	protected void initComps(){
		ct = getContentPane();
		dlm = new DefaultListModel<>();
		jlist = new JList<>(dlm);
		jb = new JButton[3];
		for (int i=0;i<jb.length;i++) {
			jb[i] = new JButton(caption[i]);
		}
		jta = new JTextArea();
		jtfFilepath = new JTextField();
		
		words = new String[3000];
		counts = new int[3000];
	}
	
	protected void addComps(){
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(1, 2, 10, 10));
		jp1.add(jta);
		jp1.add(jlist);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(2, 2));
		jp2.add(jtfFilepath);
		for (int i=0;i<jb.length;i++) {
			jp2.add(jb[i]);
		}
		
		ct.setLayout(new BorderLayout());
		ct.add(jp1, BorderLayout.CENTER);
		ct.add(jp2, BorderLayout.SOUTH);
	}
	
	protected void addListeners(){
		jb[0].addActionListener((ActionEvent ae) -> {
			jta.setText(fread());
		});
		
		jb[1].addActionListener((ActionEvent ae) -> {
			wordCount();
			mapToList();
		});

		jb[2].addActionListener((ActionEvent ae) -> {
			fwrite(listToStr());
		});
	}
	
	private void wordCount() {
		String[] content = jta.getText().replaceAll("\r\n", " ").split(" ");
		a:for(String word : content) {
			b:for(int i=0;i<wordsPtr;i++) {
				if(words[i].equals(word)) {
					counts[i]++;
					continue a;
				}
			}
			words[wordsPtr] = word;
			counts[wordsPtr] = 1;
			wordsPtr++;
		}
	}
	
	private void mapToList() {
		for(int i=0;i<wordsPtr;i++) {
			dlm.addElement(words[i] + " " + counts[i]);
		}
	}
	
	private String fread() {
		FileReader fr = null;
		StringBuffer sb = new StringBuffer();
		try {
			fr = new FileReader("C:/example/paper.txt");
			int i;
			while((i=fr.read()) != -1) 
				sb.append((char)i);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	private String listToStr() {
		int size = dlm.size();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<size;i++)
			sb.append(dlm.getElementAt(i)).append("\r\n");
		return sb.toString();
	}
	
	private void fwrite(String content) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:/example/wordcount.txt");
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void initWnd(){
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

