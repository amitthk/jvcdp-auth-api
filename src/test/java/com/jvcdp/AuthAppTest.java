package com.jvcdp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.jvcdp.controller.AuthController;

public class AuthAppTest {

	@Test
    public void testApp() {
		AuthController hc = new AuthController();
		String result = hc.home();
        assertEquals( result, "Welcome to jvcdp auth api!" );
	}
}
