package com.cc.onlinetest.service.impl;


import java.util.List;

import com.cc.onlinetest.dao.AdminDao;
import com.cc.onlinetest.domain.Admin;
import com.cc.onlinetest.service.AdminService;


public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin getAdminByUserName(Admin admin) {
		return adminDao.getAdminByUserName(admin);
	}

	@Override
	public Admin updateAdminInfo(Admin admin) {
		return adminDao.updateAdminInfo(admin);
	}


	@Override
	public boolean addAdmin(Admin admin) {
		return adminDao.addAdmin(admin);
	}

	@Override
	public Admin getAdminById(Admin admin) {
		return adminDao.getAdminById(admin);
	}

	
}
