package com.mycode.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import com.mycode.vo.Word;
  
public class JDBCJavaDicDAO implements JavaDicDAO {
	
	Connection conn = null;
	     
    public void closeConnection(){
        try {
              if (conn != null) {
                  conn.close();
              }
            } catch (Exception e) { 
                //do nothing
            }
    }
    
    
    public Connection getConnection() throws Exception {
        String driver = "org.gjt.mm.mysql.Driver";
        //String driver = "com.mysql.Driver";
        String url = "jdbc:mysql://localhost/mydic";
        String username = "root";
        String password = "nbuser";
        Class.forName(driver); // load MySQL driver
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public void sqlQueryDisplay(String s, JTextArea textArea) {
        try {
        	//if(conn == null) conn = getConnection();
        	conn = getConnection();
        	
            // create a statement: This object will be used for executing
            // a static SQL statement and returning the results it produces.
            PreparedStatement statement = conn.prepareStatement("select * from words where word=?");

            statement.setString(1, s);
            // start a transaction
            conn.setAutoCommit(false);

            // get descr records from the words table
            ResultSet resultSet = statement.executeQuery();

            textArea.selectAll();
            textArea.setText("");
            while (resultSet.next()) {
                textArea.setText(resultSet.getString(2));
            }

        } catch (Exception ex) {
            //Logger.getLogger(JavaDicMain.class.getName()).log(Level.SEVERE, null, ex);
        	System.out.print(ex);
        }
    }

    public void sqlQueryList(JComboBox combo, Vector vectorCombo) {

    	try {
        	if(conn == null) conn = getConnection();

            // create a statement: This object will be used for executing
            // a static SQL statement and returning the results it produces.
            Statement stmt = conn.createStatement();

            // start a transaction
            conn.setAutoCommit(false);

            combo.removeAllItems();
            vectorCombo.clear();

            // get all of the the records from the wordstable           
            ResultSet rs = stmt.executeQuery("SELECT word FROM words");
            
            // iterate the result set and get one row at a time
            while (rs.next()) {
                //combo.addItem(rs.getString(1));  // 1st column in query
                vectorCombo.add(rs.getString(1));
            }

            // set auto commit to true (from now on every single
            // statement will be treated as a single transaction
            conn.setAutoCommit(true);

            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
            }

        } catch (Exception e) {
            // something went wrong, we are handling the exception here    
        	System.out.print(e);
        } finally {        	
            // close db resources 
        	//System.out.print(combo.getItemCount());
        }

    }
 
}