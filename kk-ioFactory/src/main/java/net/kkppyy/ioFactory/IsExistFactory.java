package net.kkppyy.ioFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.kkppyy.ioInterface.isExists.IsExist;
import net.kkppyy.ioInterface.read.Read;
import net.kkppyy.ossIO.config.PropertiesConfig;

public class IsExistFactory {
	private static String saveMode = PropertiesConfig.getValue("save.mode");
	private static IsExist isExist;
	@SuppressWarnings("unchecked")
	public static IsExist getIsExistInstsance() {
		try {
			if (null == isExist) {
				Class<Read> clz = null;
				if (saveMode.equals("oss"))
					clz = (Class<Read>) Class.forName("net.kkppyy.ossIO.isExists.IsExistService");
				else {
					
					clz = (Class<Read>) Class.forName("net.kkppyy.fileSystemIO.read.ReadService");
				}
				Constructor<Read> constructor = clz.getConstructor();
				isExist = (IsExist) constructor.newInstance();
			}
			return isExist;
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
