package problem01_old;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Banking extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected String title;
	protected Container ct;
	
	protected JList<String> jlistDetails, jlistDeposit, jlistWithdraw;
	protected JScrollPane jspDetails, jspDeposit, jspWithdraw;
	protected JPanel jpList, jpInout, jpTransact;
	protected JRadioButton jrbDeposit, jrbWithdraw;
	protected ButtonGroup bg;
	protected JTextField jtfName, jtfAmount;
	protected JButton jbTransact, jbOpenFile, jbSaveFile;

	protected void initComps() {
		ct = getContentPane();
		
		jlistDetails = new JList<>();
		jlistDeposit= new JList<>();
		jlistWithdraw = new JList<>();
		jspDetails = new JScrollPane(jlistDetails,
									 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
									 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspDeposit = new JScrollPane(jlistDeposit,
									 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
									 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspWithdraw = new JScrollPane(jlistWithdraw,
									  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
									  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jpList = new JPanel();
		jpInout = new JPanel();
		jpTransact = new JPanel();
		jrbDeposit = new JRadioButton("입금");
		jrbWithdraw = new JRadioButton("출금");
		bg = new ButtonGroup();
		bg.add(jrbDeposit);
		bg.add(jrbWithdraw);
		jtfName = new JTextField();
		jtfAmount = new JTextField();
		jbTransact = new JButton("실행");
		jbOpenFile = new JButton("파일 불러오기");
		jbSaveFile = new JButton("파일 저장하기");
	}

	protected void addComps() {
		jpInout.setLayout(new GridLayout(2, 1, 5, 5));
		jpInout.add(jspDeposit);
		jpInout.add(jspWithdraw);

		jpList.setLayout(new GridLayout(1, 2, 5, 5));
		jpList.add(jspDetails);
		jpList.add(jpInout);
		
		jpTransact.setLayout(new GridLayout(3, 5, 5, 5));
		jpTransact.add(new JLabel("입출금 유형"));
		jpTransact.add(new JLabel(""));
		jpTransact.add(new JLabel("입출금 내용"));
		jpTransact.add(new JLabel("금액"));
		jpTransact.add(new JLabel(""));
		
		jpTransact.add(jrbDeposit);
		jpTransact.add(jrbWithdraw);
		jpTransact.add(jtfName);
		jpTransact.add(jtfAmount);
		jpTransact.add(jbTransact);
		
		jpTransact.add(new JLabel("불러오기/저장하기"));
		jpTransact.add(jbOpenFile);
		jpTransact.add(jbSaveFile);
		jpTransact.add(new JLabel(""));
		jpTransact.add(new JLabel(""));
		
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jpList, BorderLayout.CENTER);
		ct.add(jpTransact, BorderLayout.SOUTH);
	}

	protected void addListeners() {
		
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public Banking(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
	}
}


