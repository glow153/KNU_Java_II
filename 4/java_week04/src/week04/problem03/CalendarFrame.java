package week04.problem03;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CalendarFrame extends JFrame {
	protected static final String VERSION = "0.1";
	private int wndSizeWidth;
	private int wndSizeHeight;
	private String title;

	private Container ct;
	private JPanel jpHeader, jpCalendar;
	private JLabel jlMonth;
	private JButton jbLeft, jbRight;
	private JLabel[] jlDate;
	private JLabel[] jlCaption;
	private String[] caption;

	private void initComps() {
		ct = getContentPane();
		jpHeader = new JPanel();
		jpCalendar = new JPanel();
		jbLeft = new JButton("¢¸");
		jbRight = new JButton("¢º");
		jlMonth = new JLabel("", JLabel.CENTER);
		caption = new String[] {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		jlCaption = new JLabel[7];
		for (int i = 0; i < jlCaption.length; i++)
			jlCaption[i] = new JLabel(caption[i], JLabel.CENTER);
		jlDate = new JLabel[42];
		for (int i = 0; i < jlDate.length; i++)
			jlDate[i] = new JLabel(" ", JLabel.CENTER);
	}

	private void addComps() {
		// set header panel
		jpHeader.setLayout(new BorderLayout(5, 5));
		jpHeader.add(jbLeft, BorderLayout.WEST);
		jpHeader.add(jbRight, BorderLayout.EAST);
		jpHeader.add(jlMonth, BorderLayout.CENTER);

		// set calendar panel
		jpCalendar.setLayout(new GridLayout(7, 7, 5, 5));
		for (int i = 0; i < jlCaption.length; i++)
			jpCalendar.add(jlCaption[i]);
		for (int i = 0; i < jlDate.length; i++)
			jpCalendar.add(jlDate[i]);

		// set container
		ct.setLayout(new BorderLayout(10, 10));
		ct.add(jpHeader, BorderLayout.NORTH);
		ct.add(jpCalendar, BorderLayout.CENTER);
	}

	private void initWnd() {
		setTitle(title + " - v" + VERSION);
		setSize(wndSizeWidth, wndSizeHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void setView() {
		// init values
		LocalDate today = LocalDate.now();
		String sMonth = today.format(DateTimeFormatter.ofPattern("yyyy³â MM¿ù"));
		LocalDate firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
		LocalDate lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1);
		int lastDay = lastDayOfMonth.getDayOfMonth();
		
		// set header
		jlMonth.setText(sMonth);

		// set calendar
		int dow = firstDayOfMonth.getDayOfWeek().getValue();
		int dayValue = 1;
		if(dow == 7) // format 0~6
			dow = 0;
		for (int i = 0; i < 42; i++) {
			if(dayValue > lastDay)
				break;
			if(i >= dow) {
				jlDate[i].setText(Integer.toString(dayValue));
				dayValue += 1;
			}
		}
	}

	public CalendarFrame(String title, int w, int h) {
		this.title = title;
		wndSizeWidth = w;
		wndSizeHeight = h;

		initComps();
		addComps();
		initWnd();

		// set today's calendar
		setView();
	}
}
