package homework;

import java.util.Scanner;

public class OrderDemo1 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Order[] orders = new Order[100];
		int savePosition = 0;

		while (true) {
			System.out.println();
			System.out.println("=========================================================");
			System.out.println("1.조회	2.검색	3.입력	4.종료");
			System.out.println("=========================================================");

			System.out.print("번호를 입력하세요 : ");
			int menuNo = scanner.nextInt();

			if (menuNo == 1) {
				System.out.println();
				System.out.println("[고객 조회]");
				System.out.println("고객명\t고객등급\t총구매금액\t적립포인트\t사은품");
				System.out.println("=========================================================");
				for (int i = 0; i < savePosition; i++) {
					Order order = orders[i];
					System.out.print(order.name + "\t");
					System.out.print(order.grade + "\t");
					System.out.print(order.price + "\t");
					System.out.print(order.point + "\t");
					System.out.println(order.gift);
				}
			} else if (menuNo == 2) {
				System.out.println("[고객 등급 검색]");

				System.out.print("검색조건을 입력하세요(N, G) : ");
				String search = scanner.next();

				if (search.equals("N")) {
					System.out.print("고객명을 입력해주세요 : ");
					String name = scanner.next();
					Order searchOrder = null;
					for (int i = 0; i < savePosition; i++) {
						Order order = orders[i];
						if (name.equals(order.name)) {
							searchOrder = order;
						}
						if (searchOrder != null) {
							System.out.println("------ 검색 결과 --------");
							System.out.println("고 객 명 : " + searchOrder.name);
							System.out.println("고객등급 : " + searchOrder.grade);
							System.out.println("구매금액 : " + searchOrder.price);
							System.out.println("적립포인트 : " + searchOrder.point);
							System.out.println("사 은 품 : " + searchOrder.gift);
							System.out.println("------------------------");
						} else {
							System.out.println("[" + name + "]과 일치하는 고객명을 찾을 수 없습니다.");
						}
					}
				} else if (search.equals("G")) {
					System.out.print("사은품명을 입력해주세요 : ");
					String gift = scanner.next();
					Order searchOrder = null;
					for (int i = 0; i < savePosition; i++) {
						Order order = orders[i];
						if (gift.equals(order.gift)) {
							searchOrder = order;
						}
					}
					if (searchOrder != null) {
						System.out.println("------ 검색 결과 --------");
						System.out.println("고 객 명 : " + searchOrder.name);
						System.out.println("고객등급 : " + searchOrder.grade);
						System.out.println("구매금액 : " + searchOrder.price);
						System.out.println("적립포인트 : " + searchOrder.point);
						System.out.println("사 은 품 : " + searchOrder.gift);
						System.out.println("------------------------");
					} else {
						System.out.println("[" + gift + "]과 일치하는 사은품을 찾을 수 없습니다.");
					}
				}

			} else if (menuNo == 3) {
				System.out.print("이름을 입력해주세요 : ");
				String name = scanner.next();
				System.out.print("고객 등급을 입력해주세요(클래식, 베스트, 에이스, 프리미어) : ");
				String grade = scanner.next();
				System.out.print("총 구매 금액을 입력해주세요 : ");
				int totalPrice = scanner.nextInt();

				Order order = new Order();

				order.name = name;
				order.grade = grade;
				order.price = totalPrice;

				if (grade.equals("클래식")) {
					order.point = (int) (order.price * 0.01);
				} else if (grade.equals("베스트")) {
					order.point = (int) (order.price * 0.02);
				} else if (grade.equals("에이스")) {
					order.point = (int) (order.price * 0.03);
				} else if (grade.equals("프리미어")) {
					order.point = (int) (order.price * 0.05);
				}

				if (order.price >= 5000000) {
					order.gift = "숙박권";
				} else if (order.price >= 1000000) {
					order.gift = "상품권";
				} else if (order.price >= 500000) {
					order.gift = "할인권";
				} else {
					order.gift = "주차권";
				}

				orders[savePosition] = order;
				savePosition++;
			} else if (menuNo == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
}
