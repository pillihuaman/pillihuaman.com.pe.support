package pillihuaman.com.exception;

public class DeletedValidation extends RuntimeException {
	private static final long serialVersionUID = -7934381951457541789L;

	public DeletedValidation(String message) {
		super(message);
	}
}
