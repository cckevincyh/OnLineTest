package com.cc.onlinetest.dao;

import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Teacher;

public interface TeacherDao {

	Teacher getTeacherById(Teacher teacher);

	Teacher updateTeacher(Teacher updateTeacher);

	boolean addTeacher(Teacher teacher);

	PageBean<Teacher> findTeacherByPage(int pageCode, int pageSize);

	PageBean<Teacher> queryTeacher(Teacher teacher, int pageCode, int pageSize);

	boolean deleteTeacher(Teacher teacher);

	Teacher updateTeacherInfo(Teacher teacher);

}
