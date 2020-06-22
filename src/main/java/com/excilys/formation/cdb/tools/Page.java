package com.excilys.formation.cdb.tools;

public class Page {

	/*
	 Page class, containing your entities and the page information.
	 */
	private String title;
	private int number;
	private String content; // content by lines
	
	public Page() {
	}
	
	public Page(String title, int number, String content) {
		super();
		this.title = title;
		this.number = number;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void addContent(String content) {
		this.content += content;
	}
	
	public void presentPage() {
		System.out.println("\n ------ " + this.getTitle() +" ------");
		System.out.println(this.content);
		System.out.println("------ "+this.number + " ------ ");
	}

	@Override
	public String toString() {
		return "Page [title=" + title + ", number=" + number + "]";
	}
	
}
