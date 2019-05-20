package test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.entity.User;


public class TestUserDao {
	
	@Test
	public void testUserDao(){
	ApplicationContext ctx=
			new ClassPathXmlApplicationContext(
					"conf/spring-mybatis.xml");
	UserDao dao=ctx.getBean("userDao",UserDao.class);
	User user=dao.findByName("demo");
	
	System.out.println(user);
	}
	
	@Test
	public void testSave(){
		String[] conf={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		//实例化对象
		ApplicationContext ctx=
				new ClassPathXmlApplicationContext(conf);
		//获取UserDao对象
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user =new User();
		user.setCn_user_id("112");
		user.setCn_user_name("张三丰");
		user.setCn_user_password("123");
		user.setCn_user_desc("君宝");
		dao.save(user);
		System.out.println(user);
	}
	
	@Test
	public void testBook(){
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
		BookDao dao=ctx.getBean("bookDao",BookDao.class);
		List<Book> book=dao.findByUserId("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		System.out.println(book);
	}
	
}