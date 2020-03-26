package com.sample.bookstore.vo;

public class Book {
	
	public int no;
	public String title;
	public String writer;
	public int price;
	public int stock;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(int no, String title, String writer, int price, int stock) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.price = price;
		this.stock = stock;
	}
}
