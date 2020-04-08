package com.sample.school.service;

import com.sample.school.vo.Course;
import com.sample.school.vo.Professor;
import com.sample.school.vo.Student;
import com.sample.school.vo.Subject;

/**
 * 수강신청과 관련된 서비스를 제공하는 구현객체가 반드시 구현해야 하는 기능을 정의한 인터페이스다.<br />
 * <h3>교수관련 기능</h3>
 * <ul>
 * 	<li>교수등록</li>
 * 	<li>과목등록</li>
 * 	<li>과목조회</li>
 * 	<li>개설과정등록</li>
 * 	<li>개설과정 조회</li>
 * 	<li>과정 신청자 조회</li>
 * 	<li>성적 부여</li>
 * </ul>
 * 
 * <h3>학생관련 기능</h3>
 * 	<li>학생등록</li>
 * 	<li>과목조회</li>
 * 	<li>개설과정 조회</li>
 * 	<li>수강신청</li>
 * 	<li>수강신청 현황 조회</li>
 * 	<li>수강신청 취소</li>
 * 	<li>성적 조회</li>
 * </ul>

 * @author JHTA
 *
 */
public interface SchoolService {
//	* 	<li>교수등록</li>
	void addNewProfessor(Professor professor);
//		* 	<li>과목등록</li>
	void addNewSubject(Subject subject);

//		* 	<li>과목조회</li>
	void findSubject(int professorNo); 

//		* 	<li>개설과정등록</li>
	void addNewCourse(String courseName, int subjectNo, int professorNo, int quota);



//		System.out.println("---------------------------------------개설과정 조회내역---------------------------------------");
//		System.out.println("이름		이메일	학과		직위		급여");
//		System.out.println(professor.no + "\t\t" + professor.name + "\t" + professor.price + "\t\t" + professor.discountPrice+ "\t\t" + professor.stock+ "\t\t" + professor.score);
//		System.out.println("-----------------------------------------------------------------------------------------");	


//		* 	<li>개설과정 조회</li>
	void findCourse(int professorNo);

//		* 	<li>과정 신청자 조회</li>
	void findCourseApplicant(int professorNo, int courseNo);

//		* 	<li>성적 부여</li>
	void giveScore(int professorNo, int registrationNo, int score);

//		* <h3>학생관련 기능</h3>

//		* 	<li>학생등록</li>
	void addNewStudent(Student student);

//	* 	<li>과목조회</li>
	void findSubjectByStudentNo(int studentNo); 

//		* 	<li>수강신청</li>
	void registSubject(int studentNo, int courseNo);

//		* 	<li>수강신청 현황 조회</li>
	void findRegistedSubject(int studentNo);

//		* 	<li>수강신청 취소</li>
	void iscancleSubject(int studentNo, int registrationNo);

//		* 	<li>성적 조회</li>
	void findScore(int studentNo);

}