
public class ReverseString {
	public static void main(String[] args){
		String str = "Gaurav";
		StringBuffer rstr = new StringBuffer();
		char c;
		for(int j = str.length()-1;j>=0;j--){
			c=str.charAt(j);
			System.out.print(c);
			rstr.append(c);
		}
		System.out.println();
		
		System.out.println(rstr.toString());
		StringBuffer a = new StringBuffer(str);
		System.out.println(a.reverse());
	}
	
}
