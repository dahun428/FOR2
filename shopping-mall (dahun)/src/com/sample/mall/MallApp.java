package com.sample.mall;

import java.util.Scanner;

import com.sample.mall.service.MallService;

public class MallApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MallService service = new MallService();
		
		while(true) {
			System.out.println("##### 작은 쇼핑몰 #####");
			System.out.println("=================================================================================");
			System.out.println("1.모든 상품조회 2.상품상세조회 3.장바구니담기 4.내 장바구니 보기 5.구매하기 6.나의 구매내역 0.종료");
			System.out.println("=================================================================================");
			
			System.out.print("메뉴를 선택하세요: ");
			int menuNo = scanner.nextInt();
			
			if (menuNo == 1) {
				// 모든 상품을 조회하기
				// 입력값 없음
				System.out.println("[모든 상품 조회]");
				System.out.println("=================================================================================");
				System.out.println("상품번호	상품명	가격	할인가격");
				System.out.println("=================================================================================");
				
				service.displayAllProducts();
				
				
			} else if (menuNo == 2) {
				// 상품상세정보 조회하기
				// 상품번호 입력받기			
				
				System.out.println();
				System.out.println("[상품 상세 조회]");
				System.out.print("조회하고자 하는 상품 번호을 입력해주세요 : ");
				int productNo = scanner.nextInt();
				
				System.out.println();
				System.out.println("=================================================================================");
				System.out.println("상품번호	상품명	가격	할인가격	재고	판매지수");
				System.out.println("=================================================================================");
				
				service.displayProductDetail(productNo);
				
			} else if (menuNo == 3) {
				// 장바구니 담기
				// 사용자아이디, 상품번호 입력받기
				
				System.out.println();
				System.out.println("[장바구니 담기]");
				System.out.print("아이디를 입력해주세요 : ");
				String userId = scanner.next();
				System.out.print("상품 번호를 입력해주세요 : ");
				int productNo = scanner.nextInt();
				
				service.addCartItem(userId, productNo);
				
				
			} else if (menuNo == 4) {
				// 내장바구니 보기
				// 사용자아이디 입력받기
				
				System.out.println();
				System.out.println("[내 장바구니 보기]");
				System.out.print("아이디를 입력해주세요 : ");
				String userId = scanner.next();
				System.out.println();
				System.out.println("=================================================================================");
				System.out.println("아이템번호	상품번호	상품명	구매가격");
				System.out.println("=================================================================================");
				
				
				service.displayMyCart(userId);
				
				
				
				
			} else if (menuNo == 5) {
				// 구매하기
				// 사용자아이디 입력받기
				System.out.println();
				System.out.println("[구매하기]");
				System.out.print("사용자 아이디를 입력해주세요 : ");
				String userId = scanner.next();
				service.cartToOrder(userId);
				
				
			} else if (menuNo == 6) {
				// 나의 구매내역 보기
				// 사용자아이디 입력받기
				System.out.println();
				System.out.println("[나의 구매내역 보기]");
				System.out.print("사용자 아이디를 입력해주세요 : ");
				String userId = scanner.next();
				
				System.out.println("=================================================================================");
				System.out.println("구매번호	고객이름	상품번호	상품명	구매가격	적립포인트");
				System.out.println("=================================================================================");
				
				
				
				service.displayMyOrders(userId);
				
				
			} else if (menuNo == 0) {
				// 종료
				break;
			}else {
				System.out.println();
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			}
			System.out.println();
			System.out.println();
		}
		scanner.close();
		System.out.println("[프로그램 종료]");
	}
}