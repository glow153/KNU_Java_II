package problem02;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PourWaterFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected String title;
	protected Container ct;
	
	protected JLabel jlWater;
	protected JScrollPane jspDrain, jspPour;
	protected JTextArea jtaDrain, jtaPour;
	protected JProgressBar prgbar;
	protected JTextField jtfWater;
	protected JButton jbAddHole, jbPour;
	
	private int threadCount;
	
	// model
	protected WaterTankEx tank;
	
	public PourWaterFrame(String title, int w, int h){
		this.title = title;
		this.width = w;
		this.height = h;
		
		initComps();
		addComps();
		addListeners();
		initWnd();
		
		threadCount = 0;
	}
	
	public static void main(String[] args){
		new PourWaterFrame("밑 빠진 독에 물 붓기 - swing", 400, 300);
	}
	
	protected void initComps(){
		ct = getContentPane();

		// init view components
		jlWater = new JLabel("", JLabel.CENTER);
		jtaDrain = new JTextArea();
		jtaPour = new JTextArea();
		jspDrain = new JScrollPane(jtaDrain,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspPour = new JScrollPane(jtaPour,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jbAddHole = new JButton("구멍 추가 (현재 0개)");
		jtfWater = new JTextField("500");
		jbPour = new JButton("물 붓기");

		// init model
		tank = new WaterTankEx(10000);
		
		// set default view
		jtaDrain.setEditable(false);
		jtaPour.setEditable(false);
		prgbar = new JProgressBar(JProgressBar.VERTICAL); // 세로모양 진행바 생성
		prgbar.setMaximum(tank.getWater()); // 진행바의 최댓값 설정
		prgbar.setMinimum(0); // 최솟값 설정
		refreshWaterInfo();
		jtfWater.setHorizontalAlignment(JTextField.CENTER); // jtf 가운데정렬
		jlWater.setText(Integer.toString(tank.getWater()));
	}
	
	protected void addComps(){
		// left
		JLabel jl1 = new JLabel("물 빠짐", JLabel.CENTER);
		JPanel jpWest = new JPanel(new BorderLayout(5, 5));
		jpWest.add(jl1, BorderLayout.NORTH);
		jpWest.add(jspDrain, BorderLayout.CENTER);
		jpWest.add(jbAddHole, BorderLayout.SOUTH);
		
		// right
		JLabel jl2 = new JLabel("물 부음", JLabel.CENTER);
		JPanel jpEast = new JPanel(new BorderLayout(5, 5));
		jpEast.add(jl2, BorderLayout.NORTH);
		jpEast.add(jspPour, BorderLayout.CENTER);
		jpEast.add(jbPour, BorderLayout.SOUTH);
		
		// center
		JPanel jpCenter = new JPanel(new BorderLayout(5, 5));
		jpCenter.add(jlWater, BorderLayout.NORTH);
		jpCenter.add(prgbar, BorderLayout.CENTER);
		jpCenter.add(jtfWater, BorderLayout.SOUTH);
		
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jpWest, BorderLayout.WEST);
		ct.add(jpEast, BorderLayout.EAST);
		ct.add(jpCenter, BorderLayout.CENTER);
	}
	
	protected void addListeners(){
		tank.setOnWaterDrainedListener(s -> {
			jtaDrain.append("\r\n" + s);
			jtaDrain.setCaretPosition(jtaDrain.getText().length() - 1);
			refreshWaterInfo();
		});
		
		tank.setOnWaterPouredListener(s -> {
			jtaPour.append("\r\n" + s);
			jtaPour.setCaretPosition(jtaPour.getText().length() - 1);
			refreshWaterInfo();
		});
		
		jbAddHole.addActionListener(ae -> {
			threadCount += 1;
			jbAddHole.setText("구멍 추가 (현재 " + threadCount + "개)");
			new WaterHoleThreadEx(threadCount, tank, 100).start();
		});
		
		jbPour.addActionListener(ae -> {
			tank.pour(Integer.parseInt(jtfWater.getText()));
		});
	}
	
	private void refreshWaterInfo() {
		jlWater.setText(Integer.toString(tank.getWater()));
		prgbar.setValue(tank.getWater());
	}
	
	protected void initWnd(){
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
