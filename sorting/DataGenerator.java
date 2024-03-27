package sorting;

import java.util.Arrays;

public class DataGenerator {
	public static int [] getRandIntArray(int n) {
		int [] data = new int [n];
		
		for (int i=0; i< n; i++)
			data [i] =  Integer.valueOf((int) (Math.random() * 200));
		
		return data;
	}
	
	public static int [] getRandIntArrayWithRepeats(int n, int m, int r) {
		int [] data = new int [n];
		
		for (int i=0; i< n - m; i++)
			data [i] =  Integer.valueOf((int) (Math.random() * 200));
		
		for (int i=n - m; i< n ; i++)
			data [i] =r;
		
		return data;
	}
	
	public static String arrToString (int [] a) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i< a.length - 1; i++)
			sb.append(a[i] + ", ");
		sb.append(a[a.length -1]);
		return sb.toString();
	}
}
