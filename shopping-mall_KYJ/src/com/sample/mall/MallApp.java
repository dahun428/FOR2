package com.sample.mall;

import java.util.Scanner;

import com.sample.mall.service.MallService;

public class MallApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MallService service = new MallService();
		
		while(true) {
			System.out.println("##### 작은 쇼핑몰 #####");
			System.out.println("==========================================================================================");
			System.out.println("1.모든 상품조회 2.상품상세조회 3.장바구니담기 4.내 장바구니 보기 5.구매하기 6.나의 구매내역 0.종료");
			System.out.println("==========================================================================================");
			
			System.out.print("메뉴를 선택하세요: ");
			int menuNo = scanner.nextInt();
			
			if (menuNo == 1) {
				
				service.displayAllProducts();
			
			} else if (menuNo == 2) {
				System.out.print("상품번호를 입력하세요: ");
				int productNo = scanner.nextInt();
				
				service.displayProductDetail(productNo);
			
			} else if (menuNo == 3) {
				System.out.print("아이디를 입력하세요: ");
				String userId = scanner.next();
				System.out.print("상품번호를 입력하세요: ");
				int productNo = scanner.nextInt();
				
				service.addCartItem(userId, productNo);
			
			} else if (menuNo == 4) {
				System.out.print("아이디를 입력하세요: ");
				String userId = scanner.next();
				
				service.displayMyCart(userId);
			
			} else if (menuNo == 5) {
				System.out.print("아이디를 입력하세요: ");
				String userId = scanner.next();
				
				service.cartToOrder(userId);
			
			} else if (menuNo == 6) {
				System.out.print("아이디를 입력하세요: ");
				String userId = scanner.next();
				
				service.displayMyOrders(userId);
			
			} else if (menuNo == 0) {
				System.out.println("구매를 종료합니다.");
				
				// 종료
				break;
			}
			System.out.println();
			System.out.println();
		}
		scanner.close();
		System.out.println("[★작은 쇼핑몰 종료★]");
	}
}
