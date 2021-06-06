package com.uoc.clockin.app.users.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uoc.clockin.app.users.models.dao.UserDao;
import com.uoc.clockin.app.commons.models.entity.Absence;
import com.uoc.clockin.app.commons.models.entity.User;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserDao userDao;


	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsernameOrEmail(String username, String email) {
		return userDao.findByUsernameOrEmail(username, email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByIdCompany(int idCompany) {
		return (List<User>) userDao.findByIdCompany(idCompany);
	}
	
	@Override
	public List<User> findByTerm(int idCompany, String term) {
		return (List<User>) userDao.findByTerm(idCompany, term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByValidated(Boolean validated) {
		return (List<User>) userDao.findByValidated(validated);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByIdCompanyAndValidated(int idCompany, Boolean validated) {
		return (List<User>) userDao.findByIdCompanyAndValidated(idCompany, validated);
	}

	@Override
	@Transactional
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

}
