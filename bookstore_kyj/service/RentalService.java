package com.sample.bookstore.service;

import com.sample.bookstore.vo.Rental;

public class RentalService {
	
	private Rental[] db = new Rental[100];
	private int position = 0;
	private int rentalSequence = 50001;
	
	// 대여정보 저장 기능
	// 전달받은 대여정보를 db에 저장한다.
	public void insertRental(Rental rental) {
		rental.no = rentalSequence;
		db[position] = rental;
		
		position++;
		rentalSequence++;
	}
	
	// 대여정보 조회기능
	// 대여번호에 해당하는 대여정보를 반환한다.
	public Rental findRentalByNo(int rentalNo) {
		Rental result = null;
		for (int i=0; i<position;i++ ) {
			Rental rental = db[i];
			if(rentalNo == (rental.no)) {
				result=rental;
				break;
			}
		}
		return result;
	}
	
//	대여현황조회(추가기능)
//	회원번호를 입력받아서 그 고객이 대여중인 모든 책정보
//	(책번호, 제목, 가격, 대여상태) 출력하기
	
//	public void getRentalsByUserNo(int userNo) {
//		for (int i=0; i<position; i++) {
//			if (db[i].userNo==userNo) {
//				Book book = bookService.findBookByNo(db[i].bookNo);
//				System.out.println("도서번호: " + db[i].bookNo);
//				System.out.println("도서제목: " + book.title);
//				System.out.println("도서가격: " + book.price);
//				System.out.println("대여상태: " + db[i].isRent);
//				
//				
//			}
//			
//		}
//	}
//	
	
//	대여현황조회(추가기능)
//	책번호를 입력받아서 그 책을 대여중인 정보(대여번호, 회원번호, 회원명) 출력하기
	
//	public void getRentalsByBookNo(int bookNo) {
//		for (int i=0; i<position; i++) {
//			if (db[i].bookNo==bookNo) {
//				User user = userService.findUserByNo(db[i].userNo);
//				System.out.println("대여번호: " + db[i].no);
//				System.out.println("회원번호: " + user.no);
//				System.out.println("회원이름: " + user.name);
//				
//				
//			} else {
//				System.out.println("이 도서를 빌려간 회원이 없습니다.");
//			}
//			
//		}
//	}
//	
	
	
	// 대여현황조회기능
	// 모든 대여정보를 반환한다.
	public Rental[] getAllRentals() {
		return db;
	}
	
}