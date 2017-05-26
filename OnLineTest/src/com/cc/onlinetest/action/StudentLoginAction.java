package com.cc.onlinetest.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;



import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.service.StudentService;
import com.cc.onlinetest.utils.Md5Utils;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class StudentLoginAction extends ActionSupport {

	private StudentService studentService;
	
	


	

	/**
	 * @param studentService the studentService to set
	 */
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}


	private String studentId;
	private String password;


	
	

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	/**
	 * Ajax异步请求获得登录许可
	 * @return 返回登录状态
	 */
	public String login(){
		Student student = new  Student();
		student.setStudentId(studentId);
		student.setPassword(Md5Utils.md5(password));
		Student newStudent = studentService.getStudentById(student);
		int login = 1;
		if(newStudent==null){
			//用户名不存在
			login = -1;
		}else if(!newStudent.getPassword().equals(student.getPassword())){
			//密码不正确
			login = -2;
		}else{
			//存储入session
			ServletActionContext.getContext().getSession().put("student", newStudent);
		}
		 HttpServletResponse response = ServletActionContext.getResponse();
		 try {	
			response.getWriter().print(login);		
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * 退出登录
	 */
	public String logout(){
		ServletActionContext.getContext().getSession().remove("student");
		return "logout";
	}
	
	
	

	
}
