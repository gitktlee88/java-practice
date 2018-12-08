import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mycode.dao.JDBCJavaDicDAO;

public class ActionControler extends KeyAdapter implements ActionListener 
{    
	Connection conn = null;
	private static PreparedStatement statement;
	private static JPanel textPanel;
	public static JTextArea textArea;
	public static JComboBox comboBox;    
	public static JButton buttons[];
	Vector vectorCombo;	   
    private static JDBCJavaDicDAO jdbcdao;
    	
	public ActionControler(JComboBox comboBoxParam, JButton buttonParam[], 
			JTextArea textAreaParam, Vector vectorComboParam)
	{
		comboBox = comboBoxParam;
		buttons = buttonParam;
		textArea = textAreaParam;
		vectorCombo = vectorComboParam;
		jdbcdao = new JDBCJavaDicDAO(textArea, comboBox); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = (String) comboBox.getSelectedItem();

        if (e.getSource() == buttons[1]) {
        	int optionType = JOptionPane.YES_NO_OPTION; // YES+NO
            int messageType = JOptionPane.PLAIN_MESSAGE; // no standard icon
            ImageIcon icon = new ImageIcon("blob.gif", "blob");
            int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete confirmation!",
                    optionType, messageType, icon);

            if (res == JOptionPane.NO_OPTION) {
                return;
            }
            // sqlQueryDelete(conn, (String)cb.getSelectedItem(), textArea);
            jdbcdao.sqlQueryDelete(s, vectorCombo);
            return;
        } else if (e.getSource() == buttons[2]) {
            textArea.selectAll();
            textArea.setText("");
        } else if (e.getSource() == buttons[3]) {
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
            
            jdbcdao.sqlQueryAdd(name, vectorCombo);
            return;              

        } else if (e.getSource() == buttons[0]) {
           
            if (s == null) //null pointre when cancel or close
            {
                return;
            } else if (s.equals("")) //no value check
            {
                return;
            }         
            
            jdbcdao.sqlQueryUpdate(s, vectorCombo);

    } else if (s != null) 
        	 {
        	//System.out.print(e.getSource());
        	jdbcdao.sqlQueryDisplay(s);
        	return;
        }        
        
	}

	////////////////////////KeyAdapter////////////////////////////
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void keyTyped(KeyEvent key)
	{
				// TODO Auto-generated method stub
				String text = ((JTextField)key.getSource()).getText();
				comboBox.setModel(new DefaultComboBoxModel(getFilteredList(text)));
				comboBox.setSelectedIndex(-1);
				((JTextField)comboBox.getEditor().getEditorComponent()).setText(text);
				comboBox.showPopup();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getFilteredList(String text)
	{
		Vector v = new Vector();
		for(int a = 0; a<vectorCombo.size(); a++)
		{
			if(vectorCombo.get(a).toString().startsWith(text))
			{
				v.add(vectorCombo.get(a).toString());
			}
		}
		return v;
	}

	
	
}
