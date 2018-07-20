import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class JavaTest
{
	public static void main(String[] args)
	{

		System.out.println(JavaTest.class.getPackage());
		
		String path = JavaTest.class.getResource("").toString();
		System.out.println(path.substring(path.indexOf(":")+2));
		
		JavaTest jt = new JavaTest();
		Map<String,Double> data = new HashMap<String,Double>();
		data.put("f1", 10.0);
		data.put("f2", 20.0);
		data.put("f3", 30.0);
		System.out.println(jt.calculate(data));
	}

	private final DecimalFormat df = new DecimalFormat("#.#####");

	public Double calculate(Map<String, Double> data)
	{
		double d = (30 * data.get("f1") + 20 * data.get("f2")
				+ 50 * data.get("f3")) / 100;
		return Double.valueOf(df.format(d));
	}
}
