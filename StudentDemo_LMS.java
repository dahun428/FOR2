package homework;

import java.util.Scanner;

public class StudentDemo {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Student[] students = new Student[10];
		int savePosition = 0;

		while (true) {
			System.out.println("=============================================================");
			System.out.println("1.조회	2.검색	3.입력	4.종료");
			System.out.println("=============================================================");
			System.out.print("원하시는 메뉴 번호를 입력하세요 : ");
			int menuNo = scanner.nextInt();

			if (menuNo == 1) {
				System.out.println("========================== 정보 조회 =========================");
				System.out.println("학생이름\t반\t번호\t국어점수\t영어점수\t수학점수\t총합\t평균");
				System.out.println("============================================================");
				for(int i=0; i<savePosition; i++) {
					Student student = students[i];
				System.out.print(student.name + "\t");
				System.out.print(student.classNum + "\t");
				System.out.print(student.num + "\t");
				System.out.print(student.kor + "\t");
				System.out.print(student.eng + "\t");
				System.out.print(student.math + "\t");
				System.out.print(student.sum(student.kor, student.eng, student.math) + "\t");
				System.out.println(student.avg(student.kor, student.eng, student.math));
				}
			} else if (menuNo == 2) {
				System.out.println("============================ 검색 ===========================");
				System.out.print("이름을 입력해주세요 : ");
				String inputName = scanner.next();
				Student name = null;
				for (int i = 0; i < savePosition; i++) {
					Student student = students[i];
					boolean isFound = false;
					if (inputName.equals(student.name)) {
						isFound = true;
					} else {
						System.out.println("일치하는 이름의 학생을 찾을 수 없습니다.");
					}
					if (isFound) {
						student.display();
					}
				}
			} else if (menuNo == 3) {
				Student student = new Student();
				System.out.print("학생 이름을 입력해주세요 : ");
				student.name = scanner.next();
				System.out.print("학생의 반을 입력해주세요 : ");
				student.classNum = scanner.nextInt();
				System.out.print("학생 번호를 입력해주세요 : ");
				student.num = scanner.nextInt();
				System.out.print("국어 점수를 입력해주세요 : ");
				student.kor = scanner.nextInt();
				System.out.print("영어 점수를 입력해주세요 : ");
				student.eng = scanner.nextInt();
				System.out.print("수학 점수를 입력해주세요 : ");
				student.math = scanner.nextInt();
				
				students[savePosition] = student;
				savePosition++;
			} else if (menuNo == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}

	}
}
