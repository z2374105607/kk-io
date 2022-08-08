package net.kkppyy.ossIO.config;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class PropertiesConfig {
	private static Properties prop ;

	public static void init() {
		try {
			prop=new Properties();
			ClassLoader classLoader = PropertiesConfig.class.getClassLoader();// ��ȡ�����ļ�xxxxx.properties
            InputStream in = classLoader.getResourceAsStream("fileManageConfig.properties");
			prop.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		if(null==prop)
			init();
		return prop.getProperty(key);
	}
	public static Properties getProp() {
		if(null==prop)
			init();
		return prop;
	}
}
