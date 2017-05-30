package com.cc.onlinetest.service;

import java.util.List;

import com.cc.onlinetest.domain.ResultScore;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;

public interface OnLineTestService {

	boolean onLineTest(Student student, Subject subject, String answer);

	ResultScore getResultScore(Student student, Subject subject);
}
