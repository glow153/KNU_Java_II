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
	
public class MainFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected String title;
	protected Container ct;
	
	protected JTextArea jta;
	protected JList<String> jlistBasket;
	protected DefaultListModel<String> dlmBasket;
	protected JList<String> jlistLeft;
	protected DefaultListModel<String> dlmLeft;
	protected JList<String> jlistRight;
	protected DefaultListModel<String> dlmRight;
	protected JButton jbLeft, jbRight, jbCompare, jbLeft2Basket, jbRight2Basket; 
	
	private String answerBall;
	
	public static void main(String[] args) {
		new MainFrame("천칭 저울", 320, 430);
	}
	
	public MainFrame(String title, int w, int h){
		this.title = title;
		this.width = w;
		this.height = h;
		
		setWeight();
		initComps();
		addComps();
		addListeners();
		initWnd();
	}
	
	protected void initComps(){
		ct = getContentPane();
		dlmBasket = new DefaultListModel<>();
		dlmLeft = new DefaultListModel<>();
		dlmRight = new DefaultListModel<>();
		jlistBasket = new JList<>(dlmBasket);
		jlistLeft = new JList<>(dlmLeft);
		jlistRight = new JList<>(dlmRight);
		
		jbLeft = new JButton("◀");
		jbRight = new JButton("▶");
		jbCompare = new JButton("양쪽 무게 비교하기");
		jbLeft2Basket = new JButton("▶");
		jbRight2Basket = new JButton("◀");
		
		jta = new JTextArea();
		dlmBasket.addElement("1");
		dlmBasket.addElement("2");
		dlmBasket.addElement("3");
		dlmBasket.addElement("4");
		dlmBasket.addElement("5");
		dlmBasket.addElement("6");
		dlmBasket.addElement("7");
		dlmBasket.addElement("8");
	}
	
	protected void addComps(){
		JPanel jp1 = new JPanel();
		jp1.setLayout(new BorderLayout(10, 10));
		JPanel jp11 = new JPanel();
		jp11.setLayout(new GridLayout(1, 3, 3, 3));
		jp11.add(jlistLeft);
		jp11.add(jlistBasket);
		jp11.add(jlistRight);
		JPanel jp12 = new JPanel();
		jp12.setLayout(new GridLayout(1, 6, 3, 3));
		jp12.add(new JLabel());
		jp12.add(jbLeft2Basket);
		jp12.add(jbLeft);
		jp12.add(jbRight);
		jp12.add(jbRight2Basket);
		jp12.add(new JLabel());
		jp1.add(jp11, BorderLayout.CENTER);
		jp1.add(jp12, BorderLayout.SOUTH);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new BorderLayout(10, 10));
//		JPanel jp21 = new JPanel();
//		jp21.setLayout(new GridLayout(1, 3, 3, 3));
//		jp21.add(new JLabel());
//		jp21.add(jbCompare);
//		jp21.add(jbAnswer);
		jp2.add(jbCompare, BorderLayout.NORTH);
		jp2.add(jta, BorderLayout.CENTER);
		
		ct.setLayout(new GridLayout(2, 1, 10, 10));
		ct.add(jp1);
		ct.add(jp2);
	}
	
	protected void addListeners(){
		jbLeft2Basket.addActionListener((ActionEvent ae) -> {
			String selectedValue = jlistLeft.getSelectedValue();
			if(selectedValue != null) {
				dlmLeft.removeElement(selectedValue);
				dlmBasket.addElement(selectedValue);
			}
		});
		jbLeft.addActionListener((ActionEvent ae) -> {
			String selectedValue = jlistBasket.getSelectedValue();
			if(selectedValue != null) {
				dlmBasket.removeElement(selectedValue);
				dlmLeft.addElement(selectedValue);
			}
		});
		jbRight.addActionListener((ActionEvent ae) -> {
			String selectedValue = jlistBasket.getSelectedValue();
			if(selectedValue != null) {
				dlmBasket.removeElement(selectedValue);
				dlmRight.addElement(selectedValue);
			}
		});
		jbRight2Basket.addActionListener((ActionEvent ae) -> {
			String selectedValue = jlistRight.getSelectedValue();
			if(selectedValue != null) {
				dlmRight.removeElement(selectedValue);
				dlmBasket.addElement(selectedValue);
			}
		});
		
		jbCompare.addActionListener((ActionEvent ae) -> {
			if (dlmLeft.size() == 0 || dlmRight.size() == 0)
				return;
			int leftOrRight = compare();
			jta.append(dlmToStr());
			switch(leftOrRight) {
			case 1:
				jta.append(" -> 왼쪽이 무겁습니다.\r\n");
				break;
			case 0:
				jta.append(" -> 무게가 같습니다.\r\n");
				break;
			case -1:
				jta.append(" -> 오른쪽이 무겁습니다.\r\n");
				break;
			}
		});
		
//		jbAnswer.addActionListener((ActionEvent ae) -> {
//			if (jlistBasket.getSelectedIndex() < 0)
//				return;
//			String selected = jlistBasket.getSelectedValue();
//			if(selected.equals(answerBall))
//				jta.append(selected + " -> 정답입니다!\r\n");
//			else
//				jta.append(selected + " -> 오답입니다!\r\n");
//		});
	}
	
	private String dlmToStr() {
		StringBuffer sb = new StringBuffer();
		sb.append(dlmLeft.get(0));
		for (int i=1; i<dlmLeft.size();i++) {
			sb.append(", ").append(dlmLeft.get(i));
		}
		sb.append(" / ").append(dlmRight.get(0));
		for (int i=1; i<dlmRight.size();i++) {
			sb.append(", ").append(dlmRight.get(i));
		}
		
		return sb.toString();
	}
	
	private int compare() {
		int left = 0;
		int right = 0;
		for (int i=0; i<dlmLeft.size();i++) {
			if (dlmLeft.getElementAt(i).equals(answerBall)) {
				left += 2;
			} else {
				left += 1;
			}
		}
		for (int i=0; i<dlmRight.size();i++) {
			if (dlmRight.getElementAt(i).equals(answerBall)) {
				right += 2;
			} else {
				right += 1;
			}
		}
		return left > right ? 1 : left < right ? -1 : 0;
	}
	
	private void setWeight() {
		Random r = new Random();
		answerBall = Integer.toString(r.nextInt(8) + 1);
	}
	
	protected void initWnd(){
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

