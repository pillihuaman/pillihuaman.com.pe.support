package pillihuaman.com.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ErrorResponseApiGeneric {

	@JsonProperty("codigoEstado")
	private Integer status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	@JsonProperty("mensaje")
	private String message;
	private List<String> errors;

	private ErrorResponseApiGeneric() {
		timestamp = LocalDateTime.now();
	}

	public ErrorResponseApiGeneric(final Integer status, final String message, final List<String> errors) {
		this();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ErrorResponseApiGeneric(final Integer status, final String message, final String error) {
		this();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public ErrorResponseApiGeneric(HttpStatus httpStatus, String message, String message1) {
	}

	public Integer  getStatus() {
		return status;
	}

	public void setStatus(final Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(final List<String> errors) {
		this.errors = errors;
	}

	public void setError(final String error) {
		errors = Arrays.asList(error);
	}
}
