package exception;
public class DBConnectionException extends Exception {
	public DBConnectionException() {}
	private static final long serialVersionUID = 1L;
	public DBConnectionException(String msg) {
		super(msg);
	}
}
