package cn.tedu.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;

public interface BookService {
	 NoteResult<List<Book>> loadUserBooks(String userId);
	 NoteResult<Object> addBook(String bookName,String userId);
}
