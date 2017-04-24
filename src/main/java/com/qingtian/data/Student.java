package com.qingtian.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Student {
	@GeneratedValue
	@Id
	Long id;
	
	String username;
	
	/*
	 * cannot simultaneously fetch multiple bags
	 * http://dyldragon.iteye.com/blog/788385
	 */
	//(fetch = FetchType.EAGER)
	//@Fetch(FetchMode.SUBSELECT)  
	@ManyToMany
	List<Teacher> teachers;
	
	/*
	 * http://docs.jboss.org/hibernate/annotations/3.4/reference/zh_cn/html_single/#d0e1291
	 * 在EJB3规范中多对一这端几乎总是双向关联中的主体(owner)端, 而一对多这端的关联注解为@OneToMany( mappedBy=... )
	 */
	//交出维护权
	@OneToMany//(mappedBy="student") //,fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE}
	List<Book> books;
	
	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
