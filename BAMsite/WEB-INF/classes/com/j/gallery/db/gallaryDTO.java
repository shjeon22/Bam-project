package com.j.gallery.db;

import java.sql.Date;

public class gallaryDTO {
	private int num;
	private String category;
	private String name;
	private String image;
	private String content;
	private Date date;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "gallaryDTO [num=" + num + ", category=" + category + ", name=" + name + ", image=" + image
				+ ", content=" + content + ", date=" + date + "]";
	}

}
