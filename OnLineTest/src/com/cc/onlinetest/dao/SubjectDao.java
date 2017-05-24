package com.cc.onlinetest.dao;

import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Judge;
import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Subject;

public interface SubjectDao {

	PageBean<Subject> findSubjectByPage(int pageCode, int pageSize);

	Subject getSubjectByName(Subject subject);

	boolean addSubject(Subject subject);

	Subject getSubjectById(Subject subject);

	Subject updateSubject(Subject updateSubject);

	boolean deleteSubject(Subject subject);

	PageBean<Subject> querySubject(Subject subject, int pageCode, int pageSize);

	boolean setChoiceNum(Subject subject);

	boolean setJudgeNum(Subject subject);

	boolean setAllScore(Subject subject);

}
