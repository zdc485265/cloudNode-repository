package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserRegistController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/add.do")	//∆•≈‰«Î«Û
	@ResponseBody   //JSON ‰≥ˆ
	public NoteResult<Object> execute(String name,String password,String desc){
		
		NoteResult<Object> result=userService.addUser(name, password, desc);
		
		return result;
	}
}
