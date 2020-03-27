package com.sample.mall.repository;

import java.util.ArrayList;
import java.util.Arrays;

import com.sample.mall.vo.Order;

public class OrderRepository {

	private Order[] db = new Order[20];
	private int position = 0;
	private int sequence = 30001;
	
	/**
	 * 주문정보(Order객체)를 전달받아서 배열에 저장한다.<br />
	 * 
	 * 주문객체에 주문번호를 저장한다<br/>
	 * 배열에 저장하고, 포지션과 시퀀스를 1씩 증가시킨다.
	 * @param order
	 */
	public void insertOrder(Order order) {
		
		order.no = sequence;
		db[position] = order;
		
		position++;
		sequence++;
	}
	
	/**
	 * 전달받은 사용자아이디의 모든 주문내역정보를 반환한다.<br />
	 * 
	 * 배열에서 주문자아이디가 전달받은 userId와 일치하는 Order객체를 찾아서 새로운 배열에 담아서 반환한다.
	 * @param userId
	 * @return
	 */
	public Order getOrderByUserId(String userId) {
		Order result = null;
		for(int i = 0; i < position; i++) {
			Order order = db[i];
			if(order.getUserId() == userId) {
				result = order;
				break;
			}
		}
		
		return result;
	}
	
	public Order[] getOrderByUserIdArray(String userId) {
		Order[] orders = new Order[position];
		int count = 0;
		for(int i = 0; i < position; i++) {
			if(userId == db[i].getUserId()) {
				orders[count] = db[i];
				count++;
			}
		}
		return Arrays.copyOfRange(orders, 0, count);
	}
//	
//	public Order[] getOrderByUserIdArray(String userId) {
//		ArrayList<Order> list = new ArrayList<>();
//		for(int i = 0; i < position; i++) {
//			if(db[i].getUserId() == userId) {
//				list.add(db[i]);
//			}
//		}
//		Order[] result = new Order[list.size()];
//		list.toArray(result);
//		return result;
//	}
	
}