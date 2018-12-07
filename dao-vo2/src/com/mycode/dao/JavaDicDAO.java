package com.mycode.dao;

import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import com.mycode.vo.Word;

public interface JavaDicDAO {
    
   //public void insert(JavaDic person);
   //public List<JavaDic> select();
   public void sqlQueryList(JComboBox combo, Vector vectorCombo);
   public void sqlQueryDisplay(String s, JTextArea textArea);

}