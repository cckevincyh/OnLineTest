package com.cc.onlinetest.service;

import com.cc.onlinetest.domain.Course;
import com.cc.onlinetest.domain.PageBean;

public interface CourseService {

	PageBean<Course> findCourseByPage(int pageCode, int pageSize);

	boolean addCourse(Course course);

	Course getCourseByName(Course course);

	Course getCourseById(Course course);

	Course updateCourse(Course updateCourse);

	boolean deleteCourse(Course course);

}
