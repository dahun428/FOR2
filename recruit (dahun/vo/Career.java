package collection.recruit.vo;
/**
 * 경력사항 정보를 담는 객체용 설계도
 * @author JHTA
 *
 */
public class Career {

	private String companyName;		//재직했던 회사명
	private String dept;			//근무했던 부사명
	private String position;		//최종 직급(사원, 대리, 과장)
	private int year; 				//근무기간(년단위)
	
	public Career(String companyName, String dept, String position, int year) {
		super();
		this.companyName = companyName;
		this.dept = dept;
		this.position = position;
		this.year = year;
	}
	
	public Career() {}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}
