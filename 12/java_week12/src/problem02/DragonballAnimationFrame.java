package problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class DragonballAnimationFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected String title;
	protected Container ct;
	
	protected JPanel jp;
	protected JButton jbGi, jbPar, jbDefense;
	protected JLabel jlNormal, jlGi, jlPar, jlDefense, jlEnergy;
	
	public DragonballAnimationFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;
		
		initComps();
		addComps();
		addListeners();
		initWnd();
	}
	
	public static void main(String[] args) {
		new DragonballAnimationFrame("드래곤볼 파 쏘기 애니메이션", 500, 280);
	}
	
	protected void initComps() {
		ct = getContentPane();
		jp = new JPanel();
		jbGi = new JButton("기 모으기");
		jbDefense = new JButton("막기");
		jbPar = new JButton("파 쏘기");
		jlNormal = new JLabel(new ImageIcon("img/normal.png"));
		jlGi = new JLabel(new ImageIcon("img/gi.png"));
		jlPar = new JLabel(new ImageIcon("img/par.png"));
		jlDefense = new JLabel(new ImageIcon("img/defense.png"));
		jlEnergy = new JLabel(new ImageIcon("img/energy.png"));
		
		jlNormal.setSize(100, 200);
		jlGi.setSize(100, 200);
		jlPar.setSize(100, 200);
		jlDefense.setSize(100, 200);
		jlEnergy.setSize(80, 106);
	}
	
	protected void addComps() {
		jp.setLayout(null);
		jp.add(jlNormal);
		jlNormal.setLocation(10, 10);
		jp.updateUI();
		
		JPanel jpSouth = new JPanel(new GridLayout(1, 3));
		jpSouth.add(jbGi);
		jpSouth.add(jbDefense);
		jpSouth.add(jbPar);
		
		ct.setLayout(new BorderLayout());
		ct.add(jp, BorderLayout.CENTER);
		ct.add(jpSouth, BorderLayout.SOUTH);
		
	}
	
	private class UserStateChanger implements Runnable {
		private int type;
		public UserStateChanger(int type) {
			// TODO Auto-generated constructor stub
			this.type = type;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			JLabel jlTarget = null;
			switch(type) {
			case 0:
				jlTarget = jlGi;
				break;
			case 1:
				jlTarget = jlDefense;
				break;
			case 2:
				jlTarget = jlPar;
				break;
			}
			
			jp.remove(jlNormal);
			jp.add(jlTarget);
			jlTarget.setLocation(10, 10);
			jp.updateUI();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			jp.remove(jlTarget);
			jp.add(jlNormal);
			jp.updateUI();
		}
	}
	
	protected void addListeners() {
		jbGi.addActionListener(ae -> new Thread(new UserStateChanger(0)).start());
		jbDefense.addActionListener(ae -> new Thread(new UserStateChanger(1)).start());
		jbPar.addActionListener(ae -> {
			new Thread(new UserStateChanger(2)).start();
			new Thread(() -> {
				jp.add(jlEnergy);
				jlEnergy.setLocation(110, 10);
				jp.updateUI();
				
				int xMaxPos = 410;
				int x = 100, y = 50;
				while(x < xMaxPos) {
					x += 10;
					jlEnergy.setLocation(x, y);
					jp.updateUI();
					
					try {
						Thread.sleep(1000 / 60);
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				
				jp.remove(jlEnergy);
				jp.updateUI();
			}).start();
		});
	}
	
	protected void initWnd(){
		setSize(width, height);
//		setResizable(false);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}