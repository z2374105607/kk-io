package net.kkppyy.ioFactory;

import org.junit.Test;

import net.kkppyy.ioInterface.read.Read;

public class ReadFactoryTest {
	@Test
	public void getReadInstsanceTest(){
		Read read=ReadFactory.getReadInstsance();
		String result=read.readString("����1");
		System.out.println("result:"+result);
	}
}
