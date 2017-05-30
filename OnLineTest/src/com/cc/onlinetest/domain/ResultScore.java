package com.cc.onlinetest.domain;

import java.util.List;


public class ResultScore {

	private List<ChoiceAnswer> choiceAnswers;
	private List<JudgeAnswer> judgeAnswers;
	private Score score;
	/**
	 * @return the choiceAnswers
	 */
	public List<ChoiceAnswer> getChoiceAnswers() {
		return choiceAnswers;
	}
	/**
	 * @param choiceAnswers the choiceAnswers to set
	 */
	public void setChoiceAnswers(List<ChoiceAnswer> choiceAnswers) {
		this.choiceAnswers = choiceAnswers;
	}
	/**
	 * @return the judgeAnswers
	 */
	public List<JudgeAnswer> getJudgeAnswers() {
		return judgeAnswers;
	}
	/**
	 * @param judgeAnswers the judgeAnswers to set
	 */
	public void setJudgeAnswers(List<JudgeAnswer> judgeAnswers) {
		this.judgeAnswers = judgeAnswers;
	}
	/**
	 * @return the score
	 */
	public Score getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Score score) {
		this.score = score;
	}
	
	
	
	
	
	
	
}
