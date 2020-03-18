package oop2;

import java.util.Scanner;

public class BankingDemo2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Banking[] bankings = new Banking[5];
		int savePosition = 0;

		while (true) {
			System.out.println("=======================================================");
			System.out.println("1.신규\t2.조회\t3.입금\t4.출금\t5.비밀번호 변경\t6.해지\t0.종료");
			System.out.println("=======================================================");

			System.out.print("메뉴를 선택하세요(0~6): ");
			int menu = scanner.nextInt();

			if (menu == 0) {
				System.out.println("프로그램을 종료합니다.");
				System.out.println("=======================================================");
				break;
			} else if (menu == 1) {
				System.out.println("================== 신규 ==================");

				Banking bank = new Banking();

				System.out.print("이름을 입력하세요: ");
				bank.name = scanner.next();
				System.out.print("계좌 번호를 입력하세요: ");
				bank.accountNo = scanner.next();
				System.out.print("비밀번호를 입력하세요: ");
				bank.password = scanner.nextInt();
				System.out.print("잔액을 입력하세요: ");
				bank.balance = scanner.nextLong();
				System.out.print("가입 기간을 입력하세요: ");
				bank.period = scanner.nextInt();

				bankings[savePosition] = bank;
				savePosition++;
			} else if (menu == 2) {
				System.out.println("================== 조회  ==================");

				System.out.print("계좌 번호를 입력하세요: ");
				String findAccount = scanner.next();

				boolean isFound = false;
				for (int i = 0; i < savePosition; i++) {
					Banking bank = bankings[i];
					if (findAccount.equals(bank.accountNo)) {
						bank.display();
						isFound = true;
					}
				}
				if (!isFound) {
					System.out.println("일치하는 계좌 정보가 없습니다.");
					System.out.println("=======================================================");
				}
			} else if (menu == 3) {
				System.out.println("================== 입금 ==================");
				System.out.print("계좌 번호를 입력하세요: ");
				String findAccount = scanner.next();
				System.out.print("입금할 금액을 입력하세요: ");
				long inputMoney = scanner.nextLong();

				boolean isFound = false;
				for (int i = 0; i < savePosition; i++) {
					Banking bank = bankings[i];
					if (findAccount.equals(bank.accountNo)) {
						bank.deposit(inputMoney);
						isFound = true;
					}
				}
				if (!isFound) {
					System.out.println("일치하는 계좌정보가 없습니다.");
					System.out.println("=======================================================");
				}
			} else if (menu == 4) {
				System.out.println("================== 출금 ==================");
				System.out.print("계좌 번호를 입력하세요: ");
				String findAccount = scanner.next();
				System.out.print("출금할 금액을 입력하세요: ");
				long outputMoney = scanner.nextLong();
				System.out.print("비밀번호를 입력하세요: ");
				int inputPassword = scanner.nextInt();

				boolean isFound = false;
				for (int i = 0; i < savePosition; i++) {
					Banking bank = bankings[i];
					if (findAccount.equals(bank.accountNo)) {
						bank.withdraw(outputMoney, inputPassword);
						isFound = true;
					}
				}
				if (!isFound) {
					System.out.println("일치하는 계좌정보가 없습니다.");
					System.out.println("=======================================================");
				}
			} else if (menu == 5) {
				System.out.println("================== 비밀번호 변경 ==================");
				System.out.print("계좌 번호를 입력하세요: ");
				String findAccount = scanner.next();
				System.out.print("기존의 비밀번호를 입력하세요: ");
				int oldPwd = scanner.nextInt();
				System.out.print("새 비밀번호를 입력하세요: ");
				int newPwd = scanner.nextInt();

				boolean isFound = false;
				for (int i = 0; i < savePosition; i++) {
					Banking bank = bankings[i];
					if (findAccount.equals(bank.accountNo)) {
						bank.changePassword(oldPwd, newPwd);
						isFound = true;
					}
				}
				if (!isFound) {
					System.out.println("일치하는 계좌정보가 없습니다.");
					System.out.println("=======================================================");
				}
			} else if (menu == 6) {
				System.out.println("================== 해지 ==================");
				System.out.print("계좌 번호를 입력하세요: ");
				String findAccount = scanner.next();
				System.out.print("비밀번호를 입력하세요: ");
				int inputPassword = scanner.nextInt();

				boolean isFound = false;
				for (int i = 0; i < savePosition; i++) {
					Banking bank = bankings[i];
					if (findAccount.equals(bank.accountNo)) {
						bank.closeAccount(inputPassword);
						isFound = true;
					}
				}
				if (!isFound) {
					System.out.println("일치하는 계좌정보가 없습니다.");
					System.out.println("=======================================================");
				}
			}
		} // while end

		scanner.close();
	}// main ed
}
