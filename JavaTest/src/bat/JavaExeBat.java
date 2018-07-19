package bat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaExeBat
{

	public static void main2(String[] args)
	{
		String tables = "BAT_SQL_CONF,BAT_TASK_CONF,BAT_EXECUTE_INFO";
		String datadate = "20150112";
		String username = "etl";
		String password = "etl";
		String ip = "10.3.60.185";
		String service_name = "testtouhou";
		String type = "etl";
		String backup_path = "F:\\share";
		exebat(tables, datadate, username, password, ip, service_name, type,
				backup_path);
	}

	public static void exebat(String tables, String datadate, String username,
			String password, String ip, String service_name, String type,
			String backup_path)
	{
		File bakPath = new File(backup_path);
		File dateBakPath = new File(bakPath + File.separator + type + "_" + datadate);
		dateBakPath.mkdirs();
		exeBat("exp userid=" + username + "/" + password + "@" + ip + "/" + service_name + " tables=\"(" + tables + ")\" file=" + dateBakPath + "\\" + type + "db_" + datadate + ".dmp");
		exeBat("exp " + username + "/" + password + "@" + ip + "/" + service_name + " file=" + dateBakPath + "\\" + type + "bjg_" + datadate + ".dmp rows=n compress=n");
		exeBat("tar -cf " + bakPath + File.separator + type + "_" + datadate + ".tar" + " " + bakPath + File.separator + type + "_" + datadate);
		deleteFolder(dateBakPath.getAbsolutePath());
	}

	public static void exeBat(String cmdStr)
	{
		Process p = null;
		try
		{
			System.out.println(cmdStr);
			p = Runtime.getRuntime().exec(cmdStr);
			// 获取进程的标准输入流
			final InputStream is1 = p.getInputStream();
			// 获取进城的错误流
			final InputStream is2 = p.getErrorStream();
			// 启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
			new Thread()
			{
				public void run()
				{
					BufferedReader br1 = null;
					try
					{
						br1 = new BufferedReader(new InputStreamReader(is1, "gb2312"));
						String line1 = null;
						while ((line1 = br1.readLine()) != null)
						{
							if(line1 != null)
							{
								System.out.println(line1);
							}
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					finally
					{
						try
						{
							is1.close();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			}.start();

			new Thread()
			{
				public void run()
				{
					BufferedReader br2 = null;
					try
					{
						br2 = new BufferedReader(new InputStreamReader(is2, "gb2312"));
						String line2 = null;
						while ((line2 = br2.readLine()) != null)
						{
							if(line2 != null)
							{
								System.out.println(line2);
							}
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					finally
					{
						try
						{
							is2.close();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			}.start();

			p.waitFor();
			p.destroy();
			System.out.println("我想被打印...");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	  * 根据路径删除指定的目录，无论存在与否
	 * 
	 * @param sPath
	  * 要删除的目录path
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean deleteFolder(String sPath)
	{
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if(!file.exists())
		{ // 不存在返回 false
			return flag;
		}
		else
		{
			// 判断是否为文件
			if(file.isFile())
			{ // 为文件时调用删除文件方法
				return deleteFile(sPath);
			}
			else
			{ // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	  * 删除单个文件
	 * 
	 * @param sPath
	  * 被删除文件path
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath)
	{
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if(file.isFile() && file.exists())
		{
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath)
	{
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if(!sPath.endsWith(File.separator))
		{
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if(!dirFile.exists() || !dirFile.isDirectory())
		{
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			// 删除子文件
			if(files[i].isFile())
			{
				flag = deleteFile(files[i].getAbsolutePath());
				if(!flag) break;
			} // 删除子目录
			else
			{
				flag = deleteDirectory(files[i].getAbsolutePath());
				if(!flag) break;
			}
		}
		if(!flag) return false;
		// 删除当前目录
		if(dirFile.delete())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}