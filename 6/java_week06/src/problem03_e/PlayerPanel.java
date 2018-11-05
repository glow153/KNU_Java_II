package problem03_e;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	private static final Font fntAction = new Font("±Ã¼­Ã¼", Font.BOLD, 60);
	private static final Font fntLife = new Font("¸¼Àº °íµñ", Font.BOLD, 20);
	public static final String[] actions = { "±â", "¸·±â", "ÆÄ" };
	// vars
	protected Random r;
	protected String name;
	protected boolean bot;
	protected boolean gy;
	protected int life;
	protected int lastAction;
	
	// components
	protected JLabel header;
	protected JLabel jlAction;
	protected JLabel lifebar;
	
	protected void initComps() {
		header = new JLabel(name, JLabel.CENTER);
		jlAction = new JLabel("--", JLabel.CENTER);
		lifebar = new JLabel("¢¾¢¾¢¾", JLabel.CENTER);
		
		setLayout(new BorderLayout(10, 10));
		add(header, BorderLayout.NORTH);
		add(jlAction, BorderLayout.CENTER);
		add(lifebar, BorderLayout.SOUTH);
		
		jlAction.setFont(fntAction);
		lifebar.setFont(fntLife);
	}
	
	public void selectAction(String lastActionString) {
		for(int i=0;i<DragonBallFrame.actions.length;i++) {
			if(lastActionString.equals(DragonBallFrame.actions[i])) {
				lastAction = i;
				break;
			}
		}
	}
	
	public void update() {
		jlAction.setText(actions[lastAction]);
		if(life > 3) life = 3;
		else if(life < 0) life = 0;
		lifebar.setText(life == 3 ? "¢¾¢¾¢¾" :
						life == 2 ? "¢¾¢¾¢½" :
						life == 1 ? "¢¾¢½¢½" : "¢½¢½¢½");
		if(lastAction == 2)
			gy = false;
	}
	
	public PlayerPanel(String name, boolean bot) {
		r = new Random();
		this.name = name;
		this.bot = bot;
		gy = false;
		life = 3;
		lastAction = -1;
		
		initComps();
	}

	public int getLife() {
		return life;
	}

	public void attacked() {
		life--;
	}

	public int getLastAction() {
		return lastAction;
	}

	public void setLastAction(int lastAction) {
		this.lastAction = lastAction;
	}
	
	public void setRandomAction() {
		int action = -1;
		if(lastAction == -1) {
			lastAction = 0;
			return;
		} else {
			while(true) {
				action = r.nextInt(3);
				if(action == 2 && !gy) // ÆÄ¸¦ ½î·Á´Âµ¥ ±â°¡ ¾øÀ½
					continue;
				else
					break;
			}
			lastAction = action;
		}
	}

	public boolean isGyCharged() {
		return gy;
	}

	public void chargeGy(boolean gy) {
		this.gy = gy;
	}
	
	public String getStatusString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name).append("> action: ")
		  .append(actions[lastAction])
		  .append(", life: ")
		  .append(life);
		return sb.toString();
	}
}
