package problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import problem01.UdpComm;

public class UdpCalcServerFrame extends JFrame implements ActionListener {
	private final int WNDSIZE_W = 300;
	private final int WNDSIZE_H = 400;
	private final String sTitle = "UDP 계산기 서버";
	private Container ct = getContentPane();//
	private final String[] saDefaultVal = { "7777", "7788", "127.0.0.1" };
	private JLabel[] jl = new JLabel[4];
	private JTextField[] jtf = new JTextField[3];
	private JButton jbSet = new JButton("설정");
	private JTextArea jta = new JTextArea();
	private JScrollPane jsp = new JScrollPane(jta);
	private JLabel jlChat = new JLabel("채팅");
	private JTextField jtfMsg = new JTextField();
	private JButton jbSend = new JButton("보내기");
	private JPanel jpNetwork = new JPanel();
	private JPanel jpChat = new JPanel();
	private JPanel jpMsg = new JPanel();
	private UdpComm chat = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UdpCalcServerFrame();
	}	

	private void initComps() {
		for (int i = 0; i < jl.length; i++)
			jl[i] = new JLabel(UdpComm.SA_CAPTION[i], JLabel.CENTER);
		for (int i = 0; i < jtf.length; i++)
			jtf[i] = new JTextField(saDefaultVal[i]);
	}

	private void addComps() {
		jpNetwork.setLayout(new GridLayout(2, 4, 2, 2));
		jpChat.setLayout(new BorderLayout(2, 2));
		jpMsg.setLayout(new BorderLayout(2, 2));
		ct.setLayout(new BorderLayout(2, 2));

		for (int i = 0; i < jl.length; i++)
			jpNetwork.add(jl[i]);
		for (int i = 0; i < jtf.length; i++)
			jpNetwork.add(jtf[i]);
		jpNetwork.add(jbSet);
		jpMsg.add(jlChat, BorderLayout.WEST);
		jpMsg.add(jtfMsg, BorderLayout.CENTER);
		jpMsg.add(jbSend, BorderLayout.EAST);
		jpChat.add(jsp, BorderLayout.CENTER);
		jpChat.add(jpMsg, BorderLayout.SOUTH);

		ct.add(jpChat, BorderLayout.CENTER);
		ct.add(jpNetwork, BorderLayout.NORTH);
	}

	private void addListeners() {
		jbSend.addActionListener(this);
		jbSet.addActionListener(this);
		jtfMsg.addActionListener(this);
	}

	private void showWnd() {
		setTitle(sTitle);
		setSize(WNDSIZE_W, WNDSIZE_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public UdpCalcServerFrame() {
		initComps();
		addComps();
		addListeners();
		showWnd();
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(jbSet)){
			chat = new UdpComm(
					Integer.parseInt(jtf[UdpComm.MYPORT].getText()),
					Integer.parseInt(jtf[UdpComm.OPPORT].getText()),
					jtf[UdpComm.OPIP].getText(), jta);
			chat.start();
		}else{
			chat.sendMsg(jtfMsg.getText());
			jtfMsg.setText("");
		}
	}
}
