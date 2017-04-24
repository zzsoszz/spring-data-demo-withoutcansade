package com.qingtian;
/*
 * http://docs.jboss.org/hibernate/annotations/3.4/reference/zh_cn/html_single/#entity-mapping-association
 * 在EJB3规范中多对一这端几乎总是双向关联中的主体(owner)端, 而一对多这端的关联注解为@OneToMany( mappedBy=... )
 * 
 * http://dyldragon.iteye.com/blog/788385
 * Hibernate抓取策略以及如何避免cannot simultaneously fetch multiple bags异常
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;


@SpringBootApplication
public class SpringDataDemoApplication  implements ApplicationListener<ApplicationReadyEvent> {
	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent e) {
		SystemInitService systemInitService = e.getApplicationContext().getBean(SystemInitService.class);
		systemInitService.init();
	}
}

//
//
//
//TeacherResponsitory teacherResponsitory = e.getApplicationContext().getBean(TeacherResponsitory.class);
//StudentResponsitory studentResponsitory = e.getApplicationContext().getBean(StudentResponsitory.class);
//BookResponsitory bookResponsitory = e.getApplicationContext().getBean(BookResponsitory.class);
//ArrayList<Student> students=new ArrayList<Student>();
//ArrayList<Teacher> teachers=new ArrayList<Teacher>();
//ArrayList<Book> books=new ArrayList<Book>();
//Book book=new Book();
//book.setName("我欲封天");
//Teacher teacher=new Teacher();
//teacher.setName("张老师");
//Student student=new Student();
//books.add(book);
//student.setBooks(books);
//student.setTeachers(teachers);
//student.setUsername("qingtian");
//students.add(student);
//teachers.add(teacher);
//teacher.setStudents(students);
//teacherResponsitory.save(teacher);
//studentResponsitory.save(student);
//book.setStudent(student);
//bookResponsitory.save(book);
//Student student1=studentResponsitory.findOne(1L);
//System.out.println(student1);
//System.out.println(student1.getUsername());
//for(Book one:student1.getBooks()){
//	System.out.println(one.getName()+one.getId());
//	System.out.println(one.getStudent().getUsername());
//	for(Teacher two:one.getStudent().getTeachers())
//	{
//		System.out.println(two.getName()+two.getId());
//	}
//}