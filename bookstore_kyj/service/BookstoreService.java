package com.sample.bookstore.service;

import com.sample.bookstore.vo.Book;
import com.sample.bookstore.vo.Rental;
import com.sample.bookstore.vo.User;

public class BookstoreService {

	BookService bookService = new BookService();
	UserService userService = new UserService();
	RentalService rentalService = new RentalService();

	// 회원등록 서비스
	// 이름, 전화번호를 전달받아서 회원등록 서비스를 제공한다.
	// 		- User객체를 생성해서 이름과 전화번호를 저장한다.
	//		- 회원등록시 100포인트를 지급한다.
	//		- UserService의 회원등록 기능을 실행한다.
	public void addNewUser(String name, String tel) {
		User user = new User();
		user.name = name;
		user.tel = tel;
		user.point = 100;
		user.isDisabled = false;

		userService.insertUser(user);
	}

	// 회원조회 서비스
	// 회원번호를 전달받아서 회원번호에 대한 회원정보를 화면에 출력한다.
	//		- UserService의 회원조회기능을 실행해서 회원정보를 제공받는다.
	//		- 조회된 회원정보를 화면에 출력한다.
	public void retrieveUserInfo(int userNo) {
		User user = userService.findUserByNo(userNo);	
		if (user != null) {
			System.out.println("------------- 회원정보 ---------------");
			System.out.println("회원번호: " + user.no);
			System.out.println("회원이름: " + user.name);
			System.out.println("전화번호: " + user.tel);
			System.out.println("포  인  트: " + user.point);
			System.out.println("탈퇴여부: " + user.isDisabled);
			System.out.println("-------------------------------------");
		} else {
			System.out.println("### 조회하신 정보의 회원님이 없습니다.");
		}



	}

	// 회원수정 서비스
	// 수정할 회원정보를 전달받아서 해당 회원의 정보를 수정한다.
	//		- UserService의 회원정보 변경기능을 실행해서 회원정보를 수정한다.
	public void modifyUserInfo(User user) {
		userService.updateUser(user);
	}

	// 회원탈퇴 서비스
	// 탈퇴처리할 회원번호를 전달받아서 해당 회원을 탈퇴처리한다.
	//		- UserService의 회원조회기능을 실행해서 회원정보를 제공받는다.
	//		- 조회된 회원의 탈퇴여부를 탈퇴처리로 변경한다.
	//		- RentalService의 반납기능을 실행해서 해당 회원이 대여중인 모든 책을 반납처리한다.
	public void disabledUser(int userNo) {
		User user = userService.findUserByNo(userNo);
		if(user != null) {
			user.isDisabled = true;
			returnAllRental(userNo);
			System.out.println("### 회원님의 계정삭제가 완료되었습니다.");
		} else {
			System.out.println("입력한 회원번호가 없습니다.");
		}
	}


	// 전체 회원조회 서비스
	// 등록된 모든 회원정보 조회를 처리한다.
	// 		- UserService의 모든 회원정보 조회기능을 실행해서 모든 회원정보를 제공받는다.
	//		- 조회된 회원정보를 화면에 출력한다.(null체크)
	public void retrieveAllUsers() {
		User[] allUser = userService.getAllUsers();
		for (int i=0; i<allUser.length; i++) {
			if (allUser[i] != null) {				
				System.out.println("------------- 회원정보 ---------------");
				System.out.println("회원번호: " + allUser[i].no);
				System.out.println("회원이름: " + allUser[i].name);
				System.out.println("전화번호: " + allUser[i].tel);
				System.out.println("포  인  트: " + allUser[i].point);
				System.out.println("탈퇴여부: " + allUser[i].isDisabled);
				System.out.println("-------------------------------------");
			}
		}

	}	

	// 도서등록 서비스
	// 제목, 저자, 가격을 전달받아서 도서 등록 서비스를 처리한다.
	//		- Book객체를 생성해서 제목, 저자, 가격 정보를 저장한다.
	//		- 재고는 10000권으로 한다.
	// 		- BookService의 도서등록기능을 실행한다.
	public void insertNewBook(String title, String writer, int price) {
		Book book = new Book();

		book.title = title;
		book.writer = writer;
		book.price = price;
		book.stock = 10000;
		bookService.insertBook(book);
	}

	// 도서 검색 서비스
	// 제목를 전달받아서 도서 검색서비스를 처리한다.
	//		- BookService의 도서 검색기능을 실행해서 책정보를 제공받는다.
	//		- 조회된 책정보를 화면에 출력한다.
	public void searchBooks(String title) {
		Book[] sb = bookService.findBookByTitle(title);
		if(sb != null) {
			for (int i=0; i<sb.length; i++) {				
				System.out.println("------------- 도서정보 ---------------");
				System.out.println("도서번호: " + sb[i].no);
				System.out.println("도서제목: " + sb[i].title);
				System.out.println("도서작가: " + sb[i].writer);
				System.out.println("도서가격: " + sb[i].price);
				System.out.println("도서재고: " + sb[i].stock);
				System.out.println("-------------------------------------");
			}
		}
	}


