package cn.tedu.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("bookService")
public class BookServiceImpl implements BookService{
	
	@Resource
	private BookDao bookDao;
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		
		NoteResult<List<Book>> result=new NoteResult<List<Book>>();
		List<Book> book=bookDao.findByUserId(userId);
		
		
		if(book==null){
			result.setStatus(1);
			result.setMsg("无此笔记");
			return result;
		}
		result.setData(book);
		result.setStatus(0);
		result.setMsg("返回成功！");
		return result;
	}
	public NoteResult<Object> addBook(String bookName,String userId) {
		NoteResult<Object> result=new NoteResult<Object>();
		Book book=new Book();
		String bookId=NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		int num=bookDao.save(book);
		if(num==1){
			result.setStatus(1);
			result.setMsg("笔记名称不能为空");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("笔记创建成功");
			result.setData(book);
			return result;
		}
		
	}

}
