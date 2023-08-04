package day_0803.dao;

public class DuplicatedIdException extends Exception {
	public DuplicatedIdException() {
		
	}
	public DuplicatedIdException(String message) {
		super(message);
	}
}
