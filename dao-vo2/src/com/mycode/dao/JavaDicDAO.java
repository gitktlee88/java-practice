package com.mycode.dao;

import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import com.mycode.vo.Word;

public interface JavaDicDAO {
    
   //public void insert(JavaDic word);
   //public List<JavaDic> select();
	   public void sqlQueryList(Vector vectorCombo);
	   public void sqlQueryDisplay(String s);
	   public void sqlQueryDelete(String s, Vector vectorCombo);
	   public void sqlQueryAdd(String name, Vector vectorCombo);
	   public void sqlQueryUpdate(String name, Vector vectorCombo);

}