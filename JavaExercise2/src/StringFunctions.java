

public class StringFunctions {

public static void main(String[] args) throws Exception
{
	String s = "AAAA";
	String t = "BBBB";
	String u = s+t;
	String v = s.concat(t);
	
	StringBuffer w = new StringBuffer("CCCC");
	StringBuffer x = new StringBuffer("DDDD");
	StringBuffer y = w.append(x);
	char c = s.charAt(0);
	System.out.println(u);
	System.out.println(v);
	System.out.println(y);
	System.out.println(s.length());
	System.out.println(y.length());
	System.out.println(c);
	System.out.println(s.equals(t));
	System.out.println(w.equals(x));
	System.out.println(s==t);
	System.out.println(w==x);
	System.out.println(s==x.toString());
}
}

