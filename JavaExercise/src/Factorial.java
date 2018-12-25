
public class Factorial {
	public static void main(String[] args){
		int num=10;
		for(int k = 1;k<=num;k++){		
			double j =1;
			for(int i = k;i>=1;i--){
				j=j*i;
			}System.out.println(j);
		}
	}
}
