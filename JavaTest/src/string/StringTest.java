package string;

public class StringTest
{
	public static void main2(String[] args)
	{
		String etlUrl = "'jdbc:oracle:thin:@10.3.60.185:1521:testtouhou'";
		System.out.println(etlUrl.substring(etlUrl.lastIndexOf(":")+1,etlUrl.length()-1));
		String fileName = "etldb_201801011.atr";
		int in_n = fileName.indexOf("_");
		System.out.println(fileName.substring(in_n+1, in_n+9));
	}
}
