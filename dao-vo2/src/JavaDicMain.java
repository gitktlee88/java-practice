
import java.awt.BorderLayout;
import java.util.stream.Collectors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

public class JavaDicMain extends JFrame implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	final int MAX_INPUT_LENGTH = 20;
	final int PANEL_WIDTH = 770;
	final int PANEL_HEIGHT = 680;
	// Connection conn; // DB connection

	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	boolean clearOnNextDigit;
	private JLabel label1 = new JLabel();
	private JTextField textfield = new JTextField(10); // for search

	private final JButton buttons[];
	JButton button;
	private static JComboBox comboBox = new JComboBox();
	public static JTextArea textArea = new JTextArea(36, 60);
	private final JPanel textPanel = new JPanel();
	final static String CANCEL_ACTION = "cancel-search";

	private static JDBCJavaDicDAO jdbcdao = new JDBCJavaDicDAO(textArea, comboBox);
	private static PreparedStatement statement;
	
	static int[] lastwin; // = new int[7];
	
	String combotext[] = {};
	@SuppressWarnings("rawtypes")
	Vector vectorCombo = new Vector();

	// GUI.
	public JavaDicMain() {
		// set the initial size
		setSize(800, 900);
		setLocation(800, 0);

		// Exit the application on window close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// In general, when you want to set a border on a standard Swing component other
		// than JPanel or JLabel,
		// we recommend that you put the component in a JPanel and set the border on the
		// JPanel.
		((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Construct the north panel with a titled border
		JPanel northPanel = new JPanel();
		northPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Choose an Word from Combobox or Search titles"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		// using grid
		northPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		if (shouldFill) {
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		// welcome label
		label1 = new JLabel("Welcome to My Dictionary!", JLabel.HORIZONTAL); //
		if (shouldWeightX) {
			c.weightx = 0.5;
			// c.weighty = 1.0; //request any extra vertical space
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		// c.ipady = 40; //make this component tall
		// c.ipady = 0; //reset to default
		northPanel.add(label1, c);

		// combobox
		comboBox.setMaximumRowCount(30);
		comboBox.setForeground(Color.blue);
		// Fix JComboBox width which of the size is changing
		comboBox.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");
		c.fill = GridBagConstraints.HORIZONTAL;
		// c.fill = GridBagConstraints.NONE;
		c.weightx = 0.5;
		c.gridwidth = 2; // 2 columns wide
		c.gridx = 3; // third column
		c.gridy = 0; // first row
		northPanel.add(comboBox, c);

		// get to fill in combobox
		jdbcdao.sqlQueryList(vectorCombo);

		comboBox.addActionListener(this);
		comboBox.setEditable(true);

		// Get handle for textfield component for keyAdapter.
		final JTextField tf = (JTextField) comboBox.getEditor().getEditorComponent();
		tf.addKeyListener(new ComboListener(comboBox, vectorCombo));

		comboBox.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
				BasicComboPopup popup = (BasicComboPopup) comboBox.getAccessibleContext().getAccessibleChild(0);
				JList list = popup.getList();
				list.setSelectedIndex(2);
			}

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
		});

		buttons = new JButton[6];

		// function buttons.
		buttons[0] = new JButton("Update");
		buttons[1] = new JButton("Delete");
		buttons[2] = new JButton("Clear text");
		buttons[3] = new JButton("Add new");
		buttons[4] = new JButton("EuroMile");
		buttons[5] = new JButton("Most");

		c.insets = new Insets(3, 0, 0, 0); // top padding
		for (int i = 0; i <= 5; i++) {
			c.fill = GridBagConstraints.HORIZONTAL;
			// c.ipady = 40; //make this component tall
			c.weightx = 0.5;
			c.gridwidth = 1;
			c.gridx = i;
			c.gridy = 1;
			northPanel.add(buttons[i], c);
			buttons[i].addActionListener(this);
		}

		// Construct the southPanel
		textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC, 16f)); // will only change size to 12pt
		c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 40; //make this component taller
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
	public void setVectorCombo() {
		int a;
		for (a = 0; a < combotext.length; a++) {
			vectorCombo.add(combotext[a]);
		}
	}

	//////////////////////////////////////////////////////////
	// start practice for String handler //
	//////////////////////////////////////////////////////////

	public int[] guessNums(int min, int max, int cnt) {

		int[] nums = new int[cnt];
		Random rand = new Random();

		for (int i = 0; i < cnt; i++) {
			// int a = (int) (Math.random()*50 + 1);
			int a = min + (int) (Math.random() * ((max - min) + 1));

			// boolean contains = Arrays.stream(values).anyMatch("s"::equals);
			boolean contains = IntStream.of(nums).anyMatch(x -> x == a);
			
			boolean winned = IntStream.of(lastwin).anyMatch(x -> x == a);
			if (cnt < 5) winned = false;
			
			if (!contains && !winned) {
				// System.out.println(Integer.valueOf(a));
				// System.out.println(String.valueOf(a));
				// System.out.println(((Object)a).getClass().getName());
				nums[i] = a;
			} else {
				i -= 1;
			}

		}

		Arrays.sort(nums);
		return nums;
	}

	public void pickFrequentNums(int cnt) {
		String strints = textArea.getText();

		// StringBuffer buffer = new StringBuffer(strints);
		strints = strints.replace("[", "");
		strints = strints.replace("]", "");
		strints = strints.replace(",", "");
		strints = strints.replace("\n", " ");

		String[] strArray = strints.split(" ");
		//System.out.println(Arrays.toString(strArray));

		if (cnt < 5) {
			int[] ibonus = new int[strArray.length / 7 * 2];
			// get 2 nums from index * 5 and * 6
			for (int i = 0; i < strArray.length / 7; i++) {
				int bonus1 = Integer.parseInt(strArray[i * 7 + 5]);
				int bonus2 = Integer.parseInt(strArray[(i * 7 + 5) + 1]);
				ibonus[i * 2] = bonus1;
				ibonus[i * 2 + 1] = bonus2;

			}
			//System.out.println(Arrays.toString(ibonus));

			Map<Integer, Integer> bonusMap =mostWining(ibonus);
			//System.out.println("Bonus:" + bonusMap);
			if(ibonus.length >= 20) {
				System.out.println("Bonus:" + bonusMap);	
//				Collection<Integer> keys = bonusMap.keySet();
//				System.out.println("Bonus:" + keys);	
			}
								
			
		} else {
			int[] inums = new int[strArray.length / 7 * 5];
			// get 5 nums from index 0 to 4
			for (int i = 0; i < strArray.length / 7; i++) {
				for (int j = 0; j < 5; j++) {
					int nums1 = Integer.parseInt(strArray[i * 7 + j]);
					inums[i * 5 + j] = nums1;
				}
			}
			//System.out.println(Arrays.toString(inums));
			
			Map<Integer, Integer> numsMap = mostWining(inums);
			//System.out.println("Nums:" + numsMap);		
			if(inums.length >= 50) {
				System.out.println("Numbers:" + numsMap);	
//				Collection<Integer> keys = numsMap.keySet();
//				System.out.println("Numbers:" + keys);	
			}
		}
	}

	public Map<Integer, Integer> mostWining(int[] intArray) {

		HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();

		for (int i = 0; i < intArray.length; ++i) {
			int item = intArray[i];

			if (numbers.containsKey(item))
				numbers.put(item, numbers.get(item) + 1);
			else
				numbers.put(item, 1);
		}

	// Elements can traverse in any order
//		for(Map.Entry m:numbers.entrySet()){
//		        System.out.println(m.getKey()+" "+m.getValue());
//		}

	// Returns a Set view of the mappings contained in this map
//		numbers.entrySet()
//				// Returns a sequential Stream with this collection as its source
//				.stream()
//				// Sorted according to the provided Comparator
//				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//				// Performs an action for each element of this stream
//				.forEach(System.out::println);

		// let's sort this map by values first
		Map<Integer, Integer> sorted = numbers.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

		// Print the returned Map
		//System.out.println("Map:" + sorted);
		//textArea.setText("\n");
		//textArea.setText(sorted.toString());
		return sorted;
	}

	public void insertIntoArray(int[] array, int insertIndex, int newValue) {
		// System.out.println(Arrays.toString(array));
		// move elements below insertion point.
		for (int i = array.length - 1; i > insertIndex; i--) {
			array[i] = array[i - 1];
		}

		// insert new value
		array[insertIndex] = newValue;
		// System.out.println(Arrays.toString(array));
	}
	//////////////////////////////////////////////////////////
	// end practice for String handler //
	//////////////////////////////////////////////////////////

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// JavaDicMain form = new JavaDicMain();
		// form.setVisible(true);

		/*
		 * Also the usage of EventQueue.invokeAndWait is possible here. The difference
		 * is, that your calculation thread blocks until your GUI is updated. So it is
		 * obvious that this must not be used from the EDT.
		 * 
		 * Be careful not to update your Swing GUI from a different thread. In most
		 * cases this produces some strange updating/refreshing issues.
		 */

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JavaDicMain form = new JavaDicMain();
				form.setVisible(true);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = (String) comboBox.getSelectedItem();

		if (e.getSource() == buttons[1]) {
			int optionType = JOptionPane.YES_NO_OPTION; // YES+NO
			int messageType = JOptionPane.PLAIN_MESSAGE; // no standard icon
			ImageIcon icon = new ImageIcon("blob.gif", "blob");
			int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete confirmation!", optionType,
					messageType, icon);

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
			// Pop up...
			String name = JOptionPane.showInputDialog((textPanel), "What is the word to add?", null);
			// create a statement: This object will be used for executing
			// a static SQL statement and returning the results it produces.
			if (name == null) // null pointre when cancel or close
			{
				return;
			} else if (name.equals("")) // no value check
			{
				return;
			}

			jdbcdao.sqlQueryAdd(name, vectorCombo);
			return;

		} else if (e.getSource() == buttons[0]) {

			if (s == null) // null pointre when cancel or close
			{
				return;
			} else if (s.equals("")) // no value check
			{
				return;
			}

			jdbcdao.sqlQueryUpdate(s, vectorCombo);

		} else if (e.getSource() == buttons[4]) {			
			if (lastwin == null) {
				// Pop up...
				String lastwiner = JOptionPane.showInputDialog((textPanel), "What is the last win?", null);
				
				if (lastwiner == null) // null pointer when cancel or close
				{
					return;
				} else if (lastwiner.equals("")) // no value check
				{
					return;
				}
				
				lastwin = Arrays.stream(lastwiner.split(","))
					    .mapToInt(Integer::parseInt)
					    .toArray();			
				//System.out.println(Arrays.toString(lastwin));
				return;
			}
			
			int[] numArray = guessNums(1, 50, 5);
			int[] bonusArray = guessNums(1, 12, 2);
			textArea.append(Arrays.toString(numArray) + " " + Arrays.toString(bonusArray) + "\n");

		} else if (e.getSource() == buttons[5]) {
			pickFrequentNums(5);
			pickFrequentNums(2);

		} else if (s != null) {
			// System.out.print(e.getSource());
			jdbcdao.sqlQueryDisplay(s);
			return;
		}

	}

} // end of class
