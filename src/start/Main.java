package start;

import java.io.IOException;
import java.util.Scanner;

import reuters.BasicArticle;
import reuters.ReutersComments;

public class Main {

	/**
	 * @param args
	 */
	public static void print(Object a)
	{
		System.out.println(a);
	}
	
	public static int charToInt(char[] t)
	{
		int a =0;
		for(int j=(t.length-1),k=1;j>=0;j--){
			int b = (int)(t[j]-48);
			a = a + (b * k);
			k*=10;
		}
		return a;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(System.in);
		print("Enter a article URL from reuters : ");
		String input = in.nextLine();
		//extracting basic article
		BasicArticle ba = new BasicArticle(input);
		ba.connect();
		ba.printAll();
		
		//for extracting comments
		String[] sp = input.split("-");
		input = sp[sp.length-1];
		ReutersComments R = new ReutersComments(input);
		R.connect();
		R.printAll();
		
//		Test t = new Test();
		
	}

}
