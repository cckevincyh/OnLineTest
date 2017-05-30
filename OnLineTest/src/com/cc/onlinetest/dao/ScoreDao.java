package com.cc.onlinetest.dao;

import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Score;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;

public interface ScoreDao {

	boolean addScore(Score score);

	Score getScore(Student student, Subject subject);

	PageBean<Score> findScoreByPage(int pageCode, int pageSize);

	PageBean<Score> queryMyScore(Subject subject, int pageCode, int pageSize);

	PageBean<Score> findMyScoreByPage(Student student, int pageCode,
			int pageSize);

	PageBean<Score> queryScore(Subject subject, Student student, int pageCode,
			int pageSize);
}
