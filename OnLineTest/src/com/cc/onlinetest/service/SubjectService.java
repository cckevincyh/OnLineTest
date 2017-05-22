package com.cc.onlinetest.service;

import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.domain.Teacher;

public interface SubjectService {

	PageBean<Subject> findSubjectByPage(int pageCode, int pageSize);

	Subject getSubjectByName(Subject subject);

	boolean addSubject(Subject subject);

	Subject getSubjectById(Subject subject);

	Subject updateSubject(Subject updateSubject);

	boolean deleteSubject(Subject subject);

	PageBean<Subject> querySubject(Subject subject, int pageCode, int pageSize);

}
