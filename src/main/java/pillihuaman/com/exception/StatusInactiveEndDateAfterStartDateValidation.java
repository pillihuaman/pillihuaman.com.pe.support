package pillihuaman.com.exception;

public class StatusInactiveEndDateAfterStartDateValidation extends RuntimeException {
	private static final long serialVersionUID = -7934381951457541789L;

	private transient String fieldName;

	public StatusInactiveEndDateAfterStartDateValidation(String fieldName, String message) {
		super(String.format("%s", message));
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}
}
