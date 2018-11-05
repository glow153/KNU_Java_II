package problem01;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TodoListFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected String title;
	protected Container ct;
	protected DefaultListModel<String> dlm;
	protected JList<String> jlist;
	protected JTextField jtf;
	protected JButton jbAdd, jbDelete;
	
	protected void initComps(){
		ct = getContentPane();
		dlm = new DefaultListModel<>();
		jlist = new JList<>(dlm);
		dlm.addElement("자바 실습 과제 올리기");
		dlm.addElement("조별 과제 모임");
		dlm.addElement("빨래 하기");
		
		jtf = new JTextField();
		jbAdd = new JButton("추가");
		jbDelete = new JButton("삭제");
	}
	
	protected void addComps(){
		JPanel jp = new JPanel();
		JPanel jp2 = new JPanel();
		jp.setLayout(new BorderLayout(5, 5));
		jp2.setLayout(new GridLayout(1, 2, 5, 5));
		jp.add(jtf, BorderLayout.CENTER);
		jp2.add(jbAdd);
		jp2.add(jbDelete);
		jp.add(jp2, BorderLayout.EAST);
		
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jlist, BorderLayout.CENTER);
		ct.add(jp, BorderLayout.SOUTH);
	}
	
	protected void addListeners(){
		jlist.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int selected = jlist.getSelectedIndex();
				if(jlist.getSelectedIndex() != -1)
					jtf.setText(dlm.get(selected));
			}
		});
		
		jbAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dlm.addElement(jtf.getText());
				jtf.setText("");
			}
		});
		
		jbDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dlm.removeElement(jtf.getText());
				jtf.setText("");
			}
		});
	}
	
	protected void initWnd(){
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public TodoListFrame(String title, int w, int h){
		this.title = title;
		this.width = w;
		this.height = h;
		
		initComps();
		addComps();
		addListeners();
		initWnd();
	}
}