// How to choose between ArrayList and Vector?
// ArrayList is unsynchronized and not thread-safe, whereas Vectors are. 
// Only one thread can call methods on a Vector at a time, which is a slight overhead, but helpful when safety is a concern. 
// Therefore, in a single-threaded case, arrayList is the obvious choice, 
// but where multithreading is concerned, vectors are often preferable.
// If we don’t know how much data we are going to have, but know the rate at which it grows, Vector has an advantage, 
// since we can set the increment value in vectors.
// ArrayList is newer and faster. If we don’t have any explicit requirements for using either of them, we use ArrayList over vector.

import java.io.*; 
import java.util.*; 
  
class VectorArrayList 
{ 
    public static void main (String[] args) 
    { 
        // creating an ArrayList 
        ArrayList<String> al = new ArrayList<String>(); 
  
        // adding object to arraylist 
        al.add("Practice.GeeksforGeeks.org"); 
        al.add("quiz.GeeksforGeeks.org"); 
        al.add("code.GeeksforGeeks.org"); 
        al.add("contribute.GeeksforGeeks.org"); 
  
        // traversing elements using Iterator' 
        System.out.println("ArrayList elements are:"); 
        Iterator it = al.iterator(); 
        while (it.hasNext()) 
            System.out.println(it.next()); 
  
        // creating Vector 
        Vector<String> v = new Vector<String>(); 
        v.addElement("Practice"); 
        v.addElement("quiz"); 
        v.addElement("code"); 
  
        // traversing elements using Enumeration 
        System.out.println("\nVector elements are:"); 
        Enumeration e = v.elements(); 
        while (e.hasMoreElements()) 
            System.out.println(e.nextElement()); 
    } 
}
