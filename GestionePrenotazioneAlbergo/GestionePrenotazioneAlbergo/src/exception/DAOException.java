package exception;

public class DAOException extends Exception {
	public DAOException() {}
	private static final long serialVersionUID = 1L;
	public DAOException(String msg) {
		super(msg);
	}
}
