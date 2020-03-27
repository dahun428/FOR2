package com.sample.mall.service;

import com.sample.mall.repository.OrderRepository;
import com.sample.mall.repository.ProductRepository;
import com.sample.mall.repository.UserRepository;
import com.sample.mall.vo.Item;
import com.sample.mall.vo.Order;
import com.sample.mall.vo.Product;
import com.sample.mall.vo.User;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;

public class MallService {

	private OrderRepository orderRepo = new OrderRepository();
	private ProductRepository productRepo = new ProductRepository();
	private UserRepository userRepo = new UserRepository();
	
	/**
	 * 모든 상품정보를 화면에 표시한다.<br/>
	 * 출력내용 :: 상품번호, 상품명, 가격, 할인가격 정보를 표시한다.<br /><br />
	 * 
	 * ProductRepository의 모든 상품정보 조회기능을 실행해서 상품정보 배열(Product가 저장된 배열)을 제공받아서 표시한다.
	 */
	public void displayAllProducts() {
		Product [] products = productRepo.getAllProducts();
		System.out.println("----------------------모든 상품내용-----------------------");
		System.out.println("상품번호		상품명		가격		할인가격");

		for (int i=0; i<products.length ;i++) {	
			System.out.println(products[i].no + "\t\t" + products[i].name + "\t" + products[i].price + "\t\t" + products[i].discountPrice);
		}
		System.out.println("---------------------------------------------------------");
	}

	/**
	 * 상품번호를 전달받아서 그 상품번호에 해당하는 상품의 상세정보를 화면에 표시한다.<br/>
	 * 출력내용 :: 상품번호, 상품명, 가격, 할인가격, 재고, 판매지수 정보를 표시한다.<br /><br />
	 * 
	 * ProductRepository의 상품상세정보 조회 기능에 상품번호를 전달해서 상품정보(Product객체)를 제공받아서 표시한다.
	 * @param productNo 상세정보를 조회할 상품번호
	 */
	public void displayProductDetail(int productNo) {
		Product product = productRepo.getProductByNo(productNo);
		System.out.println("---------------------------------------상품상세정보---------------------------------------");
		System.out.println("상품번호		상품명		가격		할인가격		재고		판매지수");
		System.out.println(product.no + "\t\t" + product.name + "\t" + product.price + "\t\t" + product.discountPrice+ "\t\t" + product.stock+ "\t\t" + product.score);
		System.out.println("-----------------------------------------------------------------------------------------");	
	}
	
	/**
	 * 사용자 아이디와 상품번호를 전달받아서 해당 사용자의 장바구니에 상품정보를 저장한다.<br/>
	 * 사용자의 장바구니에 동일한 상품이 존재하는 경우는 장바구니에 추가되지 않는다.<br/>
	 * 상품의 재고가 0개인 경우는 장바구니에 추가되지 않는다.<br /><br />
	 * 
	 * UserRepository의 사용자정보 조회 기능에 사용자 아이디를 전달해서 사용자정보(User객체)를 제공받는다.<br/>
	 * Item객체를 생성해서 상품번호, 상품명, 구매가격을 저장한다.<br/>
	 * User객체의 장바구니 아이템 추가 기능에 생성된 Item을 전달해서 장바구니에 추가한다.<br/>
	 * ProductRepository의 상품상제정보 조회기능을 실행해서 상품정보(Product객체)를 제공받는다.
	 * 상품의 재고를 1감소시킨다.
	 * @param userId 사용자 아이디
	 * @param productNo 장바구니에 추가할 상품번호
	 */
	public void addCartItem(String userId, int productNo) {
		User user = userRepo.getUserById(userId);
		Item item = new Item();

		Product product = productRepo.getProductByNo(productNo);

		if (product==null) {
			System.out.println("입력하신 상품번호의 상품은 없습니다.");	
			return;
		}
		if (product.stock==0) {
			System.out.println("상품의 재고가 없습니다.");	
			return;
		}

		//아이디를 입력한 고객
		//의 장바구니의 아이템과
		Item[] items = user.getItems();		
		//지금 넣으려고하는 아이템을
		//포문을 돌려 확인하고자한다.
		for(int i=0; i<items.length ;i++) {			
			if (items[i].productNo==productNo) {
				System.out.println("이미 장바구니에 같은 상품이 있습니다.");
				return;
			}
		}
		
		item.productNo=product.no;
		item.productName=product.name;
		item.orderPrice=product.discountPrice;
		user.addItem(item);
		
		product.stock--;
	}
	
