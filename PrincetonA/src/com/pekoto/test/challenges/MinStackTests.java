package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.challenges.MinStack;

public class MinStackTests {
	
	@Test
	public void testMinStack() {
		MinStack ms = new MinStack();
		
		ms.push(5);
		ms.push(1);
		ms.push(7);
		ms.pop();
		ms.pop();
		assertEquals(5, ms.getMin());
		
		ms.push(512);
		ms.push(-1024);
		ms.push(-1024);
		ms.push(512);
		
		ms.pop();
		
		// -1024
		assertEquals(-1024, ms.getMin());
		
		ms.pop();
		
		// -1024
		assertEquals(-1024, ms.getMin());
		
		ms.pop();
		
		// 512
		assertEquals(512, ms.getMin());
	}
}
