package base;

public class Base64Test
{
	private static String str = "1,这是一个测试！!@#$%^&*()+_[]:\"|\\{}';,./?><`~'";
	
	public static void main(String[] args)
	{
		String estr = base64Encode(str);
		System.out.println(estr);
		System.out.println(base64Decode(estr));
	}
	
	// 编码
	public static String base64Encode(String token) {
	    byte[] encodedBytes = java.util.Base64.getEncoder().encode(token.getBytes());
	    return new String(encodedBytes,java.nio.charset.Charset.forName("UTF-8"));
	}
	
	// 解码
	public static String base64Decode(String token) {
	    byte[] decodedBytes = java.util.Base64.getDecoder().decode(token.getBytes());
	    return new String(decodedBytes, java.nio.charset.Charset.forName("UTF-8"));
	}
}
