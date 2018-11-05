package week03.problem02;

import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		CalendarWriter cw = new CalendarWriter();
		cw.inputYear();
		cw.writeCalendar("C:/example/calendar.txt");
		System.out.println("달력이 생성되었습니다.");
	}
}
