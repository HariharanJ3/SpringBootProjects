package com.global.translator.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.checkerframework.checker.regex.qual.Regex;
import org.hibernate.internal.build.AllowSysOut;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.global.translator.Service.TranslatorService;
import com.global.translator.config.DB1Config;
import com.global.translator.model.UserMaster;
import com.google.common.collect.Ordering;

public class Test1 extends DB1Config {
	
    private static final Object lock = new Object();

    public static void increment() 
    {
        synchronized(lock) {
            count++;
        }
    }

    public static int getCount() 
    {
        synchronized(lock) {
            return count;
        }
    }
    
    Test1()
    {
    	System.out.println("gsgsgdsggs");
    }
	
	public static void main(String[] args)
	{
		List<Integer> ar = Arrays.asList(10, 20, 20, 10, 10, 30, 50, 10, 20);
		
		int n = 5;
		int p = 3;
		
		 int minNoOfPages = 0;
	        int firstToLast = 0;
	        int LastTofirst = 0;
	        
	        for(int i = 1;i < n;i += 2)
	        {
	            firstToLast++;
	            if(i <= p) break;
	        }
	        
	        for(int i = n;i > 1;i -= 2)
	        {
	            LastTofirst++;
	            if(i >= p) break;
	        }
	        
	        minNoOfPages =  (firstToLast > LastTofirst)?LastTofirst:firstToLast;
        
        System.out.println(minNoOfPages);
	}
	    
    static String strings_xor(String s,String t)
    {
        String result = "";
        
        for(int i = 0;i < s.length();i++)
        {
            if(s.charAt(i) == t.charAt(i)) result +=""+0;
            else result +=""+1;
        }
        return result;
    }
	
	static int x;
	
	static 
	{
		x = 5+5;
	}
	
	public Test1(int count)
	{
		this.count = count;
	}
	
	static int count;
	
	public static void name()
	{
		System.out.println("Address : Coimbatore;");
	}
	
	public void instanMethod()
	{
		count++;
		
		if(count < 10)
		{
		    System.out.println("instanMethod in Test1 : "+count);
			instanMethod();
		}
	}
	
	final static Scanner scan = new Scanner(System.in);
    final static Priorities priorities = new Priorities();
    
    public static void miniMaxSum(List<Integer> arr) {
        
        long minSum = 0;
        long maxSum = 0;
        
        for(int a = 0;a < arr.size();a++)
        {
        	long sum = 0;
            
            for(int b = 0;b < arr.size();b++)
            {
                if(b != a) sum += arr.get(b);
            }
            
            System.out.println(sum);
            
            if(a == 0)
            {
            	minSum = maxSum = sum;
            }
            else
            {
            	if(minSum > sum) minSum = sum;
            	
            	if(maxSum < sum) maxSum = sum;
            }
        }
        System.out.println(minSum+" "+maxSum);
    }
    
    public static int month[];
    
    static Map<Integer,Map<Integer,Integer>> threeSecondBombs = new HashMap<>();
    static Map<Integer,Map<Integer,Integer>> twoSecondBombs = new HashMap<>();
    static Map<Integer,Map<Integer,Integer>> oneSecondBombs = new HashMap<>();
    static Map<Integer,Map<Integer,Integer>> damagedBombs = new HashMap<>();
    
	static boolean isPalindrome(char[] arr)
	{
		int n = arr.length;
		
		for(int i = 0,j = n - 1;i < n/2 && j >= n/2;i++,j--)
		{
			if(!(arr[i] == arr[j])) return false;
		}
		
		return true;
	}
	
	static int getSweetness(int leastCookie,int secLeastCookie)
	{
		return (1*leastCookie + 2*secLeastCookie);
	}
	
	static boolean allMatchTheCondition(List<Integer> arr,int k)
	{
		for(int i:arr)
		{
			if(i < k) return false;
		}
		
		return true;
	}

	static void addStack(Stack<Integer> s,List<Integer> numbers) {
		while (!s.empty()) {
			numbers.add(s.pop());
		}
	}
	
	static int nextPrime(int begin) {
		for (int i = begin + 1;; i++) {
			if (isPrime(i)) {
				return i;
			}
		}
	}
	
	static boolean isPrime(int n)
	{
		for(int i = 2;i * i <= n;i++)
		{
			if(n % i == 0) return false;
		}
		return true;
	}
	
	static int getLastNum(SinglyLinkedListNode node)
	{
		int lastNum = 0;
		
		while (node != null){
			lastNum = node.data;
            node = node.next;
        }
		
		return lastNum;
	}
	
