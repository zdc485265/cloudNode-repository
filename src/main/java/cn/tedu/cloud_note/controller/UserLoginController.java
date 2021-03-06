package cn.tedu.cloud_note.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;



@Controller
@RequestMapping("/user")  //匹配请求路径
public class UserLoginController{
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")	//匹配请求
	@ResponseBody   //JSON输出
	public NoteResult<User> execute(String name,String password){
		
		//调用UserService处理登录请求
		System.out.println(name+","+password);
		NoteResult result= userService.checkLogin(name, password);
		return result;
	}
	
}