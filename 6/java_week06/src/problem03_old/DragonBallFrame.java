package problem03_old;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class DragonBallFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	public static final String[] actions = { "±â", "¸·±â", "ÆÄ" };
	protected Player computer, user;

	protected String title;
	protected int width, height;
	protected Container ct;
	protected JPanel jpCom;
	protected JPanel jpUser;
	protected JLabel jlComAction;
	protected JLabel jlUserAction;
	protected JLabel jlComLife;
	protected JLabel jlUserLife;

	protected JRadioButton[] rb;
	protected ButtonGroup bg;
	protected JButton jbGo;
	protected JLabel jlStatusBar;
	
	protected void initComps() {
		computer = new Player(true);
		user = new Player(false);

		ct = getContentPane();
		jpCom = new JPanel();
		jpUser = new JPanel();
		jlComAction = new JLabel("--", JLabel.CENTER);
		jlUserAction = new JLabel("--", JLabel.CENTER);
		jlComLife = new JLabel("¢¾ ¢¾ ¢¾", JLabel.CENTER);
		jlUserLife = new JLabel("¢¾ ¢¾ ¢¾", JLabel.CENTER);
		rb = new JRadioButton[3];
		bg = new ButtonGroup();
		for (int i = 0; i < rb.length; i++) {
			rb[i] = new JRadioButton(actions[i]);
			bg.add(rb[i]);
		}
		jbGo = new JButton("GO!");
		jlStatusBar = new JLabel("", JLabel.CENTER);
		
		Font fntAction = new Font("±Ã¼­Ã¼", Font.BOLD, 60);
		Font fntLife = new Font("¸¼Àº °íµñ", Font.BOLD, 20);
		jlComAction.setFont(fntAction);
		jlUserAction.setFont(fntAction);
		jlComLife.setFont(fntLife);
		jlUserLife.setFont(fntLife);
	}

	protected void addComps() {
		jpCom.setLayout(new BorderLayout(10, 10));
		jpCom.add(new JLabel("Computer", JLabel.CENTER), BorderLayout.NORTH);
		jpCom.add(jlComAction, BorderLayout.CENTER);
		jpCom.add(jlComLife, BorderLayout.SOUTH);
		
		jpUser.setLayout(new BorderLayout(10, 10));
		jpUser.add(new JLabel("User", JLabel.CENTER), BorderLayout.NORTH);
		jpUser.add(jlUserAction, BorderLayout.CENTER);
		jpUser.add(jlUserLife, BorderLayout.SOUTH);
		
		JPanel jpCenter = new JPanel();
		jpCenter.setLayout(new GridLayout(1, 2, 10, 10));
		jpCenter.add(jpCom);
		jpCenter.add(jpUser);
		
		JPanel jpSouth = new JPanel();
		jpSouth.setLayout(new GridLayout(1, 4, 10, 10));
		jpSouth.add(rb[0]);
		jpSouth.add(rb[1]);
		jpSouth.add(rb[2]);
		jpSouth.add(jbGo);
		
		JPanel jpGameBoard = new JPanel();
		jpGameBoard.setLayout(new BorderLayout(10, 10));
		jpGameBoard.add(jpCenter, BorderLayout.CENTER);
		jpGameBoard.add(jpSouth, BorderLayout.SOUTH);
		
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jpGameBoard, BorderLayout.NORTH);
		ct.add(jlStatusBar, BorderLayout.SOUTH);
	}

	protected void addListeners() {
		jbGo.addActionListener((ActionEvent e) -> {
			// user select action
			String selected = "";
			Enumeration<AbstractButton> rbs = bg.getElements();
			while (rbs.hasMoreElements()) {
				AbstractButton button = rbs.nextElement();
				if (button.isSelected()) {
					selected = button.getText();
				}
			}
			jlUserAction.setText(selected);
			user.setLastAction(selected);
			
			// computer select action
			
			// match
			match(user, computer);
		});
	}
	
	protected void match(Player p1, Player p2) {
		
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public DragonBallFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
	}
}
