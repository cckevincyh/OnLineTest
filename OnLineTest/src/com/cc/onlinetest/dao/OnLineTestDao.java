package com.cc.onlinetest.dao;

import com.cc.onlinetest.domain.Answer;
import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Judge;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;

public interface OnLineTestDao {


	boolean addAnswer(Answer an);

	Answer getChoiceAnswer(Student student, Subject subject, Choice choice);

	Answer getJudgeAnswer(Student student, Subject subject, Judge judge);

}
