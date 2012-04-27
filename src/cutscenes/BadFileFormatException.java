package cutscenes;

public class BadFileFormatException extends Exception {
	String myMessage;
	
	public BadFileFormatException(String message) {
		myMessage = message;
	}
	
	public void displayMessage() {
		System.err.println(myMessage);
	}
}
