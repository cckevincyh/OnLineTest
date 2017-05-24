package com.cc.onlinetest.service;

import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Judge;

public interface QuestionService {

	boolean addChoice(Choice choice);

	boolean addJudge(Judge judge);

	Choice getChoiceById(Choice choice);

	Judge getJudgeById(Judge judge);

}
