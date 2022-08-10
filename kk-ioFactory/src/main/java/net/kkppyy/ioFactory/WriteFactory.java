package net.kkppyy.ioFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.kkppyy.ioInterface.write.Write;
import net.kkppyy.ossIO.config.PropertiesConfig;

@SuppressWarnings("unchecked")
public class WriteFactory {
	private static String saveMode = PropertiesConfig.getValue("save.mode");
	private static Write write;
	static {
		try {
			Class<Write> clz = null;
			if (saveMode.equals("oss"))
				clz = (Class<Write>) Class.forName("net.kkppyy.ossIO.write.WriteService");
			else
				clz = (Class<Write>) Class.forName("net.kkppyy.fileSystemIO.write.WriteService");
			Constructor<Write> constructor = clz.getConstructor();
			write = (Write) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException |IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Write getWriteInstsance() {
		try {
			if (null == write) {
				Class<Write> clz = null;
				if (saveMode.equals("oss"))
					clz = (Class<Write>) Class.forName("cn.rebim.ossIO.write.WriteService");
				else
					clz = (Class<Write>) Class.forName("fileSystemIO.write.WriteService");
				Constructor<Write> constructor = clz.getConstructor();
				write = (Write) constructor.newInstance();
			}
			return write;
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException |IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
