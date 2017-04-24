package com.qingtian;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qingtian.data.Book;
import com.qingtian.data.BookResponsitory;
import com.qingtian.data.Student;
import com.qingtian.data.StudentResponsitory;
import com.qingtian.data.Teacher;
import com.qingtian.data.TeacherResponsitory;

@Component
public class SystemInitService {
	@Autowired
	TeacherResponsitory teacherResponsitory;
	@Autowired
	StudentResponsitory studentResponsitory;
	@Autowired
	BookResponsitory bookResponsitory;
	
	
	@Transactional
	public void init(){
		ArrayList<Student> students=new ArrayList<Student>();
		ArrayList<Teacher> teachers=new ArrayList<Teacher>();
		ArrayList<Book> books=new ArrayList<Book>();
		
		/*
		 * book
		 */
		Book book=new Book();
		book.setName("我欲封天");
		bookResponsitory.save(book);
		books.add(book);
		
		/**
		 * student
		 */
		Teacher teacher=new Teacher();
		teacher.setName("张老师");
		teachers.add(teacher);
		teacherResponsitory.save(teacher);
		
		Student student=new Student();
		student.setBooks(books);
		student.setUsername("qingtian");
		studentResponsitory.save(student);
		student.setTeachers(teachers);/*具有关联的会在方法最后执行*/
		studentResponsitory.save(student);
		
		students.add(student);
		teacher.setStudents(students);/*具有关联的会在方法最后执行*/
		teacherResponsitory.save(teacher);
		
		book.setStudent(student);/*具有关联的会在方法最后执行*/
		bookResponsitory.save(book);
		
		Student student1=studentResponsitory.findOne(1L);
		for(Book one:student1.getBooks()){
			System.out.println("book:"+one.getName()+one.getId());
			System.out.println("student:"+one.getStudent().getUsername());
			for(Teacher two:one.getStudent().getTeachers())
			{
				System.out.println("teacher:"+two.getName()+two.getId());
			}
		}
		
		
	}
}
