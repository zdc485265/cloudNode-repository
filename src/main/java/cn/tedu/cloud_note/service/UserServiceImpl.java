package cn.tedu.cloud_note.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("userService")//扫描的Spring容器
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	public NoteResult<User> checkLogin(
			String name, String password) {
		//接受结果数据
		NoteResult<User> result= new NoteResult<User>();
		User user=userDao.findByName(name);
		
		//用户名检测
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//检测密码
		try {
			String md5Password=NoteUtil.md5(password);
			if(!user.getCn_user_password().equals(md5Password)){
				result.setStatus(2);
				result.setMsg("密码错误");
				return result;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	
		//用户名和密码都正确
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		return result;
	}
	
	public NoteResult<Object> addUser(String name, String password, String desc) {
		NoteResult<Object> result=new NoteResult<Object>();
		//用户检测
		User hasUser=userDao.findByName(name);
		if(hasUser!=null){
			result.setStatus(1);
			result.setMsg("用户已被占用");
			return result;
		}
		//添加用户
		User user =new User();
		//设置用户名
		user.setCn_user_name(name);
		//设置用户密码
		try {
			String md5Password=NoteUtil.md5(password);
			user.setCn_user_password(md5Password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//设置用户昵称
		user.setCn_user_desc(desc);
		//创建用户ID
		String id=NoteUtil.createId();
		//设置用户ID
		user.setCn_user_id(id);
		//插入用户数据
		userDao.save(user);
		//构建返回结果
		result.setStatus(0);
		result.setMsg("注册成功！");
		
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
				result.setMsg("修改密码成功");
				result.setData(user);
				return result;
			}else{
				result.setStatus(1);
				result.setMsg("原密码错误");
				return result;
			}
		
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
}
