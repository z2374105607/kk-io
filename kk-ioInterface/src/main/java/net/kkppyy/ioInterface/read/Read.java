package net.kkppyy.ioInterface.read;

public interface Read {
	public byte[] readByte(String fileName);

	public String readString(String fileName);

	public double readFileSize(String fileName);
}
