
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.JList;


import com.mycode.dao.JDBCJavaDicDAO;
import com.mycode.vo.Word;

public class JavaDicMain extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    final int MAX_INPUT_LENGTH = 20;
    final int PANEL_WIDTH = 770;
    final int PANEL_HEIGHT = 680;
    //Connection conn; // DB connection

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    boolean clearOnNextDigit; 
    private JLabel label1 = new JLabel(); 
    private JTextField textfield = new JTextField(10); //for search
    
    private final JButton buttons[]; 
    private JComboBox comboBox = new JComboBox();
    private JTextArea textArea = new JTextArea(36, 60);
    //private final JPanel textPanel = new JPanel();
    final static String CANCEL_ACTION = "cancel-search";
    
    String combotext[] = {};
    @SuppressWarnings("rawtypes")
	Vector vectorCombo = new Vector();

    
    
    // GUI.
    public JavaDicMain() {
        // set the initial size
        setSize(800, 900);
        setLocation(800,0);

        // Exit the application on window close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // In general, when you want to set a border on a standard Swing component other than JPanel or JLabel, 
        // we recommend that you put the component in a JPanel and set the border on the JPanel.
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(
                5, 5, 5, 5));

        // Construct the north panel with a titled border
        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
                .createTitledBorder("Choose an Word from Combobox or Search titles"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        //using grid
        northPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        //welcome label
        label1 = new JLabel("Welcome to My Dictionary!", JLabel.HORIZONTAL); // 
        if (shouldWeightX) {
            c.weightx = 0.5;
            //c.weighty = 1.0;   //request any extra vertical space
        }
        c.fill = GridBagConstraints.HORIZONTAL;        
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        //c.ipady = 40;      //make this component tall
        //c.ipady = 0;       //reset to default        
        northPanel.add(label1, c);
       
        //combobox
        comboBox.setMaximumRowCount(30);
        comboBox.setForeground(Color.blue);
        comboBox.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");  //Fix JComboBox width
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.fill = GridBagConstraints.NONE;
        c.weightx = 0.5;
        c.gridwidth = 2;     //2 columns wide
        c.gridx = 3;         //third column
        c.gridy = 0;         //first row
        northPanel.add(comboBox, c);

        /*
        //instance JDBCJavaDicDAO
        JDBCJavaDicDAO jdbcdao = new JDBCJavaDicDAO();
        //fill in combo here
        ActionListener cbActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox.getSelectedItem();

                if (s != null) {
                	jdbcdao.sqlQueryDisplay(s, textArea);
                }
            }
        };
                
        comboBox.addActionListener(cbActionListener);        
                    
        //get to fill in combobox
        jdbcdao.sqlQueryList(comboBox, vectorCombo);
        
        */
        
        
        
        comboBox.setEditable( true );     
        
        //  Get handle for textfield component.
        final JTextField tf = (JTextField)comboBox.getEditor().getEditorComponent();
        
		tf.addKeyListener(new ComboListener(comboBox, vectorCombo));
		
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
              JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
              BasicComboPopup popup = (BasicComboPopup) comboBox
                  .getAccessibleContext().getAccessibleChild(0);
              JList list = popup.getList();
              list.setSelectedIndex(2);
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

          });        
        
        buttons = new JButton[4];     

        // function buttons.
        buttons[0] = new JButton("Update");
        buttons[1] = new JButton("Delete");
        buttons[2] = new JButton("Clear text");
        buttons[3] = new JButton("Add new");

        c.insets = new Insets(3,0,0,0);  //top padding
        for (int i = 0; i <= 3; i++) {
            c.fill = GridBagConstraints.HORIZONTAL;
            //c.ipady = 40;      //make this component tall
            c.weightx = 0.5;
            c.gridwidth = 1;
            c.gridx = i;
            c.gridy = 1;
            northPanel.add(buttons[i], c);
            //buttons[i].addActionListener(this);
            buttons[i].addActionListener(new ActionControl(comboBox,buttons[0],textArea, vectorCombo));
        }

        comboBox.addActionListener(new ActionControl(comboBox,buttons[0],textArea, vectorCombo));
        
        
        // Construct the southPanel 
        textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC, 16f)); // will only change size to 12pt
        //textArea.setFont(new Font("monospaced", Font.PLAIN, 15));
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.ipady = 40;      //make this component tall
        c.weightx = 0.5;
        c.gridwidth = 6;
        c.gridx = 0;
        c.gridy = 2;
        northPanel.add(textArea, c);
        
        //        
        getContentPane().add(northPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(textArea), BorderLayout.SOUTH);
        requestFocus();
        
        setVectorCombo();
    }
    

	@SuppressWarnings("unchecked")
	public void setVectorCombo()
	{
		int a;
		for(a=0;a<combotext.length;a++)
		{
			vectorCombo.add(combotext[a]);
		}
	}    
    



    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        JavaDicMain form = new JavaDicMain();
        form.setVisible(true);
    }

   
}
