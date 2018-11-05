package problem02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GPACalcFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	protected JTextArea jta;
	protected JTextField jtfSbjName, jtfDegree, jtfGrade;
	protected JButton jbAdd;
	protected JLabel jlGPA;
	
	private String columnNames[] = {"과목명", "학점", "평점"};
//	private String rowData[][] = new String[][];

	protected void initComps() {
		ct = getContentPane();
		jta = new JTextArea();
		jtfSbjName = new JTextField();
		jtfDegree = new JTextField();
		jtfGrade = new JTextField();
		jbAdd = new JButton("추가");
		jlGPA = new JLabel("", JLabel.CENTER);
		
		jta.setEditable(false);
		jlGPA.setFont(new Font("맑은 고딕", Font.BOLD, 16));
	}

	protected void addComps() {
		JPanel jpFooter = new JPanel();
		jpFooter.setLayout(new GridLayout(3, 4, 5, 5));
		jpFooter.add(new JLabel("과목명"));
		jpFooter.add(new JLabel("학점"));
		jpFooter.add(new JLabel("평점"));
		jpFooter.add(new JLabel(""));
		jpFooter.add(jtfSbjName);
		jpFooter.add(jtfDegree);
		jpFooter.add(jtfGrade);
		jpFooter.add(jbAdd);
		jpFooter.add(new JLabel(""));
		jpFooter.add(new JLabel(""));
		jpFooter.add(new JLabel("평점평균"));
		jpFooter.add(jlGPA);
		
		ct.setLayout(new BorderLayout(5, 5));
		ct.add(jta, BorderLayout.CENTER);
		ct.add(jpFooter, BorderLayout.SOUTH);
	}

	protected void addListeners() {
		jbAdd.addActionListener((ActionEvent ae) -> {
			jta.append(jtfSbjName.getText() + " " +
					   jtfDegree.getText() + " " +
					   jtfGrade.getText() + "\r\n");
			jtfSbjName.setText("");
			jtfDegree.setText("");
			jtfGrade.setText("");
			
			calcGPA();
		});
	}
	
	private void calcGPA() {
		String[] rows = jta.getText().split("\r\n");
		double sumDegree = 0.0, sumGrade = 0.0;
		
		for (String row : rows) {
			String[] cell = row.split(" "); 
			try {
				sumDegree += Double.parseDouble(cell[1]);
				sumGrade += Double.parseDouble(cell[2]) * Double.parseDouble(cell[1]);
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				// must delete last row when exception occurred.
				return;
			}
		}
		jlGPA.setText(String.format("%.2f", sumGrade / sumDegree));
	}
	
	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public GPACalcFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
	}
}
