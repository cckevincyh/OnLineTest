package com.cc.onlinetest.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Score;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.service.ScoreService;
import com.cc.onlinetest.service.StudentService;
import com.cc.onlinetest.utils.Md5Utils;
import com.opensymphony.xwork2.ActionSupport;

public class StudentManageAction extends ActionSupport{
	
	private StudentService studentService;
	private ScoreService scoreService;
	
	
	/**
	 * @param scoreService the scoreService to set
	 */
	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
	/**
	 * @param studentService the studentService to set
	 */
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	private String studentId;
	private String studentName;
	private String password;
	private int pageCode;//当前页数
	private int subjectId;
	
	
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param pageCode the pageCode to set
	 */
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	
	
	public String getStudent(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Student student = new Student();
		student.setStudentId(studentId);
		Student newStudent = studentService.getStudentById(student);
		JSONObject jsonObject = JSONObject.fromObject(newStudent);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	

	public String updateStudent(){
		Student student = new Student();
		student.setStudentId(studentId);
		Student updateStudent = studentService.getStudentById(student);
		updateStudent.setStudentName(studentName);
		updateStudent.setPassword(Md5Utils.md5(password));
		Student newStudent = studentService.updateStudent(updateStudent);
		int success = 0;
		if(newStudent!=null){
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
	
	

	public String addStudent(){
		Student student = new Student();
		student.setStudentId(studentId);
		Student student2 = studentService.getStudentById(student);
		int success = 0;
		if(student2!=null){
			success = -1;//已经存在该学号
		}else{
			student.setStudentName(studentName);
			student.setPassword(Md5Utils.md5(password));
			boolean b = studentService.addStudent(student);
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
	
	

	public String findStudentByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<Student> pb = studentService.findStudentByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findStudentByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	
	
	
	public String queryStudent(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<Student> pb = null;
		if("".equals(studentId.trim()) && "".equals(studentName.trim())){
			pb = studentService.findStudentByPage(pageCode,pageSize);
		}else{
			Student student = new Student();
			student.setStudentId(studentId);
			student.setStudentName(studentName);
			pb = studentService.queryStudent(student,pageCode,pageSize);
			
		}
		if(pb!=null){
			pb.setUrl("queryStudent.action?studentId="+studentId+"&studentName="+studentName+"&");
		}

		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
	
	
	
	public String deleteStudent(){
		Student student = new Student();
		student.setStudentId(studentId);
		boolean deleteStudent = studentService.deleteStudent(student);
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
	
	
	
	
	public String getState(){
		Student student = new Student();
		student.setStudentId(studentId);
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		Score score = scoreService.getScore(student, subject);
		int state = 0;
		if(score!=null){
			//该试卷已经做过了
			state = -2;
		}else{
			//该试卷没做过，或者正在做着试卷
			//判断是否正在做试卷
			Student studentById = studentService.getStudentById(student);
			//锁住的状态是否等于当前科目,除了当前科目可以继续考试，不能进行其他考试
			if(!(studentById.getLockState().equals(subject.getSubjectId()) || studentById.getLockState().equals(0))){
				//正在考试
				state = -1;
			}else{
				//允许进入做试卷
				state = 1;
				//修改学生锁住状态,设置为当前考试科目
				studentById.setLockState(subject.getSubjectId());
				studentService.updateStudent(studentById);
			}
		}
		 HttpServletResponse response = ServletActionContext.getResponse();
		 try {	
			response.getWriter().print(state);		
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}

}
