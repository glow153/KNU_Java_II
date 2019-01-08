package problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import problem01.UdpComm;

public class UdpCalcClientFrame extends JFrame {
	protected static String VERSION = "1.0";
	private int wndSizeWidth;
	private int wndSizeHeight;
	private String title;
	
	protected Container ct;
	protected JTextArea jta;
	protected JTextField jtfIp, jtfMyPort, jtfOpPort, jtfFormula;
	protected JPanel jpIO, jpBtns;
	protected JButton jbConnect;
	protected JButton[] jb;
	protected String[] btnCap;
	
	protected UdpComm udp;
	
	public static void main(String[] args) {
		new UdpCalcClientFrame("UDP 계산기 클라이언트", 360, 500);
	}

	public UdpCalcClientFrame(String title, int w, int h) {
		this.title = title;
		wndSizeWidth = w;
		wndSizeHeight = h;
		
		initComps();
		addComps();
		addListeners();
		initWnd();
	}
	
	private void initComps() {
		ct = getContentPane();
		jta = new JTextArea();
		jtfIp = new JTextField("localhost");
		jtfOpPort = new JTextField("7777");
		jtfMyPort = new JTextField("7778");
		jtfFormula = new JTextField();
		jpIO = new JPanel();
		jpBtns = new JPanel();
		jbConnect = new JButton("설정");
		btnCap = new String[] {"0", "1", "2", "3", "4",
							   "5", "6", "7", "8", "9",
							   "÷", "×", "-", "+", "=", "←"};
		jb = new JButton[btnCap.length];
		for (int i = 0; i < btnCap.length; i++)
			jb[i] = new JButton(btnCap[i]);
	}
	
	private void addComps() {
		// network panel
		JPanel jpNorth = new JPanel(new GridLayout(2, 4, 5, 5));
		jpNorth.add(new JLabel("Remote IP", JLabel.CENTER));
		jpNorth.add(new JLabel("Remote Port", JLabel.CENTER));
		jpNorth.add(new JLabel("My Port", JLabel.CENTER));
		jpNorth.add(new JLabel());
		jpNorth.add(jtfIp);
		jpNorth.add(jtfOpPort);
		jpNorth.add(jtfMyPort);
		jpNorth.add(jbConnect);
		
		// set io panel
		jpIO.setLayout(new BorderLayout(5, 5));
		jpIO.add(jta, BorderLayout.CENTER);
		jpIO.add(jtfFormula, BorderLayout.SOUTH);
		
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
		ct.add(jpNorth, BorderLayout.NORTH);
		ct.add(jpIO, BorderLayout.CENTER);
		ct.add(jpBtns, BorderLayout.SOUTH);
	}

	public void addListeners() {
		jbConnect.addActionListener(ae -> {
			udp = new UdpComm(Integer.parseInt(jtfMyPort.getText()),
							  Integer.parseInt(jtfOpPort.getText()),
							  jtfIp.getText(), jta);
			udp.start();
		});
		
		for (int i = 0; i < jb.length; i++) {
			final int idx = i;

			jb[idx].addActionListener((ActionEvent ae) -> {
				// TODO Auto-generated method stub
				if (0 <= idx && idx <= 9) { // number
					String num = ae.getActionCommand();
					jtfFormula.setText(jtfFormula.getText() + num);
					
				} else if (10 <= idx && idx <= 13) { // operator
					String op = ae.getActionCommand();
					jtfFormula.setText(jtfFormula.getText() + " " + op + " ");
					
				} else if (idx == 14) { // eq
					String formula = jtfFormula.getText().trim();
					udp.sendMsg(formula);
					jtfFormula.setText("");
					
				} else if (idx == 15) { // del
					String line = jtfFormula.getText();
					if(line.length() == 0)
						return;
					jtfFormula.setText(line.substring(0, line.length() - 1));
				}
			});
		}
	}
	
	private void initWnd() {
		setTitle(title + " - v" + VERSION);
		setSize(wndSizeWidth, wndSizeHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
