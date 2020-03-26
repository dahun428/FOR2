package com.sample.bookstore.service;

import com.sample.bookstore.vo.User;

public class UserService {

	private User[] db = new User[100];
	private int position = 0;
	private int userSequence = 10001;
	
	public UserService() {
		User u1 = new User(userSequence++, "홍길동", "010-1111-2345", 100, false);
		User u2 = new User(userSequence++, "김유신", "010-2345-7839", 100, false);
		User u3 = new User(userSequence++, "강감찬", "010-1856-8953", 100, false);
		User u4 = new User(userSequence++, "이순신", "010-4386-5256", 100, false);
		User u5 = new User(userSequence++, "류관순", "010-2678-3468", 100, false);
		
		db[position++] = u1;
		db[position++] = u2;
		db[position++] = u3;
		db[position++] = u4;
		db[position++] = u5;
	}
	
	// 회원등록 기능
	// 회원정보를 전달받아서 db배열에 저장한다.
	public void insertUser(User user) {
		user.no = userSequence;
		
		db[position] = user;
		position++;
		userSequence++;		
	}
	
	// 회원정보 조회 기능
	// 회원번호를 전달받아서 db배열에서 회원정보를 찾아 반환한다.
	public User findUserByNo(int no) {
		User result = null;
		for (int i=0; i<position;i++ ) {
			User user = db[i];
			if(no == (user.no)) {
				result=user;
				break;
			}
		}
		return result;
	}
	
	// 회원정보 수정 기능
	// 회원정보를 전달받아서 회원번호에 해당하는 회원의 정보를 db에서 검색해서 회원정보를 수정한다.
	public void updateUser(User user) {
		
		User dbUser = findUserByNo(user.no);
		if(dbUser != null) {
		dbUser.name = user.name;
		dbUser.tel = user.tel;
			System.out.println("### 회원정보 수정이 완료되었습니다.");
		} else {
			System.out.println("### 조회하신 정보의 회원님이 없습니다.");
		}
		
	}
	
	// 전체 회원정보 조회기능
	// 전체 회원정보를 반환한다.
	public User[] getAllUsers() {
		return db;
	}
		
}