package net.kkppyy.ossIO.read;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.SimplifiedObjectMeta;

import net.kkppyy.ioInterface.read.Read;
import net.kkppyy.ossIO.OSSClientFactory;
import net.kkppyy.ossIO.config.PropertiesConfig;

public class ReadService implements Read {
	
	private static String bucketName = PropertiesConfig.getValue("bucketName");

	@Override
	public byte[] readByte(String fileName) {
		// TODO Auto-generated method stub
		boolean found = OSSClientFactory.getInstance().doesObjectExist(bucketName, fileName);
		if (found) {
			OSSObject ossObject = OSSClientFactory.getInstance().getObject(bucketName, fileName);
			ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			InputStream in = ossObject.getObjectContent();
			try {
				for (int n = 0; n != -1;) {
					n = in.read(buf, 0, buf.length);
					if (n != -1)
						bOut.write(buf, 0, n);
				}
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bOut.toByteArray();
		} else {
			return null;
		}
	}

	@Override
	public String readString(String fileName) {
		boolean found = OSSClientFactory.getInstance().doesObjectExist(bucketName, fileName);
		if (found) {
			OSSObject ossObject = OSSClientFactory.getInstance().getObject(bucketName, fileName);
			InputStream inputStream = ossObject.getObjectContent();
			StringBuilder objectContent = new StringBuilder();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				while (true) {
					String line = reader.readLine();
					if (line == null)
						break;
					objectContent.append(line);
				}
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return objectContent.toString();
		} else {
			return null;
		}
	}

	@Override
	public double readFileSize(String fileName) {
		boolean found = OSSClientFactory.getInstance().doesObjectExist(bucketName, fileName);
		if (found) {
			SimplifiedObjectMeta simplifiedObjectMeta = OSSClientFactory.getInstance().getSimplifiedObjectMeta(bucketName, fileName);
			return simplifiedObjectMeta.getSize();
		}else{
			return 0;
		}
	}

}
