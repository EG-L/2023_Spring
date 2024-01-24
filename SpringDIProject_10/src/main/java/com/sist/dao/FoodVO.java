package com.sist.dao;
/*
 * 
  FNO                                                NUMBER
 CNO                                                NUMBER
 NAME                                               VARCHAR2(100)
 SCORE                                              NUMBER(2,1)
 ADDRESS                                   NOT NULL VARCHAR2(300)
 PHONE                                              VARCHAR2(20)
 TYPE                                               VARCHAR2(30)
 PRICE                                              VARCHAR2(30)
 PARKING                                            VARCHAR2(30)
 TIME                                               VARCHAR2(300)
 MENU                                               CLOB
 GOOD                                               NUMBER
 SOSO                                               NUMBER
 BAD                                                NUMBER
 POSTER                                             VARCHAR2(4000)
 HIT                                                NUMBER
 * */
public class FoodVO {
	private int fno,cno;
	private double score;
	private String name,address,phone,type,price,parking,time,menu;
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
}
