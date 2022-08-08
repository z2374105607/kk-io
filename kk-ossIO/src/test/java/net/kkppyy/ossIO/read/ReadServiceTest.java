package net.kkppyy.ossIO.read;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;

import net.kkppyy.threadPool.ThreadPool;
import net.kkppyy.utils.file.WriteBytesUtil;

public class ReadServiceTest {
	@Test
	public void readByteTest(){
		ReadService readService=new ReadService();
		byte[] bytes=readService.readByte("1#¥���½ṹģ��.rebim");
		WriteBytesUtil.writeBytesUtil(bytes, "1#¥���½ṹģ��.rebim", "G:/filemanage");
		System.out.println("��ȡ����bytes���ȣ�"+bytes.length);
	}
	@Test
	public void readBytePerformanceTest() {
		ReadService readService = new ReadService();
		long start = System.currentTimeMillis();
		List<byte[]> list = new ArrayList<byte[]>();
		for (int i = 0; i < 100; i++) {
			byte[] bytes = readService.readByte("str4/字符串a" + i);
			list.add(bytes);
		}
		// WriteBytesUtil.writeBytesUtil(bytes, "1#¥���½ṹģ��.rebim",
		// "G:/filemanage");
		// System.out.println("��ȡ����bytes���ȣ�"+bytes.length);
		long end = System.currentTimeMillis();
		System.out.println("总共读到" + list.size() + "条");
		System.out.println("总共耗时：" + (end - start) + "毫秒");
	}
	@Test
	public void readBytePerformance2Test() {
		ReadService readService = new ReadService();
		long start = System.currentTimeMillis();
		List<byte[]> list = new ArrayList<byte[]>();
		List<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 10000; i++) {
			final int num = i;
			Future<String> result = ThreadPool.mainfixedThreadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					byte[] bytes = readService.readByte("str4/字符串a" + num);
					list.add(bytes);
					return "123";
				}
			});
			results.add(result);
		}
		for (Future<String> future : results) {
			try {
				String result = future.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("组装模型线程结果" + Thread.currentThread().getName()
			// + "的子线程执行结果" + result);
			// LOGGER.info("组装模型线程结果" + Thread.currentThread().getName() +
			// "-的子线程执行结果-" + result);
		}
		// WriteBytesUtil.writeBytesUtil(bytes, "1#¥���½ṹģ��.rebim",
		// "G:/filemanage");
		// System.out.println("��ȡ����bytes���ȣ�"+bytes.length);
		long end = System.currentTimeMillis();
		System.out.println("总共读到" + list.size() + "条");
		System.out.println("总共耗时：" + (end - start) + "毫秒");
	}
	@Test
	public void readStringTest(){
		ReadService readService=new ReadService();
		String bytes=readService.readString("my-first-key");
		System.out.println("��ȡ�������ݣ�"+bytes);
	}
}
