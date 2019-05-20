package cn.tedu.cloud_note.dao;

import cn.tedu.cloud_note.entity.User;

public interface UserDao {
	//��¼����
	public User findByName(String name);
	//ע�᷽��
	public void save(User user);
	
	public User findById(String cn_user_Id);
	public void updatePassword(User user);
}
