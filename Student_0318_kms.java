package oop2;

public class Student {
	String studentName;
	int classNo;
	int studentNo;
	int kor;
	int eng;
	int math;
	
	int totalScore() {
		int totalScore = 0;
		totalScore = kor+eng+math;
		
		return totalScore;
	}
	
	int avgScore() {
		int avgScore = 0;
		avgScore = totalScore()/3;
		
		return avgScore;
	}
	
	void printStudentsInfo() {
		System.out.print(studentName+"\t");
		System.out.print(classNo+"\t");
		System.out.print(studentNo+"\t");
		System.out.print(kor+"\t");
		System.out.print(eng+"\t");
		System.out.print(math+"\t");
		System.out.print(totalScore()+"\t");
		System.out.println(avgScore());
	}
	
	
	
}
