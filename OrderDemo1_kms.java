package oop1;

import java.util.Scanner;

public class OrderDemo1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Order[] orders = new Order[100];
		int savePosition = 0;
		
		while(true) {
			System.out.println("==================================================");
			System.out.println("1.조회  2.검색  3.입력  4.종료");
			System.out.println("==================================================");
			System.out.print("메뉴를 선택해주세요: ");
			int menuNum = scanner.nextInt();
			
			if (menuNum==4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if (menuNum==1) {
				Order firstOrder = new Order();
				
				System.out.println();
				System.out.println("[ 조        회 ]");
				System.out.println("고객명\t고객등급\t총구매금액\t적립포인트\t사은품");
				System.out.println("==================================================");
				for (int i=0; i<savePosition; i++) {
					firstOrder = orders[i];
					System.out.print(firstOrder.name + "\t");
					System.out.print(firstOrder.grade + "\t");
					System.out.print(firstOrder.price + "\t");
					System.out.print(firstOrder.point + "\t");
					System.out.println(firstOrder.gift);
				}
				System.out.println();
				
			} else if (menuNum==2) {
				System.out.println("[ 검        색 ]");
				System.out.print("검색조건을 입력하세요(이름:N/사은품:G): ");
				String condition = scanner.next();
				
				Order foundOrder = null;
				if (condition.equals("N")||condition.equals("n")) {
					System.out.print("검색을 원하는 이름을 입력하세요: ");
					String searchName = scanner.next();
					for (int i=0; i<savePosition; i++) {
						if (searchName.equals(orders[i].name)) {
							foundOrder = orders[i];
							break;
						}
					}
					if (foundOrder == null) {
						System.out.println("찾으시는 이름 ["+searchName+"]의 고객정보가 없습니다.");
					} else if (foundOrder != null) {
						System.out.println("["+searchName+"]에 대한 검색결과");
						System.out.println("고  객  명: "+foundOrder.name);
						System.out.println("고객  등급: "+foundOrder.grade);
						System.out.println("총구매금액: "+foundOrder.price);
						System.out.println("적립포인트: "+foundOrder.point);
						System.out.println("사  은  품: "+foundOrder.gift);
					}
				} else if (condition.equals("G")||condition.equals("g")) {
					System.out.println("검색을 원하는 사은품을 입력하세요.");
					String searchGift = scanner.next();
					for (int i=0; i<savePosition; i++) {
						if (searchGift.equals(orders[i].gift)) {
							foundOrder = orders[i];
							break;
						}
					}
					if (foundOrder == null) {
						System.out.println("찾으시는 사은품["+searchGift+"]에 관련된 고객정보가 없습니다.");
					} else if (foundOrder != null) {
						System.out.println("["+searchGift+"]에 대한 검색결과");
						System.out.println("고  객  명: "+foundOrder.name);
						System.out.println("고객  등급: "+foundOrder.grade);
						System.out.println("총구매금액: "+foundOrder.price);
						System.out.println("적립포인트: "+foundOrder.point);
						System.out.println("사  은  품: "+foundOrder.gift);
					}
				}
				
			} else if (menuNum==3) {
				Order thirdOrder = new Order();
				
				System.out.println("[ 입        력 ]");
				System.out.println("==================================================");
				
				System.out.print("고객명을 입력해주세요: ");
				thirdOrder.name = scanner.next();
				System.out.print("고객등급을 입력해주세요: ");
				thirdOrder.grade = scanner.next();
				System.out.print("총 구매금액을 입력해주세요: ");
				thirdOrder.price = scanner.nextInt();
				
				if (thirdOrder.grade.equals("프리미어")) {
					thirdOrder.point = (int)(thirdOrder.price*0.05);
				} else if (thirdOrder.grade.equals("에이스")) {
					thirdOrder.point = (int)(thirdOrder.price*0.03);
				} else if(thirdOrder.grade.equals("베스트")) {
					thirdOrder.point = (int)(thirdOrder.price*0.02);
				} else if (thirdOrder.grade.equals("클래식")) {
					thirdOrder.point = (int)(thirdOrder.price*0.01);
				} else {
					thirdOrder.point = 0;
				} // thirdOrder.point(if~else if) end
				
				if (thirdOrder.price >=5_000_000) {
					thirdOrder.gift = "숙박권";
				} else if (thirdOrder.price >=1_000_000){
					thirdOrder.gift = "상품권";
				} else if (thirdOrder.price>=500_000) {
					thirdOrder.gift = "할인권";
				} else {
					thirdOrder.gift = "주차권";
				} // thirdOrder.price (if~else if) end
				
				orders[savePosition]=thirdOrder;
				
				savePosition++;
			} // menu (if~else if) end

		}//while end
		
	}
}
