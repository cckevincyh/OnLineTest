package com.cc.onlinetest.action;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.service.OnLineTestService;
import com.opensymphony.xwork2.ActionSupport;

public class OnLineTestAction extends ActionSupport{

	private OnLineTestService onLineTestService;
	
	

	
	

	/**
	 * @param onLineTestService the onLineTestService to set
	 */
	public void setOnLineTestService(OnLineTestService onLineTestService) {
		this.onLineTestService = onLineTestService;
	}
	
	private String answer;
	private String studentId;
	private int subjectId;
	
	
	
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}



	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}





	public String onLineTest(){
		Student student = new Student();
		student.setStudentId(studentId);
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		boolean b = onLineTestService.onLineTest(student,subject,answer);
		int state = 0;
		if(b){
			state = 1;
		}else{
			state = -1;
		}
		 HttpServletResponse response = ServletActionContext.getResponse();
		 try {	
			response.getWriter().print(state);		
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	



	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	
}
