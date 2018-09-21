package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 查询内存占用情况
 * @author lxy
 *
 */
public class ProcesserTest {
	public static void main(String[] args) {
		/**
		 * 进程实体类
		 * @author lxy
		 *
		 */
		class Pro{
			/** 进程名称 */
			private String name;
			/** 进程占用内存大小 */
			private int mem;
			/** 进程PID */
			private int pid;

			/**
			 * 创建进程对象
			 * @param name 进程名称
			 * @param pid 进程PID
			 * @param mem 进程占用内存大小
			 */
			public Pro(String name,int pid,int mem) {
				this.name=name;
				this.pid=pid;
				this.mem=mem;
			}
			
			@Override
			public String toString() {
				return "\nname: "+getFixedLenString(name,25,' ')+"; PID: "+getFixedLenString(""+pid, 10,' ')+"; size: "+getFixedLenString(format(mem),20,' ');
			}
			
		}
		Process process = null;
		try {
			/**
			 * tasklist 或 ipconfig 只要在cmd 模式下可运行都可以.
			 */
			process = Runtime.getRuntime().exec("cmd.exe /c tasklist");
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = " ";
			int f = 3;//返回结果中前面无用的行数
			int s = 0;//进程总数
			List<Pro> ap = new ArrayList<>();
			while ((line = input.readLine()) != null) {
				//返回结果中前面几张没有用，不处理
				f--;
				if(f>=0) continue;
				
				if(line.length()>64){
					String name = line.substring(0,25).trim();
					int pid = Integer.parseInt(line.substring(25,35).trim());
					line = line.substring(64).replaceAll(",", "").replace("K", "");//返回的结果中进程占用内存大小中包含逗号，需要去掉
					int mem = Integer.parseInt(line.trim());
					Pro p = new Pro(name,pid,mem);
					ap.add(p);
					s += mem;
				}
			}
			Collections.sort(ap,new Comparator<Pro>() {//对进程列表根据占用内存从大到小排序
				@Override
				public int compare(Pro o1, Pro o2) {
					if(o1.mem>o2.mem) return -1;
					else if(o1.mem == o2.mem) return 0;
					else return 1;
				}
				
			});
			System.out.println("数量："+ap.size());
			System.out.println("总大小："+format(s));
			System.out.println(ap);
			
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 格式化存储字符串
	 * @param size 存储大小，单位为KB
	 * @return
	 */
	public static String format(int size){
		if(size <1000) return size+" KB";
		else if(size <1000000) return String.format("%.2f", Double.parseDouble(""+size)/1000)+" MB";
		else return String.format("%.2f", Double.parseDouble(""+size)/1000000)+" GB";
	}
	
	/**
	 * 获取定长的字符串
	 * @param str 原始字符串
	 * @param len 固定长度
	 * @param c 不够填充的字符
	 * @return 固定长度的字符串
	 */
	public static String getFixedLenString(String str, int len, char c) {
		if (str == null || str.length() == 0) {
			str = "";
		}
		if (str.length() == len) {
			return str;
		}
		if (str.length() > len) {
			return str.substring(0, len);
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() < len) {
			sb.insert(0, c);
		}
		return sb.toString();
	}
}