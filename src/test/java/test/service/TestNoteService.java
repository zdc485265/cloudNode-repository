package test.service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import test.TestBase;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestNoteService extends TestBase{
	NoteService noteService;
	@Before
	public void init(){
		noteService=super.getContext().getBean("noteService",NoteService.class);
	}
	@Test
	public void testNote(){
	NoteResult<List<Map>> result=noteService.loadBookNotes("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println(result.getData());
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	
	@Test
	public void testShow(){
		NoteResult<Note> note=noteService.loadNote("01da5d69-89d5-4140-9585-b559a97f9cb0");
		System.out.println(note.getData());
	}
	
	@Test
	public void testUpdate(){
		String id="01da5d69-89d5-4140-9585-b559a97f9cb0";
		String title="Java±Ê¼Ç";
		String body="JDBC¿ª·¢";
		NoteResult<Object> result=
		noteService.updateNote(id, title, body);
		
		System.out.println(result);
		
	}
	
	@Test
	public void testDelete(){
		String noteId="019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		NoteResult<Object> result=noteService.deleteNote(noteId);
				System.out.print(result);
	}
}
