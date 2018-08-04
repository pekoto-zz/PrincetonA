package com.pekoto.challenges;

import java.util.Stack;

public class MinStack {
	
	private int min;
	private Stack<Integer> stack;

	/** initialize your data structure here. */
	public MinStack() {
		stack = new Stack<Integer>();
		min = Integer.MAX_VALUE;
	}

	public void push(int x) {
		if(x <= min) {
			stack.push(min);
			min = x;
		}
		
		stack.push(x);
	}

	public void pop() {
		if (stack.pop() == min) {
			min = stack.pop();
		}
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return min;
	}
}
