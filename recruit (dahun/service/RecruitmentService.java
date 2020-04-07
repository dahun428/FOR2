package collection.recruit.service;

import java.util.ArrayList;

import collection.recruit.repo.RecruitmentRepository;
import collection.recruit.vo.Career;
import collection.recruit.vo.Recruitment;
import collection.recruit.vo.Resume;

public class RecruitmentService {

	private RecruitmentRepository repo = new RecruitmentRepository();
	
	// 이력서 등록기능
	public void addNewResume(Resume resume) {
		
		Recruitment recruitment = new Recruitment();
		recruitment.setResume(resume);
		int recruitmentNum = repo.addRecruitment(recruitment);
		// 구직신청객체 생성
		// 구직신청객체에 전달받은 이력서 객체 저장
		// 레포지토리객체를 사용해서 db에 저장하기
		System.out.println();
		System.out.println("당신의 구직신청 번호는 ["+recruitmentNum+"] 입니다.");
		
	}
	
	// 경력사항 등록기능
	public void addCareer(int recruitmentNo, Career career) {
		Recruitment recruitment = repo.getRecruitmentByNo(recruitmentNo);
		if(recruitment == null) {
			System.out.println("["+recruitmentNo+"] 번호에 해당하는 구직신청현황이 존재하지 않습니다.");
			return;
		}
		recruitment.addCareer(career);
		System.out.println();
		System.out.println("경력사항 등록이 완료되었습니다.");
		// 레포지토리에서 구직신청번호에 해당하는 구직신청객체를 조회한다.
		// 구직신청객체의 경력사항에 전달받은 경력사항객체를 추가한다.
	}
	public void getIsPassed(int recruitmentNo) {
		Recruitment recruitment = repo.getRecruitmentByNo(recruitmentNo);
		if(recruitment == null) {
			System.out.println("["+recruitmentNo+"] 번호에 해당하는 구직신청현황이 존재하지 않습니다.");
			return;
		}
		boolean isPassed = recruitment.isPassed();
		if(isPassed) {
			System.out.println("축하합니다 ["+recruitment.getResume().getName()+"]님 합격되셨습니다.");
		} else {
			System.out.println("지원해주셔서 감사합니다. ["+recruitment.getResume().getName()+"]님 불합격되었습니다.");
		} 
	}
	// 구직신청 상태 조회 기능
	public void printRecruitmentDetail(int recruitmentNo) {
		Recruitment recruitment = repo.getRecruitmentByNo(recruitmentNo);
		if(recruitment == null) {
			System.out.println("["+recruitmentNo+"] 번호에 해당하는 구직신청현황이 존재하지 않습니다.");
			return;
		}
		Resume resume = recruitment.getResume();
		System.out.println();
		System.out.println("===========["+resume.getName()+"] 님의 이력서정보============");
		
		System.out.println("신청번호	: "+recruitment.getNo());
		System.out.println("전화번호	: "+resume.getTel());
		System.out.println("지원부서	: "+resume.getDept());
		
		ArrayList<Career> careers = recruitment.getCareers();
		if(careers.isEmpty()) {
			System.out.println("["+recruitmentNo+"] 번호에 해당하는 경력사항 정보가 없습니다.");
		} else {
			System.out.println();
			System.out.println("=====["+resume.getName()+"]님의 경력사항==============");
			System.out.println("재직회사	재직부서	최종직급	근무기간(연)");
			System.out.println("=========================================");
			
			for(Career career : careers) {
				String companyName = career.getCompanyName();
				String dept = career.getDept();
				String position = career.getPosition();
				int year = career.getYear();
				
				System.out.println(companyName+"\t"+dept+"\t"+position+"\t"+year);
			}
		}
		
		
		// 레포지토리에서 구직신청번호에 해당하는 구직신청객체를 조회해서 화면에 출력한다.
		
	}
	
	// 이력서 수정 기능
	public void updateResume(int recruitmentNo, Resume resume) {
		Recruitment recruitment = repo.getRecruitmentByNo(recruitmentNo);
		if(recruitment == null) {
			System.out.println("["+recruitmentNo+"] 번호에 해당하는 구직신청현황이 존재하지 않습니다.");
			return;
		}
		
		Resume oldResume = recruitment.getResume();
		oldResume.setName(resume.getName());
		oldResume.setTel(resume.getTel());
		oldResume.setDept(resume.getDept());
		recruitment.setResume(resume);
		
		System.out.println("이력서가 성공적으로 수정 완료 되었습니다.");
		
		// 레포지토리에서 구직신청번호에 해당하는 구직신청객체를 조회한다.
		// 구직신청객체의 이력서정보를 전달받은 이력서 정보로 교체한다.
	}
	
	// 구직신청 삭제하기
	public void deleteRecruitment(int recruitmentNo) {
		Recruitment recruitment = repo.getRecruitmentByNo(recruitmentNo);
		if(recruitment == null) {
			System.out.println("["+recruitmentNo+"] 번호에 해당하는 구직신청현황이 존재하지 않습니다.");
			return;
		}
		repo.deleteRecruitmentByNo(recruitmentNo);
		System.out.println("이력서 삭제가 완료 되었습니다.");
		// 레포지토리에서 구직신청번호에 해당하는 구직신청객체를 삭제한다.
	}
	
	// 전체 구직신청정보 간단 조회
	public void printAllRecruitment() {
		ArrayList<Recruitment> recruitments = repo.getAllRecruitments();
		for(Recruitment recruitment : recruitments) {
			Resume resume = recruitment.getResume();
			System.out.println(recruitment.getNo()+"\t"+resume.getName()+"\t"
					+resume.getTel()+"\t"+recruitment.isPassed());
		}
		// 레포지토리에서 모든 구직신청 정보를 조회해서 화면에 출력한다.
	}
	
	// 구직신청 심사하기
	public void checkRecruitment(int recruitmentNo, boolean passed) {
		Recruitment recruitment = repo.getRecruitmentByNo(recruitmentNo);
		if(recruitment == null) {
			System.out.println("["+recruitmentNo+"] 번호에 해당하는 구직신청현황이 존재하지 않습니다.");
			return;
		}
		recruitment.setPassed(passed);
		
		System.out.println("심사가 완료되었습니다.");
		// 레포지토리에서 구직신청번호에 해당하는 구직신청객체를 조회한다.
		// 합격/불합격여부를 결정한다.
	}

}