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
		//�������ݼ���
		List<Map> list=noteDao.findByBookId(bookId);
		//����Result
		NoteResult<List<Map>> result=new NoteResult<List<Map>>();
		result.setStatus(0);
		result.setMsg("���رʼǳɹ�");
		result.setData(list);
		System.out.println(list);
		return result;
	}
	public NoteResult<Note> loadNote(String noteId) {
		Note note=noteDao.findByNoteId(noteId);
		NoteResult<Note> result=new NoteResult<Note>();
		if(note==null){
			result.setStatus(1);
			result.setMsg("δ�ҵ�����");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("���ҳɹ�");
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
		
		//�������ݿ�
		int rows=noteDao.updateNote(note);
		//����result
		NoteResult<Object> result=new NoteResult<Object>();
		
		if(rows==1){
			result.setStatus(0);
			result.setMsg("����ʼǳɹ�");
			return result;
		}else{
		result.setStatus(1);
		result.setMsg("����ʼ�ʧ��");
		return result;
		}
	}
	public NoteResult<Note> addNote(String userId, String bookId, String title) {
		Note note=new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(title);
		//�ʼ�ID
		String noteId=NoteUtil.createId();
		note.setCn_note_id(noteId);
		//�ʼ�����
		note.setCn_note_body("");
		//����ʱ��
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		//����޸�ʱ��
		note.setCn_note_last_modify_time(time);
		//״̬:1-normal 2-delete
		note.setCn_note_status_id("1");
		//���ͣ�1-normal 2-favor 3-share
		note.setCn_note_type_id("1");
		noteDao.save(note);
		//����result
		NoteResult<Note> result=new NoteResult<Note>();
		
		result.setStatus(0);
		result.setMsg("�����ʼǳɹ�");
		result.setData(note);
		return result;

	}
	public NoteResult<Object> deleteNote(String noteId) {
		Note note=new Note();
		note=noteDao.findByNoteId(noteId);
		System.out.println("Ҫ�޸ĵıʼ�ID"+noteId);
		System.out.println("Ҫɾ���ıʼ�"+note);
		noteDao.deleteNote(note);
		NoteResult<Object> result=new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("ɾ���ʼǳɹ�");
		result.setData(note);
		return result;
	}


}
