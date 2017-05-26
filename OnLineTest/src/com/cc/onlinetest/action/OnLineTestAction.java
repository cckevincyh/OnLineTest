package com.cc.onlinetest.action;


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
		return null;
	}



	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	
}
