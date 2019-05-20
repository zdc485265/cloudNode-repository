package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserPasswordController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/upassword.do")
	@ResponseBody
	public NoteResult<User> execute(String userId,String password,String new_password){
			NoteResult<User> result=userService.update(userId,password,new_password);
		return result;
	}
}
