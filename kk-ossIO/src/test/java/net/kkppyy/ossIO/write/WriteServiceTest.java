package net.kkppyy.ossIO.write;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;

import net.kkppyy.ossIO.OSSClientFactory;
import net.kkppyy.ossIO.config.PropertiesConfig;
import net.kkppyy.threadPool.ThreadPool;
import net.kkppyy.utils.file.ReadbytesUtil;

public class WriteServiceTest {
	@Test
	public void writeTest(){
		WriteService writeService=new WriteService();
		boolean is=writeService.write("/番禺项目B2类型1.rebim", ReadbytesUtil.readFromByteFile("G:/filemanage/番禺项目B2类型.rebim"));
		System.out.println("文件写入完成"+is);
	}
	@Test
	public void writePerformanceTest(){
		long start=System.currentTimeMillis();
		WriteService writeService=new WriteService();
		for (int i = 0; i < 1000; i++) {
			writeService.write("str/字符串"+i, ("xzcdkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
					+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
					+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
					+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
					+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4k"+i).getBytes());
		}
		long end=System.currentTimeMillis();
		System.out.println("总共耗时："+(end-start)+"毫秒");
	}
	//单例多线程
	@Test
	public void writePerformanceTest4() {
		long start = System.currentTimeMillis();
		List<Future<String>> results = new ArrayList<Future<String>>();
		WriteService writeService = new WriteService();
		for (int i = 0; i < 100000; i++) {
			final int num = i;
			Future<String> result = ThreadPool.mainfixedThreadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					
					writeService.write("str4/字符串a" + num,
							("123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa123saddkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
									+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
									+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
									+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
									+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kdsfa" + num).getBytes());
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
	private static String bucketName = PropertiesConfig.getValue("bucketName");
	private static String endpoint = PropertiesConfig.getValue("endpoint");

	private static String accessKeyId = PropertiesConfig.getValue("accessKeyId");
	private static String accessKeySecret = PropertiesConfig.getValue("accessKeySecret");
	@Test
	public void writePerformanceTest2() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			byte[] bytes = ("dkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
					+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
					+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
					+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
					+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4k" + i).getBytes();
			OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
			InputStream is = new ByteArrayInputStream(bytes);
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, "str21/字符串" + i, is);
			ossClient.shutdown();
		}

		long end = System.currentTimeMillis();
		System.out.println("总共耗时：" + (end - start) + "毫秒");
	}
	@Test
	public void writePerformanceTest3() {
		long start = System.currentTimeMillis();
		List<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 1000; i++) {
			final int num=i;
			Future<String> result = ThreadPool.mainfixedThreadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					byte[] bytes = ("dkfakkjkajksjdfkjaskdjfjsadlkjflkaslkdjflkajsdkjfkajskdjfkajsdlkfjakjdsfkjaksjdlkfjaksjdflkaskdjfkjasdjfkasfaskdjfkjasdkjfirwiutiwuoierjsdkjkjdsewqtrw"
							+ "erytterytrjhuytjuymfghnfhfdzvcvnvjiuyrtrwdsaasdgfsdgwehrkhwekjjbxnbiuuwodkjhv875quweijhc w87uwqidjsdvbutwg5498t2y98qp9uwef98739842587tfuhfkjdskjncjhds"
							+ "asudfiuasiudyfiuysaiudfhiuwerhyyt87ywt8eruhgfuhdkjvhjfdhgoiuoqiauiwfduhashkjfcnsvkjkjfdhgjhiuewryiuqywiuhyiewuhfkajshdfjdsjhvbthyrg43y5893218r32rfuwhqeewrg5er4g98re4bg6s5d1v1ft65h4tr84h65ers4"
							+ "ser64g8e4rg848rg48ew74rg84sd61v32dsf2g4ter748yg7er8gsd4fbg65dfs1f5h4rt874jyt4k564iuylk564iu564po56uy4ilk21y31jrty6f54hdr8th789ert4bg6dsa54ca65ds4dq8we7rew64t56erwt14yer321utyt4i56uyo7u8i7po98ui7khgj897m89hgv 7nvc89b7x9c8v798zcs789sadc56sa4d5qw13ewqrw4ter56y8tr7u89ytui789uyo7l68g4jhk56h4n3cf21bg3xdf56"
							+ "54s6d5f4g6dfh8r4t64u65tyr4u56yt4kqw" + num).getBytes();
					//OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
					//InputStream is = new ByteArrayInputStream(bytes);
					//PutObjectResult putObjectResult = ossClient.putObject(bucketName, "str3/字符串" + num, is);
					//ossClient.shutdown();
					//return putObjectResult.getETag();
					//System.out.print(num+" ");
					//Thread.sleep(10);
					return num+"";
				}
			});
			results.add(result);
		}
		for (Future<String> future : results) {
			try {
				String result = future.get();
				System.out.println(result);
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
	public void writeMateTest(){
		WriteService writeService=new WriteService();
		boolean is=writeService.write("gzipTest1.bin", ReadbytesUtil.readFromByteFile("G:/filemanage/test/四个小桌子/637516448294386333.bin"),"gzip");
		System.out.println("gzipTest"+is);
	}
}
