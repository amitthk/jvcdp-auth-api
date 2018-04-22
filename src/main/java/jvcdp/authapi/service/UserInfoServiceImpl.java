package jvcdp.authapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jvcdp.authapi.common.Utility;
import jvcdp.authapi.model.UserInfo;
import jvcdp.authapi.repository.UserInfoMapper;

@Service
@Component
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public List<UserInfo> findAll() {
		return userInfoMapper.selectAll();
	}

	@Override
	public UserInfo addUser(UserInfo userinfo) {
		Long count = userInfoMapper.insert(userinfo);
		userinfo.setId(count);
		return (userinfo);
	}

	@Override
	public UserInfo updateUser(UserInfo userinfo) {
		int count = userInfoMapper.updateByPrimaryKey(userinfo);
		if (count == 1) {
			return (userinfo);
		} else {
			return (null);
		}
	}

	@Override
	public UserInfo findOne(Long id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(UserInfo delUserInfo) {
		return userInfoMapper.deleteByPrimaryKey(delUserInfo.getId());
	}

	@Override
	public UserInfo findByEmail(String email) {
		return userInfoMapper.selectByEmail(email);
	}

	@Override
	public boolean authenticate(String name, String password) {
		UserInfo existing = userInfoMapper.selectByEmail(name);
		if (existing != null) {
			String salt = existing.getSalt();
			String hash = Utility.md5Hash(password, salt);
			if (existing.getPasswordhash().equalsIgnoreCase(hash)) {
				return true;
			}
		}
		return false;
	}

}
