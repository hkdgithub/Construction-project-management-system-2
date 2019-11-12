package com.how2java.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.userManageMapper;
import com.how2java.springboot.pojo.t_user;


@Controller
public class userManageController {
	
@Autowired 
private userManageMapper uMM;

//...................................用户管理信息的增删改查..................................
   //查询用户信息
	@GetMapping(value="/userQuery")
	public String userQuery(Model m,@RequestParam(value="start",
defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size)throws Exception {
		
		System.out.println("开始查询。。。");
		
		//设置分页信息
		PageHelper.startPage(start,size);		
		//查询用户信息
		List<t_user> tu=uMM.seek();
		//将信息进行分页
		PageInfo<t_user> page= new PageInfo<>(tu);			
		m.addAttribute("page",page);  
		m.addAttribute("list",tu);
		
		System.out.println("查询成功。。。");
		
		return "userManage";
	}

    //添加用户信息
	@GetMapping(value="/userAdd")
		public String userAdd( String name, String pwd, String dept,Model m,@RequestParam(value="start",
				defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size) {
		
		System.out.println("正在添加用户"+name);
		
		uMM.add(name,pwd,dept);
		PageHelper.startPage(start,size);
		List<t_user> tu=uMM.seek();
		PageInfo<t_user> page= new PageInfo<>(tu);			
		m.addAttribute("page",page);  
		m.addAttribute("list",tu);
		
		System.out.println("用户"+name+"添加完毕");
		
			return "userManage";
		}
	
	//修改用户信息
	@GetMapping(value="/userUpdate")
	public String userUpdate(String name, String dept,Model m,@RequestParam(value="start",
			defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size) {
		
		System.out.println("正在修改用户"+name);
		
				PageHelper.startPage(start,size);
				uMM.update(name, dept);
				List<t_user> tu=uMM.seek();
				PageInfo<t_user> page= new PageInfo<>(tu);			
				m.addAttribute("page",page);  
				m.addAttribute("list",tu);

		System.out.println("修改"+name+"完成");
		
		return "userManage";
	}

	//用户删除界面
	@GetMapping(value="/userDelete")
	public String userDelete(@RequestParam("name") String name,Model m,@RequestParam(value="start",
			defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size) {
		
		System.out.println("开始删除"+name);
		
		uMM.delete(name);
		PageHelper.startPage(start,size);
		List<t_user> tu=uMM.seek();
		PageInfo<t_user> page= new PageInfo<>(tu);			
		m.addAttribute("page",page);  
		m.addAttribute("list",tu);
		
		System.out.println("删除"+name+"完成");
		
		return "userManage";
	}
	
	//返回用户管理(在此要重新分页)
	@GetMapping(value="/userManage")
	public String userManage(Model m,@RequestParam(value="start",
			defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size) {
		
		System.out.println("正在进入用户管理");
		
		PageHelper.startPage(start,size);		
		List<t_user> tu=uMM.seek();
		PageInfo<t_user> page= new PageInfo<>(tu);			
		m.addAttribute("page",page);  
		m.addAttribute("list",tu);
		
		System.out.println("进入用户管理完成");
		
		return "userManage";
	}
	
	//...........................界面之间的跳转..................................
	

	
	//跳转到用户添加界面
	@RequestMapping(value="/userManageAdd")
	public String userManageAdd() {
		return "userManageAdd";
	}
	
	@RequestMapping(value="/userManageUpdate")
	public String userManageUpdate(Model m,String name,String dept) {
		m.addAttribute("c",name);
		m.addAttribute("d",dept);
		return "userManageUpdate";
	}
	}

