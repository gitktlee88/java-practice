package com.mycode.vo;

public class Word {
	
    private String word;
    private String descr;
    
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
     
    public String toString(){
        return "Word: " + word + " Descr: " + descr;
    }
}