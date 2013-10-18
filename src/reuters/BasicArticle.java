package reuters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BasicArticle {
	public static void print(Object a, PrintWriter pw)
	{
		System.out.println(a);
		pw.println(a);
	}
	String ArticleID;
	String ArticleURL;
	String DocTitle;
	String Text;
	String FocusParagraph;
	String ArticleInfo;
	
	public BasicArticle(String input){
		ArticleURL = input;
		DocTitle = "";
		Text = "";
		String temp[] = input.split("-");
		ArticleID = temp[(temp.length-1)];
	}
	public void connect() throws IOException{
		Document doc = Jsoup.connect(ArticleURL).get();
		DocTitle = doc.title();
		FocusParagraph = doc.getElementsByClass("focusParagraph").text();
		Element e = doc.getElementById("articleText");
		ArticleInfo = e.getElementById("articleInfo").text();
		
		
	}
	public void printAll() throws FileNotFoundException {
		// TODO Auto-generated method stub
		String out = "Reuters/Articles/"+ ArticleID + ".txt";
		PrintWriter Pw = new PrintWriter(out);
		print(FocusParagraph, Pw);
		
		Pw.close();
		
	}
}
