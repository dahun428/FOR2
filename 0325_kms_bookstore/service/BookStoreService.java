package com.sample.bookstore.service;

import com.sample.bookstore.vo.*;

public class BookStoreService {
	BookService bookService = new BookService();
	UserService userService = new UserService();
	RentalService rentalService = new RentalService();

	// 회원등록 서비스
	public void addNewUser(String name, String tel) {
		User user = new User();

		user.name = name;
		user.tel = tel;
		user.point = 100;
		user.isDisabled = false;

		userService.insertUser(user);
		System.out.println("신규 회원[" + name + "]님의 회원번호는 [" + user.no + "]입니다.");
	}

	// 회원조회 서비스
	public void retrieveUserInfo(int userNo) {
		User user = userService.findUserByNo(userNo);
		System.out.println("회원번호: " + user.no);
		System.out.println("회원명: " + user.name);
		System.out.println("전화번호: " + user.tel);
		System.out.println("포인트: " + user.point);
		System.out.println("탈퇴여부: " + user.isDisabled);
	}

	// 전체 회원조회 서비스
	// 등록된 모든 회원정보 조회를 처리한다.
	// - UserService의 모든 회원정보 조회기능을 실행해서 모든 회원정보를 제공받는다.
	// - 조회된 회원정보를 화면에 출력한다.
	public void retrieveAllUsers() {
		User[] users = userService.getAllUsers();

		System.out.println("===============================================");
		System.out.println("[회원번호]\t[이  름]\t[전   화    번   호]\t[포인트]\t[탈퇴여부]");
		System.out.println("===============================================");

		int count = 0;
		while (users[count] != null) {
			System.out.print(users[count].no + "\t");
			System.out.print(users[count].name + "\t");
			System.out.print(users[count].tel + "\t");
			System.out.print(users[count].point + "\t");
			System.out.println(users[count].isDisabled);

			count++;
			if (count == users.length) {
				break;
			}
		}
	}

	// 회원정보 수정 서비스
	// 회원정보를 전달받아서, UserService의 회원정보수정기능을 실행한다.
	public void modifyUserInfo(User user) {
		userService.updateUser(user);
	}

	// 회원탈퇴 서비스
	// 회원번호를 전달받아서, UserService의 회원탈퇴기능을 실행한다.
	public void disabledUser(int userNo) {
		User user = userService.findUserByNo(userNo);
		rentalService.returnAllRentalByUserNo(userNo);

		user.isDisabled = true;
		
		System.out.println("### ["+user.name+"] 회원님이 대여중이었던 모든 도서가 반납되었습니다.");
	}

	// 도서등록 서비스
	public void insertNewBook(String title, String writer, int price) {
		Book book = new Book();
		book.title = title;
		book.writer = writer;
		book.price = price;
		book.stock = 10000;

		bookService.insertBook(book);
	}

	// 도서 검색 서비스
	public void searchBooks(String title) {
		Book[] books = bookService.findBookByTitle(title);
		int count = 0;

		System.out.println("===============================================");
		System.out.println("[책번호]\t[제          목]\t[작가]\t[가격]\t[재고]");
		System.out.println("===============================================");

		while (books[count] != null) {
			System.out.print(books[count].no + "\t");
			System.out.print(books[count].title + "      "+"\t");
			System.out.print(books[count].writer + "\t");
			System.out.print(books[count].price + "\t");
			System.out.println(books[count].stock);

			count++;
			if (count == books.length) {
				break;
			}
		}
	}

	// 도서 정보 수정 서비스
	// 책정보를 전달받아서 책정보 수정서비스를 처리한다.
	// - BookService의 책정보 수정기능을 실행한다.
	public void modifyBook(Book book) {
		bookService.updateBook(book);
	}

	// 전체 도서 조회 서비스
	// 등록된 모든 도서정보 조회를 처리한다.
	public void retrieveAllBooks() {
		System.out.println("===============================================");
		System.out.println("[책번호]\t[제          목]\t[작가]\t[가격]\t[재고]");
		System.out.println("===============================================");

		Book[] books = bookService.getAllBooks();
		int count = 0;
		while (books[count] != null) {
			System.out.print(books[count].no + "\t");
			System.out.print(books[count].title + "      "+"\t");
			System.out.print(books[count].writer + "\t");
			System.out.print(books[count].price + "\t");
			System.out.println(books[count].stock);

			count++;
			if (count == books.length) {
				break;
			}
		}
	}

	// 대여 서비스
	// 사용자번호와 책번호를 전달받아서 대여 서비스를 처리한다.
	// - Rental객체를 생성해서 사용자번호, 책번호를 저장한다.
	// - 대여여부는 대여중으로 설정한다.
	// - 책재고 변경하기
	// - BookService에서 책번호에 해당하는 책정보 조회기능 실행
	// - 조회된 책의 재고를 1감소시킨다.
	public void rentBook(int userNo, int bookNo) {
		Rental rental = new Rental();
		rental.userNo = userNo;
		rental.bookNo = bookNo;
		rental.isRent = true;
		
		Book rentBook = bookService.findBookByNo(bookNo);
		rentBook.stock --;
		
		rentalService.insertRental(rental);
		
		System.out.println("대여가 완료되었습니다.");
		System.out.println("대여번호는 ["+rental.no+"]입니다.");
	}

	// 반납 서비스
	// 대여번호를 전달받아서 반납 서비스를 처리한다.
	// - RentalService에서 대여번호에 해당하는 대여정보 조회 기능을 실행
	// - 조회된 대여정보의 반납여부를 false로 설정한다.
	// - 사용자포인트 증가, 책재고 변경하기
	// - UserService에서 사용자번호로 사용자정보 조회하기 실행
	// - BookService에서 책번호로 책정보 조회하기 실행
	// - 조회된 사용자의 포인트를 책가격의 1%만큼 증가시키기
	// - 조회된 책정보의 재고를 1증가시키기
	public void returnBook(int rentalNo) {
		Rental rental = rentalService.findRentalByNo(rentalNo);
		User user = userService.findUserByNo(rental.userNo);
		Book book = bookService.findBookByNo(rental.bookNo);
		
		rental.isRent = false;
		user.point += (int)(book.price*0.01);
		book.stock ++;
	}

	// 대여현황 조회 서비스
	// 모든 대여정보를 조회하는 서비스를 처리한다.
	// - RentalService의 전체 대여정보조회 기능을 실행한다.
	// - 조회된 대여정보를 화면에 출력한다. (null체크)
	public void retrieveAllRentals() {
		Rental[] rentals = rentalService.getAllRentals();
		int count=0;
		System.out.println("===============================================");
		System.out.println("[대여번호]\t[회원번호]\t[책번호]\t[대여현황]");
		System.out.println("===============================================");
		
		while(rentals[count]!=null) {
			System.out.print(rentals[count].no + "\t");
			System.out.print(rentals[count].userNo + "\t");
			System.out.print(rentals[count].bookNo + "\t");
			System.out.println(rentals[count].isRent);
			
			if (count==rentals.length) {
				break;
			}
			count++;
		}
	}
}
