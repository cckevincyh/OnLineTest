package com.cc.onlinetest.domain;

public class Judge {

	private Integer judgeId;
	private Subject subject;
	private String question;
	private String answer;
	
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the judgeId
	 */
	public Integer getJudgeId() {
		return judgeId;
	}
	/**
	 * @param judgeId the judgeId to set
	 */
	public void setJudgeId(Integer judgeId) {
		this.judgeId = judgeId;
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
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
