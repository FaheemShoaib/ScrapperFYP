package reuters;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class ReutersComments  {
	String baseURL;
	String DerivedURL;
	int NoOfComments;
	String DocTitle;
	String[] Comments;
	String FileName;
	
	public static void print(Object a)
	{
		System.out.println(a);
	}
	
	public static void print(Object a, PrintWriter pw)
	{
		System.out.println(a);
		pw.println(a);
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
	
	public ReutersComments(String input){
		baseURL = "http://www.reuters.com/article/comments/";
		DerivedURL = baseURL + input;
		NoOfComments = 0;
		FileName = input;
	}
	public void connect()
	{
		try{
			Document doc = Jsoup.connect(DerivedURL).get();
			DocTitle = doc.title();
			String str = doc.getElementsByClass("commentsHeader").html().toString();
			StringTokenizer st = new StringTokenizer(str);
			print("processing .....\n");
			if(st.countTokens()==2)
			{
				st.nextToken();
				String NoOf = st.nextToken(); 
				String a = NoOf.substring(1, (NoOf.length()-1));
				NoOfComments = charToInt(a.toCharArray());
				Iterator<Element> b = doc.getElementsByClass("commentsBody").iterator();
				
				ArrayList<String> Al = new ArrayList<String>();
				
				while(b.hasNext())
				{
					Al.add(b.next().text());
				}
//				Main.print(Al.size());
				
				Comments = new String[Al.size()];
				
				for(int i=0;i<Al.size();i++)
				{
					Comments[i] = Al.get(i);
				}
				
				print("done\n");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void printAll() throws FileNotFoundException{
		String out = "Reuters/Comments/" + FileName + ".txt";
		PrintWriter Pw = new PrintWriter(out);

		print("\n", Pw);
		print("Document Title : " + DocTitle, Pw);
		print("Total Comments : " + NoOfComments, Pw);
		
		for(int i=0;i<Comments.length;i++)
		{
			print("Comment # " + (i+1),Pw);
			Comments[i] = Comments[i].replace("</p>", "");
			print(Comments[i],Pw);
		}
		Pw.close();
	}
	
}
