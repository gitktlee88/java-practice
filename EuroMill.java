package com.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
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
        lbl = new JLabel("버튼을 click 하세요.");
        btn1 = new JButton("첫번째 버튼");
        btn2 = new JButton("두번째 버튼");
          // 버튼1,2를 ActionListener 사용하기
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        // 패널 생성
        JPanel panel = new JPanel();
        panel.add(btn1);
        panel.add(btn2);
        
        add("Center", panel);
        add("South", lbl);
        
        setSize(300, 300);
        setVisible(true);
    }
    
   // 이벤트 발생시 행동하는 함수
    public void actionPerformed(ActionEvent e) 
    {
        Object obj = e.getSource();
        if((JButton)obj == btn1) 
        {
            lbl.setText("First button clicked.");
            int[] numArray = guessNums(1, 50);
            String strArray[] = new String[numArray.length];

    		for (int i = 0; i < numArray.length; i++)
    			strArray[i] = String.valueOf(numArray[i]);

    		System.out.println(Arrays.toString(strArray));
            lbl.setText(Arrays.toString(strArray));
            
        }
        else     {
            lbl.setText("두번째 버튼을 눌렀습니다.");
        }
    }
    
    public int[] guessNums(int min, int max){
    	
    	int[] nums = new int[5]; 
    	
    	for (int i = 0; i <5; i++){
    		Integer  a = (int) ((Math.random()*((max-min)+1))+min);
    		//System.out.println(((Object)a).getClass().getName());
    		//boolean contains = Arrays.stream(values).anyMatch("s"::equals);
    		boolean contains = IntStream.of(nums).anyMatch(x -> x == a);
    		
    		if ( !contains){    			
    			//System.out.println(a.intValue());
    			if (a.intValue() != 0d)	nums[i] = a;
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
