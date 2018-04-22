package com.jvcdp.controller;

import com.jvcdp.common.Utility;
import com.jvcdp.model.*;
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
	public String login(@RequestBody AccountCredentials appUser) throws AuthenticationException{

		if(Utility.authenticate(applicationUserRepository, appUser.getUserName(), appUser.getPassword())){
		    //Update the login timestamp here
			return ("Login Successfull!");
		}else{
			throw new InvalidCredentialsException("Unable to login!");
		}
	}

	@RequestMapping(value="/register", method=RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AppUser register(@Valid @RequestBody AccountCredentials appUser) throws AuthenticationException{
		if(applicationUserRepository.countApplicationUsersByEmail((appUser.getEmailAddress()))>0){
			throw new EmailExistsException("There is an account with that email address");
		}

		if(applicationUserRepository.countApplicationUsersByUserName(appUser.getUserName())>0){
		    throw new UserNameAlreadyAllotted("This UserName is already taken!");
        }

		try{
			AppUser newUser= new AppUser();
			newUser.setUserName(appUser.getUserName());
			newUser.setEmail(appUser.getEmailAddress());
			newUser.setApiId(appUser.getApiId());

			//Password hashing
			String salt =Utility.getRandomHash();
			newUser.setPasswordHash(Utility.md5Hash(appUser.getPassword(), salt));
			newUser.setSalt(salt);
			newUser= applicationUserRepository.saveAndFlush(newUser);
			return(newUser);
		}catch(Exception exc){
			throw(exc);
		}
	}

}