	static boolean contains(SinglyLinkedList list,int data)
	{
		SinglyLinkedListNode node = list.head;
		
		while (node != null)
		{
        	if(node.data == data) return true;
        	
            node = node.next;
        }
		
		return false;
	}
	
	static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList 
    {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) 
        {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }
	
	public static int mostFrequent(List<Integer> arr, int n) 
	{ 
	    int maxcount = 0; 
	    int element_having_max_freq = 0; 
	    for (int i = 0; i < n; i++) { 
	      int count = 0; 
	      for (int j = 0; j < n; j++) { 
	        if (arr.get(i) == arr.get(j)) { 
	          count++; 
	        } 
	      } 
	  
	      if (count > maxcount) { 
	        maxcount = count; 
	        element_having_max_freq = arr.get(i); 
	      } 
	    } 
	  
	    return element_having_max_freq; 
	  } 
    
    //Plants a bomb on all open tiles
    static void plantBombs(Map<Integer,Map<Integer,Integer>> bombSet, char[][] bomberBox)
    {
        for(int i = 0; i < bomberBox.length; i++)
        {
            for(int j = 0; j < bomberBox[0].length; j++)
            {
                if(bomberBox[i][j] == '.')
                {
                    //System.out.println("Planting 2s Bomb");
                    if(bombSet.get(i) == null)
                    {
                        //System.out.println("No bomb in row "+i);
                        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
                        bombSet.put(i, map);  
                        bombSet.get(i).put(j,0);
                    }
                    else
                    {
                        bombSet.get(i).put(j,0);
                    }
                    bomberBox[i][j] = 'O';
                }
            }
        }
    }
    
    //Detonates bombs of a given Map updating the other maps and the bomberBox
    static void detonateBombs(Map<Integer,Map<Integer,Integer>> bombSet, char[][] bomberBox)
    {
        
        for(Map.Entry<Integer, Map<Integer,Integer>> x : bombSet.entrySet())
        {
            int px = x.getKey();
            for(Map.Entry<Integer,Integer> y : x.getValue().entrySet())
            {
                removeDamage(px,y.getKey(),bomberBox);
            }
        }

        for(Map.Entry<Integer, Map<Integer,Integer>> x : damagedBombs.entrySet())
        {
            int px = x.getKey();
            for(Map.Entry<Integer,Integer> y : x.getValue().entrySet())
            {
                //System.out.println("Removing Bomb at("+px+","+y.getKey()+")");
                if(threeSecondBombs.get(px) != null)
                {
                    threeSecondBombs.get(px).remove(y.getKey());
                    //System.out.println("Removing 3s Bomb");
                }
                if(twoSecondBombs.get(px) != null)
                {
                    twoSecondBombs.get(px).remove(y.getKey());
                    //System.out.println("Removing 2s Bomb");
                }
                if(oneSecondBombs.get(px) != null)
                {
                    oneSecondBombs.get(px).remove(y.getKey());
                    //System.out.println("Removing 1s Bomb");
                }
            }
        }
        damagedBombs = new HashMap<>();
    }
    
    
    static void removeDamage(int x, int y, char[][] bomberBox)
    {
        bomberBox[x][y] = '.';
        removeBomb(x, y);
        
        //Left
        if(y-1 >= 0)
        {
            bomberBox[x][y-1] = '.';
            removeBomb(x, y-1);
        }
            
        //Right
        if(y+1 < bomberBox[0].length)
        {
            bomberBox[x][y+1] = '.';
            removeBomb(x, y+1);
        }
        
        //Up
        if(x-1 >= 0)
        {
            bomberBox[x-1][y] = '.';
            removeBomb(x-1, y);
        }
        
        //Down
        if(x+1 < bomberBox.length)
        {
            bomberBox[x+1][y] = '.';
            removeBomb(x+1, y);
        }
    }
    
    //Adds a bomb to the Map of damaged bombs
    static void removeBomb(int x, int y)
    {
        if(damagedBombs.get(x) == null)
        {
            Map<Integer,Integer> map = new HashMap<Integer, Integer>();
            damagedBombs.put(x, map);  
            damagedBombs.get(x).put(y,0);
        }
        else
        {
            damagedBombs.get(x).put(y,0);
        }
    }
    