	// 도서 정보 수정 서비스
	// 책정보를 전달받아서 책정보 수정서비스를 처리한다.
	//		- BookService의 책정보 수정기능을 실행한다.
	public void modifyBook(Book book) {
		bookService.updateBook(book);

	}
	// 전체도서 조회 서비스
	// 모든 책정보 조회 서비스를 처리한다.
	// 		- BookService의 모든 책조회 기능을 실행한다.
	public void retrieveAllBooks() {
		Book[] allBook = bookService.getAllBooks();
		for (int i=0; i<allBook.length; i++) {				
			if(allBook[i] != null) {
				System.out.println("------------- 도서정보 ---------------");
				System.out.println("도서번호: " + allBook[i].no);
				System.out.println("도서제목: " + allBook[i].title);
				System.out.println("도서작가: " + allBook[i].writer);
				System.out.println("도서가격: " + allBook[i].price);
				System.out.println("도서재고: " + allBook[i].stock);
				System.out.println("-------------------------------------");
			}
		}

	}

	// 대여 서비스
	// 사용자번호와 책번호를 전달받아서 대여 서비스를 처리한다.
	//		- Rental객체를 생성해서 사용자번호, 책번호를 저장한다.
	//		- 대여여부는 대여중으로 설정한다.
	//		- 책재고 변경하기
	//			- BookService에서 책번호에 해당하는 책정보 조회기능 실행
	//			- 조회된 책의 재고를 1감소시킨다.
	public void rentBook(int userNo, int bookNo) {

		Rental rental = new Rental();
		rental.userNo = userNo;
		rental.bookNo = bookNo;
		rental.isRent = true;
		Book book = bookService.findBookByNo(bookNo);
		book.stock -= 1;
		rentalService.insertRental(rental);
	}

	// 반납 서비스
	// 대여번호를 전달받아서 반납 서비스를 처리한다.
	//		- RentalService에서 대여번호에 해당하는 대여정보 조회 기능을 실행
	//		- 조회된 대여정보의 반납여부를 false로 설정한다.
	//		- 사용자포인트 증가, 책재고 변경하기
	//			- UserService에서 사용자번호로 사용자정보 조회하기 실행
	//			- BookService에서 책번호로 책정보 조회하기 실행
	//			- 조회된 사용자의 포인트를 책가격의 1%만큼 증가시키기
	//			- 조회된 책정보의 재고를 1증가시키기
	public void returnBook(int rentalNo) {
		Rental rental = rentalService.findRentalByNo(rentalNo);
		rental.isRent = false;
		
		User user = userService.findUserByNo(rental.userNo);
		Book book = bookService.findBookByNo(rental.bookNo);
		user.point += (int)(book.price*0.01);
		book.stock += 1;
		
		
	}
	
	public void returnAllRental(int userNo) {
		Rental[] rentals = rentalService.getAllRentals();
		for (Rental rental : rentals) {
			if (rental == null) {
				System.out.println("### 회원님이 대여한 도서가 없습니다.");
				break;
			}
			if ((rental != null) && (userNo == rental.userNo)) {
				rental.isRent = false;
				System.out.println("### 모든 도서를 반납하였습니다.");
			}
		}
	}

	// 대여현황 조회 서비스
	// 모든 대여정보를 조회하는 서비스를 처리한다.
	//		- RentalService의 전체 대여정보조회 기능을 실행한다.
	//		- 조회된 대여정보를 화면에 출력한다. (null체크)
	public void retrieveAllRentals() {
		Rental [] allRental = rentalService.getAllRentals();
		for (int i=0; i<allRental.length; i++) {				
			if(allRental[i] != null) {
				System.out.println("------------- 대여현황 ---------------");
				System.out.println("대여번호: " + allRental[i].no);
				System.out.println("회원번호: " + allRental[i].userNo);
				User user = userService.findUserByNo(allRental[i].userNo);
				System.out.println("회원이름: " + user.name);
				System.out.println("도서번호: " + allRental[i].bookNo);
				Book book = bookService.findBookByNo(allRental[i].bookNo);
				System.out.println("도서제목: " + book.title);
				System.out.println("대여여부: " + allRental[i].isRent);
				System.out.println("-------------------------------------");

			}

		}

	}

	public void getRentalsByUserNo(int userNo) {
		Rental [] allRental = rentalService.getAllRentals();
		for (int i=0; i<allRental.length; i++) {				
			if(allRental[i] == null) {
				break;
			}
			if (allRental[i].userNo == userNo) {
				
				Book book = bookService.findBookByNo(allRental[i].bookNo);
				System.out.println("도서번호: " + allRental[i].bookNo);
				System.out.println("도서제목: " + book.title);
				System.out.println("도서가격: " + book.price);
				System.out.println("대여상태: " + allRental[i].isRent);
				
			}
			
		}
		

	}

	public void getRentalsByBookNo(int bookNo) {
		Rental [] allRental = rentalService.getAllRentals();
		for (int i=0; i<allRental.length; i++) {				
			if(allRental[i] == null) {
				break;
			}
			if (allRental[i].bookNo == bookNo) {
				User user = userService.findUserByNo(allRental[i].userNo);
				System.out.println("대여번호: " + allRental[i].no);
				System.out.println("회원번호: " + user.no);
				System.out.println("회원이름: " + user.name);
			}
		}
				
	}


}