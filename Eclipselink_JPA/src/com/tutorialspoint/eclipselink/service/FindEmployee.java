package com.tutorialspoint.eclipselink.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.tutorialspoint.eclipselink.entity.Employee;

public class FindEmployee {	

	//private final static  Logger logr = Logger.getLogger("FindEmployee"); //
	//private final static Logger logr = Logger.getLogger(FindEmployee.class.getName());
	private final static  Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	
	public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
      EntityManager entitymanager = emfactory.createEntityManager();
//      Employee employee = entitymanager.find( Employee.class, 1201 );
//
//      System.out.println("employee ID = " + employee.getEid( ));
//      System.out.println("employee NAME = " + employee.getEname( ));
//      System.out.println("employee SALARY = " + employee.getSalary( ));
//      System.out.println("employee DESIGNATION = " + employee.getDeg( ));
      
      //////////////////////////////////
      // call Native SQL query example //
      //////////////////////////////////
      Query q = entitymanager.createNativeQuery("SELECT a.EID, a.ENAME, a.SALARY, a.DEG  FROM employee a");
      List<Object[]> employees = q.getResultList();
      
      /////////////////////////////////
      // java.util.logging.* example     //
      //////////////////////////////// SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
      for (Object[] a : employees) {
    	  logr.info("EID = " + a[0] + "\n" +
    			  			"ENAME = " + a[1] + "\n" +
    	  					"SALARY = " + a[2] + "\n" +
							"DEG = " + a[3]);
      }
      //log manager
      LogManager.getLogManager().reset();
      logr.setLevel(Level.ALL);
      
      //console handler
      ConsoleHandler ch = new ConsoleHandler();
      ch.setLevel(Level.SEVERE);
      logr.addHandler(ch);
      logr.log(Level.SEVERE, "console logging test");
      
      //file handler
      try {
		//FileHandler fh = new FileHandler("myLogger.log");  //overwrite log
		FileHandler fh = new FileHandler("myLogger.log", true);  //append log
		fh.setLevel(Level.FINE);
		logr.addHandler(fh);
		logr.log(Level.INFO, "file logging test");
	} catch (SecurityException | IOException e) {
		logr.log(Level.SEVERE, "File logger not working", e);
	}
      
      
      entitymanager.close( );
      emfactory.close( );
   }
}