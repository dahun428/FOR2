package collection.recruit.repo;

import java.util.ArrayList;

import collection.recruit.vo.Recruitment;

public class RecruitmentRepository {

	private ArrayList<Recruitment> db = new ArrayList<Recruitment>();
	private int sequence = 100;
	
	//구직신청서 등록하기
	public int addRecruitment(Recruitment recruitment) {
		recruitment.setNo(sequence++);
		db.add(recruitment);
		
		return sequence-1;
	}
	
	//나의 구직신청 현황 조회하기
	public Recruitment getRecruitmentByNo(int recruitmentNo) {
		Recruitment result = null;
		for(Recruitment recruitment : db) {
			if(recruitment.getNo() == recruitmentNo) {
				result = recruitment;
				break;
			}
		}
		return result;
	}
	
	//나의 구직 신청 삭제하기
	public void deleteRecruitmentByNo(int recruitmentNo) {
		for(Recruitment recruitment : db) {
			if(recruitment.getNo() == recruitmentNo) {
				db.remove(recruitment);
			}
		}
	}
	
	//전체 구직 현황 조회하기
	public ArrayList<Recruitment> getAllRecruitments(){
		return db;
	}
	
}
