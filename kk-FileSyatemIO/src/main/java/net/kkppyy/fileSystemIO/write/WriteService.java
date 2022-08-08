package net.kkppyy.fileSystemIO.write;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.kkppyy.ioInterface.write.Write;

public class WriteService implements Write {
	@Override
	public boolean write(String path, byte[] bytes) {
		try {
			String dir=path.replace(path.split("/")[path.split("/").length-1], "");
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(bytes);
			fos.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean write(String path, byte[] bytes, String mate) {
		// TODO Auto-generated method stub
		return false;
	}
}
