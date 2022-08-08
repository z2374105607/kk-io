package net.kkppyy.ioFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import net.kkppyy.ioInterface.write.Write;
import net.kkppyy.threadPool.ThreadPool;
public class WriteFactoryTest {
	@Test
	public void getWriteInstsanceTest() {
		Write write=WriteFactory.getWriteInstsance();
		System.out.println("write.hashCode():"+write.hashCode());
		boolean is=write.write("����3", "����ɹ�".getBytes());
		System.out.println("�Ƿ��ϴ��ɹ�"+is);
	}
	@Test
	public void writePerformanceTest() {
		long start = System.currentTimeMillis();
		List<Future<String>> results = new ArrayList<Future<String>>();
		Write write = WriteFactory.getWriteInstsance();
		// System.out.println("write.hashCode():"+write.hashCode());
		for (int i = 0; i < 10000; i++) {
			final int num = i;
			Future<String> result = ThreadPool.mainfixedThreadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					boolean is = write.write("factory1/字符串" + num,
							("f123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa" + num).getBytes(),"gzip");
					//System.out.println("�Ƿ��ϴ��ɹ�" + is);
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
		long end = System.currentTimeMillis();
		System.out.println("总共耗时：" + (end - start) + "毫秒");
	}
	@Test
	public void getWriteInstsanceFileTest() {
		Write write=WriteFactory.getWriteInstsance();
		System.out.println("write.hashCode():"+write.hashCode());
		boolean is=write.write("G:/filemanage/test/123.txt", "123".getBytes());
		System.out.println("结果为"+is);
	}
}
