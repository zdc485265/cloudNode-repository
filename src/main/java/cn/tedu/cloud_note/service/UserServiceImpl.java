package cn.tedu.cloud_note.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("userService")//ɨ���Spring����
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	public NoteResult<User> checkLogin(
			String name, String password) {
		//���ܽ������
		NoteResult<User> result= new NoteResult<User>();
		User user=userDao.findByName(name);
		
		//�û������
		if(user==null){
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//�������
		try {
			String md5Password=NoteUtil.md5(password);
			if(!user.getCn_user_password().equals(md5Password)){
				result.setStatus(2);
				result.setMsg("�������");
				return result;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	
		//�û��������붼��ȷ
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		result.setData(user);
		return result;
	}
	
	public NoteResult<Object> addUser(String name, String password, String desc) {
		NoteResult<Object> result=new NoteResult<Object>();
		//�û����
		User hasUser=userDao.findByName(name);
		if(hasUser!=null){
			result.setStatus(1);
			result.setMsg("�û��ѱ�ռ��");
			return result;
		}
		//����û�
		User user =new User();
		//�����û���
		user.setCn_user_name(name);
		//�����û�����
		try {
			String md5Password=NoteUtil.md5(password);
			user.setCn_user_password(md5Password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//�����û��ǳ�
		user.setCn_user_desc(desc);
		//�����û�ID
		String id=NoteUtil.createId();
		//�����û�ID
		user.setCn_user_id(id);
		//�����û�����
		userDao.save(user);
		//�������ؽ��
		result.setStatus(0);
		result.setMsg("ע��ɹ���");
		
		return result;
	}

	public NoteResult<User> update(String userId,String password,String new_password){
		NoteResult<User> result=new NoteResult<User>();
		User user=new User();
		user=userDao.findById(userId);
		String upassword=user.getCn_user_password();
		try {
			String md5Password =NoteUtil.md5(password);
			if(md5Password.equals(upassword)){
				System.out.println(user);
				String md5Password2;
				try {
					md5Password2=NoteUtil.md5(new_password);
					user.setCn_user_password(md5Password2);
					userDao.updatePassword(user);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				result.setStatus(0);
				result.setMsg("�޸�����ɹ�");
				result.setData(user);
				return result;
			}else{
				result.setStatus(1);
				result.setMsg("ԭ�������");
				return result;
			}
		
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
}
