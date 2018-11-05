package problem01;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BaseConversionFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;

	protected JTextField jtfIn, jtfOut;
	protected JRadioButton[] rbo;
	protected ButtonGroup bgo;
	protected String[] rbCaption;

	protected ActionListener listener = (ActionEvent e) -> {
		String out = convertToString(jtfIn.getText(), e.getActionCommand());
		jtfOut.setText(out);
	};
	
	protected String convertToString(String input, String base) {
		int value = 0;
		String output = "";
		
		try {
			value = Integer.parseInt(input);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		
		if(base.equals("2진수"))
			output = Integer.toBinaryString(value);
		else if(base.equals("16진수"))
			output = Integer.toHexString(value);
		else
			output = input;
		return output;
	}
	
	protected void initComps() {
		ct = getContentPane();
		rbCaption = new String[] { "2진수", "10진수", "16진수" };
		bgo = new ButtonGroup();

		jtfIn = new JTextField();
		jtfOut = new JTextField();
		rbo = new JRadioButton[4];
		for (int i = 0; i < 3; i++) {
			rbo[i] = new JRadioButton(rbCaption[i]);
			bgo.add(rbo[i]);
		}
	}

	protected void addComps() {
		ct.setLayout(new GridLayout(5, 2, 5, 5));
		ct.add(new JLabel("입력", JLabel.CENTER));
		ct.add(new JLabel("출력", JLabel.CENTER));
		ct.add(jtfIn);
		ct.add(jtfOut);
		for (int i = 0; i < 3; i++) {
			ct.add(new JLabel(""));
			ct.add(rbo[i]);
		}	
	}

	protected void addListeners() {
		for (int i = 0; i < 3; i++) {
			rbo[i].addActionListener(listener);
		}
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public BaseConversionFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
	}
}
