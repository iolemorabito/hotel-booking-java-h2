package exception;
public class OperationException extends Exception {
	public OperationException() {}
	private static final long serialVersionUID = 1L;
	public OperationException(String msg) {
		super(msg);
	}
}