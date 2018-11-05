package week03.problem02;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class CalendarWriter {
	private String year;

	public CalendarWriter() {

	}

	void inputYear() {
		Scanner sc = new Scanner(System.in);
		System.out.print("연도를 입력하세요(yyyy) : ");
		year = sc.nextLine();
	}

	private String getMonthCalendar(int year, int month) {
		StringBuffer sbMonthCal = new StringBuffer();
		LocalDate firstDay = LocalDate.of(year, month, 1);
		LocalDate lastDay = firstDay.plusMonths(1).minusDays(1);
		int dow = firstDay.getDayOfWeek().getValue();
		
		if (dow == 7)
			dow = 0;

		// make String Calendar
		sbMonthCal.append("<" + month + "월>\r\n")
				  .append(" Sun  Mon  Tue  Wed  Thu  Fri  Sat\r\n");

		for (int i = 0; i < dow; i++)
			sbMonthCal.append("     ");
		for (int i = 0; i < lastDay.getDayOfMonth(); i++) {
			if (0 < (i+1) && (i+1) < 10)
				sbMonthCal.append("  " + (i+1) + "  ");
			else
				sbMonthCal.append(" " + (i+1) + "  ");
			
			dow += 1;
			if (dow % 7 == 0)
				sbMonthCal.append("\r\n");
			
		}
		sbMonthCal.append("\r\n===================================\r\n");
		return sbMonthCal.toString();
	}

	void writeCalendar(String calFilePath) {
		FileWriter fw = null;
		int y = Integer.parseInt(year);
		try {
			fw = new FileWriter(calFilePath);
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		for(int m=1;m<=12;m++) {
			String monthCal = getMonthCalendar(y, m);
			try {
				fw.write(monthCal);				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		if (fw != null) {
			try {
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
