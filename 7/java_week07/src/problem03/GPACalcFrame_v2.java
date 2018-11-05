package problem03;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GPACalcFrame_v2 extends JFrame {
	private static final String delimeter = "    ";
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	// protected JTextArea jta;
	protected JList<String> jlist;
	protected DefaultListModel<String> dlm;
	protected JTextField jtfSbjName, jtfDegree, jtfGrade;
	protected JButton jbAdd, jbDelete, jbFr, jbFw;
	protected JLabel jlGPA;

	private String columnNames[] = { "과목명", "학점", "평점" };
	// private String rowData[][] = new String[][];

	protected void initComps() {
		ct = getContentPane();
		// jta = new JTextArea();
		dlm = new DefaultListModel<>();
		jlist = new JList<>(dlm);
		jtfSbjName = new JTextField();
		jtfDegree = new JTextField();
		jtfGrade = new JTextField();
		jbAdd = new JButton("추가");
		jbDelete = new JButton("선택삭제");
		jlGPA = new JLabel("", JLabel.CENTER);

		jbFr = new JButton("불러오기");
		jbFw = new JButton("내보내기");

		// jta.setEditable(false);
		jlGPA.setFont(new Font("맑은 고딕", Font.BOLD, 16));
	}

	protected void addComps() {
		JPanel jpFooter = new JPanel();
		jpFooter.setLayout(new GridLayout(3, 4, 5, 5));
		jpFooter.add(new JLabel("과목명"));
		jpFooter.add(new JLabel("학점"));
		jpFooter.add(new JLabel("평점"));
		jpFooter.add(jbDelete);
		jpFooter.add(jtfSbjName);
		jpFooter.add(jtfDegree);
		jpFooter.add(jtfGrade);
		jpFooter.add(jbAdd);
		jpFooter.add(new JLabel("평점평균"));
		jpFooter.add(jlGPA);
		jpFooter.add(jbFr);
		jpFooter.add(jbFw);

		ct.setLayout(new BorderLayout(5, 5));
		// ct.add(jta, BorderLayout.CENTER);
		ct.add(jlist, BorderLayout.CENTER);
		ct.add(jpFooter, BorderLayout.SOUTH);
	}

	protected void addListeners() {
		ActionListener listenerAdd = (ActionEvent ae) -> {
			addRow();
			jlGPA.setText(String.format("%.2f", calcGPA()));
			jtfSbjName.grabFocus();
		};
		jbAdd.addActionListener(listenerAdd);
		jtfGrade.addActionListener(listenerAdd);
		
		jbDelete.addActionListener((ActionEvent e) -> deleteRow());
		
		jbFr.addActionListener((ActionEvent e) -> {
			fRead();
			jlGPA.setText(String.format("%.2f", calcGPA()));
		});
		
		jbFw.addActionListener((ActionEvent e) -> fWrite());
	}
	
	private void addRow() {
		dlm.addElement(jtfSbjName.getText() + delimeter +
						jtfDegree.getText()+ delimeter +
						jtfGrade.getText());
		jtfSbjName.setText("");
		jtfDegree.setText("");
		jtfGrade.setText("");
	}
	
	private void deleteRow() {
		dlm.removeElementAt(jlist.getSelectedIndex());
	}

	private double calcGPA() {
		Enumeration<String> rows = dlm.elements();
		double sumDegree = 0.0, sumGrade = 0.0;

		while (rows.hasMoreElements()) {
			String row = rows.nextElement();
			String[] cell = row.split(delimeter);
			try {
				sumDegree += Double.parseDouble(cell[1]);
				sumGrade += Double.parseDouble(cell[2]) * Double.parseDouble(cell[1]);
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				// must delete last row when exception occurred.
				dlm.remove(dlm.size() - 1);
			}
		}
		return sumGrade / sumDegree;
	}

	private void setGPA() {
		double gpa = calcGPA();
		
	}
	

	private void fRead() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("gpa.txt")));
			String line;
			while((line = br.readLine()) != null) {
				if(!line.startsWith("평점평균"))
					dlm.addElement(line);
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	private void fWrite() {
		StringBuffer sb = new StringBuffer();
		Enumeration<String> rows = dlm.elements();
		while (rows.hasMoreElements())
			sb.append(rows.nextElement()).append("\r\n");
		sb.append("평점평균 : " + String.format("%.2f", calcGPA()));
		
		FileWriter fw = null;
		try {
			fw = new FileWriter("gpa.txt");
			fw.write(sb.toString());
			fw.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public GPACalcFrame_v2(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
	}
}
