package com.jvcdp;

import java.util.List;

import com.jvcdp.model.AppUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jvcdp.repository.ApplicationUserRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Test
	public void testFindAll() {
		List<AppUser> us = applicationUserRepository.findAll();
		assertThat(us.size(), is(greaterThanOrEqualTo(0)));
	}
	
}
