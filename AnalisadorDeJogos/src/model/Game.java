package model;

import java.sql.Date;
import java.util.ArrayList;

public class Game {
	private String name;
	private Integer site;
	private Date date;
	private double price;
	private String dev;
	private String pub;
	private ArrayList<String> cat;
	
	public ArrayList<String> getCategory() {
		return cat;
	}
	public void setCategory(ArrayList<String> category) {
		this.cat = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSite() {
		return site;
	}
	public void setSite(Integer site) {
		this.site = site;
	}
	public Date getDate_release() {
		return date;
	}
	public void setDate_release(Date date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDeveloper() {
		return dev;
	}
	public void setDeveloper(String developer) {
		this.dev = developer;
	}
	public String getPublisher() {
		return pub;
	}
	public void setPublisher(String publisher) {
		this.pub = publisher;
	}
	
	
}