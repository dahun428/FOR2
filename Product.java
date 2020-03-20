package oop4;

public class Product {

	String name;
	String maker;
	String category;
	int price;
	double discountRate;
	int stock;
	boolean isSoldOut;

	Product() {}

	// 신제품 객체의 멤버변수 초기화에 적합한 생성자 메소드
	Product(String name, String maker, String category, int price, int stock) {
//		this() 메소드 사용 전 코드
//		this.name = name;
//		this.maker = maker;
//		this.category = category;
//		this.price = price;
//		this.discountRate = 0.0;
//		this.isSoldOut = false;

		this(name, maker, category, price, stock, 0.0, false);
	}

	// 이월 제품 객체의 멤버변수 초기화에 적합한 생성자 메소드
	Product(String name, String maker, String category, int price, int stock, double discountRate) {
//		this() 메소드 사용 전 코드
//		this.name = name;
//		this.maker = maker;
//		this.category = category;
//		this.price = price;
//		this.discountRate = discountRate;
//		this.isSoldOut = false;

		this(name, maker, category, price, stock, discountRate, false);
	}

	Product(String name, String maker, String category, int price, int stock, double discountRate, boolean isSoldOut) {
		this.name = name;
		this.maker = maker;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.discountRate = discountRate;
		this.isSoldOut = isSoldOut;
	}

	void display() {
		System.out.println("----------------------- 상품정보-----------------------");
		System.out.println("상품이름 : " + name);
		System.out.println("제조회사 : " + maker);
		System.out.println("카테고리 : " + category);
		System.out.println("판매가격 : " + price);
		System.out.println("할인비율 : " + discountRate);
		System.out.println("재 고 량 : " + stock);
		System.out.println("절판여부 : " + isSoldOut);
		System.out.println("------------------------------------------------------");
	}
	
	void display2() {
		System.out.print(name + "\t");
		System.out.print(maker + "\t");
		System.out.print(category + "\t");
		System.out.print(price + "\t");
		System.out.print(discountRate + "\t");
		System.out.print(stock + "\t");
		System.out.println(isSoldOut);
	}
}
