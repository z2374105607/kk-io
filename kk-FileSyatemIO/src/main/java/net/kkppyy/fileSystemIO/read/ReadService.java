package net.kkppyy.fileSystemIO.read;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import net.kkppyy.ioInterface.read.Read;

public class ReadService implements Read{

	@Override
	public byte[] readByte(String fileName) {
		File filename = new File(fileName);
		BufferedInputStream in = null;
		byte[] content=null;
		try {
			in = new BufferedInputStream(new FileInputStream(filename));
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			byte[] temp = new byte[1024];
			int size = 0;
			while ((size = in.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
			in.close();
			content = out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}

	@Override
	public String readString(String fileName) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

	@Override
	public double readFileSize(String fileName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
