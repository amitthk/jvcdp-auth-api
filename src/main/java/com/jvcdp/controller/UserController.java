package com.jvcdp.controller;

import java.util.List;

import com.jvcdp.model.AppUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvcdp.repository.ApplicationUserRepository;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@GetMapping(value = "")
	public List<AppUser> list() {
		return applicationUserRepository.findAll();
	}

	@GetMapping(value = "/username/{userName}")
	public List<AppUser> ApplicationUsersByUserName(@PathVariable String userName) {
		return applicationUserRepository.getApplicationUsersByUserName(userName);
	}

	@PostMapping(value = "")
	public AppUser create(@RequestBody AppUser AppUser) {
		return applicationUserRepository.saveAndFlush(AppUser);
	}

	@GetMapping(value = "/{id}")
	public AppUser get(@PathVariable Long id) {
		return applicationUserRepository.findOne(id);
	}

	@PutMapping(value = "/{id}")
	public AppUser update(@PathVariable Long id, @RequestBody AppUser AppUser) {
		AppUser existingApplicationUser = applicationUserRepository.findOne(id);
		BeanUtils.copyProperties(AppUser, existingApplicationUser);
		return applicationUserRepository.saveAndFlush(existingApplicationUser);
	}

	@DeleteMapping(value = "/{id}")
	public AppUser delete(@PathVariable Long id) {
		AppUser existingApplicationUser = applicationUserRepository.findOne(id);
		applicationUserRepository.delete(existingApplicationUser);
		return existingApplicationUser;
	}
	
}
