package list;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo2 {
	
	public static void main(String[] args) {
		
//		오류 : 타입파라미터<E> 에는 클래스명이 정의되어야 한다.
//				int, long, double 는 자바의 키워드다. 클래스명이 아니다.
//		ArrayList<int> numbers = new ArrayList<int>	();
//		ArrayList<Long> numbers = new ArrayList<Long>();
//		ArrayList<Double> numbers = new ArrayList<Double>();
		
		// 기본 자료형에 대응되는 Wrapper 클래스를 타입파라미터로 지정한다.
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		ArrayList<Long> currencies = new ArrayList<Long>();
		ArrayList<Double> score = new ArrayList<Double>();
//		(단, 거의 이렇게 사용할 일은 거의 없다. 가끔 map에는 사용한다.)
		
		// 오토박싱
		// 기본자료형값을 변수에 대입하거나, 매개변수로 전달할 때
		// 변수의 타입이 int이면 정수값이 전달된다.
		// 변수의 타입이 Integer 혹은 Object라면 정수값을 담은 Integer객체가 자동으로 생성된다.
		// 오토박싱 적용
		double d1 = 3.14;
		// 오토박싱 적용
		double d2 = 3.14;		// Double d2 = new Double(3.14);(오토박싱)
		
		ArrayList<Long> currency = new ArrayList<Long>();
		// 오토박싱 적용
		currency.add(100000000L);				//currencies.add(new Long(100000000L);
		
		numbers.add(new Integer(10));
		// 오토박싱 적용
		numbers.add(10);								// 실제로 실행되는 코드 : numbers.add(new Integer(10));
		
		currencies.add(new Long(1000000000000L));
		// 오토박싱 적용
		currencies.add(1000000000000L);		// 실제로 실행되는 코드 : currencies.add(new Long(1000000000000L));
		
		score.add(new Double(4.2));
		// 오토박싱 적용
		score.add(4.2);								// 실제로 실행되는 코드 : score.add(new Double(4.2))
	
		/*
		// 오토언박싱(Auto-Unboxing)
//		Wrapper객체를 변수에 대입하거나, 매개변수에 전달할 때 변수의 타입이 기본자료형타입이면 자동으로 Wrapper객체에 저장된 기본자료형 값만 대입된다.
		
		Double x = new Double(3.14);				// 오토-언박싱 지원으로 실제 실행되는 코드
		double y = x;									// double y = x.doubleValue();
		
		Integer x = new Integer(100);
		int y=x;											// int y = x.intValue();
		
		Long x = new Long(100000000000L);
		long y = x;										// long y = x.longValue();

		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores.add(new Integer(100));
		scores.add(98);
		scores.add(68);
		
		for (Integer s : scores) {					for (int s: scores) {
			
		}													}
*/		
		for (Integer num : numbers) {
			System.out.println(num);
		}
		for (int num : numbers) {		// 반복될 때 마다 Iteger 객체가 꺼내진다. ----->  언박싱되어서 숫자만 num에 대입
			System.out.println(num);
		}
		
		Iterator<Long> itr = currencies.iterator();
		while (itr.hasNext()) {
			Long curr = itr.next();
			System.out.println(curr);
			
		}
		
		itr = currencies.iterator();
		while (itr.hasNext()) {
			long curr = itr.next();		// itr.next() --> Long객체 반환 --> 언박싱되어서 숫자만 curr에 대입
			System.out.println(curr);
		}
	}

}
