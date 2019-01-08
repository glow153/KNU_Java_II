package problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatServerFrame extends JFrame implements ActionListener {
	private final int WNDSIZE_W = 300;
	private final int WNDSIZE_H = 600;
	private final String sTitle = "TCP 채팅 서버측";
	private Container ct = getContentPane();//
	
	private JLabel[] jl = new JLabel[2];
	private JTextField jtfPort = new JTextField("7777");
	private JButton jbSet = new JButton("생성");
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JList jlst = new JList(dlm);
	private JScrollPane jsp = new JScrollPane(jlst,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JLabel jlChat = new JLabel("채팅");
	private JTextField jtfMsg = new JTextField();
	private JButton jbSend = new JButton("보내기");
	private JPanel jpNetwork = new JPanel();
	private JPanel jpChat = new JPanel();
	private JPanel jpMsg = new JPanel();
	private TcpServer chat = null;
	
	public static void main(String[] args) {
		new ChatServerFrame();
	}

	private void initComps() {
		for (int i = 0; i < jl.length; i++)
			jl[i] = new JLabel(TcpServer.SA_CAPTION[i], JLabel.CENTER);
	}

	private void addComps() {
		jpNetwork.setLayout(new GridLayout(2, 4, 2, 2));
		jpChat.setLayout(new BorderLayout(2, 2));
		jpMsg.setLayout(new BorderLayout(2, 2));
		ct.setLayout(new BorderLayout(2, 2));

		for (int i = 0; i < jl.length; i++)
			jpNetwork.add(jl[i]);
		jpNetwork.add(jtfPort);
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

	public ChatServerFrame() {
		initComps();
		addComps();
		addListeners();
		showWnd();
	}	

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(jbSet)){
			chat = new TcpServer(Integer.parseInt(jtfPort.getText()), dlm);
			chat.start();
		}else{
			chat.sendMsg("Server : " + jtfMsg.getText());
			jtfMsg.setText("");
		}
	}
}
