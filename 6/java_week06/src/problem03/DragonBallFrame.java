package problem03;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class DragonBallFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	public static final String[] actions = { "기", "막기", "파" };

	protected String title;
	protected int width, height;
	protected Container ct;
	protected PlayerPanel ppCom;
	protected PlayerPanel ppUser;

	protected JRadioButton[] rb;
	protected ButtonGroup bg;
	protected JButton jbGo;
	protected JTextArea jta; 
	
	protected void initComps() {
		ct = getContentPane();
		ppCom = new PlayerPanel("Computer", true);
		ppUser = new PlayerPanel("User", false);
		rb = new JRadioButton[3];
		bg = new ButtonGroup();
		for (int i = 0; i < rb.length; i++) {
			rb[i] = new JRadioButton(actions[i]);
			bg.add(rb[i]);
		}
		jbGo = new JButton("GO!");
		jta = new JTextArea();
	}

	protected void addComps() {
		JPanel jpCenter = new JPanel();
		jpCenter.setLayout(new GridLayout(1, 2, 10, 10));
		jpCenter.add(ppCom);
		jpCenter.add(ppUser);
		
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
		ct.add(jta, BorderLayout.CENTER);
		
		rb[2].setEnabled(false);
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
			ppUser.selectAction(selected);
			
			// computer select action
			ppCom.setRandomAction();
			
			// match
			match(ppCom, ppUser);
			
			rb[2].setEnabled(ppUser.isGyCharged());
		});
	}
	
	protected void match(PlayerPanel p1, PlayerPanel p2) {
		int action1 = p1.getLastAction();
		int action2 = p2.getLastAction();
		
		if(action1 == 2 && action2 == 2) {
			p1.attacked();
			p2.attacked();
		} else if(action1 == 2 && action2 == 0) {
			p2.attacked();
		} else if(action1 == 0 && action2 == 2) {
			p1.attacked();
		} else {
			if(action1 == 0) p1.chargeGy(true);
			if(action2 == 0) p2.chargeGy(true);
		}
		
		p1.update();
		p2.update();
		
		jta.append(p1.getStatusString() + "       " + p2.getStatusString() + "\r\n");
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

