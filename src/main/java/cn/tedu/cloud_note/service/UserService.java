package cn.tedu.cloud_note.service;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;



public interface UserService {
	public NoteResult<User> checkLogin(
			String name,String password);
	public NoteResult<Object> addUser(
			String name,String password,String desc);
	public NoteResult<User> update(String userId,String password,String new_password);
}
