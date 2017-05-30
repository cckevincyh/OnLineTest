package com.cc.onlinetest.service.impl;

import com.cc.onlinetest.dao.StudentDao;
import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.service.StudentService;

public class StudentServiceImpl implements StudentService{

	private StudentDao studentDao;

	/**
	 * @param studentDao the studentDao to set
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public Student getStudentById(Student student) {
		// TODO Auto-generated method stub
		return studentDao.getStudentById(student);
	}

	@Override
	public Student updateStudent(Student updateStudent) {
		// TODO Auto-generated method stub
		return studentDao.updateStudent(updateStudent);
	}

	@Override
	public boolean addStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDao.addStudent(student);
	}

	@Override
	public PageBean<Student> findStudentByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return studentDao.findStudentByPage(pageCode,pageSize);
	}

	@Override
	public PageBean<Student> queryStudent(Student student, int pageCode,
			int pageSize) {
		// TODO Auto-generated method stub
		return studentDao.queryStudent(student,pageCode,pageSize);
	}

	@Override
	public boolean deleteStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDao.deleteStudent(student);
	}

	@Override
	public Student updateStudentInfo(Student student) {
		// TODO Auto-generated method stub
		return studentDao.updateStudentInfo(student);
	}
	
	
	
}
