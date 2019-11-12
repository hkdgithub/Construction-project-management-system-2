package com.how2java.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.bidManageMapper;
import com.how2java.springboot.pojo.t_ent_proj_info;

@Controller
public class bidManageController {

	@Autowired
	private bidManageMapper bMM;
	
	
//...........................投标管理信息的增删改查....................................
	
	//投标添加
	@RequestMapping(value="/bidAdd")
	public String bidAdd(Model m,String prid,String qyid,String ysbj, String tbbj,String degq, String tbgq,
			@RequestParam(value="start",defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size) {
		
		System.out.println("开始添加投标"+prid);
		
		
		int ysbj1=Integer.parseInt(ysbj);
		int tbbj1=Integer.parseInt(tbbj);
		int degq1=Integer.parseInt(degq);
		int tbgq1=Integer.parseInt(tbgq);
		
		bMM.add(prid, qyid, ysbj1, tbbj1, degq1, tbgq1);
		PageHelper.startPage(start,size);
		List<t_ent_proj_info> tepi=bMM.seek();
		PageInfo<t_ent_proj_info> page= new PageInfo<>(tepi);			
		m.addAttribute("page",page);  
		m.addAttribute("list",tepi);
		
		System.out.println("投标信息"+prid+"完成");
		System.out.println("跳转到投标管理");
		
		return "bidManage";
	}
	
	
	//投标修改
	@RequestMapping(value="/bidUpdate")
	public String bidUpdate(Model m, String prid, String qyid,String ysbj,
			 String tbbj,String degq,String tbgq,@RequestParam(value="start",
			defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size) {
			
		System.out.println("开始修改"+prid);
		
		bMM.modify(qyid, ysbj, tbbj, degq, tbgq, prid);
		PageHelper.startPage(start,size);
		List<t_ent_proj_info> tepi=bMM.seek();
		PageInfo<t_ent_proj_info> page= new PageInfo<>(tepi);			
		m.addAttribute("page",page);  
		m.addAttribute("list",tepi);
		
		System.out.println("修改"+prid+"完成");
		System.out.println("跳转到投标管理界面");
		
		return "bidManage";	
	}
	
	//投标删除
	@RequestMapping(value="/bidDelete")
	public String bidUpdate(String prid,Model m,@RequestParam(value="start",
			defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size) {
		
		System.out.println("开始删除投标"+prid);
		
		bMM.remove(prid);
		PageHelper.startPage(start,size);
		List<t_ent_proj_info> tepi=bMM.seek();
		PageInfo<t_ent_proj_info> page= new PageInfo<>(tepi);			
		m.addAttribute("page",page);  
		m.addAttribute("list",tepi);
		
		System.out.println("投标"+prid+"删除完成");
		
		return "bidManage";
	}
	
	//投标查询
	@RequestMapping(value="/bidQuery")
	public String bidQuery(Model m,@RequestParam(value="start",
			defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size) {
		
		System.out.println("开始查询");
		
		//设置分页信息
		PageHelper.startPage(start,size);
		//获得结果
		List<t_ent_proj_info> tepi=bMM.seek();
		//将信息进行分页
		PageInfo<t_ent_proj_info> page= new PageInfo<>(tepi);			
		m.addAttribute("page",page);  
		m.addAttribute("list",tepi);
		
		System.out.println("查询完成");
		
	return "bidManage";
	}
	
	//返回投标管理
	@RequestMapping(value="/bidManage")
	public String bidManage(Model m,@RequestParam(value="start",
			defaultValue="0") Integer start,@RequestParam(value="size",defaultValue="8") Integer size) {
		
		System.out.println("开始进入投标管理");
		
				PageHelper.startPage(start,size);
				List<t_ent_proj_info> tepi=bMM.seek();
				PageInfo<t_ent_proj_info> page= new PageInfo<>(tepi);			
				m.addAttribute("page",page);  
				m.addAttribute("list",tepi);
				
		System.out.println("进入投标管理完成");		
				
		return "bidManage";
	}
	
//...........................投标管理子界面之间的跳转..................................
	
	//投标添加
	@RequestMapping(value="/bidManageAdd")
	public String bidManageAdd() {
		return "bidManageAdd";
	}
	
	//投标修改
    @RequestMapping(value="/bidManageUpdate")
    public String bidManageUpdate(Model m,String prid,String qyid,String ysbj,String tbbj,String degq,String tbgq) {
    	
    	m.addAttribute("a", prid);
    	m.addAttribute("b", qyid);
    	m.addAttribute("c", ysbj);
    	m.addAttribute("d", tbbj);
    	m.addAttribute("e", degq);
    	m.addAttribute("f", tbgq);
    	
    	return "bidManageUpdate";
    }

}
