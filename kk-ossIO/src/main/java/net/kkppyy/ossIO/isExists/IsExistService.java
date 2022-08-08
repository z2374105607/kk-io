package net.kkppyy.ossIO.isExists;

import net.kkppyy.ioInterface.isExists.IsExist;
import net.kkppyy.ossIO.OSSClientFactory;
import net.kkppyy.ossIO.config.PropertiesConfig;

public class IsExistService implements IsExist {
	
	private static String bucketName = PropertiesConfig.getValue("bucketName");

	@Override
	public boolean isExist(String path) {
		// TODO Auto-generated method stub
		boolean found = OSSClientFactory.getInstance().doesObjectExist(bucketName, path);
		return found;
	}

}
