package pack.test.lxy;

public class PackageTest
{
	public static void main(String[] args)
	{
		System.out.println(PackageTest.class.getPackage().getName());
		System.out.println(PackageTest.class.getPackage().getName().replaceAll("\\.", "/"));
	}
}
