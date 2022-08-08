package net.kkppyy.ossIO;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import net.kkppyy.ossIO.config.PropertiesConfig;

public class OSSClientFactory {

	private static String endpoint = PropertiesConfig.getValue("endpoint");

	private static String accessKeyId = PropertiesConfig.getValue("accessKeyId");
	private static String accessKeySecret = PropertiesConfig.getValue("accessKeySecret");
	
	private static OSS ossClient;
	static {
		ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
	}

	private OSSClientFactory() {
	}

	public static OSS getInstance() {
		if (null == ossClient) {
			ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
		}
		return ossClient;
	}
}
