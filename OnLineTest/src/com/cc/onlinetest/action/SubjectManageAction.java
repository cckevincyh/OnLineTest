package com.cc.onlinetest.action;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.onlinetest.domain.Course;
import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Score;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.service.ScoreService;
import com.cc.onlinetest.service.StudentService;
import com.cc.onlinetest.service.SubjectService;
import com.cc.onlinetest.utils.Md5Utils;
import com.opensymphony.xwork2.ActionSupport;

public class SubjectManageAction extends ActionSupport{

	private SubjectService subjectService;
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



	/**
	 * @param subjectService the subjectService to set
	 */
	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	private int pageCode;
	
	private String subjectName;
	private int courseId;
	private int subjectTime;
	private int choiceScore;
	private int judgeScore;
	private int subjectId;
	
	
	
	
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}



	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}



	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}



	/**
	 * @param subjectTime the subjectTime to set
	 */
	public void setSubjectTime(int subjectTime) {
		this.subjectTime = subjectTime;
	}



	/**
	 * @param choiceScore the choiceScore to set
	 */
	public void setChoiceScore(int choiceScore) {
		this.choiceScore = choiceScore;
	}



	/**
	 * @param judgeScore the judgeScore to set
	 */
	public void setJudgeScore(int judgeScore) {
		this.judgeScore = judgeScore;
	}



	/**
	 * @param pageCode the pageCode to set
	 */
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}



	public String findSubjectByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<Subject> pb = subjectService.findSubjectByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findSubjectByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	
	
	public String addSubject(){
		Subject subject = new Subject();
		subject.setSubjectName(subjectName);
		Subject subject2 = subjectService.getSubjectByName(subject);
		int success = 0;
		if(subject2!=null){
			success = -1;//已经存在试卷
		}else{
			subject.setChoiceScore(choiceScore);
			subject.setJudgeScore(judgeScore);
			subject.setSubjectTime(subjectTime);
			Course course = new Course();
			course.setCourseId(courseId);
			subject.setCourse(course);
			boolean b = subjectService.addSubject(subject);
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
	
	
	
	
	public String getSubject(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		Subject newSubject = subjectService.getSubjectById(subject);
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
		
		JSONObject jsonObject = JSONObject.fromObject(newSubject,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	public String updateSubject(){
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		Subject updateSubject = subjectService.getSubjectById(subject);
		updateSubject.setSubjectName(subjectName);
		updateSubject.setSubjectTime(subjectTime);
		updateSubject.setChoiceScore(choiceScore);
		updateSubject.setJudgeScore(judgeScore);
		int success = 0;
		Subject newSubject = subjectService.updateSubject(updateSubject);//修改该课程对象
		if(newSubject!=null){
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
	
	
	
	public String deleteSubject(){
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		boolean deleteSubject = subjectService.deleteSubject(subject);
		int success = 0;
		if(deleteSubject){
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
	
	
	public String querySubject(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<Subject> pb = null;
		if(courseId==-1 && "".equals(subjectName.trim())){
			pb = subjectService.findSubjectByPage(pageCode,pageSize);
		}else{
			Subject subject = new Subject();
			subject.setSubjectName(subjectName);
			Course course = new Course();
			course.setCourseId(courseId);
			subject.setCourse(course);
			pb = subjectService.querySubject(subject,pageCode,pageSize);
			
		} 
		if(pb!=null){
			pb.setUrl("querySubject.action?courseId="+courseId+"&subjectName="+subjectName+"&");
		}

		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
	
	
	public String questionManage(){
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		Subject newSubject = subjectService.getSubjectById(subject);
		
		
		
		ServletActionContext.getRequest().setAttribute("subject", newSubject);
		return "question";
	}
	
	
	
	
	/**
	 * 得到试卷下的所有试题
	 * @return
	 */
	public String getQuestions(){
		Subject subject = new Subject();
		subject.setSubjectId(subjectId);
		Subject newSubject = subjectService.getSubjectById(subject);
		Student student = (Student) ServletActionContext.getContext().getSession().get("student");
		
		Score score = scoreService.getScore(student, subject);
		if(score!=null){
			//该试卷已经做过了
			return null;
		}
		//判断是否正在做试卷
		Student studentById = studentService.getStudentById(student);
		//锁住的状态是否等于当前科目,除了当前科目可以继续考试，不能进行其他考试
		if(!(studentById.getLockState().equals(subject.getSubjectId()) || studentById.getLockState().equals(0))){
			return null;
		}
		ServletActionContext.getRequest().setAttribute("subject", newSubject);
		return "questions";
	}
	
}
