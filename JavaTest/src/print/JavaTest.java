package print;

public class JavaTest
{
	public static void main(String[] args)
	{
		try {
			System.out.print("hello");
			Thread.sleep(1000); // Just to give the user a chance to see "hello".
			System.out.print("\b\b\b\b\b");
			System.out.print("world");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
