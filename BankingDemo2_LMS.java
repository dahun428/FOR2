package homework;

import java.util.Scanner;

public class BankingDemo2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Banking[] bankings = new Banking[100];
		int savePosition = 0;

		while (true) {
			System.out.println("=============================================================");
			System.out.println("1.신규	2.조회	3.입금	4.출금	5.비번변경	6.해지	0.종료");
			System.out.println("=============================================================");
			System.out.print("원하시는 메뉴 번호를 입력하세요 : ");
			int menuNo = scanner.nextInt();

			if (menuNo == 1) {
				Banking banking = new Banking();

				System.out.print("이름을 입력해주세요 : ");
				banking.name = scanner.next();
				System.out.print("계좌번호를 입력해주세요 : ");
				banking.no = scanner.next();
				System.out.print("비밀번호를 입력해주세요 : ");
				banking.password = scanner.nextInt();
				System.out.print("잔액을 입력해주세요 : ");
				banking.balance = scanner.nextLong();
				System.out.print("가입기간을 입력해주세요 : ");
				banking.period = scanner.nextInt();

				bankings[savePosition] = banking;
				savePosition++;
			} else if (menuNo == 2) {
				System.out.print("계좌번호를 입력해주세요 : ");
				String budgetNum = scanner.next();
				boolean isFound = false;

				for (int i = 0; i < savePosition; i++) {
					Banking banking = bankings[i];
					if (budgetNum.equals(banking.no)) {
						banking.display();
						isFound = true;
					}
				}
				if (isFound != true) {
					System.out.println("일치하는 계좌번호가 없습니다.");
				}
			} else if (menuNo == 3) {
				System.out.print("계좌번호를 입력해주세요 : ");
				String budgetNum = scanner.next();
				boolean isFound = false;

				for (int i = 0; i < savePosition; i++) {
					Banking banking = bankings[i];
					if (budgetNum.equals(banking.no)) {
						System.out.print("입금액을 입력해주세요 : ");
						long money = scanner.nextLong();
						banking.deposit(money);
						isFound = true;
					}
					if (isFound != true) {
						System.out.println("일치하는 계좌번호가 없습니다.");
					}
				}
			} else if (menuNo == 4) {
				System.out.print("계좌번호를 입력해주세요 : ");
				String budgetNum = scanner.next();

				boolean isFound = false;

				for (int i = 0; i < savePosition; i++) {
					Banking banking = bankings[i];
					if (budgetNum.equals(banking.no)) {
						System.out.print("출금액을 입력해주세요 : ");
						long money = scanner.nextLong();
						System.out.print("비밀번호를 입력해주세요 : ");
						int pwd = scanner.nextInt();
						banking.withdraw(money, pwd);
						isFound = true;
					}
					if (isFound != true) {
						System.out.println("일치하는 계좌번호가 없습니다.");
					}
				}
			} else if (menuNo == 5) {
				System.out.print("계좌번호를 입력해주세요 : ");
				String budgetNum = scanner.next();

				boolean isFound = false;

				for (int i = 0; i < savePosition; i++) {
					Banking banking = bankings[i];
					if (budgetNum.equals(banking.no)) {
						System.out.print("현재 비밀번호를 입력해주세요 : ");
						int pwd = scanner.nextInt();
						System.out.print("새로운 비밀번호를 입력해주세요 : ");
						int newPwd = scanner.nextInt();
						banking.changePassword(pwd, newPwd);
						isFound = true;
					}
					if (isFound != true) {
						System.out.println("일치하는 계좌번호가 없습니다.");
					}
				}
			} else if (menuNo == 6) {
				boolean isFound = false;
				System.out.print("계좌번호를 입력해주세요 : ");
				String budgetNum = scanner.next();
				for (int i = 0; i < savePosition; i++) {
					Banking banking = bankings[i];
					if (budgetNum.equals(banking.no)) {
						System.out.print("비밀번호를 입력해주세요 : ");
						int pwd = scanner.nextInt();
						banking.close(pwd);
						isFound = true;
					}
					if (isFound != true) {
						System.out.println("일치하는 계좌번호가 없습니다.");
					}

				}
			} else if (menuNo == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
}
