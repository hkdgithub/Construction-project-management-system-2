package com.how2java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.how2java.springboot.mapper.t_userMapper;
import com.how2java.springboot.pojo.t_user;


@Controller
public class loginController {
	@Autowired 
	private t_userMapper tM1;
	
	@RequestMapping("/login")
	public String login(Model m,String name,String pwd)  {
			
		System.out.println(name+"开始进行登录");
      
        //判断用户是否存在，如果存在的话，跳转到主窗口，否则，返会原页面
		if(tM1.seek(name,pwd)!=null) {
			
		System.out.println(name+"完成登录");
		
			return "mainWindow";
		}
		else {
			m.addAttribute("error", "没有该用户的信息，请重新输入");
		    return "login";
		}
	}
//.............................页面之间的跳转..............................
	@RequestMapping(value="/register")
	public String register() {
				return "register";
			}

	@RequestMapping(value="/returnMainWindow")
	public String returnMainWindow() {
		return "mainWindow";
	}

	//返回到登录界面
	@RequestMapping(value="/returnLogin")
	public String returnLogin() {
		return "login";
	}
}		
			
	
	
