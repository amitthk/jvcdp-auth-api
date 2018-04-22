package jvcdp.authapi.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jvcdp.authapi.common.Utility;
import jvcdp.authapi.model.AccountCredentials;
import jvcdp.authapi.model.EmailExistsException;
import jvcdp.authapi.model.UserInfo;
import jvcdp.authapi.service.UserInfoService;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	UserInfoService userInfoService;
	

	@RequestMapping(value="login", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserInfo login(@RequestBody UserInfo userinfo){
		
		UserInfo existing = userInfoService.findByEmail(userinfo.getEmail());
		if(existing!=null) {
		String salt =existing.getSalt();
		String hash = Utility.md5Hash(userinfo.getPasswordhash(), salt);
		if(hash==existing.getPasswordhash())
		{
		Date now = new Date();
		userinfo.setLastlogin(now);
		return existing;
		}
		}
	return null;
	}
	
	@RequestMapping(value="register", method=RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserInfo register(@Valid @RequestBody AccountCredentials userinfo) throws Exception{
		if(null!=userInfoService.findByEmail((userinfo.getUsername()))){
			throw new EmailExistsException("There is an account with that email address");
		}
		try{
			UserInfo newUser= new UserInfo();
			newUser.setUsername(userinfo.getUsername());
			newUser.setEmail(userinfo.getUsername());
			newUser.setProjectid(userinfo.getProjectId());
			Date now = new Date();
			newUser.setCreatedate(now);
			newUser.setLastlogin(now);
			newUser.setLastupdate(now);
			
			//Password hashing
			String salt =Utility.getRandomHash();
			newUser.setPasswordhash(Utility.md5Hash(userinfo.getPassword(), salt));
			newUser.setSalt(salt);

			newUser= userInfoService.addUser(newUser);
			return(newUser);
		}catch(Exception exc){
			throw(exc);
		}
	}
	
}
