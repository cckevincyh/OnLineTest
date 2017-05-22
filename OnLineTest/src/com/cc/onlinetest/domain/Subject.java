package com.cc.onlinetest.domain;

import java.util.Set;

public class Subject {

	private Integer subjectId;
	private String subjectName;
	private Integer subjectTime;
	private Integer choiceNum;
	private Integer judgeNum;
	private Integer choiceScore;
	private Integer judgeScore;
	private Integer allScore;
	private Set<Choice> choices;
	private Set<Judge> judges;
	private Course course;
	
	
	
	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	/**
	 * @return the choices
	 */
	public Set<Choice> getChoices() {
		return choices;
	}
	/**
	 * @param choices the choices to set
	 */
	public void setChoices(Set<Choice> choices) {
		this.choices = choices;
	}
	/**
	 * @return the judges
	 */
	public Set<Judge> getJudges() {
		return judges;
	}
	/**
	 * @param judges the judges to set
	 */
	public void setJudges(Set<Judge> judges) {
		this.judges = judges;
	}
	/**
	 * @return the subjectId
	 */
	public Integer getSubjectId() {
		return subjectId;
	}
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * @return the subjectTime
	 */
	public Integer getSubjectTime() {
		return subjectTime;
	}
	/**
	 * @param subjectTime the subjectTime to set
	 */
	public void setSubjectTime(Integer subjectTime) {
		this.subjectTime = subjectTime;
	}
	/**
	 * @return the choiceNum
	 */
	public Integer getChoiceNum() {
		return choiceNum;
	}
	/**
	 * @param choiceNum the choiceNum to set
	 */
	public void setChoiceNum(Integer choiceNum) {
		this.choiceNum = choiceNum;
	}
	/**
	 * @return the judgeNum
	 */
	public Integer getJudgeNum() {
		return judgeNum;
	}
	/**
	 * @param judgeNum the judgeNum to set
	 */
	public void setJudgeNum(Integer judgeNum) {
		this.judgeNum = judgeNum;
	}
	/**
	 * @return the choiceScore
	 */
	public Integer getChoiceScore() {
		return choiceScore;
	}
	/**
	 * @param choiceScore the choiceScore to set
	 */
	public void setChoiceScore(Integer choiceScore) {
		this.choiceScore = choiceScore;
	}
	/**
	 * @return the judgeScore
	 */
	public Integer getJudgeScore() {
		return judgeScore;
	}
	/**
	 * @param judgeScore the judgeScore to set
	 */
	public void setJudgeScore(Integer judgeScore) {
		this.judgeScore = judgeScore;
	}
	/**
	 * @return the allScore
	 */
	public Integer getAllScore() {
		return allScore;
	}
	/**
	 * @param allScore the allScore to set
	 */
	public void setAllScore(Integer allScore) {
		this.allScore = allScore;
	}
	
	
	
	
}
