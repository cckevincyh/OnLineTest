package com.cc.onlinetest.dao;

import com.cc.onlinetest.domain.Score;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;

public interface ScoreDao {

	boolean addScore(Score score);

	Score getScore(Student student, Subject subject);
}
