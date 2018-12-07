import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.mycode.dao.JDBCJavaDicDAO;

public class ActionControl implements ActionListener 
{
	JComboBox cbListener = null;
	JButton buttonListener = null;
	JTextArea textArea = null;
	Vector vectorCombo = null;
	private final JPanel textPanel = new JPanel();
	
	
	JDBCJavaDicDAO jdbcdao = new JDBCJavaDicDAO();
	Connection conn; // DB connection
	
	public ActionControl(JComboBox cbListenerParam, JButton buttonParam, 
			JTextArea textAreaParam, Vector vectorComboParam)
	{
		cbListener = cbListenerParam;
		buttonListener = buttonParam;
		textArea = textAreaParam;
		vectorCombo = vectorComboParam;
	}
	
	
/*	
	@Override
    public void actionPerformed(ActionEvent e) {
        String s = (String) cbListener.getSelectedItem();

        if (s != null) {
        	jdbcdao.sqlQueryDisplay(s, textArea);
        }
	}
	
*/	
	
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	JDBCJavaDicDAO jdbcdao = new JDBCJavaDicDAO();
        PreparedStatement statement;
        try {
            conn = jdbcdao.getConnection();

            if (e.getSource() == buttonListener) {
                try {
                    int optionType = JOptionPane.YES_NO_OPTION; // YES+NO
                    int messageType = JOptionPane.PLAIN_MESSAGE; // no standard icon
                    ImageIcon icon = new ImageIcon("blob.gif", "blob");
                    int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete confirmation!",
                            optionType, messageType, icon);

                    if (res == JOptionPane.NO_OPTION) {
                        return;
                    }

                    // sqlQueryDelete(conn, (String)cb.getSelectedItem(), textArea);
                    statement = conn.prepareStatement("DELETE FROM words WHERE word=?");

                    String s = (String) cbListener.getSelectedItem();
                    statement.setString(1, s);

                    // add a word into the words table
                    statement.executeUpdate();
                    textArea.setText("");
                    JOptionPane.showMessageDialog(null, s + " delete completed");
                    jdbcdao.sqlQueryList(cbListener, vectorCombo);

                } catch (SQLException ex) {
                    Logger.getLogger(JavaDicMain.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (e.getSource() == buttonListener) {
                textArea.selectAll();
                textArea.setText("");
            } else if (e.getSource() == buttonListener) {
                try {
                    //Pop up...
                    String name = JOptionPane.showInputDialog((textPanel), "What is the word to add?", null);
                    // create a statement: This object will be used for executing
                    // a static SQL statement and returning the results it produces. 
                    if (name == null) //null pointre when cancel or close
                    {
                        return;
                    } else if (name.equals("")) //no value check
                    {
                        return;
                    }

                    statement = conn.prepareStatement("INSERT INTO words VALUES(?, ?)");
                    
                    //String strings = textArea.getText();
                    statement.setString(1, name);
                    statement.setString(2, "put contents here & click Update!");
                    /*
                    if (strings.equals("")) {
                        return;
                    } //no value check
                    */
                    
                    
                    
                    // add a word into the words table
                    statement.executeUpdate();
                    jdbcdao.sqlQueryList(cbListener, vectorCombo);
                    cbListener.setSelectedItem(name);
                    //JOptionPane.showMessageDialog(null, name + " needs to be updated with contents");
                    jdbcdao.sqlQueryDisplay(name, textArea);

                } catch (SQLException ex) {
                    Logger.getLogger(JavaDicMain.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex);
                }

            } else if (e.getSource() == buttonListener) {
                String name = (String) cbListener.getSelectedItem();
                if (name == null) //null pointre when cancel or close
                {
                    return;
                } else if (name.equals("")) //no value check
                {
                    return;
                }

                statement = conn.prepareStatement("UPDATE words SET descr = ? WHERE word = ?");

                String strings = textArea.getText();
                if (strings.equals("")) {
                    JOptionPane.showMessageDialog(null, name + " contents are empty!!");
                    return;
                } //no value check
                statement.setString(1, strings);
                statement.setString(2, name);

                // add a word into the words table
                statement.executeUpdate();
                jdbcdao.sqlQueryList(cbListener, vectorCombo);
                cbListener.setSelectedItem(name);
                JOptionPane.showMessageDialog(null, name + " update completed");
                jdbcdao.sqlQueryDisplay(name, textArea);
            }

        } catch (Exception ex) {
            Logger.getLogger(JavaDicMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            /*           if ( statement != null) {
             try {
             statement.close();
             } catch (SQLException ex) {
             Logger.getLogger(MyDictionary.class.getName()).log(Level.SEVERE, null, ex);
             }
             }*/
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JavaDicMain.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }

    }	
	
	
	
	
	
}
