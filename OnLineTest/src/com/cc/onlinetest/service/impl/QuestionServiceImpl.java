package com.cc.onlinetest.service.impl;

import com.cc.onlinetest.dao.QuestionDao;
import com.cc.onlinetest.dao.SubjectDao;
import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Judge;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.service.QuestionService;

public class QuestionServiceImpl implements QuestionService{

	private QuestionDao questionDao;
	private SubjectDao subjectDao;
	
	/**
	 * @param subjectDao the subjectDao to set
	 */
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	/**
	 * @param questionDao the questionDao to set
	 */
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public boolean addChoice(Choice choice) {
		// TODO Auto-generated method stub
		boolean b = questionDao.addChoice(choice);
		//同时需要对subject的choiceNum进行修改
		subjectDao.setChoiceNum(choice.getSubject());
		//同时需要对总分进行修改
		subjectDao.setAllScore(choice.getSubject());
		return b;
	}

	@Override
	public boolean addJudge(Judge judge) {
		// TODO Auto-generated method stub
		boolean b = questionDao.addJudge(judge);
		//同时需要对subject的judgeNum进行修改
		subjectDao.setJudgeNum(judge.getSubject());
		//同时需要对总分进行修改
		subjectDao.setAllScore(judge.getSubject());
		return b;
	}

	@Override
	public Choice getChoiceById(Choice choice) {
		// TODO Auto-generated method stub
		return questionDao.getChoiceById(choice);
	}

	@Override
	public Judge getJudgeById(Judge judge) {
		// TODO Auto-generated method stub
		return questionDao.getJudgeById(judge);
	}

	@Override
	public Choice updateChoice(Choice choice) {
		// TODO Auto-generated method stub
		return questionDao.updateChoice(choice);
	}

	@Override
	public Judge updateJudge(Judge judgeById) {
		// TODO Auto-generated method stub
		return questionDao.updateJudge(judgeById);
	}

	@Override
	public boolean deleteChoice(Choice choice) {
		Choice choice2 = questionDao.getChoiceById(choice);
		boolean b = questionDao.deleteChoice(choice);
		//同时需要对subject的judgeNum进行修改
		subjectDao.setChoiceNum(choice2.getSubject());
		//同时需要对总分进行修改
		subjectDao.setAllScore(choice2.getSubject());
		return b;
	}

	@Override
	public boolean deleteJudge(Judge judge) {
		Judge judge2 = questionDao.getJudgeById(judge);
		boolean b = questionDao.deleteJudge(judge);
		//同时需要对subject的judgeNum进行修改
		subjectDao.setJudgeNum(judge2.getSubject());
		//同时需要对总分进行修改
		subjectDao.setAllScore(judge2.getSubject());
		return b;
	}

	

	
	
}
