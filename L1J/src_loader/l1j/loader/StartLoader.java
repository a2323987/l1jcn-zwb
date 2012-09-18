/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package l1j.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 开始加载器
 */
public class StartLoader {

	private static String JAVA_INI = "./config/java.properties";

	private static String BIN_FILE = "l1jserver.jar";

	private static Process l1jProcess = null;

	private static boolean isShutdown = false;

	public static void main(String[] args) {
		File file;
		// 检查设定档是否存在
		file = new File(JAVA_INI);
		if (!file.exists()) {
			System.err.print("JAVA 启动参数档案不存在！");
			System.err.print("请确认 config/java.properties 是否可正常读取。");
			System.exit(1);
		}

		// 初始化执行命令
		List<String> vars = new ArrayList<String>();
		vars.add("java");

		// 读取参数资料
		try {
			BufferedReader br = new BufferedReader(new FileReader(JAVA_INI));
			String s;

			// 逐行读取参数资料
			while ((s = br.readLine()) != null) {
				if (s.startsWith("-")) {
					vars.add(s);
				}
			}
			br.close();
		}
		catch (Exception e) {
			System.err.print("读取启动参数时发生错误，系统无法启动。");
			System.exit(1);
		}

		// 设置程序终止时的挂钩
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (l1jProcess != null) {
					isShutdown = true;
					try {
						l1jProcess.destroy();
					}
					catch (Exception e) {
					}
				}
			}
		});

		// 指定执行目录
		vars.add("-Duser.dir=" + System.getProperty("user.dir"));

		// 指定主程序
		vars.add("-jar");
		vars.add(BIN_FILE);

		// 加入其他参数
		vars.addAll(Arrays.asList(args));

		// 初始化执行参数
		ProcessBuilder pb = new ProcessBuilder(vars);
		pb.redirectErrorStream(true);

		// 输出提示讯息
		System.out.println("警告！请勿使用右上角的 X 关闭程式。");
		System.out.println("请使用键盘组合键 Ctrl + C 关闭。");
		System.out.println("");

		// 执行程序
		boolean restart = true;
		while (!isShutdown && restart) {
			try {
				// 起始程序
				l1jProcess = pb.start();

				// 撷取荧幕输出的资讯
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(l1jProcess.getInputStream()));
					String echo = null;
					while ((echo = br.readLine()) != null) {
						System.out.println(echo);
					}
				}
				catch (Exception e) {
				} finally {
					br.close();
				}

				// 判断是否重新启动(暂时无作用)
				// restart = (l1jProcess.exitValue() == 6);
			}
			catch (Exception e) {
				System.err.print("发生不明错误，伺服器无法启动。");
				System.exit(1);
			}
		}

		System.exit(0);
	}
}
