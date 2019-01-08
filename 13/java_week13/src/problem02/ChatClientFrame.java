package problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatClientFrame extends JFrame {
	private final int WNDSIZE_W = 300;
	private final int WNDSIZE_H = 600;
	private final String sTitle = "TCP 채팅 클라이언트측";
	private Container ct = getContentPane();//
	
	private final String[] saDefaultVal = { "localhost", "7777" };
	private JLabel[] jl = new JLabel[3];
	private JTextField[] jtf = new JTextField[2];
	private JButton jbSet = new JButton("접속");
	private JTextArea jta = new JTextArea();
	private JScrollPane jsp = new JScrollPane(jta,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JLabel jlChat = new JLabel("채팅");
	private JTextField jtfMsg = new JTextField();
	private JButton jbSend = new JButton("보내기");
	private JPanel jpNetwork = new JPanel();
	private JPanel jpChat = new JPanel();
	private JPanel jpMsg = new JPanel();
	private TcpClient chat = null;
	
	public static void main(String[] args) {
		new ChatClientFrame();
	}

	public ChatClientFrame() {
		initComps();
		addComps();
		addListeners();
		initWnd();
	}
	
	private void initComps() {
		for (int i = 0; i < jl.length; i++)
			jl[i] = new JLabel(TcpClient.SA_CAPTION[i], JLabel.CENTER);
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
		jbSet.addActionListener(ae -> {
			chat = new TcpClient(
					jtf[TcpClient.SVR_IP].getText(),
					Integer.parseInt(jtf[TcpClient.SVR_PORT].getText()), jta);
			chat.start();
		});
		
		ActionListener al = (ae) -> {
			chat.sendMsg(jtfMsg.getText());
			jtfMsg.setText("");
		};
		
		jbSend.addActionListener(al);
		jtfMsg.addActionListener(al);
	}

	private void initWnd() {
		setTitle(sTitle);
		setSize(WNDSIZE_W, WNDSIZE_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
