package collection.recruit.vo;
/**
 * 구직정보를 담는 객체용 설계도
 * @author JHTA
 *
 */

import java.util.ArrayList;

public class Recruitment {

	private int no;					//구직신청번호, Repository에서 자동증가
	private Resume resume;			//이력서 정보
	private ArrayList<Career> careers = new ArrayList<Career>();// 경력사항
	private boolean isPassed;		//합격여부
	
	public Recruitment() {}

	public Recruitment(int no, Resume resume, ArrayList<Career> careers, boolean isPassed) {
		super();
		this.no = no;
		this.resume = resume;
		this.careers = careers;
		this.isPassed = isPassed;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public ArrayList<Career> getCareers() {
		return careers;
	}

	public void setCareers(ArrayList<Career> careers) {
		this.careers = careers;
	}
	/**
	 * 경력사항 추가
	 * @param career
	 */
	public void addCareer(Career career) {
		this.careers.add(career);
	}
	

	public boolean isPassed() {
		return isPassed;
	}

	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	
	
	
	
}
