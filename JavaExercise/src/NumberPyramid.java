
public class NumberPyramid {
	public static void main(String[] args){
		int num=5;
		for(int i=1;i<=num;i++){
			for(int j=num-i;j>0;j--){
				System.out.print(" ");
			}
			for(int k=1;k<=i;k++){
				System.out.print(k);
			}
			for(int l=i-1;l>0;l--){
				System.out.print(l);
			}System.out.println("");
		}
	}
}
