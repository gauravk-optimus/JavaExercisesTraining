public class Armstrong{
public static void main(String[] args){
	int num = 371,h,t,u,ar;
	h=num/100;
	System.out.println(h);
	t=(num/10)-(h*10);
	System.out.println(t);
	u=num-(num/10)*10;
	System.out.println(u);
	ar=h*h*h+t*t*t+u*u*u;
		if(num==ar){
		System.out.println("Armstrong");
		}	
	}
}