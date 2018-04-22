package com.jvcdp.controller;

import com.jvcdp.common.Utility;
import com.jvcdp.model.AccountCredentials;
import com.jvcdp.model.AppUser;
import com.jvcdp.model.EmailExistsException;
import com.jvcdp.model.InvalidCredentialsException;
import com.jvcdp.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	ApplicationUserRepository applicationUserRepository;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
	    return ("Welcome to jvcdp auth api!");
    }

	@RequestMapping(value="/login", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String login(@RequestBody AccountCredentials AppUser) throws AuthenticationException{

		if(Utility.authenticate(applicationUserRepository, AppUser.getUserName(), AppUser.getPassword())){
			return ("Login Successfull!");
		}else{
			throw new InvalidCredentialsException("Unable to login!");
		}
	}

	@RequestMapping(value="/register", method=RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AppUser register(@Valid @RequestBody AccountCredentials AppUser) throws Exception{
		if(null!=applicationUserRepository.getApplicationUsersByUserName((AppUser.getUserName()))){
			throw new EmailExistsException("There is an account with that email address");
		}
		try{
			AppUser newUser= new AppUser();
			newUser.setUserName(AppUser.getUserName());
			newUser.setEmail(AppUser.getUserName());
			newUser.setApiId(AppUser.getApiId());

			//Password hashing
			String salt =Utility.getRandomHash();
			newUser.setPasswordHash(Utility.md5Hash(AppUser.getPassword(), salt));
			newUser.setSalt(salt);
			newUser= applicationUserRepository.saveAndFlush(newUser);
			return(newUser);
		}catch(Exception exc){
			throw(exc);
		}
	}

}
