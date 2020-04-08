package com.sample.school.service;

import com.sample.school.repository.CourseRepository;
import com.sample.school.repository.ProfessorRepository;
import com.sample.school.repository.RegistrationRepository;
import com.sample.school.repository.StudentRepository;
import com.sample.school.repository.SubjectRepository;
import com.sample.school.vo.Course;
import com.sample.school.vo.Professor;
import com.sample.school.vo.Registration;
import com.sample.school.vo.Student;
import com.sample.school.vo.Subject;

public class SchoolServiceImple implements SchoolService {

	private CourseRepository courseRepo = new CourseRepository();
	private ProfessorRepository professorRepo = new ProfessorRepository();
	private RegistrationRepository registrationRepo = new RegistrationRepository();
	private SubjectRepository subjectRepo = new SubjectRepository();
	private StudentRepository studentRepo = new StudentRepository();

	@Override
	public void addNewProfessor(Professor professor) {
		Professor[] professors = professorRepo.getAllProfessor();
		for(Professor pro : professors) {
			if (pro.getEmail().equals(professor.getEmail())) {
				System.out.println("입력하신 이메일로 가입한 회원정보가 있습니다.");
				return;
			}
		}
		professorRepo.insertProfessor(professor);
		System.out.println("등록을 완료하였습니다.");
	}



	@Override
	public void addNewSubject(Subject subject) {
		Subject[] subjects = subjectRepo.getAllSubject();
		for (Subject sub : subjects) {
			if (sub.getTitle().equals(subject.getTitle())) {
				System.out.println("동일한 제목의 과목이 이미 등록되어있습니다.");
				return;
			}
		}
		subjectRepo.insertSubject(subject);
		System.out.println("등록을 완료하였습니다.");
	}

	@Override
	public void addNewStudent(Student student) {
		Student[] students = studentRepo.getAllStudents();
		for(Student stu : students) {
			if (stu.getEmail().equals(student.getEmail())) {
				System.out.println("입력하신 이메일로 가입한 회원정보가 있습니다.");
				return;
			}
		}
		studentRepo.insertStudent(student);
		System.out.println("등록을 완료하였습니다.");
	}

	@Override
	public void findSubject(int professorNo) {
		Subject[] subjects = subjectRepo.getAllSubject();
		Professor professor = professorRepo.getProfessorByNo(professorNo);
		if (professor==null) {
			System.out.println("없는 교수번호입니다.");
			return;
		}
		for(Subject sub : subjects) {
			if(sub.getDept().equals(professor.getDept())) {
				System.out.println(sub.getTitle());
				System.out.println(sub.getNo()+	sub.getTitle()	+sub.getDept()	+sub.getCredit());
			}
		}
	}
	@Override
	public void addNewCourse (String courseName, int subjectNo, int professorNo, int quota) {
		Professor professor = professorRepo.getProfessorByNo(professorNo);
		Subject subject = subjectRepo.getSubjectByNo(subjectNo);
		if (!professor.getDept().equals(subject.getDept())) {
			System.out.println("일치하지 않는 정보입니다.");
			return;
		}
		Course course = new Course();
		course.setName(courseName);
		course.setSubjectNo(subjectNo);
		course.setProfessorNo(professorNo);
		course.setQuota(quota);
		courseRepo.insertCourse(course);

		System.out.println("등록을 완료하였습니다.");
	}

	@Override
	public void findCourse(int professorNo) {
		Professor professor = professorRepo.getProfessorByNo(professorNo);
		professor.getDept();
		Course [] courses = courseRepo.getAllCourse();
		for (Course cou : courses) {
			if (cou.getProfessorNo()==professorNo) {
				Course c1= courseRepo.getCourseByNo(cou.getNo());
				Subject subject = subjectRepo.getSubjectByNo(cou.getSubjectNo());
				System.out.println("개설과정번호"+cou.getNo() + "\t\t과정명" +cou.getName() + "\t\t과목명" +subject.getTitle());

			}  
		}

	}


	@Override
	public void findSubjectByStudentNo(int studentNo) {
		Subject[] subjects = subjectRepo.getAllSubject();
		Student student = studentRepo.getStudentByNo(studentNo);
		for(Subject sub : subjects) {
			if(sub.getDept().equals(student.getDept())) {
				System.out.println(sub.getNo() + "\t" + sub.getTitle()+ "\t" + sub.getDept()+ "\t" + sub.getCredit());
			}
		}


	}


	@Override
	public void findCourseApplicant(int professorNo, int courseNo) {
		Professor p1 = professorRepo.getProfessorByNo(professorNo);
		Course c1 = courseRepo.getCourseByNo(courseNo);
		Subject subject = subjectRepo.getSubjectByNo(c1.getSubjectNo());
		System.out.println("과목번호: " + c1.getName() + "\t과목명: " + subject.getTitle() +  "\t개설과정명: " + c1.getName() + "\t정원: " + c1.getQuota());
		Registration[] r1 = registrationRepo.getAllRegistrationis();
		for (Registration registration : r1) {
			if(registration.getCourseNo() == courseNo || c1.getProfessorNo() == professorNo ) {  
				Student s1 = studentRepo.getStudentByNo(registration.getStudentNo());
				System.out.println("학생이름: " + s1.getName() + "\t학과: " + s1.getDept() +  "\t학년: " + s1.getGrade());
			}
		}
	}

