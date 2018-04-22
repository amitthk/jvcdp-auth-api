package com.jvcdp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.jvcdp.model.AppUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.jvcdp.controller.UserController;
import com.jvcdp.repository.ApplicationUserRepository;

public class UserControllerTest {
	@InjectMocks
	private UserController sc;

	@Mock
	private ApplicationUserRepository applicationUserRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testUserGet() {
		AppUser sw = new AppUser();
    	sw.setId(1l);
		when(applicationUserRepository.findOne(1l)).thenReturn(sw);

		AppUser u = sc.get(1L);

		verify(applicationUserRepository).findOne(1l);

//		assertEquals(1l, wreck.getId().longValue());	
		assertThat(u.getId(), is(1l));
	}

}