    static void printBombSet(Map<Integer,Map<Integer,Integer>> bombSet)
    {
        for(Map.Entry<Integer, Map<Integer,Integer>> x : bombSet.entrySet())
        {
            int px = x.getKey();
            for(Map.Entry<Integer,Integer> y : x.getValue().entrySet())
            {
                System.out.println("("+px+","+y.getKey()+")");
            }
        }
    }
    
    static void printbomberBox(char[][] bomberBox)
    {
        for(char[] l : bomberBox)
        {
            for(char m : l)
            {
                System.out.print(m);
            }
            System.out.println("");
        }
        //System.out.println(""); //Uncomment if you are printing iteratively
    }
    
	static int anagram(String s) {
		
		int minNoOfChanes = 0;
		
		if ((s.length() & 1) == 1)
			return -1;
		
		int charFreq[] = new int[26];
		
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			
			System.out.println(index);
			
			if (i < s.length() / 2) {
				charFreq[index]++;
			} else {
				charFreq[index]--;
			}
		}
		
		System.out.println(Arrays.toString(charFreq));
		
		for (int i = 0; i < 26; i++) {
			if (charFreq[i] > 0) {
				
				System.out.println(charFreq[i]);
				minNoOfChanes += charFreq[i];
			}
		}
		
		return minNoOfChanes;
	}
	
	static boolean isAnagram(String firstPart, String secondPart)
	{
		Map<String,Integer> charAndCountFirst = getCharCountMap(firstPart);
		Map<String,Integer> charAndCountSecond = getCharCountMap(secondPart);
		
		for(Map.Entry<String,Integer> pair:charAndCountFirst.entrySet())
		{
			if(charAndCountSecond.keySet().contains(pair.getKey()))
			{
				 int count = charAndCountSecond.get(pair.getKey());
				 
				 if(!(count > 0 && count == pair.getValue())) return false;
			}
			else return false;
		}
		
		return true;
	}
	
	static Map<String,Integer> getCharCountMap(String s)
	{
		Map<String,Integer> charAndCount = new HashMap<>();
		
		for(char character:s.toCharArray())
		{
			charAndCount.put(""+character, charAndCount.getOrDefault(""+character, 0)+1);
		}
		
		return charAndCount;
	}
    
    static boolean isPalindrome(String s)
    {
        String s1 = "";
        
        for(int i = s.length() - 1;i >= 0;i--)
        {
            s1 += s.charAt(i);
        }
        
        System.out.println(s1);
        
        if(!s.equals(s1)) return false;
        
        return true;
    }

	private static int numZeroes(long n) {
	    int count = 0;
	    while (n > 0) {
	        if ((n & 1) == 0) {
	            count++;
	        }
	        n >>= 1; // divides by 2
	    }
	    return count;
	}
	
	static boolean isPowerOfTwo(long n)
	{
		if(n == 0) return false;
		
		while (n != 1) 
		{
            if (n % 2 != 0)
            	return false;
            	
            n = n / 2;
        }
		
		return true;
	}

    public static void updateLeapYear(int year) 
    {
        if(year % 400 == 0) {
            month[2] = 28;
        } else if(year % 100 == 0) {
            month[2] = 29;
        } else if(year % 4 == 0) {
            month[2] = 29;
        } else {
            month[2] = 28;
        }
    }
	    
    public static void storeMonth() 
    {
        month[1] = 31;
        month[2] = 28;
        month[3] = 31;
        month[4] = 30;
        month[5] = 31;
        month[6] = 30;
        month[7] = 31;
        month[8] = 31;
        month[9] = 30;
        month[10] = 31;
        month[11] = 30;
        month[12] = 31;
    }
	   
	public static int findPrimeDates(int d1, int m1, int y1, int d2, int m2, int y2) 
	{
        storeMonth();
    
        int result = 0;
    
        while(true) {
            int x = d1;
            x = x * 100 + m1;
            x = x * 10000 + y1;
            
            System.out.println(x);
            if(x % 4 == 0 || x % 7 == 0) {
                result = result + 1;
            }
            if(d1 == d2 && m1 == m2 && y1 == y2) {
                break;
            }
            updateLeapYear(y1);
            d1 = d1 + 1;
            if(d1 > month[m1]) {
                m1 = m1 + 1;
                d1 = 1;
                if(m1 > 12) {
                    y1 =  y1 + 1;
                    m1 = 1;
                }
            }
        }
	   return result;
	}
	
	private static int getIndex(String s)
	{
		Map<String,Integer> alphabetIndexAndChar = new HashMap<String,Integer>();
		alphabetIndexAndChar.put("a",1);
		alphabetIndexAndChar.put("b",2);
		alphabetIndexAndChar.put("c",3);
		alphabetIndexAndChar.put("d",4);
		alphabetIndexAndChar.put("e",5);
		alphabetIndexAndChar.put("f",6);
		alphabetIndexAndChar.put("g",7);
		alphabetIndexAndChar.put("h",8);
		alphabetIndexAndChar.put("i",9);
		alphabetIndexAndChar.put("j",10);
		alphabetIndexAndChar.put("k",11);
		alphabetIndexAndChar.put("l",12);
		alphabetIndexAndChar.put("m",13);
		alphabetIndexAndChar.put("n",14);
		alphabetIndexAndChar.put("o",15);
		alphabetIndexAndChar.put("p",16);
		alphabetIndexAndChar.put("q",17);
		alphabetIndexAndChar.put("r",18);
		alphabetIndexAndChar.put("s",19);
		alphabetIndexAndChar.put("t",20);
		alphabetIndexAndChar.put("u",21);
		alphabetIndexAndChar.put("v",22);
		alphabetIndexAndChar.put("w",23);
		alphabetIndexAndChar.put("x",24);
		alphabetIndexAndChar.put("y",25);
		alphabetIndexAndChar.put("z",26);
		
		if(alphabetIndexAndChar.keySet().contains(s))
			return alphabetIndexAndChar.get(s);
		
		return 0;
	}
	
	private static int pageCount(int n ,int p)
	{
		if (p > n / 2) {
			if (n % 2 == 0 && n - p == 1){
				return 1;
			}
			return pageCount(n, n - p);
		} else {
			return (p / 2);
		}
	}
	
	private static String getDay(int dayOfWeek)
	{
		
		switch (dayOfWeek) 
		{
	        case Calendar.SUNDAY: return "SUNDAY";
	        case Calendar.MONDAY: return "MONDAY";
	        case Calendar.TUESDAY: return "TUESDAY";
	        case Calendar.WEDNESDAY: return "WEDNESDAY";
	        case Calendar.THURSDAY: return "THURSDAY";
	        case Calendar.FRIDAY: return "FRIDAY";
	        case Calendar.SATURDAY: return "SATURDAY";
	        default: return "Invalid day";
        }
	}
	
	
	//1 1 1 0 0 0
	//0 1 0 0 0 0
	//1 1 1 0 0 0
	//0 0 2 4 4 0
	//0 0 0 2 0 0
	//0 0 1 2 4 0
	
	 public static boolean isBalanced(String brackets) {
	        // set matching pairs
        Map<Character, Character> braces = new HashMap<>();
        braces.put('(', ')');
        braces.put('[',']');
        braces.put('{','}');

        // if length of string is odd, then it is not balanced
        if (brackets.length() % 2 != 0) {
            return false;
        }

        // travel half until openings are found and compare with
        // remaining if the closings matches
        Stack<Character> halfBraces = new Stack<Character>();
        for(char ch: brackets.toCharArray()) {
            if (braces.containsKey(ch)) {
                halfBraces.push(braces.get(ch));
            }
            // if stack is empty or if closing bracket is not equal to top of stack,
            // then braces are not balanced
            else if(halfBraces.isEmpty() || ch != halfBraces.pop()) {
            	
            	System.out.println(halfBraces.pop());
                return false;
            }
        }
        return halfBraces.isEmpty();
    }
	        
    public static boolean canWin(int leap, int[] game) 
    {
    	int index = 0;
        ArrayList<Integer> added = new ArrayList<Integer>();
        
        if(game[index+1] == 0)
            added.add(index+1);
        if(index-1>=0 && game[index-1] == 0)
            added.add(index-1);
        if(index+leap<game.length && game[index+leap] == 0)
            added.add(index+leap);
        if(leap >= game.length)
            return true;
        
        int bufferLength = 0;
        
        while(true)
        {
            int count = 0;
            bufferLength = added.size();
            while(count < added.size())
            {
                if((added.get(count) + leap > game.length - 1) || (added.get(count) + 1 > game.length-1))
                    return true;
                if(game[added.get(count) + 1] == 0)
                {
                    if(!added.contains(added.get(count) + 1))
                        added.add(added.get(count) + 1);
                }
                if(added.get(count)-1>=0 && game[added.get(count) - 1] == 0)
                {
                    if(!added.contains(added.get(count) - 1))
                        added.add(added.get(count) - 1);
                }
                if(game[added.get(count) + leap] == 0)
                {
                    if(!added.contains(added.get(count) + leap))
                        added.add(added.get(count) + leap);
                }
                count ++;
            }
            
            if(bufferLength == added.size()) // meaning added didnt change in length so no new items were added
                return false;
        }
    }
    
    class Prime
    {
    	public void checkPrime(int[] input)
    	{
    		String result = "";
    		
    		for(int i:input)
    		{
    			if(isPrime(i)) result += i +" ";
    				
    		}
    		System.out.println(result);
    	}
    	
    	boolean isPrime(int n)
    	{
    		if((n < 2) || ((n > 2) && (n % 2 == 0))) return false;
    		
    		for ( int i = 3; i * i <= n; i += 2 ) 
    		{
    			if ( n % i == 0 ) return false;
    		}
    		return true;
    	}
    }
}