	/**
	 * 사용자 아이디를 전달받아서 그 사용자의 장바구니에 저장된 모든 상품정보를 화면에 출력한다.<br />
	 * 표시내용 :: 아이템번호, 상품번호, 상품명, 구매가격 정보를 표시한다.<br /><br />
	 * 
	 * UserRepository의 사용자정보 조회 기능에 사용자 아이디를 전달해서 사용자정보(User객체)를 제공받는다.<br />
	 * User객체의 장바구니 아이템조회기능을 사용해서 모든 Item정보를 제공받고 화면에 출력한다.
	 * @param userId 장바구니에 저장될 상품 정보를 조회할 사용자 아이디
	 */
	public void displayMyCart(String userId) {
		User user = userRepo.getUserById(userId);
		Item [] items = user.getItems();
		System.out.println("--------------------------나의 장바구니 내역--------------------------");
		System.out.println("아이템번호	상품번호		상품명		구매가격");
		for(int i=0; i<items.length; i++) {
			System.out.println(items[i].no + "\t\t" + items[i].productNo + "\t\t" + items[i].productName + "\t" + items[i].orderPrice);
		}
		System.out.println("---------------------------------------------------------------------");	
		
	}
	
	
	/**
	 * 사용자 아이디를 전달받아서 그 사용자의 장바구니에 저장된 모든 상품을 주문한다.<br /><br />
	 * 
	 * UserRepository의 사용자정보 조회 기능에 사용자 아이디를 전달해서 사용자정보(User객체)를 제공받는다.<br />
	 * <ol>
	 * <li>Order객체를 생성하고, 사용자 아이디를 저장한다.</li>
	 * <li>위에서 조회된 User객체의 장바구니에서 Item객체를 하나씩 꺼낸다.
	 * 	<ol>
	 * 		<li>Item객체에서 구매가격를 꺼낸다.</li>
	 * 		<li>구매가격의 1%만큼 포인트를 위에서 조회한 User객체의 포인트에 적립한다.</li>
	 * 		<li>Item객체를 Order객체의 items배열에 저장한다.</li>
	 * 	</ol>
	 * </li>
	 * <li>위에서 생성한 Order객체를 OrderRepository의 주문저장기능에 전달해서 저장되게 한다.</li>
	 * <li>User객체의 장바구니 비우기 기능을 실행한다.</li>
	 * </ol>
	 * @param userId 장바구니에 저장된 모든 상품정보를 주문하고 싶어하는 사용자 아이디
	 */
	public void cartToOrder(String userId) {
		 User user = userRepo.getUserById(userId);
		 Order order = new Order();
		 order.userId=userId;
		 Item [] items = user.getItems();
		 for(int i=0; i<items.length; i++) {
			 user.point=(int)(items[i].orderPrice*0.01);
			 order.addItem(items[i]);
			 orderRepo.insertOrder(order);
			 user.clearItems();
		
		 }
	}
	
	/**
	 * 사용자 아이디를 전달받아서 그 사용자의 모든 주문내역을 화면에 출력한다.<br/><br/>
	 * 
	 * OrderRepository의 주문내역조회 기능에 사용자 아이디를 전달해서 그 사용자의 모든 주문정보(Order배열)를 제공받아서 출력한다.
	 * @param userId
	 */
	public void displayMyOrders(String userId) {
		Order [] order = orderRepo.getOrderByUserId(userId);
		User user = userRepo.getUserById(userId);
		System.out.println("-------------------------------------나의 결제 내역-------------------------------------");
		System.out.println("주문번호		고객이름		상품번호		상품명		구매가격		적립포인트");
		for(int i=0; i<order.length; i++) {
			System.out.println(order[i].no + "\t\t" + user.name + "\t\t" + order[i].userId + "\t\t" + order[i].items[i].productName + "\t" + order[i].items[i].orderPrice + "\t\t" + user.point);
		}
		System.out.println("-----------------------------------------------------------------------------------------");	
		
		
	}
}
