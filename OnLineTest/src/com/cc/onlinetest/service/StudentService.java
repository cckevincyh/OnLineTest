package com.cc.onlinetest.service;

import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.domain.Student;

public interface StudentService {

	Student getStudentById(Student student);

	Student updateStudent(Student updateStudent);

	boolean addStudent(Student student);

	PageBean<Student> findStudentByPage(int pageCode, int pageSize);

	PageBean<Student> queryStudent(Student student, int pageCode, int pageSize);

	boolean deleteStudent(Student student);

	Student updateStudentInfo(Student student);

}
