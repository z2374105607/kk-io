package net.kkppyy.ioInterface.write;

public interface Write {
	public boolean write(String path, byte[] bytes);
	public boolean write(String path, byte[] bytes,String mate);
}
