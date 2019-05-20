package test.service;

import java.util.List;

import org.junit.Test;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestBase;

public class TestBookService extends TestBase{
	BookService service;
	@Test
	public void test(){
		service=getContext().getBean("bookService",BookService.class);
		NoteResult<List<Book>> result=service.loadUserBooks("52f9b276-38ee-447f-a3aa-0d54e7a736e4");
		System.out.println(result.getData());
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		for(Book book:result.getData()){
			System.out.println(book.getCn_notebook_name());
		}
	}
	
	@Test
	public void test2(){
		service=getContext().getBean("bookService",BookService.class);
		
		NoteResult<Object> result=service.addBook("asd", "dasd");
		System.out.println(result.getData());
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
}
