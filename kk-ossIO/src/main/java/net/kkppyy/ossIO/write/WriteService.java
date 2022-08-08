package net.kkppyy.ossIO.write;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import net.kkppyy.ioInterface.write.Write;
import net.kkppyy.ossIO.OSSClientFactory;
import net.kkppyy.ossIO.config.PropertiesConfig;

public class WriteService implements Write {
	private static String bucketName = PropertiesConfig.getValue("bucketName");

	@Override
	public boolean write(String path, byte[] bytes) {
		// TODO Auto-generated method stub
		try {
			InputStream is = new ByteArrayInputStream(bytes);
			PutObjectResult putObjectResult=OSSClientFactory.getInstance().putObject(bucketName, path, is);
			//System.out.println("putObjectResult ETAG:"+putObjectResult.getETag());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean write(String path, byte[] bytes, String mate) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			// 创建上传文件的元信息，可以通过文件元信息设置HTTP header。
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentEncoding(mate);
			InputStream is = new ByteArrayInputStream(bytes);
			/*String endpoint = PropertiesConfig.getValue("endpoint");

			String accessKeyId = PropertiesConfig.getValue("accessKeyId");
			String accessKeySecret = PropertiesConfig.getValue("accessKeySecret");
			OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
			ossClient.putObject(bucketName, path, is,meta);
			ossClient.shutdown();*/
			PutObjectResult putObjectResult = OSSClientFactory.getInstance().putObject(bucketName, path, is,meta);
			// System.out.println("putObjectResult
			// ETAG:"+putObjectResult.getETag());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}
