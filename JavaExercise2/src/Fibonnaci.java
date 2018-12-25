
public class Fibonnaci {
	public static void main(String[] args){
		int f0=0,f1=1,temp;
		/*while(f0<21){
			System.out.println(f0);
			temp=f1;
			f1=f0+f1;
			f0=temp;
		}*/
		System.out.println(f0);
		System.out.println(f1);
		for(int i=0;i<10;i++){
			temp = f0+f1;			
			f0=f1;
			f1=temp;
			
			System.out.println(f1);
		}
		
	}
}
