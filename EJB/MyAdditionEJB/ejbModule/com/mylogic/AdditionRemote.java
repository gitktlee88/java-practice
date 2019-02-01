package com.mylogic;

import javax.ejb.Remote;

@Remote
public interface AdditionRemote {
	public int add(int a, int b);
}
