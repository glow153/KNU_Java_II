package week02.problem03;

public class Main {
	public static void main(String[] args) {
		GPACalc gpa = new GPACalc();
		gpa.readSbjGrade("C:/example/1st_sem.txt");
		gpa.printContent();
		gpa.printGpa();
	}
}
