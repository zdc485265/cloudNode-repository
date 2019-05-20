package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import test.TestBase;
import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;

public class TestNoteDao extends TestBase{
	private NoteDao noteDao;
	@Before
	public void init(){
		noteDao=super.getContext().getBean("noteDao",NoteDao.class);
	}
	
	@Test
	public void testNoteDao(){
		List<Map> list=noteDao.findByBookId("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		for(Map note:list){
			System.out.println(note.get("cn_note_id")+","+note.get("cn_note_title"));
		}
	}
	
	@Test
	public void testFind(){
		String noteId="003ec2a1-f975-4322-8e4d-dfd206d6ac0c";
		Note note=noteDao.findByNoteId(noteId);
		System.out.println(note.getCn_note_id());
	}
	
	@Test
	public void testUpdate(){
		Note note=new Note();
		String noteId="003ec2a1-f975-4322-8e4d-dfd206d6ac0c";
		note.setCn_note_id(noteId);
		String title="传奇和克晶";
		note.setCn_note_title(title);
		String body="已经上了搜索引擎";
		note.setCn_note_body(body);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		
		int num=noteDao.updateNote(note);
		System.out.println(num);
	}
	
	@Test
	public void testDelete(){
		Note note =new Note();
		String noteId="003ec2a1-f975-4322-8e4d-dfd206d6ac0c";
		note.setCn_note_id(noteId);
		noteDao.deleteNote(note);
		System.out.println(noteId);
	}
}
