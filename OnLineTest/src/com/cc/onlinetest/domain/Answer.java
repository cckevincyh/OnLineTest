package com.cc.onlinetest.domain;

public class Answer {

	private Integer answerId;
	private Student student;
	private Subject subject;
	private Integer question;
	private Integer questionType;
	private String answer;
	private String goodAnswer;
	private Integer score;
	/**
	 * @return the answerId
	 */
	public Integer getAnswerId() {
		return answerId;
	}
	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
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
	 * @return the question
	 */
	public Integer getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(Integer question) {
		this.question = question;
	}
	/**
	 * @return the questionType
	 */
	public Integer getQuestionType() {
		return questionType;
	}
	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
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
	/**
	 * @return the goodAnswer
	 */
	public String getGoodAnswer() {
		return goodAnswer;
	}
	/**
	 * @param goodAnswer the goodAnswer to set
	 */
	public void setGoodAnswer(String goodAnswer) {
		this.goodAnswer = goodAnswer;
	}
	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", student=" + student
				+ ", subject=" + subject + ", question=" + question
				+ ", questionType=" + questionType + ", answer=" + answer
				+ ", goodAnswer=" + goodAnswer + ", score=" + score + "]";
	}
	
	
	
	
	
}
