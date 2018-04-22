package jvcdp.authapi.service;

import java.util.List;
import jvcdp.authapi.model.UserInfo;


public interface UserInfoService {

	List<UserInfo> findAll();

	UserInfo addUser(UserInfo userinfo);

	UserInfo updateUser(UserInfo userinfo);

	UserInfo findOne(Long id);

	int delete(UserInfo delUserInfo);

	UserInfo findByEmail(String email);

	boolean authenticate(String name, String password);
	
}
