package com.bilgeadam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.bilgeadam.dao.RoleDao;
import com.bilgeadam.dao.RuleDao;
import com.bilgeadam.dao.UserDao;
import com.bilgeadam.entity.Gender;
import com.bilgeadam.entity.Role;
import com.bilgeadam.entity.Rule;
import com.bilgeadam.entity.User;
import com.bilgeadam.entity.UserDetail;

public class Test {

	public static void main(String[] args) {
		
		List<Rule> ruleList = new ArrayList<>();
		
		Rule rule = new Rule();
		Rule rule2 = new Rule();
		
		rule.setRule("LOGIN");
		rule.setDescription("Authorization for logging in");
		
		rule2.setRule("PROFILE_UPDATE");
		rule2.setDescription("Updating profile settings");
		
		RuleDao ruleDao = new RuleDao();
		ruleDao.create(rule2);
		ruleDao.create(rule);
		
		ruleList.add(rule);
		ruleList.add(rule2);
		
		//-------------------------//
		
		Role role = new Role();
		Role role2 = new Role();
		
		role.setRole("Student");
		role.setRules(ruleList);
		role2.setRole("Teacher");
		role2.setRules(ruleList);
		
		RoleDao roleDao = new RoleDao();
		
		roleDao.create(role2);
		roleDao.create(role);
		//-------------------------//
		UserDetail userDetail = new UserDetail();
		FileInputStream input;
		try {
			
			input = new FileInputStream(new File("C:\\Users\\cturk\\BilgeAdamJava2DB\\hibenateallconnections\\maticpunk.jpg"));
			userDetail.setPicture(IOUtils.toByteArray(input));

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		userDetail.setFirstName("Cagri");
		userDetail.setGender(Gender.WOMAN);
		
		
		//------------------------//
		
		User user = new User();
		User user2 = new User();
		
		user.setRole(role);
		user.setEmail("cagri@ogrenci");
		user.setPassword("123");
		user.setUserDetail(userDetail);
		
		user2.setRole(role2);
		user2.setEmail("cagri@trainer");
		user2.setPassword("321");
		
		UserDao userDao = new UserDao();
		userDao.create(user2);
		userDao.create(user);
		
		userDao.listAll();
		
		
		
		
		
		
		
		
		
		
	}

}
