package problem01;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
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
	protected JButton jbAdd, jbCalc, jbFout;
	protected String[] caption = {"�й�", "����", "����", "����"};
	protected double[] scoresum = {0.0, 0.0, 0.0};
	
	protected void initComps(){
		ct = getContentPane();
		dlm = new DefaultListModel<>();
		jlist = new JList<>(dlm);
		jl = new JLabel();
		jtf = new JTextField[4];
		for (int i=0;i<jtf.length;i++) {
			jtf[i] = new JTextField();
		}
		jbAdd = new JButton("�߰�");
		jbCalc = new JButton("��� ���");
		jbFout = new JButton("���Ϸ� ����");
		
//		dlm.addElement("�й� : 20180001, ���� : 80, ���� : 100, ���� : 100, ��� : 93.33");
//		dlm.addElement("�й� : 20180002, ���� : 85, ���� : 80, ���� : 90, ��� : 85.0");
//		jl.setText("<���� ���> ���� : 82.5, ���� : 90.0, ���� : 95.0, �� ��� : 89.16");
		jl.setText("<���� ���>");
	}
	
	protected void addComps() {
		JPanel jp1 = new JPanel();
		jp1.setLayout(new BorderLayout());
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(3, 1));
		
		JPanel jp21 = new JPanel();
		jp21.setLayout(new GridLayout(1, 5));
		for (int i=0;i<caption.length;i++)
			jp21.add(new JLabel(caption[i], JLabel.CENTER));
		jp21.add(new JLabel());
		
		JPanel jp22 = new JPanel();
		jp22.setLayout(new GridLayout(1, 5));
		for (int i=0;i<jtf.length;i++) {
			jp22.add(jtf[i]);
		}
		jp22.add(jbAdd);
		
		JPanel jp23 = new JPanel();
		jp23.setLayout(new GridLayout(1, 2));
		jp23.add(jbCalc);
		jp23.add(jbFout);
		
		jp2.add(jp21);
		jp2.add(jp22);
		jp2.add(jp23);

		jp1.add(jp2, BorderLayout.NORTH);
		jp1.add(jlist, BorderLayout.CENTER);
		jp1.add(jl, BorderLayout.SOUTH);
		
		ct.setLayout(new BorderLayout());
		ct.add(jp1, BorderLayout.CENTER);
	}
	
	protected void addListeners(){
		jbAdd.addActionListener((ActionEvent ae) -> {
			StringBuffer sb = new StringBuffer();
			double[] sc = {0.0, 0.0, 0.0};
			double avg = 0;
			
			sb.append(caption[0] + " : " + jtf[0].getText() + ", ");
			for (int i=1;i<jtf.length;i++) {
				sb.append(caption[i] + " : " + jtf[i].getText() + ", ");
				sc[i-1] = Double.parseDouble(jtf[i].getText());
				scoresum[i-1] += sc[i-1];
			}
			for (int i=0;i<sc.length;i++)
				avg += sc[i];
			avg /= 3;
			sb.append("��� : ").append(String.format("%.2f", avg)).append("\r\n");
			dlm.addElement(sb.toString());

			for (int i=0;i<jtf.length;i++) {
				jtf[i].setText("");
			}
		});
		
		jbCalc.addActionListener((ActionEvent ae) -> {
			setLabel();
		});
	}
	
	private void setLabel() {
		StringBuffer sb = new StringBuffer();
		int size = dlm.size();
		sb.append("<���� ���> ");
		double sum = 0.0;
		for (int i=0;i<scoresum.length;i++) {
			sb.append(caption[i+1]).append(" : ")
			  .append(String.format("%.2f", scoresum[i] / size)).append(", ");
			sum += scoresum[i];
		}
		sb.append("�� ��� : ").append(sum / (3 * size));
		
		jl.setText(sb.toString());
	}
	
	protected void initWnd(){
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public MainFrame(String title, int w, int h){
		this.title = title;
		this.width = w;
		this.height = h;
		
		initComps();
		addComps();
		addListeners();
		initWnd();
	}
	
	public static void main(String[] args) {
		new MainFrame("���� ó�� ���α׷�", 400, 270);
	}
}
