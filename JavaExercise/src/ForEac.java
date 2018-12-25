import java.util.ArrayList;
import java.util.List;


public class ForEac {
	public static void main(String[] args){
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		/*
		int[] ar = {1,2,3,4};
		for(int m:ar){
			System.out.println(m);
		}*/
		int[][] arr = {{1,2},{3,4},{5,6}};
		for(int m:arr[0]){
			System.out.println(m);
		}/*
		for(int l:list){
			System.out.println(l);
		}
		for(int i =0;i<list.size();i++){
			int k = list.get(i);
			
			System.out.println(k);
		}*/
	}
}
