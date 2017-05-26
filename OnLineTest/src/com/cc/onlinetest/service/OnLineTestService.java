package com.cc.onlinetest.service;

import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;

public interface OnLineTestService {

	boolean onLineTest(Student student, Subject subject, String answer);

}
