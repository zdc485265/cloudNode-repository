package cn.tedu.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;






import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNotesController {
	@Resource
	NoteService noteService;
	
	@RequestMapping("/loadNotes.do")
	@ResponseBody
	public NoteResult<List<Map>> execute(String bookId){
		
		
		NoteResult<List<Map>> result=noteService.loadBookNotes(bookId);
		System.out.println(result);
		System.out.println("bookid:"+bookId);
		return result;
	}
	
	@RequestMapping("/load.do")
	@ResponseBody
	public NoteResult<Note> execute2(String noteId){
		
		NoteResult<Note> result=noteService.loadNote(noteId);
		System.out.println(result);
		System.out.println("noteId"+noteId);
		return result;
		
	}
}
