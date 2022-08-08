package net.kkppyy.ioFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.kkppyy.ioInterface.read.Read;
import net.kkppyy.ossIO.config.PropertiesConfig;

public class ReadFactory {
	private static String saveMode = PropertiesConfig.getValue("save.mode");
	private static Read read;

	@SuppressWarnings("unchecked")
	public static Read getReadInstsance() {
		try {
			if (null == read) {
				Class<Read> clz = null;
				if (saveMode.equals("oss"))
					clz = (Class<Read>) Class.forName("cn.rebim.ossIO.read.ReadService");
				else
					clz = (Class<Read>) Class.forName("fileSystemIO.read.ReadService");
				Constructor<Read> constructor = clz.getConstructor();
				read = (Read) constructor.newInstance();
			}
			return read;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
