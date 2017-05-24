package com.cc.onlinetest.action;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

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
	private int choiceId;
	private int judgeId;
	
	
	
	/**
	 * @param choiceId the choiceId to set
	 */
	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}

	/**
	 * @param judgeId the judgeId to set
	 */
	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}

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
	
	
	
	public String getChoice(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Choice choice = new Choice();
		choice.setChoiceId(choiceId);
		Choice newChoice = questionService.getChoiceById(choice);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Set||name.equals("subjects") || name.equals("choices") || name.equals("judges")){//过滤掉集合
				return true;
			}else{
				return false;
			}
		   }
		});
		
		JSONObject jsonObject = JSONObject.fromObject(newChoice,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	
	public String getJudge(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Judge judge = new Judge();
		judge.setJudgeId(judgeId);
		Judge newJudge = questionService.getJudgeById(judge);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Set||name.equals("subjects") || name.equals("choices") || name.equals("judges")){//过滤掉集合
				return true;
			}else{
				return false;
			}
		   }
		});
		
		JSONObject jsonObject = JSONObject.fromObject(newJudge,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	
	
	public String updateChoice(){
		Choice choice = new Choice();
		choice.setChoiceId(choiceId);
		Choice choiceById = questionService.getChoiceById(choice);
		choiceById.setQuestion(question);
		choiceById.setOptionA(optionA);
		choiceById.setOptionB(optionB);
		choiceById.setOptionC(optionC);
		choiceById.setOptionD(optionD);
		choiceById.setAnswer(answer);
		Choice updateChoice = questionService.updateChoice(choiceById);
		int success = 0;
		if(updateChoice!=null){
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
	
	
	public String updateJudge(){
		Judge judge = new Judge();
		judge.setJudgeId(judgeId);
		Judge judgeById = questionService.getJudgeById(judge);
		judgeById.setQuestion(question);
		judgeById.setAnswer(answer);
		Judge updateJudge = questionService.updateJudge(judgeById);
		int success = 0;
		if(updateJudge!=null){
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
	
	
	public String deleteChoice(){
		Choice choice = new Choice();
		choice.setChoiceId(choiceId);
		boolean deleteChoice = questionService.deleteChoice(choice);
		int success = 0;
		if(deleteChoice){
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
	
	
	public String deleteJudge(){
		Judge judge = new Judge();
		judge.setJudgeId(judgeId);
		boolean deleteJudge = questionService.deleteJudge(judge);
		int success = 0;
		if(deleteJudge){
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
