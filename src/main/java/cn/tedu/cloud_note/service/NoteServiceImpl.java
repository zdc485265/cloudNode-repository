package cn.tedu.cloud_note.service;

import java.util.List;




import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteDao noteDao;
	public NoteResult<List<Map>> loadBookNotes(String bookId) {
		//返回数据集合
		List<Map> list=noteDao.findByBookId(bookId);
		//构建Result
		NoteResult<List<Map>> result=new NoteResult<List<Map>>();
		result.setStatus(0);
		result.setMsg("加载笔记成功");
		result.setData(list);
		System.out.println(list);
		return result;
	}
	public NoteResult<Note> loadNote(String noteId) {
		Note note=noteDao.findByNoteId(noteId);
		NoteResult<Note> result=new NoteResult<Note>();
		if(note==null){
			result.setStatus(1);
			result.setMsg("未找到数据");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(note);
			return result;
		}
	}
	public NoteResult<Object> updateNote(String noteId,String title,String body) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_body(body);
		note.setCn_note_title(title);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		
		//更新数据库
		int rows=noteDao.updateNote(note);
		//构建result
		NoteResult<Object> result=new NoteResult<Object>();
		
		if(rows==1){
			result.setStatus(0);
			result.setMsg("保存笔记成功");
			return result;
		}else{
		result.setStatus(1);
		result.setMsg("保存笔记失败");
		return result;
		}
	}
	public NoteResult<Note> addNote(String userId, String bookId, String title) {
		Note note=new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(title);
		//笔记ID
		String noteId=NoteUtil.createId();
		note.setCn_note_id(noteId);
		//笔记内容
		note.setCn_note_body("");
		//创建时间
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		//最后修改时间
		note.setCn_note_last_modify_time(time);
		//状态:1-normal 2-delete
		note.setCn_note_status_id("1");
		//类型：1-normal 2-favor 3-share
		note.setCn_note_type_id("1");
		noteDao.save(note);
		//创建result
		NoteResult<Note> result=new NoteResult<Note>();
		
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(note);
		return result;

	}
	public NoteResult<Object> deleteNote(String noteId) {
		Note note=new Note();
		note=noteDao.findByNoteId(noteId);
		System.out.println("要修改的笔记ID"+noteId);
		System.out.println("要删除的笔记"+note);
		noteDao.deleteNote(note);
		NoteResult<Object> result=new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("删除笔记成功");
		result.setData(note);
		return result;
	}


}
