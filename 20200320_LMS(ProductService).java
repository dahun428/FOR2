package oop4;

public class ProductService {

	Product[] db = new Product[20];
	int position = 0;

	// 배열에 저장된 모든 상품정보를 출력한다.
	void printAllProducts() {
		System.out.println("===============================================================");
		System.out.println("이름	제조사	카테고리	가격	할인율	재고	품절여부");
		System.out.println("===============================================================");
		for (int i = 0; i < position; i++) {
			Product product = db[i];
			System.out.print(product.name + "\t");
			System.out.print(product.maker + "\t");
			System.out.print(product.category + "\t");
			System.out.print(product.price + "\t");
			System.out.print(product.discountRate + "\t");
			System.out.print(product.stock + "\t");
			System.out.println(product.isSoldOut);
		}
		System.out.println("===============================================================");
	}

	// 상품객체(새 상품, 이월상품)를 전달받아서 배열에 저장한다.
	void insertProduct(String name, String maker, String category, int price, int stock) {
		Product newProduct = new Product(name, maker, category, price, stock);
		db[position] = newProduct;
		position++;
	}

	void insertProduct(String name, String maker, String category, int price, int stock, double discountRate) {
		Product oldProduct = new Product(name, maker, category, price, stock, discountRate);
		db[position] = oldProduct;
		position++;
	}

	// 상품명과 입고량을 전달받아서 이미 배열에 저장된 상품의 재고량을 증가시킨다.
	void addProductStock(String name, int amount) {
		Product product = new Product();
		Product result = null;
		for (int i = 0; i < position; i++) {
			Product foundProduct = db[i];
			if (foundProduct.name.equals(name)) {
				result = foundProduct;
				break;
			}
		}
		if (result != null) {
			result.stock += amount;
		} else {
			System.out.println("일치하는 상품명이 없습니다.");
		}
	}

	// 상품명과 출고량을 전달받아서 배열에서 해당 상품을 찾아서 재고량을 감소시킨다.
	// 단, 재고량보다 출고량이 많은 경우 오류 메세지를 표시한다.
	// 단, 재고량이 0이 되면 해당 상품의 결판 여부를 true로 설정한다.
	void exportProductStock(String name, int amount) {
		Product product = new Product();
		Product result = null;
		for (int i = 0; i < position; i++) {
			Product foundProduct = db[i];
			if (foundProduct.name.equals(name)) {
				result = foundProduct;
				break;
			}
		}
		if (result != null) {
			if (amount > result.stock) {
				System.out.println("오류! 출고량이 재고량보다 많습니다.");
			} else {
				result.stock -= amount;
			}
			if (amount == 0) {
				result.isSoldOut = true;
			}
		} else {
			System.out.println("일치하는 상품명이 없습니다.");
		}
	}

	// 상품명을 전달받아서 배열에서 상품명이 일치하는 상품정보를 출력한다.
	void printProductsByName(String name) {
		Product product = new Product();
		Product result = null;
		System.out.println("===============================================================");
		System.out.println("이름	제조사	카테고리	가격	할인율	재고	품절여부");
		System.out.println("===============================================================");
		for (int i = 0; i < position; i++) {
			Product foundProduct = db[i];
			if (foundProduct.name.equals(name)) {
				result = foundProduct;
				result.display2();
			}
		}
		if (result == null) {
			System.out.println("일치하는 이름이 없습니다.");
		}
	}

	// 제조사명을 전달받아서 배열에서 제조사명이 일치하는 상품정보를 출력한다.
	void printProductsByMaker(String name) {
		Product product = new Product();
		Product result = null;
		System.out.println("===============================================================");
		System.out.println("이름	제조사	카테고리	가격	할인율	재고	품절여부");
		System.out.println("===============================================================");
		for (int i = 0; i < position; i++) {
			Product foundProduct = db[i];
			if (foundProduct.maker.equals(name)) {
				result = foundProduct;
				result.display2();
			}
		}
		if (result == null) {
			System.out.println("일치하는 이름이 없습니다.");
		}
	}

	// 카테고리명을 전달받아서 배열에서 카테고리명이 일치하는 상품정보를 출력한다.
	void printProductsByCategory(String name) {
		Product product = new Product();
		Product result = null;
		System.out.println("===============================================================");
		System.out.println("이름	제조사	카테고리	가격	할인율	재고	품절여부");
		System.out.println("===============================================================");
		for (int i = 0; i < position; i++) {
			Product foundProduct = db[i];
			if (foundProduct.category.equals(name)) {
				result = foundProduct;
				result.display2();
			}
		}
		if (result == null) {
			System.out.println("일치하는 이름이 없습니다.");
		}
	}

	// 최저가격과 최고가격을 전달받아서 배열에서 상품명이 일치하는 상품정보를 출력한다.
	void printProductsByPrice(int minPrice, int maxPrice) {
		Product product = new Product();
		Product result = null;
		System.out.println("===============================================================");
		System.out.println("이름	제조사	카테고리	가격	할인율	재고	품절여부");
		System.out.println("===============================================================");
		for (int i = 0; i < position; i++) {
			Product foundProduct = db[i];
			if (foundProduct.price >= minPrice && maxPrice >= foundProduct.price) {
				result = foundProduct;
				result.display2();
			}
		}
		if (result == null) {
			System.out.println("일치하는 이름이 없습니다.");
		}
	}
}
