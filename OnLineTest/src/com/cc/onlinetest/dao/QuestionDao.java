package com.cc.onlinetest.dao;

import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Judge;

public interface QuestionDao {

	boolean addChoice(Choice choice);

	boolean addJudge(Judge judge);

}
