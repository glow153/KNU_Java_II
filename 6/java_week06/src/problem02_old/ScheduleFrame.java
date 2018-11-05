package problem02_old;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ScheduleFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	protected JPanel jpSchedule, jpNorth, jpWest, jpCenter;
	protected JLabel titleHeader;
	protected JTextField jtfTitle, jtfStartDateTime, jtfEndDateTime;
	protected JButton jbStartDateTime, jbEndDateTime, jbAdd;
	protected JComboBox jcbxCategory;
	protected JTextArea jta;
	
	protected JLabel jlMyTime, jlOtherTime;
	protected String[] categories;

	protected void initComps() {
		ct = getContentPane();
		categories = new String[] {"기념일", "휴일", "출장", "회의", "기타"};
		
		jpSchedule = new JPanel();
		jpNorth = new JPanel();
		jpWest = new JPanel();
		jpCenter = new JPanel();
		
		titleHeader = new JLabel(title + " - v" + serialVersionUID);
		jtfTitle = new JTextField();
		jtfStartDateTime = new JTextField();
		jtfEndDateTime = new JTextField();
		jbStartDateTime = new JButton("시작 일시");
		jbEndDateTime = new JButton("종료 일시");
		jcbxCategory = new JComboBox<String>(categories);
		jta = new JTextArea();
		jbAdd = new JButton("일정 추가하기");
		
		titleHeader.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		jtfStartDateTime.setHorizontalAlignment(JTextField.CENTER);
		jtfEndDateTime.setHorizontalAlignment(JTextField.CENTER);
		jta.setEditable(false);
	}

	protected void addComps() {
		jpNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpNorth.add(titleHeader);
		
		jpWest.setLayout(new GridLayout(4, 1, 5, 5));
		jpWest.add(new JLabel("제목"));
		jpWest.add(new JLabel("시작"));
		jpWest.add(new JLabel("종료"));
		jpWest.add(new JLabel("유형"));
		
		jpCenter.setLayout(new GridLayout(4, 1, 5, 5));
		jpCenter.add(jtfTitle);
		jpCenter.add(jbStartDateTime);
		jpCenter.add(jbEndDateTime);
		jpCenter.add(jcbxCategory);
		
		jpSchedule.setLayout(new BorderLayout(5, 5));
		jpSchedule.add(jpNorth, BorderLayout.NORTH);
		jpSchedule.add(jpCenter, BorderLayout.CENTER);
		jpSchedule.add(jpWest, BorderLayout.WEST);
		jpSchedule.add(jbAdd, BorderLayout.SOUTH);
		
		ct.setLayout(new BorderLayout(5, 5));
		ct.add(jpSchedule, BorderLayout.WEST);
		ct.add(jta, BorderLayout.CENTER);
	}

	protected void addListeners() {
		jbStartDateTime.addActionListener((ActionEvent e) -> {
			jpCenter.remove(1);
			jpCenter.add(jtfStartDateTime, 1);
			if (jbStartDateTime.getText().equals("시작 일시"))
				jtfStartDateTime.setText("yyyy-MM-dd HH:mm");
			else
				jtfStartDateTime.setText(jbStartDateTime.getText());
			jpCenter.updateUI();
		});
		
		jtfStartDateTime.addActionListener((ActionEvent e) -> {
			jpCenter.remove(1);
			jpCenter.add(jbStartDateTime, 1);
			jbStartDateTime.setText(jtfStartDateTime.getText());
			jpCenter.updateUI();
		});
		
		jbEndDateTime.addActionListener((ActionEvent e) -> {
			jpCenter.remove(2);
			jpCenter.add(jtfEndDateTime, 2);
			if (jbEndDateTime.getText().equals("종료 일시")) {
				jtfEndDateTime.setText("yyyy-MM-dd HH:mm");
			} else {
				jtfEndDateTime.setText(jbEndDateTime.getText());
			}
			jpCenter.updateUI();
		});
		
		jtfEndDateTime.addActionListener((ActionEvent e) -> {
			jpCenter.remove(2);
			jpCenter.add(jbEndDateTime, 2);
			jbEndDateTime.setText(jtfEndDateTime.getText());
			jpCenter.updateUI();
		});
		
		jbAdd.addActionListener((ActionEvent e) -> addItem());
	}
	
	private void addItem() {
		StringBuffer sb = new StringBuffer();
		sb.append("<")
		  .append((String)jcbxCategory.getSelectedItem())
		  .append(">")
		  .append(jtfTitle.getText())
		  .append(" : ")
		  .append(jbStartDateTime.getText())
		  .append(" ~ ")
		  .append(jbEndDateTime.getText())
		  .append("\r\n");
		jta.append(sb.toString());
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public ScheduleFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
	}
}
