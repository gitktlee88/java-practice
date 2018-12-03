package com.mycode.dao;

import java.awt.Panel;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.mycode.vo.Word;
  
public class JDBCJavaDicDAO implements JavaDicDAO {
	
	Connection conn = null;
	private static PreparedStatement statement;
	private static JPanel textPanel;
	public static JTextArea textArea;
	
	public JDBCJavaDicDAO(JTextArea textAreaParam) {
		textArea = textAreaParam;
	}
	     
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

    //public void sqlQueryDisplay(String s, JTextArea textArea) {
    public void sqlQueryDisplay(String s) {
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
            
            //closeConnection();
            
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

            combo.removeAllItems();
            vectorCombo.clear();            
            
            PreparedStatement statement = conn.prepareStatement("select word FROM words");

            // start a transaction
            conn.setAutoCommit(false);

            // get records from the words table
            ResultSet rs = statement.executeQuery();
            
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


	@Override
	//public void sqlQueryDelete(JComboBox combo, String s, JTextArea textArea,  Vector vectorCombo) {
	public void sqlQueryDelete(JComboBox combo, String s, Vector vectorCombo) {	
		// TODO Auto-generated method stub
		try {
			statement = conn.prepareStatement("DELETE FROM words WHERE word=?");		
			statement.setString(1, s);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e);
		}
        textArea.setText("");
        JOptionPane.showMessageDialog(null, s + " delete completed");
       sqlQueryList(combo, vectorCombo);
	}


	@Override
	//public void sqlQueryAdd(JComboBox combo, String name,  JTextArea textArea, Vector vectorCombo) {
	public void sqlQueryAdd(JComboBox combo, String name, Vector vectorCombo) {
		// TODO Auto-generated method stub
		try {
			statement = conn.prepareStatement("INSERT INTO words VALUES(?, ?)");		
			statement.setString(1, name);
			statement.setString(2, "put contents here & click Update!");   
	        // add a word into the words table
	        statement.executeUpdate();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e);
		}
        
        sqlQueryList(combo, vectorCombo);
        combo.setSelectedItem(name);
        //JOptionPane.showMessageDialog(null, name + " needs to be updated with contents");
        sqlQueryDisplay(name);
        
	}

	@Override
	//public void sqlQueryUpdate(JComboBox combo, String name, JTextArea textArea, Vector vectorCombo) {
	public void sqlQueryUpdate(JComboBox combo, String name, Vector vectorCombo) {
		// TODO Auto-generated method stub
		String strings = textArea.getText();
		
		try {
			statement = conn.prepareStatement("UPDATE words SET descr = ? WHERE word = ?");
		
			if (strings.equals("")) {
		        JOptionPane.showMessageDialog(null, name + " contents are empty!!");
		        return;
		    } //no value check
	
		    statement.setString(1, strings);
		    statement.setString(2, name);
	
		    // add a word into the words table
		    statement.executeUpdate();
		    
		    sqlQueryList(combo, vectorCombo);
		    combo.setSelectedItem(name);
		    JOptionPane.showMessageDialog(null, name + " update completed");
		    sqlQueryDisplay(name);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e);
		}
	}
 
}