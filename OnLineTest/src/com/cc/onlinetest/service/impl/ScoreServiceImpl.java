package com.cc.onlinetest.service.impl;

import com.cc.onlinetest.dao.ScoreDao;
import com.cc.onlinetest.domain.Score;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.service.ScoreService;


public class ScoreServiceImpl implements ScoreService{

	private ScoreDao scoreDao;
	
	/**
	 * @param scoreDao the scoreDao to set
	 */
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Override
	public Score getScore(Student student, Subject subject) {
		// TODO Auto-generated method stub
		return scoreDao.getScore(student,subject);
	}

}
