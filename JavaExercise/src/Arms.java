public class Arms{
public static void main(String[] args){
	int x = 2, y = 3;
	System.out.println("old x " + x);
	System.out.println("old y " + y);
	x = x + y;
	y = x - y;
	x = x - y;
	System.out.println("new x " + x);
	System.out.println("new y " + y);
}
}