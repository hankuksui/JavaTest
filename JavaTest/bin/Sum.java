import java.util.Map;
import java.text.DecimalFormat;
public class Sum{
	private final DecimalFormat df = new DecimalFormat("#.#####");
	public Double calculate(Map<String,Double> data){
		double d = (30*data.get("f1") + 20*data.get("f2") + 50*data.get("f3"))/100;
		return Double.valueOf(df.format(d));
	}
	public String getHelloStr(String name){
		return "Hello, "+name+"!";
	}
}
