package problem02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaletteFrame extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected String title;
	protected int width, height;
	protected Container ct;
	protected JPanel jpPalette;
	protected JTextField[] jtfColor;
	protected JButton jbStart;

	protected ColorChangerThread[] colorChanger;

	public static void main(String[] args) {
		new PaletteFrame("∆»∑π∆Æ", 250, 180);
	}

	public PaletteFrame(String title, int w, int h) {
		this.title = title;
		this.width = w;
		this.height = h;

		initComps();
		addComps();
		addListeners();
		initWnd();
	}

	protected void initComps() {
		ct = getContentPane();
		jpPalette = new JPanel();
		jbStart = new JButton("Ω√¿€");

		jtfColor = new JTextField[3];
		colorChanger = new ColorChangerThread[3];

		for (int i = 0; i < jtfColor.length; i++) {
			jtfColor[i] = new JTextField("255");
			colorChanger[i] = new ColorChangerThread(i);
		}
	}

	protected void addComps() {
		JPanel jpRGB = new JPanel();
		jpRGB.setLayout(new GridLayout(1, 3, 10, 10));
		for (int i = 0; i < jtfColor.length; i++)
			jpRGB.add(jtfColor[i]);

		JPanel jpCenter = new JPanel();
		jpCenter.setLayout(new BorderLayout());
		jpCenter.add(jpPalette, BorderLayout.CENTER);
		jpCenter.add(jpRGB, BorderLayout.SOUTH);

		ct.setLayout(new BorderLayout());
		ct.add(jpCenter, BorderLayout.CENTER);
		ct.add(jbStart, BorderLayout.SOUTH);
		
		jpPalette.setBackground(new Color(255, 255, 255));
	}

	protected void addListeners() {
		jbStart.addActionListener((ActionEvent ae) -> {
			for (int i = 0; i < colorChanger.length; i++) {
				colorChanger[i].start();
			}
			jbStart.setEnabled(false);
		});
	}

	protected void initWnd() {
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private synchronized void decrement(int rgbIdx) {
		int value = Integer.parseInt(jtfColor[rgbIdx].getText());
		value -= 1;
		jtfColor[rgbIdx].setText(Integer.toString(value));
		
		int r = Integer.parseInt(jtfColor[0].getText());
		int g = Integer.parseInt(jtfColor[1].getText());
		int b = Integer.parseInt(jtfColor[2].getText());
		jpPalette.setBackground(new Color(r, g, b));
	}
	
	private class ColorChangerThread extends Thread {
		private int index;

		public ColorChangerThread(int index) {
			// TODO Auto-generated constructor stub
			this.index = index;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 255; i > 0; i--) {
				decrement(index);	// update view (not synchronized!)
//				setPalette();
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
