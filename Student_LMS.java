package homework;

public class Student {
	
	String name;
	int classNum;
	int num;
	int kor;
	int eng;
	int math;
	
	int sum(int kor, int eng, int math) {
		int sumArr = kor + eng + math;
		return sumArr;
	}
	
	int avg(int kor, int eng, int math) {
		int avgerage = (int)((kor + eng + math) / 3);
		return avgerage;
	}
	
	void display() {
		System.out.println("----------------------------------");
		System.out.println("학생이름 : " + name);
		System.out.println("반 : " + classNum);
		System.out.println("번    호 : " + num);
		System.out.println("국어점수 : " + kor);
		System.out.println("영어점수 : " + eng);
		System.out.println("수학점수 : " + math);
		System.out.println("총    점 : " + sum(kor, eng, math));
		System.out.println("평    균 : " + avg(kor, eng, math));
	}
}
