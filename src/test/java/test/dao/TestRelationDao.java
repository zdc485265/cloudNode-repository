package test.dao;


import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.RelationDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.entity.User;
import test.TestBase;

public class TestRelationDao extends TestBase{
	private RelationDao rdao;
	
	@Before
	public void init(){
		rdao=getContext().getBean("relationDao",RelationDao.class);
	}
	
	@Test
	public void test(){
		User user=rdao.findUserAndBooks("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println("*************用户信息*************");
		System.out.println("名字："+user.getCn_user_name());
		System.out.println("昵称:"+user.getCn_user_desc());
		System.out.println("笔记本数量："+user.getBooks().size());
		for(Book book:user.getBooks()){
			System.out.println(book.getCn_notebook_name());
		}
	}
	
	@Test
	public void testMay(){
		User user=rdao.findUserAndBooks1("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println("*************用户信息*************");
		System.out.println("名字："+user.getCn_user_name());
		System.out.println("昵称:"+user.getCn_user_desc());
		System.out.println("笔记本数量："+user.getBooks().size());
		for(Book book:user.getBooks()){
			System.out.println(book.getCn_notebook_name());
		}
	}
	
	@Test
	public void testOne(){
		List<Book> list=rdao.findBookAndUser();
		for(Book book:list){
			System.out.println(book.getCn_notebook_name());
		}
	}
	@Test
	public void testTwo(){
		List<Book> list=rdao.findBookAndUser2();
		for(Book book:list){
			System.out.println(book.getCn_notebook_name());
		}
	}
}
