package file;


import java.io.File;
import java.util.Scanner;

public class FileTest {
	
	private static int printLength = 0;
	
	private static void showFoulderSize(String path) {
		File f = new File(path);
		//System.out.println(new File("C:\\Users\\All Users\\Dell\\SARemediation").canWrite());
		File files[] = f.listFiles();
		long total = 0;
		long length = 0;
		for(File file:files) {
			if(file.isDirectory()) {
				System.out.print("drct: "+file.getName()+": ");
				length = getFoulderSize(file);
			}
			else if(file.isFile()) {
				System.out.print("file: "+file.getName()+": ");
				length = file.length();
			}
			//System.err.println(file.getName());
			System.err.println(formatSize(length));
			total += length;
			length = 0;
		}
		System.err.print("total size: "+formatSize(total));
	}
	
	private static void showFoulderSizes(String path) {
		deletePrint(printLength);
		String str = "now caculating size of foulder "+path;
		printLength = str.length();
		System.out.print(str);
		File f = new File(path);
		File files[] = f.listFiles();
		long total = 0;
		long length = 0;
		for(File file:files) {
			if(file.isDirectory()) {
				length = getFoulderSize(file);
				deletePrint(printLength);
				System.out.println("drct: "+file.getName()+": "+formatSize(length));
			}
			else if(file.isFile()) {
				length = file.length();
				System.out.println("file: "+file.getName()+": "+formatSize(length));
			}
			//System.err.println(file.getName());
			total += length;
			length = 0;
		}
		System.err.print("total size: "+formatSize(total));
	}
	
	private static void deletePrint(int length) {
		String str="";
		while(length>0) {
			str+="\b";
		}
		//System.out.print(str);
	}
	
	private static long getFoulderSize(File file) {
		long length = 0;
		File files[] = file.listFiles();
		if(files == null) return 0;
		if(files.length > 0)
			for(File f:files) {
				if(f.isFile()) length += f.length();
				else if(f.isDirectory()) length += getFoulderSize(f);
			}
		return length;
	}
	
	private static String formatSize(long size) {
		if(size > 1024*1024*1024) return String.format("%.2f", (double)size/1024/1024/1024)+" GB";
		else if(size > 1024*1024) return String.format("%.2f", (double)size/1024/1024)+" MB";
		else if(size > 1024) return String.format("%.2f", (double)size/1024)+" KB";
		else return size + " B";
	}
	
	public static void main(String[] args) {
		//showFoulderSize("C:\\Users");
		Scanner s = new Scanner(System.in);
		String path = s.next();
		s.close();
		showFoulderSizes(path);
		//System.out.println(formatSize(1000000000000l));
		//System.out.println(formatSize(new File("F:/ubuntu-16.04.3-desktop-amd64.iso").length()));
	}
}
