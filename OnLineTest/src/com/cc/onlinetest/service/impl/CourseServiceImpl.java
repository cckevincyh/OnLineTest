package com.cc.onlinetest.service.impl;

import java.util.List;

import com.cc.onlinetest.dao.AdminDao;
import com.cc.onlinetest.dao.CourseDao;
import com.cc.onlinetest.domain.Course;
import com.cc.onlinetest.domain.PageBean;
import com.cc.onlinetest.service.CourseService;

public class CourseServiceImpl implements CourseService{

	private CourseDao courseDao;
	
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}



	@Override
	public PageBean<Course> findCourseByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return courseDao.findCourseByPage(pageCode,pageSize);
	}



	@Override
	public boolean addCourse(Course course) {
		// TODO Auto-generated method stub
		return courseDao.addCourse(course);
	}



	@Override
	public Course getCourseByName(Course course) {
		// TODO Auto-generated method stub
		return courseDao.getCourseByName(course);
	}



	@Override
	public Course getCourseById(Course course) {
		// TODO Auto-generated method stub
		return courseDao.getCourseById(course);
	}



	@Override
	public Course updateCourse(Course updateCourse) {
		// TODO Auto-generated method stub
		return courseDao.updateCourse(updateCourse);
	}



	@Override
	public boolean deleteCourse(Course course) {
		// TODO Auto-generated method stub
		return courseDao.deleteCourse(course);
	}



	@Override
	public PageBean<Course> queryCourse(Course course, int pageCode,
			int pageSize) {
		// TODO Auto-generated method stub
		return courseDao.queryCourse(course, pageCode,pageSize);
	}



	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return courseDao.getAllCourses();
	}
}
