package collection.demo;

import java.util.ArrayList;

import vo.Product;

public class ProductRepository {

	ArrayList<Product> db = new ArrayList<Product>();
	private int sequence = 100000;

	// 새 상품 등록 기능
	public void insertProduct(Product product) {
		product.setNo(sequence++);	// 상품번호 자동증가
		db.add(product);
	}

	// 등록된 모든 상품을 반환하는 기능
	public ArrayList<Product> getAllProduct(){
		return db;
	}

	// 전달받은 상품번호에 해당하는 상품을 반환하는 기능
	public Product getProductByNo(int productNo) {
		Product result = null;
		
		for (Product product : db) {
			if (product.getNo() == productNo) {
				result = product;
				break;
			}
		}
		return result;
	}

	// 전달받은 상품명에 해당하는 상품을 반환하는 기능
	public ArrayList<Product> serchProducts(String keyword){
		ArrayList<Product> result = new ArrayList<Product>();	// null 값이면 오류

		for(Product product : db) {
			String productName = product.getName();
			String productMaker = product.getMaker();

			if (productName.contains(keyword) || productMaker.contains(keyword)) {
				result.add(product);
			}

		}
		return result;
	}

	// 전달받은 상품번호에 해당하는 상품을 삭제하는 기능
	public void deleteProduct(int productNo) {

	}

}