	@Override
	public void giveScore(int professorNo, int registrationNo, int score) {
		Registration r1 = registrationRepo.getRegistrationByNo(registrationNo);
		Course c1 = courseRepo.getCourseByNo(r1.getCourseNo());
		if (c1.getProfessorNo()!=professorNo) {
			System.out.println("교수번호와 수강신청번호가 일치하지 않습니다.");
		} else if (c1.getProfessorNo()==professorNo) {
			r1.setScore(score);
			System.out.println("성적입력이 완료되었습니다.");
		}

	}


	@Override
	public void findRegistedSubject(int studentNo) {
		Registration[] registrations = registrationRepo.getAllRegistrationis();
		for(int i =0; i<registrations.length;i++) {
			if (registrations[i].getStudentNo() == studentNo) {
				Course c1 = courseRepo.getCourseByNo(registrations[i].getCourseNo());
				System.out.println("수강신청번호: " + registrations[i].getNo() + "\t개설과정명: " + c1 + "\t취소여부: " + registrations[i].isCancel());
			}
		}



	}

	@Override
	public void iscancleSubject(int studentNo, int registrationNo) {
		Registration r1 = registrationRepo.getRegistrationByNo(registrationNo);
		if(r1.getStudentNo() == studentNo) {
			r1.setCancel(true);
		}


	}
	@Override
	public void registSubject(int studentNo, int courseNo) {
		Student s1 = studentRepo.getStudentByNo(studentNo);
		Course c1 = courseRepo.getCourseByNo(courseNo);
		Registration [] r1 = registrationRepo.getAllRegistrationis();
		if(s1 == null) {
			System.out.println("존재하지 않은 학생입니다. 다시 입력해주세요");
			return;
		}
		if(c1 == null) {
			System.out.println("존재하지 않은 과정입니다. 다시 입력해주세요");
			return;
		}
		if(c1.getQuota() > r1.length) {
			System.out.println("정원 초과되었습니다.");
			return;
		}
		String studentDept = s1.getDept();
		
			
		Registration registration = new Registration();
		registration.setStudentNo(s1.getNo());
		registration.setCourseNo(c1.getNo());
		registrationRepo.insertRegistration(registration);
		
	}

	@Override
	public void findScore(int studentNo) {
		Registration [] r1 = registrationRepo.getAllRegistrationis();
		int sumOfAllScore = 0;
		for (int i = 0; i < r1.length;i++) {
			if (r1[i].getStudentNo() == studentNo) {
				Course c1 = courseRepo.getCourseByNo(r1[i].getCourseNo());
				Subject s1 = subjectRepo.getSubjectByNo(c1.getSubjectNo());
				Professor p1 = professorRepo.getProfessorByNo(c1.getProfessorNo());
				String gradePoint = null;
				if (r1[i].getScore() >= 95) {
					gradePoint="A+";
				} else if (r1[i].getScore() >= 90) {
					gradePoint="A";
				} else if (r1[i].getScore() >= 85) {
					gradePoint="B+";
				} else if (r1[i].getScore() >= 80) {
					gradePoint="B";
				} else if (r1[i].getScore() >= 75) {
					gradePoint="C+";
				} else if (r1[i].getScore() >= 70) {
					gradePoint="C";
				} else if (r1[i].getScore() >= 65) {
					gradePoint="D+";
				} else if (r1[i].getScore() >= 60) {
					gradePoint="D";
				} else if (r1[i].getScore() < 60) {
					gradePoint="F";
				}
				sumOfAllScore =  sumOfAllScore + r1[i].getScore();
				int averageScore = sumOfAllScore/r1.length;
				
				String allGradePoint = null;
				if (averageScore >= 95) {
					allGradePoint="A+";
				} else if (averageScore >= 90) {
					allGradePoint="A";
				} else if (averageScore >= 85) {
					allGradePoint="B+";
				} else if (averageScore >= 80) {
					allGradePoint="B";
				} else if (averageScore >= 75) {
					allGradePoint="C+";
				} else if (averageScore >= 70) {
					allGradePoint="C";
				} else if (averageScore >= 65) {
					allGradePoint="D+";
				} else if (averageScore >= 60) {
					allGradePoint="D";
				} else if (averageScore < 60) {
					allGradePoint="F";
				}
				
				
				System.out.println("과목번호: "+c1.getSubjectNo()+ " 과목명: " +s1.getTitle()+ "개설과정명: " +c1.getName() + " 담당교수명:" + p1.getName() +  "성적(점수): " +r1[i].getScore()   +
						" 학점: " +gradePoint + "전체  평균: " +averageScore+ "전체  학점: " + allGradePoint );

			}
		}
	}
}
