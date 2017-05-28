package com.cc.onlinetest.service;

import com.cc.onlinetest.domain.Score;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;

public interface ScoreService {

	Score getScore(Student student,Subject subject);
}
