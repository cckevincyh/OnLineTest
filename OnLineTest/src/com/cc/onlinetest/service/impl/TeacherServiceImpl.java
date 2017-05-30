package com.cc.onlinetest.service.impl;

import com.cc.onlinetest.dao.TeacherDao;
import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Teacher;
import com.cc.onlinetest.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{

	private TeacherDao teacherDao;

	/**
	 * @param teacherDao the teacherDao to set
	 */
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Override
	public Teacher getTeacherById(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.getTeacherById(teacher);
	}

	@Override
	public Teacher updateTeacher(Teacher updateTeacher) {
		// TODO Auto-generated method stub
		return teacherDao.updateTeacher(updateTeacher);
	}

	@Override
	public boolean addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.addTeacher(teacher);
	}

	@Override
	public PageBean<Teacher> findTeacherByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return teacherDao.findTeacherByPage(pageCode,pageSize);
	}

	@Override
	public PageBean<Teacher> queryTeacher(Teacher teacher, int pageCode,
			int pageSize) {
		// TODO Auto-generated method stub
		return teacherDao.queryTeacher(teacher,pageCode,pageSize);
	}

	@Override
	public boolean deleteTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.deleteTeacher(teacher);
	}

	@Override
	public Teacher updateTeacherInfo(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.updateTeacherInfo(teacher);
	}
	
	
}
