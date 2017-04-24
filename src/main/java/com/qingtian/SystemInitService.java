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
		
		ArrayList<Book> books2=new ArrayList<Book>();
		Book book2=new Book();
		book2.setName("魔界");
		book2.setStudent(student1);
		bookResponsitory.save(book2);
		books2.add(book2);
		
//		student1.getBooks().clear();
//		student1.getBooks().add(book2);
		student1.setBooks(books2);
		studentResponsitory.save(student1);
		
		student1=studentResponsitory.findOne(1L);
		System.out.println("-----------------------------");
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
/*
 * mappedBy： 
 *1>只有OneToOne，OneToMany，ManyToMany上才有mappedBy属性，ManyToOne不存在该属性； 
 *2>mappedBy标签一定是定义在被拥有方的，他指向拥有方； 
 *3>mappedBy的含义，应该理解为，拥有方能够自动维护跟被拥有方的关系，当然，如果从被拥有方，通过手工强行来维护拥有方的关系也是可以做到的； 
 *4>mappedBy跟joinColumn/JoinTable总是处于互斥的一方，可以理解为正是由于拥有方的关联被拥有方的字段存在，拥有方才拥有了被拥有方。mappedBy这方定义JoinColumn/JoinTable总是失效的，不会建立对应的字段或者表。 
*/