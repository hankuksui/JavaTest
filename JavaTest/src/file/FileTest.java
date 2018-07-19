package file;

import java.io.File;

public class FileTest {
	public static void showFoulderSize(String path) {
		File f = new File(path);
		//System.out.println(new File("C:\\Users\\All Users\\Dell\\SARemediation").canWrite());
		File files[] = f.listFiles();
		long total = 0;
		long length = 0;
		for(File file:files) {
			if(file.isDirectory()) {
				System.out.print("目录："+file.getName()+": ");
				length = getFoulderSize(file);
			}
			else if(file.isFile()) {
				System.out.print("文件："+file.getName()+": ");
				length = file.length();
			}
			//System.err.println(file.getName());
			System.err.println(formatSize(length));
			total += length;
			length = 0;
		}
		System.err.print("总计大小："+formatSize(total));
	}
	
	public static void showFoulderSizes(String path) {
		File f = new File(path);
		File files[] = f.listFiles();
		long total = 0;
		long length = 0;
		for(File file:files) {
			if(file.isDirectory()) {
				System.out.print("目录："+file.getName()+": ");
				length = getFoulderSize(file);
			}
			else if(file.isFile()) {
				System.out.print("文件："+file.getName()+": ");
				length = file.length();
			}
			//System.err.println(file.getName());
			System.err.println(formatSize(length));
			total += length;
			length = 0;
		}
		System.err.print("总计大小："+formatSize(total));
	}
	
	public static long getFoulderSize(File file) {
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
	
	public static String formatSize(long size) {
		if(size > 1024*1024*1024) return String.format("%.2f", (double)size/1024/1024/1024)+" GB";
		else if(size > 1024*1024) return String.format("%.2f", (double)size/1024/1024)+" MB";
		else if(size > 1024) return String.format("%.2f", (double)size/1024)+" KB";
		else return size + " B";
	}
	
	public static void main2(String[] args) {
		//showFoulderSize("C:\\Users");
		showFoulderSizes("F:\\工作文件\\研发组");
		//System.out.println(formatSize(1000000000000l));
		//System.out.println(formatSize(new File("F:/ubuntu-16.04.3-desktop-amd64.iso").length()));
	}
}
