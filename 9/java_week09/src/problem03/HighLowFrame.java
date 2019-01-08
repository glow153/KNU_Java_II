package problem03;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HighLowFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	protected JTextArea jta;
	protected JButton jbNew, jbStart;
	protected JLabel jl;

	protected GameThread gameThread = null;

	public static void main(String[] args) {
		new HighLowFrame("Thread HighLow", 300, 300);
	}

	public HighLowFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
		
		initGame();
	}

	protected void initComps() {
		ct = getContentPane();
		jbNew = new JButton("�� ����");
		jbStart = new JButton("����");
		jta = new JTextArea();
		jl = new JLabel("�� ������ ������, ���� ��ư�� �����ּ���.");
	}

	protected void addComps() {
		JPanel jpNorth = new JPanel();
		jpNorth.setLayout(new GridLayout(1, 2, 10, 10));
		jpNorth.add(jbNew);
		jpNorth.add(jbStart);

		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jpNorth, BorderLayout.NORTH);
		ct.add(jta, BorderLayout.CENTER);
		ct.add(jl, BorderLayout.SOUTH);
	}

	protected void addListeners() {
		jbNew.addActionListener((ActionEvent ae) -> initGame());
		jbStart.addActionListener((ActionEvent ae) -> {
			jta.setText("");
			gameThread.start();
		});
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	protected void initGame() {
		if (gameThread != null) {
			gameThread.stopThread();
			gameThread = null;
		}
		
		gameThread = new GameThread();
	}

	private class GameThread extends Thread {
		private Random r;
		private boolean running;
		private boolean[] tryTable;
		private int answer, tryCount;
		
		GameThread() {
			// TODO Auto-generated constructor stub
			r = new Random();
			answer = r.nextInt(100) + 1;
			tryTable = new boolean[100];
			tryCount = 8;
			for (int i=0;i<tryTable.length;i++)
				tryTable[i] = false;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			running = true;
			while (running) {
				int tryNum = -1;
				
				// choose without coupled
				while(true) {
					tryNum = r.nextInt(100) + 1;
					if(!tryTable[tryNum - 1])
						break;
				}

				// compare number
				if (tryNum > answer) {
					jta.append(tryNum + "��(��) ���亸�� Ů�ϴ�.\r\n");
				} else if (tryNum < answer) {
					jta.append(tryNum + "��(��) ���亸�� �۽��ϴ�.\r\n");
				} else {
					jta.append(tryNum + "��(��) �����Դϴ�!\r\n");
					running = false;
					break;
				}

				// check try count
				tryCount--;
				jl.setText("���� �õ� Ƚ�� : " + tryCount + "ȸ");
				if (tryCount <= 0) {
					jta.append("�õ� Ƚ���� ��� �����߽��ϴ�.\r\n");
					jta.append("������ " + answer + "�Դϴ�!\r\n");
					break;
				}
				
				// memo to table
				tryTable[tryNum - 1] = true;
				
				// pause
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void stopThread() {
			running = false;
		}
	}
}