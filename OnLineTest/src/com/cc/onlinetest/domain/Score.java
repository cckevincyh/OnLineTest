package com.cc.onlinetest.domain;

public class Score {

	private Integer scoreId;
	private Student student;
	private Subject subject;
	private Integer choiceScore;
	private Integer judgeScore;
	private Integer allScore;
	/**
	 * @return the scoreId
	 */
	public Integer getScoreId() {
		return scoreId;
	}
	/**
	 * @param scoreId the scoreId to set
	 */
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
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