class Student
{
    int id;
    String name;
    double cgpa;
    
    Student()
    {
    	
    }
    
    Student(int id,String name,double cgpa)
    {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }
    
    public int getID()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getCGPA()
    {
        return cgpa;
    }
    
    int add(int a,int b)
    {
    	return a+b;
    }
    
}

class CustomComparator implements Comparator<Student>{ 
    
    @Override
    public int compare(Student s1, Student s2) {
    	
       if (s1.getCGPA() == s2.getCGPA()) 
            return s1.getName().compareTo(s2.getName()); 
       else if ((s1.getName() == s2.getName()) && (s1.getCGPA() == s2.getCGPA()))
            return ((Integer) s1.getID()).compareTo(s2.getID());
       else
            return ((Double) s2.getCGPA()).compareTo(s1.getCGPA()); 
    } 
}

class Priorities
{
	
	private final PriorityQueue<Student> queue = new PriorityQueue<Student>(
	        new Comparator<Student>() {
	            public int compare(Student s1, Student s2) {
	                if (Double.compare(s2.getCGPA(), s1.getCGPA()) != 0) {
	                    return Double.compare(s2.getCGPA(), s1.getCGPA());
	                } else if (!s1.getName().equals(s2.getName())) {
	                    return s1.getName().compareTo(s2.getName());
	                } else {
	                    return Integer.compare(s1.getID(), s2.getID());
	                }
	            }
	        }
	    );
	
