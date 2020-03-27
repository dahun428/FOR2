package com.sample.mall.service;

import com.sample.mall.repository.OrderRepository;
import com.sample.mall.repository.ProductRepository;
import com.sample.mall.repository.UserRepository;
import com.sample.mall.vo.Item;
import com.sample.mall.vo.Order;
import com.sample.mall.vo.Product;
import com.sample.mall.vo.User;

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
		
		Product[] products = productRepo.getAllProducts();
		for(int i = 0; i < products.length; i++) {
			System.out.println(products[i].getNo()+"\t"+products[i].getName()
				+"+\t"+products[i].getPrice()+"\t"+products[i].getDiscountPrice());
		}
		
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
		
		if(product == null) {
			System.out.println("찾으시는 상품이 없습니다. 다시 입력해주세요");
			return;
		}
		
		System.out.println(product.getNo()+"\t"+product.getName()
				+"\t"+product.getPrice()+"\t"+product.getDiscountPrice()+"\t"
				+product.getStock()+"\t"+product.getScore());
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
		Product product = productRepo.getProductByNo(productNo);
		
		if(user == null) {
			System.out.println("존재하지 않은 사용자입니다. 다시 입력해주세요");
			return;
		}
		
		if(product == null) {
			System.out.println("존재하지 않은 상품입니다. 다시 입력해주세요");
			return;
		}
		
		Item[] items = user.getItems();
		
		for(int i = 0; i < items.length; i++) {
			if(items[i].productNo == productNo) {
				System.out.println("이미 장바구니에 존재하는 상품입니다.");
				return;
			}
		}
		
		if(product.getStock() == 0) {
			System.out.println("재고가 없습니다.");
			return;
		}
		
		
		Item item = new Item();
		item.setProductNo(product.getNo());
		item.setOrderPrice(product.getPrice());
		item.setProductName(product.getName());
		user.addItem(item);
		product.setStock(product.getStock()-1);
		
		
		
		System.out.println();
		System.out.println("상품이 장바구니에 담겼습니다.");
		
		
		
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
		
		if(user == null) {
			System.out.println("존재하지 않은 회원입니다.");
			return;
		}
		
		Item[] items = user.getItems();
		
		for(int i = 0; i < items.length; i++) {
			System.out.println(items[i].getNo()+"\t"+items[i].getProductNo()+"\t"
					+items[i].getProductName()+"\t"+items[i].getOrderPrice());
		}
		
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
		if(user == null) {
			System.out.println("존재하지 않은 사용자입니다.");
			return;
		}
		
		Order order = new Order();
		order.setUserId(user.getId());

		//user의 아이템을 하나씩 꺼내서 order item 배열에 담는다.
		Item[] userItems = user.getItems();
		
		for(int i = 0; i < userItems.length; i++) {
			order.addItem(userItems[i]);
			//userItem 배열에 담긴 상품들의 포인트 적립
			user.setPoint((int)(userItems[i].getOrderPrice()*0.01));
		}
		
		//주문정보 담기 기능 완료
		orderRepo.insertOrder(order);
		//장바구니 비우기 기능 완료
		user.clearItems();
		
		
	}
	
	/**
	 * 사용자 아이디를 전달받아서 그 사용자의 모든 주문내역을 화면에 출력한다.<br/><br/>
	 * 
	 * OrderRepository의 주문내역조회 기능에 사용자 아이디를 전달해서 그 사용자의 모든 주문정보(Order배열)를 제공받아서 출력한다.
	 * @param userId
	 */
	public void displayMyOrders(String userId) {
	
		User user = userRepo.getUserById(userId);
		if(user == null) {
			System.out.println("유효한 사용자 아이디가 아닙니다. 다시 입력해주세요");
			return;
		}
		if(user.getItems() == null) {
			System.out.println("장바구니에 상품이 없습니다.");
			return;
		}
		
		Order[] orders = orderRepo.getOrderByUserIdArray(user.getId());
		for (Order order : orders) {
			Item[] items = order.getItems();
			for(Item item : items) {
				System.out.println(order.getNo()+"\t"+user.getId()+"\t"+item.getNo()
					+"\t"+item.getProductName()+"\t"+item.getOrderPrice()
					+"\t"+((int)item.getOrderPrice()*0.01));
			}
		}
		
	}
}