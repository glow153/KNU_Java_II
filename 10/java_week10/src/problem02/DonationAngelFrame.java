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
import javax.swing.JScrollPane;

import problem02.callback.DonationCenterCallback;

public class DonationAngelFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	
	protected JLabel jlState; // 잔액
	protected JList<String> jlstDonate, jlstRecipient;
	protected DefaultListModel<String> dlmDonate, dlmRecipient;
	protected JScrollPane jspDonate, jspRecipient;
	protected JButton jbStart;
	
	protected DonationCenter dc;
	protected Donator donator;
	protected Recipient recipient;

	public DonationAngelFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
	}
	
	public static void main(String[] args) {
		new DonationAngelFrame("기부천사 - v2.0", 450, 350);
	}
	
	protected void initComps() {
		// init views
		ct = getContentPane();
		jlState = new JLabel("Start 버튼을 눌러 기부천사가 되어보세요!");
		dlmDonate = new DefaultListModel<>();
		dlmRecipient = new DefaultListModel<>();
		jlstDonate = new JList<>(dlmDonate);
		jlstRecipient = new JList<>(dlmRecipient);
		jspDonate = new JScrollPane(jlstDonate,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspRecipient = new JScrollPane(jlstRecipient,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jbStart = new JButton("START");
		
		// init models
		dc = new DonationCenter(new DonationCenterCallback() {
			@Override
			public void onRefresh(String s) {
				// TODO Auto-generated method stub
				jlState.setText(s);	
			}
			
			@Override
			public void noMoney() {
				// TODO Auto-generated method stub
				dlmRecipient.addElement("잔액이 부족하여 입금 대기중...");
			}
		});
		
		donator = new Donator(dc);
		recipient = new Recipient(dc);
	}

	protected void addComps() {
		JPanel jpcc = new JPanel(new GridLayout(1, 2, 10, 10));
		jpcc.add(jspDonate);
		jpcc.add(jspRecipient);
		JPanel jpCenter = new JPanel(new BorderLayout(10, 10));
		jpCenter.add(jlState, BorderLayout.NORTH);
		jpCenter.add(jpcc, BorderLayout.CENTER);
		
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jpCenter, BorderLayout.CENTER);
		ct.add(jbStart, BorderLayout.SOUTH);
	}

	protected void addListeners() {
		donator.setOnDonateListener(amount -> {
			// add to list
			dlmDonate.addElement(amount + "원 입금");
			
			// scroll list to last
			jspDonate.getVerticalScrollBar().setValue(
					jspDonate.getVerticalScrollBar().getMaximum());
		});
		
		recipient.setOnReceiveListener(amount -> {
			// add to list
			dlmRecipient.addElement(amount + "원 출금");
			
			//scroll list to last
			jspRecipient.getVerticalScrollBar().setValue(
					jspRecipient.getVerticalScrollBar().getMaximum());
		});
		
		jbStart.addActionListener(ae -> {
			donator.start();
			recipient.start();
			jbStart.setEnabled(false);
		});
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
