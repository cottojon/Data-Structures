package Exceptions;

public class FileFormatException extends Exception {
	public FileFormatException() {
		super();
	}
	
	public FileFormatException(String s) {
		super(s); //pass a message into the Exception
	}
	
}
