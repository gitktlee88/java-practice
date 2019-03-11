package com.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class EuroMill extends JFrame implements ActionListener
{
    // 레이블과 버튼 정의
    JLabel lbl;
    JButton btn1, btn2;
    
    public EuroMill(String str)
    {
        super(str);
        
        lbl = new JLabel("click the button.");
        btn1 = new JButton("button 1");
        btn2 = new JButton("버튼 2");
          // 버튼1,2를 ActionListener 사용하기
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        // 패널 생성
        JPanel panel = new JPanel();
        panel.add(btn1);
        panel.add(btn2);
        
        add("Center", panel);
        add("South", lbl);
        
        setSize(450, 350);
        setVisible(true);
    }
    
   // 이벤트 발생시 행동하는 함수
   public void actionPerformed(ActionEvent e) 
   {
        Object obj = e.getSource();
        if((JButton)obj == btn1) 
        {
            lbl.setText("First button clicked.");
            int[] numArray = guessNums(1, 50, 5);
            String strArray[] = new String[numArray.length];

    		      for (int i = 0; i < numArray.length; i++)
    			         strArray[i] = String.valueOf(numArray[i]);

    		      System.out.println(Arrays.toString(strArray));
            lbl.setText(Arrays.toString(strArray));
            
        }
        else     {
            lbl.setText("Second button clicked.");
            int[] numArray = guessNums(1, 12, 2);
            String strArray[] = new String[numArray.length];

    		      for (int i = 0; i < numArray.length; i++)
    			         strArray[i] = String.valueOf(numArray[i]);

    		      System.out.println(Arrays.toString(strArray));
            lbl.setText(Arrays.toString(strArray));
        }
    }
    
    public int[] guessNums(int min, int max, int cnt){    	
    	
    	int[] nums = new int[cnt]; 
    	Random rand = new Random();
    	
    	for (int i = 0; i <cnt; i++){
    		   //int a = (int) (Math.random()*50 + 1);    		
    		   int a = min + (int)(Math.random()*((max - min) + 1));
    		
    		   //boolean contains = Arrays.stream(values).anyMatch("s"::equals);    		
    		   boolean contains = IntStream.of(nums).anyMatch(x -> x == a);
    		
    		   if ( !contains) {  
				         //System.out.println(Integer.valueOf(a));
				         //System.out.println(String.valueOf(a));
				         //System.out.println(((Object)a).getClass().getName());
				         nums[i] = a;
    		   }
    		   else {
    			      i -= 1;			
    		   }
    	
    	}	
    	
    	Arrays.sort(nums);
    	return nums;
    }
    
    public static void main(String[] args) 
    {
        new EuroMill("ActionEvent 테스트");
    }
    
}
