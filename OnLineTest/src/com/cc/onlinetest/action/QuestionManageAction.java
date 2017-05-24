package com.cc.onlinetest.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Judge;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.service.QuestionService;
import com.opensymphony.xwork2.ActionSupport;

public class QuestionManageAction extends ActionSupport{

	private QuestionService questionService;


	/**
	 * @param questionService the questionService to set
	 */
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	private int subjectId;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String answer;
	
	
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @param optionA the optionA to set
	 */
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}


	/**
	 * @param optionB the optionB to set
	 */
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}


	/**
	 * @param optionC the optionC to set
	 */
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}


	/**
	 * @param optionD the optionD to set
	 */
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}


	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}


	public String addChoice(){
		Choice choice = new Choice();
		choice.setQuestion(question);
		choice.setOptionA(optionA);
		choice.setOptionB(optionB);
		choice.setOptionC(optionC);
		choice.setOptionD(optionD);
		choice.setAnswer(answer);
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		choice.setSubject(subject);
		boolean b = questionService.addChoice(choice);
		int success = 0;
		if(b){
			success = 1;
		}else{
			success = 0;
		
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);//向浏览器响应是否成功的状态码
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	
	public String addJudge(){
		Judge judge = new Judge();
		judge.setQuestion(question);
		judge.setAnswer(answer);
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		judge.setSubject(subject);
		boolean b = questionService.addJudge(judge);
		int success = 0;
		if(b){
			success = 1;
		}else{
			success = 0;
		
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);//向浏览器响应是否成功的状态码
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
}
