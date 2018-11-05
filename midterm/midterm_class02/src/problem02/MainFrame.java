package problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	protected JList<String> jlist;
	protected DefaultListModel<String> dlm;
	protected JLabel jl;
	protected JTextField[] jtf;
	protected JButton jb;
	protected JTextArea jta;

	// baseball game vars
	protected int tryCount;
	protected int[] answer;
	protected boolean bAnswer;

	protected void initGame() {
		Random r = new Random();
		tryCount = 7;
		answer = new int[3];
		bAnswer = false;

		for (int i = 0; i < answer.length; i++) {
			answer[i] = r.nextInt(9)+1;
			for (int j = 0; j < i; j++) {
//				System.out.println(answer[i] + " " + answer[j]);
				if (answer[i] == answer[j]){
					i--;
					break;
				}
			}
		}
//		System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
	}

	protected void initComps() {
		ct = getContentPane();
		jta = new JTextArea();
		jl = new JLabel("  ");
		jtf = new JTextField[3];
		for (int i = 0; i < jtf.length; i++) {
			jtf[i] = new JTextField();
		}
		jb = new JButton("Throw");
	}

	protected void addComps() {
		JPanel jp1 = new JPanel();
		jp1.setLayout(new BorderLayout());
		jp1.add(jta, BorderLayout.CENTER);
		jp1.add(jl, BorderLayout.SOUTH);

		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(1, 4));
		for (int i = 0; i < jtf.length; i++) {
			jp2.add(jtf[i]);
		}
		jp2.add(jb);

		ct.setLayout(new BorderLayout());
		ct.add(jp1, BorderLayout.CENTER);
		ct.add(jp2, BorderLayout.SOUTH);
	}

	protected void addListeners() {
		jb.addActionListener((ActionEvent ae) -> {
			tryCount--;

			// get user input
			int[] tryball = new int[jtf.length];
			for (int i = 0; i < jtf.length; i++){
				tryball[i] = Integer.parseInt(jtf[i].getText());
				jtf[i].setText("");
			}

			// calculate
			String result = throwBall(tryball);

			// write to jta
			jta.append(tryball[0] + " " + tryball[1] + " " + tryball[2]
					+ "         -> " + result + "\r\n");

			// write to jl
			if (bAnswer) {
				jl.setText("정답입니다!!");
			} else {
				jl.setText("남은 시도 횟수 : " + tryCount + "회");
				if (tryCount == 0) {
					jta.append("남은 횟수를 모두 소진하였습니다.\r\n정답은 "
							+ answer[0] + " " + answer[1] + " " + answer[2]
							+ "입니다.\r\n");
					jb.setEnabled(false);
				}
			}
		});
	}

	private String throwBall(int[] tryball) {
		int strike = 0, ball = 0;
		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer.length; j++) {
//				System.out.println(answer[i] + " " + tryball[i]);
				if (answer[i] == tryball[j]) {
					if (i == j)
						strike++;
					else
						ball++;
					break;
				}
			}
		}
		if(strike == 3)
			bAnswer = true;
		return strike + " Strike " + ball + " Ball";
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public MainFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initGame();
		initComps();
		addComps();
		addListeners();
		initWnd();
	}

	public static void main(String[] args) {
		new MainFrame("숫자 야구 게임", 300, 300);
	}
}
