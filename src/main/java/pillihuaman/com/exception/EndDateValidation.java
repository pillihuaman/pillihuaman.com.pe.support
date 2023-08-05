package pillihuaman.com.exception;

public class EndDateValidation extends RuntimeException {

	private static final long serialVersionUID = 2830089284417707015L;
	private transient String fieldName;

	public EndDateValidation(String fieldName, String message) {
		super(String.format("%s", message));
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}
}
