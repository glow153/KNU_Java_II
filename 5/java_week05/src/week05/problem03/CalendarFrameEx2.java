package week05.problem03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CalendarFrameEx2 extends CalendarFrame {
	protected static final String VERSION = "0.2";
	private LocalDate today;
	
	protected void setView(int diffMonth) {
		today = today.plusMonths(diffMonth);
		super.setView(today);
	}
	
	private void addListeners() {
		jbLeft.addActionListener( (ActionEvent e) -> setView(-1) );
		jbRight.addActionListener( (ActionEvent e) -> setView(1) );
	}

	public CalendarFrameEx2(String title, int w, int h) {
		super("", w, h);
		today = LocalDate.now();
		
		setTitle(title + " - v" + VERSION);
		addListeners();
	}
}
