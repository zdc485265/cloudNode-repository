package test.dao;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import test.TestBase;
import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;

public class TestBookDao extends TestBase{
	private BookDao bookDao;
	@Before
	public void init(){
		bookDao=super.getContext().getBean("bookDao",BookDao.class);
	}
	
	@Test
	public void testBookSave(){
		Book book=new Book();
		book.setCn_notebook_id("123");
		book.setCn_user_id("12");
		book.setCn_notebook_name("225");
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		int num=bookDao.save(book);
		System.out.println(num);
	}
}
