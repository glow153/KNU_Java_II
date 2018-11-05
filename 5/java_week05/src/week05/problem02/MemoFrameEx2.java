package week05.problem02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MemoFrameEx2 extends MemoFrame {
	protected static final String VERSION = "0.2";
	private static final String MEMOFILE = "editor.txt";
	private String[] imgpath = {"new.png", "open.png", "save.png", "exit.png"};

	public void addListeners() {
		for (int i = 0; i < jb.length; i++) {
			final int idx = i;
			jb[i].addActionListener((ActionEvent e) -> {
				// TODO Auto-generated method stub
				switch (idx) {
				case 0: // new
					fNew();
					break;
				case 1: // open
					fRead(MEMOFILE);
					break;
				case 2: // save
					fSave();
					break;
				case 3: // exit
					System.exit(0);
					break;
				}
			});
		}
	}
	
	public void setBtnCaption() {
		for (int i = 0; i < jb.length; i++) {
			jb[i].setText("");
			jb[i].setIcon(new ImageIcon("./imgs/" + imgpath[i]));
		}
	}
	
	private void fNew() {
		jta.setText("");
	}
	
	private void fSave() {
		FileWriter fw = null;
		try {
			fw = new FileWriter(MEMOFILE);
			fw.write(jta.getText());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MemoFrameEx2(String title, int w, int h) {
		super("", w, h);
		setTitle(title + " - v" + VERSION);
		setBtnCaption();
		addListeners();
	}
}
