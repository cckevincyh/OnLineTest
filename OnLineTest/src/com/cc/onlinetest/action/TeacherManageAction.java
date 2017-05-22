package com.cc.onlinetest.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Teacher;
import com.cc.onlinetest.service.TeacherService;
import com.cc.onlinetest.utils.Md5Utils;
import com.opensymphony.xwork2.ActionSupport;

public class TeacherManageAction extends ActionSupport{

	private TeacherService teacherService;

	/**
	 * @param teacherService the teacherService to set
	 */
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	private String teacherId;
	private String teacherName;
	private int pageCode;
	private String password;
	
	
	
	
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @param teacherId the teacherId to set
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}


	/**
	 * @param teacherName the teacherName to set
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	/**
	 * @param pageCode the pageCode to set
	 */
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}


	public String getTeacher(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Teacher teacher = new Teacher();
		teacher.setTeacherId(teacherId);
		Teacher newTeacher = teacherService.getTeacherById(teacher);
		JSONObject jsonObject = JSONObject.fromObject(newTeacher);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	

	public String updateTeacher(){
		Teacher teacher = new Teacher();
		teacher.setTeacherId(teacherId);
		Teacher updateTeacher = teacherService.getTeacherById(teacher);
		updateTeacher.setTeacherName(teacherName);
		updateTeacher.setPassword(Md5Utils.md5(password));
		Teacher newTeacher = teacherService.updateTeacher(updateTeacher);
		int success = 0;
		if(newTeacher!=null){
			success = 1;
			//由于是转发并且js页面刷新,所以无需重查
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	

	public String addTeacher(){
		Teacher teacher = new Teacher();
		teacher.setTeacherId(teacherId);
		Teacher teacher2 = teacherService.getTeacherById(teacher);
		int success = 0;
		if(teacher2!=null){
			success = -1;//已经存在该职工号
		}else{
			teacher.setTeacherName(teacherName);
			teacher.setPassword(Md5Utils.md5(password));
			boolean b = teacherService.addTeacher(teacher);
			if(b){
				success = 1;
			}else{
				success = 0;
			
			}
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);//向浏览器响应是否成功的状态码
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	

	public String findTeacherByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<Teacher> pb = teacherService.findTeacherByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findTeacherByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	
	
	
	public String queryTeacher(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<Teacher> pb = null;
		if("".equals(teacherId.trim()) && "".equals(teacherName.trim())){
			pb = teacherService.findTeacherByPage(pageCode,pageSize);
		}else{
			Teacher teacher = new Teacher();
			teacher.setTeacherId(teacherId);
			teacher.setTeacherName(teacherName);
			pb = teacherService.queryTeacher(teacher,pageCode,pageSize);
			
		}
		if(pb!=null){
			pb.setUrl("queryStudent.action?teacherId="+teacherId+"&teacherName="+teacherName+"&");
		}

		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
	
	
	
	public String deleteTeacher(){
		Teacher teacher = new Teacher();
		teacher.setTeacherId(teacherId);
		boolean deleteStudent = teacherService.deleteTeacher(teacher);
		int success = 0;
		if(deleteStudent){
			success = 1;
			//由于是转发并且js页面刷新,所以无需重查
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
		return null;
	}
}