    public List<Student> getStudents(List<String> events)
    { 
    	List<Student> sortedList = new LinkedList<>();
    	
    	for(String input:events)
    	{
    		String[] inputArr =  input.split(" ");
    		
    		String func = inputArr[0];
    		
    		if(func.equals("ENTER"))
    		{
    			String name = inputArr[1];
    			double cgpa = Double.valueOf(inputArr[2]);
    			int id = Integer.valueOf(inputArr[3]);
    			
    			queue.add(new Student(id,name,cgpa));
    		}
    		
    	}
    	
    	while(!queue.isEmpty())
    		sortedList.add(queue.poll());
    	
        return sortedList;
    }
}

class SampleDemo
{
	private Thread t;
	private String threadname;
	
	SampleDemo(String threadname)
	{
		this.threadname = threadname;
	}
	
	public void run()
	{
		System.out.println(threadname);
	}
	
	public void start()
	{
		if(t == null)
		{
			
		}
	}
}

class MyQueue<T>
{
	Stack<T> leftStack = new Stack<T>();
	Stack<T> rightStack = new Stack<T>();
	
	void enqueue(T item)
	{
		leftStack.push(item);
	}
	
	T dequeue()
	{
		transferIfNeeded();
		return rightStack.pop();
	}
	
	T peek()
	{
		transferIfNeeded();
		return rightStack.peek();
	}
	
	void transferIfNeeded()
	{
		if(rightStack.empty())
		{
			while(!leftStack.empty())
			{
				rightStack.push(leftStack.pop());
			}
		}
	}
}

class Operation {
    int type;
    Object argument;

    Operation(int type, Object argument) {
        this.type = type;
        this.argument = argument;
    }
}

class Test2
{
	protected int x,y;
}


interface A 
{
	void a();
	void b();
	void c();
	void d();
}

abstract class B implements A{  
	
	public void sum()
	{
		System.out.println(5+9);
	}
}  
  
//Creating subclass of abstract class, now we need to provide the implementation of rest of the methods  
class M extends B{  
public void a(){System.out.println("I am a");}  
public void b(){System.out.println("I am b");}
public void c(){System.out.println("I am c");}
public void d(){System.out.println("I am d");}
}  
  