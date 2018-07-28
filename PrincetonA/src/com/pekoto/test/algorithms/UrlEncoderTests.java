package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.pekoto.algorithms.UrlEncoder;

public class UrlEncoderTests {

	@Test
	public void testUrlEncoder() {
		UrlEncoder encoder = new UrlEncoder();
		
		String short1 = encoder.encode("google.com");
		String short2 = encoder.encode("amazon.com");
		String short3 = encoder.encode("facebook.com");
		
		assertFalse(short1.equals(short2));
		assertFalse(short2.equals(short3));
		assertFalse(short1.equals(short3));
		assertEquals("google.com", encoder.decode(short1));
		assertEquals("amazon.com", encoder.decode(short2));
		assertEquals("facebook.com", encoder.decode(short3));
	}
}
