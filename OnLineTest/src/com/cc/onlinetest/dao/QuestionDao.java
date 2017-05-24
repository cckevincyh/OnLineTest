package com.cc.onlinetest.dao;

import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Judge;

public interface QuestionDao {

	boolean addChoice(Choice choice);

	boolean addJudge(Judge judge);

	Choice getChoiceById(Choice choice);

	Judge getJudgeById(Judge judge);

	Choice updateChoice(Choice choice);

	Judge updateJudge(Judge judgeById);

	boolean deleteChoice(Choice choice);

	boolean deleteJudge(Judge judge);

}
