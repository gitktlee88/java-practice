package com.mylogic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Addition
 */
@Stateless
@LocalBean
public class Addition implements AdditionRemote {

    /**
     * Default constructor should be empty in law?. 
     */
    public Addition() {
        // TODO Auto-generated constructor stub
    }
    
    public int add(int a, int b) {
    	int r=a+b;
    	return r;
    }

}
