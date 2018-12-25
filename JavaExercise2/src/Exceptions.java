
public class Exceptions {
	
	public static void main(String[] args){
		//Null Pointer
		/*String s=null;  
		System.out.println(s.length());*/
		//Number Format
		/*String s="abc";  
		int i=Integer.parseInt(s);*/
		//ArrayIndexOutOfBound
		/*int a[]=new int[5];  
		a[10]=50; //ArrayIndexOutOfBoundsException*/
		//try catch arithmetic exception
		try{  
		      int data=50/0;  
		   } catch(ArithmeticException e){System.out.println("task1 is completed");}  
		   catch(ArrayIndexOutOfBoundsException e){System.out.println("task 2 completed");}  
		   catch(Exception e){System.out.println("common task completed");} 
		 finally{System.out.println("finally block is always executed");}  
		   System.out.println("rest of the code...");  
		}  

}
